#!/usr/bin/env node

/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Max Schaefer    - refactoring
 *******************************************************************************/

var fs = require("fs"),
    util = require("util"),
    esprima = require("esprima"),
    escodegen = require("escodegen"),
    estraverse = require("estraverse"),
    cp = require("child_process"),
    config = require(__dirname + "/config.js");

function usage() {
    console.error("Usage: " + process.argv[0] + " " + process.argv[1] +
		  " [-q|--quick] [--no-fixpoint] [--cmd COMMAND]" +
		  " [--record FILE | --replay FILE]" +
		  " [--errmsg ERRMSG] [--msg MSG] FILE [PREDICATE] OPTIONS...");
    process.exit(-1);
}

function execSync(cmd) {
    if (cp.execSync) {
        // node v0.12; use the built-in functionality
        try {
            cp.execSync(cmd);
            return false;
        } catch (e) {
            return true;
        }
    } else {
        // node v0.10; fall back on execSync package
        return require("execSync").run(cmd);
    }
}

function log_debug(msg) {
    //console.log(msg);
}

var /** only knock out entire statements */
    quick = false,

    /** Repeat until a fixpoint is found */
    findFixpoint = true,

    /** command to invoke to determine success/failure */
    cmd = null,

    /** error message indicating failure of command */
    errmsg = null,

    /** message indicating failure of command, either on stdout or stderr */
    msg = null,

    /** file to minimise */
    file = null,

    /** predicate to use for minimisation */
    predicate = {},

    /** arguments to pass to the predicate */
    predicate_args = [],

    /** file to record predicate results to */
    record = null,

    /** array to read predicate results from */
    replay = null, replay_idx = -1;

// command line option parsing; manual for now
// TODO: find good npm package to use
for(var i=2;i<process.argv.length;++i) {
    var arg = process.argv[i];
    if(arg === '--quick' || arg === '-q') {
	quick = true;
    } else if(arg === '--no-fixpoint') {
        findFixpoint = false;
    }else if(arg === '--cmd') {
	if(cmd === null)
	    cmd = String(process.argv[++i]);
	else
	    console.warn("More than one command specified; ignoring.");
    } else if(arg === '--timeout') {
	console.warn("Timeout ignored.");
    } else if(arg === '--errmsg') {
        if(errmsg === null)
            errmsg = String(process.argv[++i]);
        else
            console.warn("More than one error message specified; ignoring.");
    } else if (arg === '--msg') {
        if (msg === null) {
            msg = String(process.argv[++i]);
        } else {
            console.warn("More than one message specified; ignoring.");
        }
    } else if(arg === '--record') {
	record = process.argv[++i];
	if(fs.existsSync(record))
	    fs.unlinkSync(record);
    } else if(arg === '--replay') {
	if(cmd) {
	    console.warn("--replay after --cmd ignored");
	} else {
	    replay = fs.readFileSync(process.argv[++i], 'utf-8').split('\n');
	    replay_idx = 0;
	}
    } else if(arg === '--') {
	file = process.argv[i+1];
	i += 2;
	break;
    } else if(arg[0] === '-') {
	usage();
    } else {
	file = process.argv[i++];
	break;
    }
}

// check that we have something to minimise
if(!file)
    usage();

// check whether a predicate module was specified
if(i < process.argv.length)
    predicate = require(process.argv[i++]);

// the remaining arguments will be passed to the predicate
predicate_args = process.argv.slice(i);

// initialise predicate module
if(typeof predicate.init === 'function')
    predicate.init(predicate_args);

// if no predicate module was specified, synthesise one from the other options
if(!predicate.test) {
    predicate.cmd = predicate.cmd || cmd;

    if(replay) {
	predicate.test = function(fn) {
	    var stats = fs.statSync(fn);
	    console.log("Testing candidate " + fn + 
			" (" + stats.size + " bytes)");
	    var res = replay[replay_idx++] === 'true';
	    if(res)
			console.log("    aborted with relevant error (recorded)");
	    else
			console.log("    completed successfully (recorded)");
	    return res;
	};
    } else {
	if(!predicate.cmd) {
	    console.error("No test command specified.");
	    process.exit(-1);
	}
	
	if(typeof predicate.checkResult !== 'function') {
	    if(errmsg || msg) {
		predicate.checkResult = function(fn, error, stdout, stderr) {
			console.log(stderr)
		    if((errmsg && stderr && stderr.indexOf(errmsg) !== -1) ||
                (msg && ((stderr && stderr.indexOf(msg) !== -1) ||
                    (stdout && stdout.indexOf(msg) !== -1)))) {
				console.log(fn + ":    aborted with relevant error");
				return true;
		    } else if(error==3 ||error==0) {
		    	console.log(stderr)
				fs.unlinkSync(fn);
				fs.unlinkSync(fn + ".stdout");
				fs.unlinkSync(fn + ".stderr");
				round--;
				return false;
		    } else {
				return true;
		    }
		};
	    } else {
		predicate.checkResult = function(fn, error, stdout, stderr) {
			if(error==3 ||error==0) {
				fs.unlinkSync(fn);
				fs.unlinkSync(fn + ".stdout");
				fs.unlinkSync(fn + ".stderr");
				round--;
				return false;
			} else {
				return true;
		    }
		};
	    }
	}
	
	predicate.test = function(fn) {
	    var stats = fs.statSync(fn);
	    /*console.log("Testing candidate " + fn + 
			" (" + stats.size + " bytes)");*/
	    var start = new Date();
	    var stdout_file = fn + ".stdout",
	    stderr_file = fn + ".stderr";
	    var error = execSync(predicate.cmd + " '" + fn + "'" +
				     " >'" + stdout_file + "'" +
				     " 2>'" + stderr_file + "'");
	    var end = new Date();
	    var stdout = fs.readFileSync(stdout_file, "utf-8"),
	    stderr = fs.readFileSync(stderr_file, "utf-8");
	    return predicate.checkResult(fn, error, stdout, stderr, end - start);
	};
    }
}

