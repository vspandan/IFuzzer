JS Delta
==========

JS Delta is a [delta debugger](http://www.st.cs.uni-saarland.de/dd/) for debugging JavaScript-processing tools.  Given a JavaScript program `test.js` that is causing a JS-processing tool to crash or otherwise misbehave, it shrinks `test.js` by deleting statements, functions and sub-expressions, looking for a small sub-program of `test.js` which still causes the problem.  In general, JS Delta can search for a small input satisfying some predicate `P` implemented in JavaScript, allowing for arbitrarily complex tests.

For example, `P` could invoke a static analysis like [WALA](http://wala.sf.net) on its input program and check whether it times out.  If `test.js` is very big, it may be hard to see what is causing the timeout.  JS Delta will find a (sometimes very much) smaller program on which the analysis still times out, making it easier to diagnose the root cause of the scalability problem. Special support for debugging WALA-based analyses with JS Delta is provided by the [WALADelta](http://github.com/wala/WALADelta) utility.

JS Delta can also be used to help debug programs taking JSON as input.  For this use case, make sure the input file ends with extension `.json`.  

Installation
------------
From npm:

```
npm install [-g] jsdelta
```

This places the `jsdelta` script in your `$PATH` if run with `-g`,
otherwise in `node_modules/.bin`.  The script is a symlink to the
`delta.js` source file.

We've tested JS Delta on Linux and Mac OS X.

Usage
-----

JS Delta takes as its input a JavaScript file `f.js` and a predicate `P`. It first copies `f.js` to `<tmp>/delta_js_0.js`, where `<tmp>` is a fresh directory created under the `tmp_dir` specified in `config.js` (`/tmp` by default).

It then evaluates `P` on `<tmp>/delta_js_0.js`. If `P` does not hold for this file, it aborts with an error. Otherwise, it reduces the input file by removing a number of statements or expressions, writing the result to `<tmp>/delta_js_1.js`, and evaluating `P` on this new file. While `P` holds, it keeps reducing the input file in this way until it has found a reduced version `<tmp>/delta_js_n.js` such that `P` holds on it, but not on any further reduced version. At this point, JS Delta stops and copies the smallest reduced version to `<tmp>/delta_js_smallest.js`.

There are several ways for providing a predicate `P`.

At its most general, `P` is an arbitrary Node.js module that exports a function `test`. This function is invoked with the name of the file to test; if the predicate holds, `P` should return `true`, otherwise `false`.

A slightly more convenient (but less general) way of writing a predicate is to implement a Node.js module exporting a string `cmd` and a function `checkResult`. In this case, JS Delta provides a default implementation of the function `test` that does the following:

  1. It invokes `cmd` as a shell command with the file `fn` to test as its only argument.
  2. It captures the standard output and standard error of the command and writes them into files `fn.stdout` and `fn.stderr`.
  3. It invokes function `checkResult` with four arguments: the `error` code returned from executing `cmd` by the `exec` method [in the Node.js standard library](http://nodejs.org/api/child_process.html#child_process_child_process_exec_command_options_callback); a string containing the complete standard output of the command; a string containing the complete standard error of the command; and the time (in milliseconds) it took the command to finish.
  4. The (boolean) return value of `checkResult` is returned as the value of the predicate.

Finally, you can specify the predicate implicitly through command line arguments: invoking JS Delta 

> jsdelta --cmd CMD --errmsg ERR file-to-reduce.js

takes `CMD` to be the command to execute; the predicate is deemed to hold if the command outputs an error message (i.e., on stderr) containing string `ERR`. To check for a message on either stderr or stdout, use the `--msg` option instead.  Note that `CMD` is run with the minimized version of the input file as its only argument. If your command needs other arguments, you may need to write a wrapper script that invokes it with the right arguments.

As a special case, you can run your analysis using the `timeout.sh` script bundled with JS Delta, which will output the error message `TIMEOUT` if the given timeout is exceeded; this can be detected by specifying `--errmsg TIMEOUT`.

Finally, you can just specify a command (without providing the `--errmsg` or `--msg` flags), in which case the predicate is deemed to hold if the command exits with an error.

License
-------

JS Delta is distributed under the Eclipse Public License.  See the LICENSE.txt file in the root directory or <a href="http://www.eclipse.org/legal/epl-v10.html">http://www.eclipse.org/legal/epl-v10.html</a>.