// figure out file extension; default is 'js'
var ext = (file.match(/\.(\w+)$/) || [, 'js'])[1];

var src = fs.readFileSync(file, 'utf-8');

// hack to make JSON work
if(ext === 'json')
    src = '(' + src + ')';

// parse given file
var ast = esprima.parse(src);

// determine a suitable temporary directory
var tmp_dir=config.tmp_dir;
var round = 0;
if (!fs.existsSync(config.tmp_dir))
	fs.mkdirSync(config.tmp_dir);
//for(i=0; fs.existsSync(tmp_dir=config.tmp_dir+"/tmp"+i); ++i);
for(round=0; true; round++)
{
	if(!fs.existsSync(tmp_dir + "/" + round + "." + ext))
		break;
    //fs.mkdirSync(tmp_dir);
}

// keep track of the number of attempts so far
//var round = 0;

// the smallest test case so far is kept here
var smallest = tmp_dir + "/smallest." + ext;
//var smallest = tmp_dir + "/delta_js_smallest." + ext;

// get name of current test case
function getTempFileName() {
    //var fn = tmp_dir + "/delta_js_" + round + "." + ext;
    var fn = tmp_dir + "/" + round + "." + ext;
    ++round;
    return fn;
}

function minimise_array(array, nonempty) {
    log_debug("minimising array " + util.inspect(array, false, 1));
    if(!nonempty && array.length === 1) {
	// special case: if there is only one element, try removing it
	var elt = array[0];
	array.length = 0;
	if(!test())
	    // didn't work, need to put it back
	    array[0] = elt;
    } else {
	// try removing as many chunks of size sz from array as possible
	// once we're done, switch to size sz/2; if size drops to zero,
	// recursively invoke minimise on the individual elements
	// of the array
	for(var sz=array.length>>>1;sz>0;sz>>>=1) {
	    log_debug("  chunk size " + sz);
	    var nchunks = Math.floor(array.length/sz);
	    for(var i=nchunks-1;i>=0;--i) {
		// try removing chunk i
		log_debug("    chunk #" + i);
		var lo = i*sz,
		    hi = i===nchunks-1 ? array.length : (i+1)*sz;

		// avoid creating empty array if nonempty is set
		if(!nonempty || lo > 0 || hi < array.length) {
		    var removed = array.splice(lo, hi-lo);
		    if(!test()) {
			// didn't work, need to put it back
			Array.prototype.splice.apply(array, 
						     [lo,0].concat(removed));
		    }
		}
	    }
	}
    }

    // now minimise each element in turn
    for(var i=0;i<array.length;++i)
	minimise(array[i], array, i);
}

// the main minimisation function
function minimise(nd, parent, idx) {
    if(typeof parent === 'string') {
	idx = parent;
	parent = nd;
	nd = parent[idx];
    }

    if(!nd || typeof nd !== 'object')
	return;

    log_debug("minimising " + util.inspect(nd));
    switch(nd.type) {
    case 'Program':
	minimise_array(nd.body);
	break;
    case 'BlockStatement':
	// knock out as many statements in the block as possible
	// if we end up with a single statement, replace the block with
	// that statement
	minimise_array(nd.body);
	if(!quick && nd.body.length === 1)
	    Replace(parent, idx).With(nd.body[0]);
	break;
    case 'FunctionDeclaration':
    case 'FunctionExpression':
	if(!quick) {
	    if(nd.type === 'FunctionExpression')
		Replace(nd, 'name').With(null);
	    minimise_array(nd.params);
	}
	minimise_array(nd.body.body);
	break;
    case 'ObjectExpression':
	minimise_array(nd.properties);
	break;
    case 'VariableDeclaration':
	minimise_array(nd.declarations, true);
	break;
    default:
	// match other node types only if we're not doing quick minimisation
	// if quick is set, !quick && ndtp will be undefined, so the
	// default branch is taken
	switch(!quick && nd.type) {
	case 'Literal':
	    return;
	case 'UnaryExpression':
	case 'UpdateExpression':
	    // try replacing with operand
	    if(Replace(parent, idx).With(nd.argument))
		minimise(parent, idx);
	    else
		minimise(nd, 'argument');
	    break;
	case 'AssignmentExpression':
	case 'BinaryExpression':
	case 'LogicalExpression':
	    if(Replace(parent, idx).With(nd.left))
		minimise(parent, idx);
	    else if(Replace(parent, idx).With(nd.right))
		minimise(parent, idx);
	    else {
		minimise(nd, 'left');
		minimise(nd, 'right');
	    }
	    break;
	case 'ReturnStatement':
	    if(nd.argument && !Replace(nd, 'argument').With(null))
		minimise(nd, 'argument');
	    break;
	case 'CallExpression':
	case 'NewExpression':
	        minimise(nd, 'callee');
	        minimise_array(nd['arguments']);
            break;
	case 'ArrayExpression':
	    minimise_array(nd.elements, nd, 'elements');
	    break;
	case 'IfStatement':
	case 'ConditionalExpression':
	    if(Replace(parent, idx).With(nd.consequent))
		minimise(parent, idx);
	    else if(nd.alternate && Replace(parent, idx).With(nd.alternate))
		minimise(parent, idx);
            else if(Replace(parent, idx).With(nd.test))
		minimise(parent, idx);
	    else {
		minimise(nd, 'test');
		minimise(nd, 'consequent');
		minimise(nd, 'alternate');
	    }
	    break;
	case 'SwitchStatement':
	    minimise(nd, 'discriminant');
	    minimise_array(nd.cases);
	    break;
        case 'WhileStatement':
            if(Replace(parent, idx).With(nd.body))
		minimise(parent, idx);
            else if(Replace(parent, idx).With(nd.test))
		minimise(parent, idx);
            else {
	        minimise(nd, 'test');
	        minimise(nd, 'body');
            }
            break;
	case 'ForStatement':
	    Replace(nd, 'test').With(null);
	    Replace(nd, 'update').With(null);
            if(Replace(parent, idx).With(nd.body))
		minimise(parent, idx);
            else if(nd.test && Replace(parent, idx).With(nd.test))
		minimise(parent, idx);
            else {
	        minimise(nd, 'init');
	        minimise(nd, 'test');
	        minimise(nd, 'update');
	        minimise(nd, 'body');
            }
	    break;
	default:
	    if(Array.isArray(nd)) {
		minimise_array(nd);
	    } else {
		estraverse.VisitorKeys[nd.type].forEach(function(ch) {
            if (!quick || ch !== 'arguments') {
                minimise(nd, ch);
            }
		});
	    }
	}
    }
}

function pp(ast) {
    // we pass the 'parse' option here to avoid converting 0.0 to 0, etc.
    return escodegen.generate(ast, {
	format: {
	    json: ext === 'json'
	},
        parse: esprima.parse
    });
}

// write the current test case out to disk
function writeTempFile() {
    var fn = getTempFileName();
    fs.writeFileSync(fn, pp(ast));
    return fn;
}

var  testSucceededAtLeastOnce = false;
// test the current test case
function test() {
    var fn = writeTempFile();
    var res = predicate.test(fn);
    if(record)
	fs.appendFileSync(record, !!res + "\n");
    if(res) {
        testSucceededAtLeastOnce = true;
	// if the test succeeded, save it to file 'smallest'
	//fs.writeFileSync(smallest, pp(ast));
	return true;
    } else {
	return false;
    }
}

// save a copy of the original input
var orig = getTempFileName(),
    input = fs.readFileSync(file, 'utf-8');
fs.writeFileSync(orig, input);
//fs.writeFileSync(smallest, input);

// get started
var res = predicate.test(orig);
if(record)
    fs.appendFileSync(record, !!res + "\n");
if(res) {
    if(findFixpoint){
        var iterations = 0;
        do{
            testSucceededAtLeastOnce = false;
            //console.log("Starting fixpoint iteration #%d", ++iterations);
            minimise(ast, null, -1);
        } while(testSucceededAtLeastOnce)
    }else{
        minimise(ast, null, -1);
    }
    /*var stats = fs.statSync(smallest);
    if(stats.size < 2000){
        // small enough to display
        console.log();
        console.log(fs.readFileSync(smallest, 'utf8'));
        console.log();
    }
    console.log("Minimisation finished; final version is in %s (%d bytes)", smallest, stats.size);*/
    process.exit(0);
} else {
    console.error("Original file doesn't satisfy predicate.");
    process.exit(-1);
}

function Replace(nd, idx) {
    var oldval = nd[idx];
    return {
	With: function(newval) {
	    if(oldval === newval) {
		return true;
	    } else {
		nd[idx] = newval;
		if(test()) {
		    return true;
		} else {
		    nd[idx] = oldval;
		    return false;
		}
	    }
	}
    };
}
