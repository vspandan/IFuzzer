
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
 * Test402 tests all pass unless they throw, and there are no @negative tests.
 * Once Test262 includes @negative support, and this call in test262-shell.js is
 * removed, this'll need to be uncommented.
 */
//testPassesUnlessItThrows();

/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*- */
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */


var TZ_DIFF = getTimeZoneDiff();

/*
 * Originally, the test suite used a hard-coded value TZ_DIFF = -8.
 * But that was only valid for testers in the Pacific Standard Time Zone!
 * We calculate the proper number dynamically for any tester. We just
 * have to be careful to use a date not subject to Daylight Savings Time...
 */
function getTimeZoneDiff()
{
  return -((new Date(2000, 1, 1)).getTimezoneOffset())/60;
}

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
 * The current crop of Test262 test cases that we run are expected to pass
 * unless they crash or throw.  (This isn't true for all Test262 test cases --
 * for the ones marked @negative the logic is inverted.  We'll have to deal with
 * that concern eventually, but for now we're punting so we can run subsets of
 * Test262 tests.)
 */

/*
 * Test262 function $ERROR throws an error with the message provided. Test262
 * test cases call it to indicate failure.
 */
function $ERROR(msg)
{
  throw new Error("Test262 error: " + msg);
}

/*
 * Test262 function $INCLUDE loads a file with support functions for the tests.
 * This function is replaced in browser.js.
 */
function $INCLUDE(file)
{
  load("supporting/" + file);
}

/*
 * Test262 function fnGlobalObject returns the global object.
 */
var fnGlobalObject = (function()
{
  var global = Function("return this")();
  return function fnGlobalObject() { return global; };
})();

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
 * Test402 tests all pass unless they throw, and there are no @negative tests.
 * Once Test262 includes @negative support, and this call in test262-shell.js is
 * removed, this'll need to be uncommented.
 */
//testPassesUnlessItThrows();

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */


if (typeof assertThrowsInstanceOf === 'undefined') {
    var assertThrowsInstanceOf = function assertThrowsInstanceOf(f, ctor, msg) {
        var fullmsg;
        try {
            f();
        } catch (exc) {
            if (exc instanceof ctor)
                return;
            fullmsg = "Assertion failed: expected exception " + ctor.name + ", got " + exc;
        }
        if (fullmsg === undefined)
            fullmsg = "Assertion failed: expected exception " + ctor.name + ", no exception thrown";
        if (msg !== undefined)
            fullmsg += " - " + msg;
        throw new Error(fullmsg);
    };
}

if (typeof assertThrowsValue === 'undefined') {
    var assertThrowsValue = function assertThrowsValue(f, val, msg) {
        var fullmsg;
        try {
            f();
        } catch (exc) {
            if ((exc === val) === (val === val) && (val !== 0 || 1 / exc === 1 / val))
                return;
            fullmsg = "Assertion failed: expected exception " + val + ", got " + exc;
        }
        if (fullmsg === undefined)
            fullmsg = "Assertion failed: expected exception " + val + ", no exception thrown";
        if (msg !== undefined)
            fullmsg += " - " + msg;
        throw new Error(fullmsg);
    };
}

if (typeof assertDeepEq === 'undefined') {
    var assertDeepEq = (function(){
        var call = Function.prototype.call,
            Array_isArray = Array.isArray,
            Map_ = Map,
            Error_ = Error,
            Map_has = call.bind(Map.prototype.has),
            Map_get = call.bind(Map.prototype.get),
            Map_set = call.bind(Map.prototype.set),
            Object_toString = call.bind(Object.prototype.toString),
            Function_toString = call.bind(Function.prototype.toString),
            Object_getPrototypeOf = Object.getPrototypeOf,
            Object_hasOwnProperty = call.bind(Object.prototype.hasOwnProperty),
            Object_getOwnPropertyDescriptor = Object.getOwnPropertyDescriptor,
            Object_isExtensible = Object.isExtensible,
            Object_getOwnPropertyNames = Object.getOwnPropertyNames,
            uneval_ = uneval            

        // Return true iff ES6 Type(v) isn't Object.
        // Note that `typeof document.all === "undefined"`.
        function isPrimitive(v) {
            return (v === null ||
                    v === undefined ||
                    typeof v === "boolean" ||
                    typeof v === "number" ||
                    typeof v === "string" ||
                    typeof v === "symbol");
        }

        function assertSameValue(a, b, msg) {
            try {
                assertEq(a, b);
            } catch (exc) {
                throw Error_(exc.message + (msg ? " " + msg : ""));
            }
        }

        function assertSameClass(a, b, msg) {
            var ac = Object_toString(a), bc = Object_toString(b);
            assertSameValue(ac, bc, msg);
            switch (ac) {
            case "[object Function]":
                assertSameValue(Function_toString(a), Function_toString(b), msg);
            }
        }

        function at(prevmsg, segment) {
            return prevmsg ? prevmsg + segment : "at _" + segment;
        }

        // Assert that the arguments a and b are thoroughly structurally equivalent.
        //
        // For the sake of speed, we cut a corner:
        //        var x = {}, y = {}, ax = [x];
        //        assertDeepEq([ax, x], [ax, y]);  // passes (?!)
        //
        // Technically this should fail, since the two object graphs are different.
        // (The graph of [ax, y] contains one more object than the graph of [ax, x].)
        //
        // To get technically correct behavior, pass {strictEquivalence: true}.
        // This is slower because we have to walk the entire graph, and Object.prototype
        // is big.
        //
        return function assertDeepEq(a, b, options) {
            var strictEquivalence = options ? options.strictEquivalence : false;

            function assertSameProto(a, b, msg) {
                check(Object_getPrototypeOf(a), Object_getPrototypeOf(b), at(msg, ".__proto__"));
            }

            function failPropList(na, nb, msg) {
                throw Error_("got own properties " + uneval_(na) + ", expected " + uneval_(nb) +
                             (msg ? " " + msg : ""));
            }

            function assertSameProps(a, b, msg) {
                var na = Object_getOwnPropertyNames(a),
                    nb = Object_getOwnPropertyNames(b);
                if (na.length !== nb.length)
                    failPropList(na, nb, msg);

                // Ignore differences in whether Array elements are stored densely.
                if (Array_isArray(a)) {
                    na.sort();
                    nb.sort();
                }

                for (var i = 0; i < na.length; i++) {
                    var name = na[i];
                    if (name !== nb[i])
                        failPropList(na, nb, msg);
                    var da = Object_getOwnPropertyDescriptor(a, name),
                        db = Object_getOwnPropertyDescriptor(b, name);
                    var pmsg = at(msg, /^[_$A-Za-z0-9]+$/.test(name)
                                       ? /0|[1-9][0-9]*/.test(name) ? "[" + name + "]" : "." + name
                                       : "[" + uneval_(name) + "]");
                    assertSameValue(da.configurable, db.configurable, at(pmsg, ".[[Configurable]]"));
                    assertSameValue(da.enumerable, db.enumerable, at(pmsg, ".[[Enumerable]]"));
                    if (Object_hasOwnProperty(da, "value")) {
                        if (!Object_hasOwnProperty(db, "value"))
                            throw Error_("got data property, expected accessor property" + pmsg);
                        check(da.value, db.value, pmsg);
                    } else {
                        if (Object_hasOwnProperty(db, "value"))
                            throw Error_("got accessor property, expected data property" + pmsg);
                        check(da.get, db.get, at(pmsg, ".[[Get]]"));
                        check(da.set, db.set, at(pmsg, ".[[Set]]"));
                    }
                }
            };

            var ab = new Map_();
            var bpath = new Map_();

            function check(a, b, path) {
                if (typeof a === "symbol") {
                    // Symbols are primitives, but they have identity.
                    // Symbol("x") !== Symbol("x") but
                    // assertDeepEq(Symbol("x"), Symbol("x")) should pass.
                    if (typeof b !== "symbol") {
                        throw Error_("got " + uneval_(a) + ", expected " + uneval_(b) + " " + path);
                    } else if (uneval_(a) !== uneval_(b)) {
                        // We lamely use uneval_ to distinguish well-known symbols
                        // from user-created symbols. The standard doesn't offer
                        // a convenient way to do it.
                        throw Error_("got " + uneval_(a) + ", expected " + uneval_(b) + " " + path);
                    } else if (Map_has(ab, a)) {
                        assertSameValue(Map_get(ab, a), b, path);
                    } else if (Map_has(bpath, b)) {
                        var bPrevPath = Map_get(bpath, b) || "_";
                        throw Error_("got distinct symbols " + at(path, "") + " and " +
                                     at(bPrevPath, "") + ", expected the same symbol both places");
                    } else {
                        Map_set(ab, a, b);
                        Map_set(bpath, b, path);
                    }
                } else if (isPrimitive(a)) {
                    assertSameValue(a, b, path);
                } else if (isPrimitive(b)) {
                    throw Error_("got " + Object_toString(a) + ", expected " + uneval_(b) + " " + path);
                } else if (Map_has(ab, a)) {
                    assertSameValue(Map_get(ab, a), b, path);
                } else if (Map_has(bpath, b)) {
                    var bPrevPath = Map_get(bpath, b) || "_";
                    throw Error_("got distinct objects " + at(path, "") + " and " + at(bPrevPath, "") +
                                 ", expected the same object both places");
                } else {
                    Map_set(ab, a, b);
                    Map_set(bpath, b, path);
                    if (a !== b || strictEquivalence) {
                        assertSameClass(a, b, path);
                        assertSameProto(a, b, path);
                        assertSameProps(a, b, path);
                        assertSameValue(Object_isExtensible(a),
                                        Object_isExtensible(b),
                                        at(path, ".[[Extensible]]"));
                    }
                }
            }

            check(a, b, "");
        };
    })();
}

// Checks that |a_orig| and |b_orig| are:
//   1. Both instances of |type|, and
//   2. Are structurally equivalent (as dictated by the structure of |type|).
function assertTypedEqual(type, a_orig, b_orig) {
  try {
    recur(type, a_orig, b_orig);
  } catch (e) {
    print("failure during "+
          "assertTypedEqual("+type.toSource()+", "+a_orig.toSource()+", "+b_orig.toSource()+")");
    throw e;
  }

  function recur(type, a, b) {
    if (type instanceof ArrayType) {
      assertEq(a.length, type.length);
      assertEq(b.length, type.length);
      for (var i = 0; i < type.length; i++)
        recur(type.elementType, a[i], b[i]);
      } else if (type instanceof StructType) {
        var fieldNames = Object.getOwnPropertyNames(type.fieldTypes);
        for (var i = 0; i < fieldNames.length; i++) {
          var fieldName = fieldNames[i];
          recur(type.fieldTypes[fieldName], a[fieldName], b[fieldName]);
        }
      } else {
        assertEq(a, b);
      }
  }
}

/* -*- indent-tabs-mode: nil; js-indent-level: 4 -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// NOTE: This only turns on 1.6 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js.
if (typeof version != 'undefined')
{
  version(160);
}


/* -*- indent-tabs-mode: nil; js-indent-level: 4 -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// NOTE: This only turns on 1.8.0 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js.
if (typeof version != 'undefined')
{
  version(180);
}


/* -*- indent-tabs-mode: nil; js-indent-level: 4 -*- */
/*
 * Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/licenses/publicdomain/
 */

// Note, copied from elsewhere
if (typeof assertThrowsInstanceOf === 'undefined') {
    var assertThrowsInstanceOf = function assertThrowsInstanceOf(f, ctor, msg) {
        var fullmsg;
        try {
            f();
        } catch (exc) {
            if (exc instanceof ctor)
                return;
            fullmsg = "Assertion failed: expected exception " + ctor.name + ", got " + exc;
        }
        if (fullmsg === undefined)
            fullmsg = "Assertion failed: expected exception " + ctor.name + ", no exception thrown";
        if (msg !== undefined)
            fullmsg += " - " + msg;
        throw new Error(fullmsg);
    };
}

// NOTE: This only turns on 1.8.5 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js (which only turns on 1.8, the most
//       the browser supports).
if (typeof version != 'undefined')
{
  version(185);
}


// various combinations of identifiers and destructuring patterns:
function makePatternCombinations(id, destr)
{
    return [
      [ id(1)                                            ],
      [ id(1),    id(2)                                  ],
      [ id(1),    id(2),    id(3)                        ],
      [ id(1),    id(2),    id(3),    id(4)              ],
      [ id(1),    id(2),    id(3),    id(4),    id(5)    ],

      [ destr(1)                                         ],
      [ destr(1), destr(2)                               ],
      [ destr(1), destr(2), destr(3)                     ],
      [ destr(1), destr(2), destr(3), destr(4)           ],
      [ destr(1), destr(2), destr(3), destr(4), destr(5) ],

      [ destr(1), id(2)                                  ],

      [ destr(1), id(2),    id(3)                        ],
      [ destr(1), id(2),    id(3),    id(4)              ],
      [ destr(1), id(2),    id(3),    id(4),    id(5)    ],
      [ destr(1), id(2),    id(3),    id(4),    destr(5) ],
      [ destr(1), id(2),    id(3),    destr(4)           ],
      [ destr(1), id(2),    id(3),    destr(4), id(5)    ],
      [ destr(1), id(2),    id(3),    destr(4), destr(5) ],

      [ destr(1), id(2),    destr(3)                     ],
      [ destr(1), id(2),    destr(3), id(4)              ],
      [ destr(1), id(2),    destr(3), id(4),    id(5)    ],
      [ destr(1), id(2),    destr(3), id(4),    destr(5) ],
      [ destr(1), id(2),    destr(3), destr(4)           ],
      [ destr(1), id(2),    destr(3), destr(4), id(5)    ],
      [ destr(1), id(2),    destr(3), destr(4), destr(5) ],

      [ id(1),    destr(2)                               ],

      [ id(1),    destr(2), id(3)                        ],
      [ id(1),    destr(2), id(3),    id(4)              ],
      [ id(1),    destr(2), id(3),    id(4),    id(5)    ],
      [ id(1),    destr(2), id(3),    id(4),    destr(5) ],
      [ id(1),    destr(2), id(3),    destr(4)           ],
      [ id(1),    destr(2), id(3),    destr(4), id(5)    ],
      [ id(1),    destr(2), id(3),    destr(4), destr(5) ],

      [ id(1),    destr(2), destr(3)                     ],
      [ id(1),    destr(2), destr(3), id(4)              ],
      [ id(1),    destr(2), destr(3), id(4),    id(5)    ],
      [ id(1),    destr(2), destr(3), id(4),    destr(5) ],
      [ id(1),    destr(2), destr(3), destr(4)           ],
      [ id(1),    destr(2), destr(3), destr(4), id(5)    ],
      [ id(1),    destr(2), destr(3), destr(4), destr(5) ]
    ];
}

function runtest(main) {
    try {
        main();
        if (typeof reportCompare === 'function')
            reportCompare(true, true);
    } catch (exc) {
        print(exc.stack);
        throw exc;
    }
}

/* -*- indent-tabs-mode: nil; js-indent-level: 4 -*- */
/*
 * Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/licenses/publicdomain/
 */


// The Worker constructor can take a relative URL, but different test runners
// run in different enough environments that it doesn't all just automatically
// work. For the shell, we use just a filename; for the browser, see browser.js.
var workerDir = '';

// explicitly turn on js185
// XXX: The browser currently only supports up to version 1.8
if (typeof version != 'undefined')
{
  version(185);
}


var BUGNUMBER;
var summary;

function runDSTOffsetCachingTestsFraction(part, parts)
{
  BUGNUMBER = 563938;
  summary = 'Cache DST offsets to improve SunSpider score';

  print(BUGNUMBER + ": " + summary);

  var MAX_UNIX_TIMET = 2145859200;
  var RANGE_EXPANSION_AMOUNT = 30 * 24 * 60 * 60;

  /**
   * Computes the time zone offset in minutes at the given timestamp.
   */
  function tzOffsetFromUnixTimestamp(timestamp)
  {
    var d = new Date(NaN);
    d.setTime(timestamp); // local slot = NaN, UTC slot = timestamp
    return d.getTimezoneOffset(); // get UTC, calculate local => diff in minutes
  }

  /**
   * Clear the DST offset cache, leaving it initialized to include a timestamp
   * completely unlike the provided one (i.e. one very, very far away in time
   * from it).  Thus an immediately following lookup for the provided timestamp
   * will cache-miss and compute a clean value.
   */
  function clearDSTOffsetCache(undesiredTimestamp)
  {
    var opposite = (undesiredTimestamp + MAX_UNIX_TIMET / 2) % MAX_UNIX_TIMET;

    // Generic purge to known, but not necessarily desired, state
    tzOffsetFromUnixTimestamp(0);
    tzOffsetFromUnixTimestamp(MAX_UNIX_TIMET);

    // Purge to desired state.  Cycle 2x in case opposite or undesiredTimestamp
    // is close to 0 or MAX_UNIX_TIMET.
    tzOffsetFromUnixTimestamp(opposite);
    tzOffsetFromUnixTimestamp(undesiredTimestamp);
    tzOffsetFromUnixTimestamp(opposite);
    tzOffsetFromUnixTimestamp(undesiredTimestamp);
  }

  function computeCanonicalTZOffset(timestamp)
  {
    clearDSTOffsetCache(timestamp);
    return tzOffsetFromUnixTimestamp(timestamp);
  }

  var TEST_TIMESTAMPS_SECONDS =
    [
     // Special-ish timestamps
     0,
     RANGE_EXPANSION_AMOUNT,
     MAX_UNIX_TIMET,
    ];

  var ONE_DAY = 24 * 60 * 60;
  var EIGHTY_THREE_HOURS = 83 * 60 * 60;
  var NINETY_EIGHT_HOURS = 98 * 60 * 60;
  function nextIncrement(i)
  {
    return i === EIGHTY_THREE_HOURS ? NINETY_EIGHT_HOURS : EIGHTY_THREE_HOURS;
  }

  // Now add a long sequence of non-special timestamps, from a fixed range, that
  // overlaps a DST change by "a bit" on each side.  67 days should be enough
  // displacement that we can occasionally exercise the implementation's
  // thirty-day expansion and the DST-offset-change logic.  Use two different
  // increments just to be safe and catch something a single increment might not.
  var DST_CHANGE_DATE = 1268553600; // March 14, 2010
  for (var t = DST_CHANGE_DATE - 67 * ONE_DAY,
           i = nextIncrement(NINETY_EIGHT_HOURS),
           end = DST_CHANGE_DATE + 67 * ONE_DAY;
       t < end;
       i = nextIncrement(i), t += i)
  {
    TEST_TIMESTAMPS_SECONDS.push(t);
  }

  var TEST_TIMESTAMPS =
    TEST_TIMESTAMPS_SECONDS.map(function(v) { return v * 1000; });

  /**************
   * BEGIN TEST *
   **************/

  // Compute the correct time zone offsets for all timestamps to be tested.
  var CORRECT_TZOFFSETS = TEST_TIMESTAMPS.map(computeCanonicalTZOffset);

  // Intentionally and knowingly invoking every single logic path in the cache
  // isn't easy for a human to get right (and know he's gotten it right), so
  // let's do it the easy way: exhaustively try all possible four-date sequences
  // selecting from our array of possible timestamps.

  var sz = TEST_TIMESTAMPS.length;
  var start = Math.floor((part - 1) / parts * sz);
  var end = Math.floor(part / parts * sz);

  print("Exhaustively testing timestamps " +
        "[" + start + ", " + end + ") of " + sz + "...");

  try
  {
    for (var i = start; i < end; i++)
    {
      print("Testing timestamp " + i + "...");

      var t1 = TEST_TIMESTAMPS[i];
      for (var j = 0; j < sz; j++)
      {
        var t2 = TEST_TIMESTAMPS[j];
        for (var k = 0; k < sz; k++)
        {
          var t3 = TEST_TIMESTAMPS[k];
          for (var w = 0; w < sz; w++)
          {
            var t4 = TEST_TIMESTAMPS[w];

            clearDSTOffsetCache(t1);

            var tzo1 = tzOffsetFromUnixTimestamp(t1);
            var tzo2 = tzOffsetFromUnixTimestamp(t2);
            var tzo3 = tzOffsetFromUnixTimestamp(t3);
            var tzo4 = tzOffsetFromUnixTimestamp(t4);

            assertEq(tzo1, CORRECT_TZOFFSETS[i]);
            assertEq(tzo2, CORRECT_TZOFFSETS[j]);
            assertEq(tzo3, CORRECT_TZOFFSETS[k]);
            assertEq(tzo4, CORRECT_TZOFFSETS[w]);
          }
        }
      }
    }
  }
  catch (e)
  {
    assertEq(true, false,
             "Error when testing with timestamps " +
             i + ", " + j + ", " + k + ", " + w +
             " (" + t1 + ", " + t2 + ", " + t3 + ", " + t4 + ")!");
  }

  reportCompare(true, true);
  print("All tests passed!");
}

/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*- */
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
 * Date: 07 February 2001
 *
 * Functionality common to Array testing -
 */
//-----------------------------------------------------------------------------


var CHAR_LBRACKET = '[';
var CHAR_RBRACKET = ']';
var CHAR_QT_DBL = '"';
var CHAR_QT = "'";
var CHAR_NL = '\n';
var CHAR_COMMA = ',';
var CHAR_SPACE = ' ';
var TYPE_STRING = typeof 'abc';


/*
 * If available, arr.toSource() gives more detail than arr.toString()
 *
 * var arr = Array(1,2,'3');
 *
 * arr.toSource()
 * [1, 2, "3"]
 *
 * arr.toString()
 * 1,2,3
 *
 * But toSource() doesn't exist in Rhino, so use our own imitation, below -
 *
 */
function formatArray(arr)
{
  try
  {
    return arr.toSource();
  }
  catch(e)
  {
    return toSource(arr);
  }
}



/*
 * Imitate SpiderMonkey's arr.toSource() method:
 *
 * a) Double-quote each array element that is of string type
 * b) Represent |undefined| and |null| by empty strings
 * c) Delimit elements by a comma + single space
 * d) Do not add delimiter at the end UNLESS the last element is |undefined|
 * e) Add square brackets to the beginning and end of the string
 */
function toSource(arr)
{
  var delim = CHAR_COMMA + CHAR_SPACE;
  var elt = '';
  var ret = '';
  var len = arr.length;

  for (i=0; i<len; i++)
  {
    elt = arr[i];

    switch(true)
    {
    case (typeof elt === TYPE_STRING) :
      ret += doubleQuote(elt);
      break;

    case (elt === undefined || elt === null) :
      break; // add nothing but the delimiter, below -

    default:
      ret += elt.toString();
    }

    if ((i < len-1) || (elt === undefined))
      ret += delim;
  }

  return  CHAR_LBRACKET + ret + CHAR_RBRACKET;
}


function doubleQuote(text)
{
  return CHAR_QT_DBL + text + CHAR_QT_DBL;
}


function singleQuote(text)
{
  return CHAR_QT + text + CHAR_QT;
}


/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*- */

/*
 * Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/licenses/publicdomain/
 */


/*
 * Return true if both of these return true:
 * - LENIENT_PRED applied to CODE
 * - STRICT_PRED applied to CODE with a use strict directive added to the front
 *
 * Run STRICT_PRED first, for testing code that affects the global environment
 * in loose mode, but fails in strict mode.
 */
function testLenientAndStrict(code, lenient_pred, strict_pred) {
  return (strict_pred("'use strict'; " + code) && 
          lenient_pred(code));
}

/*
 * completesNormally(CODE) returns true if evaluating CODE (as eval
 * code) completes normally (rather than throwing an exception).
 */
function completesNormally(code) {
  try {
    eval(code);
    return true;
  } catch (exception) {
    return false;
  }
}

/*
 * returns(VALUE)(CODE) returns true if evaluating CODE (as eval code)
 * completes normally (rather than throwing an exception), yielding a value
 * strictly equal to VALUE.
 */
function returns(value) {
  return function(code) {
    try {
      return eval(code) === value;
    } catch (exception) {
      return false;
    }
  }
}

/*
 * returnsCopyOf(VALUE)(CODE) returns true if evaluating CODE (as eval code)
 * completes normally (rather than throwing an exception), yielding a value
 * that is deepEqual to VALUE.
 */
function returnsCopyOf(value) {
  return function(code) {
    try {
      return deepEqual(eval(code), value);
    } catch (exception) {
      return false;
    }
  }
}

/*
 * raisesException(EXCEPTION)(CODE) returns true if evaluating CODE (as
 * eval code) throws an exception object that is an instance of EXCEPTION,
 * and returns false if it throws any other error or evaluates
 * successfully. For example: raises(TypeError)("0()") == true.
 */
function raisesException(exception) {
  return function (code) {
    try {
      eval(code);
      return false;
    } catch (actual) {
      return actual instanceof exception;
    }
  };
};

/*
 * parsesSuccessfully(CODE) returns true if CODE parses as function
 * code without an error.
 */
function parsesSuccessfully(code) {
  try {
    Function(code);
    return true;
  } catch (exception) {
    return false;
  }
};

/*
 * parseRaisesException(EXCEPTION)(CODE) returns true if parsing CODE
 * as function code raises EXCEPTION.
 */
function parseRaisesException(exception) {
  return function (code) {
    try {
      Function(code);
      return false;
    } catch (actual) {
      return actual instanceof exception;
    }
  };
};

/*
 * Return the result of applying uneval to VAL, and replacing all runs
 * of whitespace with a single horizontal space (poor man's
 * tokenization).
 */
function clean_uneval(val) {
  return uneval(val).replace(/\s+/g, ' ');
}

/*
 * Return true if A is equal to B, where equality on arrays and objects
 * means that they have the same set of enumerable properties, the values
 * of each property are deep_equal, and their 'length' properties are
 * equal. Equality on other types is ==.
 */
function deepEqual(a, b) {
    if (typeof a != typeof b)
        return false;

    if (typeof a == 'object') {
        var props = {};
        // For every property of a, does b have that property with an equal value?
        for (var prop in a) {
            if (!deepEqual(a[prop], b[prop]))
                return false;
            props[prop] = true;
        }
        // Are all of b's properties present on a?
        for (var prop in b)
            if (!props[prop])
                return false;
        // length isn't enumerable, but we want to check it, too.
        return a.length == b.length;
    }

    if (a === b) {
        // Distinguish 0 from -0, even though they are ===.
        return a !== 0 || 1/a === 1/b;
    }

    // Treat NaNs as equal, even though NaN !== NaN.
    // NaNs are the only non-reflexive values, i.e., if a !== a, then a is a NaN.
    // isNaN is broken: it converts its argument to number, so isNaN("foo") => true
    return a !== a && b !== b;
}

gTestsubsuite='JSON';

function testJSON(str, expectSyntaxError)
{
  // Leading and trailing whitespace never affect parsing, so test the string
  // multiple times with and without whitespace around it as it's easy and can
  // potentially detect bugs.

  // Try the provided string
  try
  {
    JSON.parse(str);
    reportCompare(false, expectSyntaxError,
                  "string <" + str + "> " +
                  "should" + (expectSyntaxError ? "n't" : "") + " " +
                  "have parsed as JSON");
  }
  catch (e)
  {
    if (!(e instanceof SyntaxError))
    {
      reportCompare(true, false,
                    "parsing string <" + str + "> threw a non-SyntaxError " +
                    "exception: " + e);
    }
    else
    {
      reportCompare(true, expectSyntaxError,
                    "string <" + str + "> " +
                    "should" + (expectSyntaxError ? "n't" : "") + " " +
                    "have parsed as JSON, exception: " + e);
    }
  }

  // Now try the provided string with trailing whitespace
  try
  {
    JSON.parse(str + " ");
    reportCompare(false, expectSyntaxError,
                  "string <" + str + " > " +
                  "should" + (expectSyntaxError ? "n't" : "") + " " +
                  "have parsed as JSON");
  }
  catch (e)
  {
    if (!(e instanceof SyntaxError))
    {
      reportCompare(true, false,
                    "parsing string <" + str + " > threw a non-SyntaxError " +
                    "exception: " + e);
    }
    else
    {
      reportCompare(true, expectSyntaxError,
                    "string <" + str + " > " +
                    "should" + (expectSyntaxError ? "n't" : "") + " " +
                    "have parsed as JSON, exception: " + e);
    }
  }

  // Now try the provided string with leading whitespace
  try
  {
    JSON.parse(" " + str);
    reportCompare(false, expectSyntaxError,
                  "string < " + str + "> " +
                  "should" + (expectSyntaxError ? "n't" : "") + " " +
                  "have parsed as JSON");
  }
  catch (e)
  {
    if (!(e instanceof SyntaxError))
    {
      reportCompare(true, false,
                    "parsing string < " + str + "> threw a non-SyntaxError " +
                    "exception: " + e);
    }
    else
    {
      reportCompare(true, expectSyntaxError,
                    "string < " + str + "> " +
                    "should" + (expectSyntaxError ? "n't" : "") + " " +
                    "have parsed as JSON, exception: " + e);
    }
  }

  // Now try the provided string with whitespace surrounding it
  try
  {
    JSON.parse(" " + str + " ");
    reportCompare(false, expectSyntaxError,
                  "string < " + str + " > " +
                  "should" + (expectSyntaxError ? "n't" : "") + " " +
                  "have parsed as JSON");
  }
  catch (e)
  {
    if (!(e instanceof SyntaxError))
    {
      reportCompare(true, false,
                    "parsing string < " + str + " > threw a non-SyntaxError " +
                    "exception: " + e);
    }
    else
    {
      reportCompare(true, expectSyntaxError,
                    "string < " + str + " > " +
                    "should" + (expectSyntaxError ? "n't" : "") + " " +
                    "have parsed as JSON, exception: " + e);
    }
  }
}

function makeExpectedMatch(arr, index, input) {
    var expectedMatch = {
        index: index,
        input: input,
        length: arr.length,
    };

    for (var i = 0; i < arr.length; ++i)
        expectedMatch[i] = arr[i];

    return expectedMatch;
}

function checkRegExpMatch(actual, expected) {
    assertEq(actual.length, expected.length);
    for (var i = 0; i < actual.length; ++i)
        assertEq(actual[i], expected[i]);

    assertEq(actual.index, expected.index);
    assertEq(actual.input, expected.input);
}

/* -*- indent-tabs-mode: nil; js-indent-level: 4 -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// NOTE: This only turns on 1.7 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js.
if (typeof version != 'undefined')
{
  version(170);
}


/* -*- indent-tabs-mode: nil; js-indent-level: 4 -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// NOTE: This only turns on 1.8.1 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js (which only turns on 1.8, the most
//       the browser supports).
if (typeof version != 'undefined')
{
  version(181);
}


/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*- */

/*
 * Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/licenses/publicdomain/
 */


/*
 * Return true if both of these return true:
 * - LENIENT_PRED applied to CODE
 * - STRICT_PRED applied to CODE with a use strict directive added to the front
 *
 * Run STRICT_PRED first, for testing code that affects the global environment
 * in loose mode, but fails in strict mode.
 */
function testLenientAndStrict(code, lenient_pred, strict_pred) {
  return (strict_pred("'use strict'; " + code) && 
          lenient_pred(code));
}

/*
 * completesNormally(CODE) returns true if evaluating CODE (as eval
 * code) completes normally (rather than throwing an exception).
 */
function completesNormally(code) {
  try {
    eval(code);
    return true;
  } catch (exception) {
    return false;
  }
}

/*
 * raisesException(EXCEPTION)(CODE) returns true if evaluating CODE (as eval
 * code) throws an exception object whose prototype is
 * EXCEPTION.prototype, and returns false if it throws any other error
 * or evaluates successfully. For example: raises(TypeError)("0()") ==
 * true.
 */
function raisesException(exception) {
  return function (code) {
    try {
      eval(code);
      return false;
    } catch (actual) {
      return exception.prototype.isPrototypeOf(actual);
    }
  };
};

/*
 * parsesSuccessfully(CODE) returns true if CODE parses as function
 * code without an error.
 */
function parsesSuccessfully(code) {
  try {
    Function(code);
    return true;
  } catch (exception) {
    return false;
  }
};

/*
 * parseRaisesException(EXCEPTION)(CODE) returns true if parsing CODE
 * as function code raises EXCEPTION.
 */
function parseRaisesException(exception) {
  return function (code) {
    try {
      Function(code);
      return false;
    } catch (actual) {
      return exception.prototype.isPrototypeOf(actual);
    }
  };
};

/*
 * Return the result of applying uneval to VAL, and replacing all runs
 * of whitespace with a single horizontal space (poor man's
 * tokenization).
 */
function clean_uneval(val) {
  return uneval(val).replace(/\s+/g, ' ');
}

// The loop count at which we trace
const RECORDLOOP = this.tracemonkey ? tracemonkey.HOTLOOP : 8;
// The loop count at which we run the trace
const RUNLOOP = RECORDLOOP + 1;

/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*- */
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/* all files in this dir need version(120) called before they are *loaded* */


version(120);

// NOTE: This only turns on 1.8.5 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js (which only turns on 1.8, the most
//       the browser supports).
if (typeof version != 'undefined')
  version(185);

function assertThrownErrorContains(thunk, substr) {
    try {
        thunk();
    } catch (e) {
        if (e.message.indexOf(substr) !== -1)
            return;
        throw new Error("Expected error containing " + substr + ", got " + e);
    }
    throw new Error("Expected error containing " + substr + ", no exception thrown");
}

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */


if (typeof assertThrowsInstanceOf === 'undefined') {
    var assertThrowsInstanceOf = function assertThrowsInstanceOf(f, ctor, msg) {
        var fullmsg;
        try {
            f();
        } catch (exc) {
            if (exc instanceof ctor)
                return;
            fullmsg = "Assertion failed: expected exception " + ctor.name + ", got " + exc;
        }
        if (fullmsg === undefined)
            fullmsg = "Assertion failed: expected exception " + ctor.name + ", no exception thrown";
        if (msg !== undefined)
            fullmsg += " - " + msg;
        throw new Error(fullmsg);
    };
}

if (typeof assertThrowsValue === 'undefined') {
    var assertThrowsValue = function assertThrowsValue(f, val, msg) {
        var fullmsg;
        try {
            f();
        } catch (exc) {
            if ((exc === val) === (val === val) && (val !== 0 || 1 / exc === 1 / val))
                return;
            fullmsg = "Assertion failed: expected exception " + val + ", got " + exc;
        }
        if (fullmsg === undefined)
            fullmsg = "Assertion failed: expected exception " + val + ", no exception thrown";
        if (msg !== undefined)
            fullmsg += " - " + msg;
        throw new Error(fullmsg);
    };
}

if (typeof assertDeepEq === 'undefined') {
    var assertDeepEq = (function(){
        var call = Function.prototype.call,
            Array_isArray = Array.isArray,
            Map_ = Map,
            Error_ = Error,
            Symbol_ = Symbol,
            Map_has = call.bind(Map.prototype.has),
            Map_get = call.bind(Map.prototype.get),
            Map_set = call.bind(Map.prototype.set),
            Object_toString = call.bind(Object.prototype.toString),
            Function_toString = call.bind(Function.prototype.toString),
            Object_getPrototypeOf = Object.getPrototypeOf,
            Object_hasOwnProperty = call.bind(Object.prototype.hasOwnProperty),
            Object_getOwnPropertyDescriptor = Object.getOwnPropertyDescriptor,
            Object_isExtensible = Object.isExtensible,
            Object_getOwnPropertyNames = Object.getOwnPropertyNames,
            uneval_ = uneval;

        // Return true iff ES6 Type(v) isn't Object.
        // Note that `typeof document.all === "undefined"`.
        function isPrimitive(v) {
            return (v === null ||
                    v === undefined ||
                    typeof v === "boolean" ||
                    typeof v === "number" ||
                    typeof v === "string" ||
                    typeof v === "symbol");
        }

        function assertSameValue(a, b, msg) {
            try {
                assertEq(a, b);
            } catch (exc) {
                throw Error_(exc.message + (msg ? " " + msg : ""));
            }
        }

        function assertSameClass(a, b, msg) {
            var ac = Object_toString(a), bc = Object_toString(b);
            assertSameValue(ac, bc, msg);
            switch (ac) {
            case "[object Function]":
                assertSameValue(Function_toString(a), Function_toString(b), msg);
            }
        }

        function at(prevmsg, segment) {
            return prevmsg ? prevmsg + segment : "at _" + segment;
        }

        // Assert that the arguments a and b are thoroughly structurally equivalent.
        //
        // For the sake of speed, we cut a corner:
        //        var x = {}, y = {}, ax = [x];
        //        assertDeepEq([ax, x], [ax, y]);  // passes (?!)
        //
        // Technically this should fail, since the two object graphs are different.
        // (The graph of [ax, y] contains one more object than the graph of [ax, x].)
        //
        // To get technically correct behavior, pass {strictEquivalence: true}.
        // This is slower because we have to walk the entire graph, and Object.prototype
        // is big.
        //
        return function assertDeepEq(a, b, options) {
            var strictEquivalence = options ? options.strictEquivalence : false;

            function assertSameProto(a, b, msg) {
                check(Object_getPrototypeOf(a), Object_getPrototypeOf(b), at(msg, ".__proto__"));
            }

            function failPropList(na, nb, msg) {
                throw Error_("got own properties " + uneval_(na) + ", expected " + uneval_(nb) +
                             (msg ? " " + msg : ""));
            }

            function assertSameProps(a, b, msg) {
                var na = Object_getOwnPropertyNames(a),
                    nb = Object_getOwnPropertyNames(b);
                if (na.length !== nb.length)
                    failPropList(na, nb, msg);

                // Ignore differences in whether Array elements are stored densely.
                if (Array_isArray(a)) {
                    na.sort();
                    nb.sort();
                }

                for (var i = 0; i < na.length; i++) {
                    var name = na[i];
                    if (name !== nb[i])
                        failPropList(na, nb, msg);
                    var da = Object_getOwnPropertyDescriptor(a, name),
                        db = Object_getOwnPropertyDescriptor(b, name);
                    var pmsg = at(msg, /^[_$A-Za-z0-9]+$/.test(name)
                                       ? /0|[1-9][0-9]*/.test(name) ? "[" + name + "]" : "." + name
                                       : "[" + uneval_(name) + "]");
                    assertSameValue(da.configurable, db.configurable, at(pmsg, ".[[Configurable]]"));
                    assertSameValue(da.enumerable, db.enumerable, at(pmsg, ".[[Enumerable]]"));
                    if (Object_hasOwnProperty(da, "value")) {
                        if (!Object_hasOwnProperty(db, "value"))
                            throw Error_("got data property, expected accessor property" + pmsg);
                        check(da.value, db.value, pmsg);
                    } else {
                        if (Object_hasOwnProperty(db, "value"))
                            throw Error_("got accessor property, expected data property" + pmsg);
                        check(da.get, db.get, at(pmsg, ".[[Get]]"));
                        check(da.set, db.set, at(pmsg, ".[[Set]]"));
                    }
                }
            };

            var ab = new Map_();
            var bpath = new Map_();

            function check(a, b, path) {
                if (typeof a === "symbol") {
                    // Symbols are primitives, but they have identity.
                    // Symbol("x") !== Symbol("x") but
                    // assertDeepEq(Symbol("x"), Symbol("x")) should pass.
                    if (typeof b !== "symbol") {
                        throw Error_("got " + uneval_(a) + ", expected " + uneval_(b) + " " + path);
                    } else if (uneval_(a) !== uneval_(b)) {
                        // We lamely use uneval_ to distinguish well-known symbols
                        // from user-created symbols. The standard doesn't offer
                        // a convenient way to do it.
                        throw Error_("got " + uneval_(a) + ", expected " + uneval_(b) + " " + path);
                    } else if (Map_has(ab, a)) {
                        assertSameValue(Map_get(ab, a), b, path);
                    } else if (Map_has(bpath, b)) {
                        var bPrevPath = Map_get(bpath, b) || "_";
                        throw Error_("got distinct symbols " + at(path, "") + " and " +
                                     at(bPrevPath, "") + ", expected the same symbol both places");
                    } else {
                        Map_set(ab, a, b);
                        Map_set(bpath, b, path);
                    }
                } else if (isPrimitive(a)) {
                    assertSameValue(a, b, path);
                } else if (isPrimitive(b)) {
                    throw Error_("got " + Object_toString(a) + ", expected " + uneval_(b) + " " + path);
                } else if (Map_has(ab, a)) {
                    assertSameValue(Map_get(ab, a), b, path);
                } else if (Map_has(bpath, b)) {
                    var bPrevPath = Map_get(bpath, b) || "_";
                    throw Error_("got distinct objects " + at(path, "") + " and " + at(bPrevPath, "") +
                                 ", expected the same object both places");
                } else {
                    Map_set(ab, a, b);
                    Map_set(bpath, b, path);
                    if (a !== b || strictEquivalence) {
                        assertSameClass(a, b, path);
                        assertSameProto(a, b, path);
                        assertSameProps(a, b, path);
                        assertSameValue(Object_isExtensible(a),
                                        Object_isExtensible(b),
                                        at(path, ".[[Extensible]]"));
                    }
                }
            }

            check(a, b, "");
        };
    })();
}

if (typeof assertWarning === 'undefined') {
    function assertWarning(func, name) {
        enableLastWarning();
        func();
        var warning = getLastWarning();
        assertEq(warning !== null, true);
        assertEq(warning.name, name);
        disableLastWarning();
    }
}

// The nearest representable values to +1.0.
const ONE_PLUS_EPSILON = 1 + Math.pow(2, -52);  // 0.9999999999999999
const ONE_MINUS_EPSILON = 1 - Math.pow(2, -53);  // 1.0000000000000002

{
    var fail = function (msg) {
        var exc = new Error(msg);
        try {
            // Try to improve on exc.fileName and .lineNumber; leave exc.stack
            // alone. We skip two frames: fail() and its caller, an assertX()
            // function.
            var frames = exc.stack.trim().split("\n");
            if (frames.length > 2) {
                var m = /@([^@:]*):([0-9]+)$/.exec(frames[2]);
                if (m) {
                    exc.fileName = m[1];
                    exc.lineNumber = +m[2];
                }
            }
        } catch (ignore) { throw ignore;}
        throw exc;
    };

    var ENDIAN;  // 0 for little-endian, 1 for big-endian.

    // Return the difference between the IEEE 754 bit-patterns for a and b.
    //
    // This is meaningful when a and b are both finite and have the same
    // sign. Then the following hold:
    //
    //   * If a === b, then diff(a, b) === 0.
    //
    //   * If a !== b, then diff(a, b) === 1 + the number of representable values
    //                                         between a and b.
    //
    var f = new Float64Array([0, 0]);
    var u = new Uint32Array(f.buffer);
    var diff = function (a, b) {
        f[0] = a;
        f[1] = b;
        //print(u[1].toString(16) + u[0].toString(16) + " " + u[3].toString(16) + u[2].toString(16));
        return Math.abs((u[3-ENDIAN] - u[1-ENDIAN]) * 0x100000000 + u[2+ENDIAN] - u[0+ENDIAN]);
    };

    // Set ENDIAN to the platform's endianness.
    ENDIAN = 0;  // try little-endian first
    if (diff(2, 4) === 0x100000)  // exact wrong answer we'll get on a big-endian platform
        ENDIAN = 1;
    assertEq(diff(2,4), 0x10000000000000);
    assertEq(diff(0, Number.MIN_VALUE), 1);
    assertEq(diff(1, ONE_PLUS_EPSILON), 1);
    assertEq(diff(1, ONE_MINUS_EPSILON), 1);

    var assertNear = function assertNear(a, b, tolerance=1) {
        if (!Number.isFinite(b)) {
            fail("second argument to assertNear (expected value) must be a finite number");
        } else if (Number.isNaN(a)) {
            fail("got NaN, expected a number near " + b);
        } else if (!Number.isFinite(a)) {
            if (b * Math.sign(a) < Number.MAX_VALUE)
                fail("got " + a + ", expected a number near " + b);
        } else {
            // When the two arguments do not have the same sign bit, diff()
            // returns some huge number. So if b is positive or negative 0,
            // make target the zero that has the same sign bit as a.
            var target = b === 0 ? a * 0 : b;
            var err = diff(a, target);
            if (err > tolerance) {
                fail("got " + a + ", expected a number near " + b +
                     " (relative error: " + err + ")");
            }
        }
    };
}

// NOTE: This only turns on 1.8.5 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js (which only turns on 1.8, the most
//       the browser supports).
if (typeof version != 'undefined')
  version(185);

// List of a few values that are not objects.
var SOME_PRIMITIVE_VALUES = [
    undefined, null,
    false,
    -Infinity, -1.6e99, -1, -0, 0, Math.pow(2, -1074), 1, 4294967295,
    Number.MAX_SAFE_INTEGER, Number.MAX_SAFE_INTEGER + 1, 1.6e99, Infinity, NaN,
    "", "Phaedo",
    Symbol(), Symbol("iterator"), Symbol.for("iterator"), Symbol.iterator
];



// NOTE: This only turns on 1.8.5 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js (which only turns on 1.8, the most
//       the browser supports).
if (typeof version != 'undefined')
  version(185);

// Synthesize a constructor for a shared memory array from the
// constructor for unshared memory.  This has "good enough" fidelity
// for many uses.  In cases where it's not good enough, use the
// __isShared__ flags or call isSharedConstructor for local workarounds.

function sharedConstructor(constructor) {
    var c = function(...args) {
	if (!new.target)
	    throw new TypeError("Not callable");
	var array = new constructor(...args);
	var buffer = array.buffer;
	var offset = array.byteOffset;
	var length = array.length;
	var sharedBuffer = new SharedArrayBuffer(buffer.byteLength);
	var sharedArray = new constructor(sharedBuffer, offset, length);
	for ( var i=0 ; i < length ; i++ )
	    sharedArray[i] = array[i];
	assertEq(sharedArray.buffer, sharedBuffer);
	return sharedArray;
    };
    c.prototype = Object.create(constructor.prototype);
    c.__isShared__ = true;
    c.__baseConstructor__ = constructor;
    c.from = constructor.from;
    c.of = constructor.of;
    return c;
}

function isSharedConstructor(x) {
    return typeof x == "function" && x.__isShared__;
}

function isFloatingConstructor(c) {
    return c == Float32Array ||
	c == Float64Array ||
	(c.hasOwnProperty("__baseConstructor__") &&
	 isFloatingConstructor(c.__baseConstructor__));
}

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

function assertFalse(a) { assertEq(a, false) }
function assertTrue(a) { assertEq(a, true) }
function assertNotEq(found, not_expected) { assertFalse(found === expected) }
function assertIteratorResult(result, value, done) {
    assertDeepEq(result.value, value);
    assertEq(result.done, done);
}
function assertIteratorNext(iter, value) {
    assertIteratorResult(iter.next(), value, false);
}
function assertIteratorDone(iter, value) {
    assertIteratorResult(iter.next(), value, true);
}

function runNormalizeTest(test) {
  function codePointsToString(points) {
    return points.map(x => String.fromCodePoint(x)).join("");
  }
  function stringify(points) {
    return points.map(x => x.toString(16)).join();
  }

  var source = codePointsToString(test.source);
  var NFC = codePointsToString(test.NFC);
  var NFD = codePointsToString(test.NFD);
  var NFKC = codePointsToString(test.NFKC);
  var NFKD = codePointsToString(test.NFKD);
  var sourceStr = stringify(test.source);
  var nfcStr = stringify(test.NFC);
  var nfdStr = stringify(test.NFD);
  var nfkcStr = stringify(test.NFKC);
  var nfkdStr = stringify(test.NFKD);

  /* NFC */
  assertEq(source.normalize(), NFC, "NFC of " + sourceStr);
  assertEq(NFC.normalize(), NFC, "NFC of " + nfcStr);
  assertEq(NFD.normalize(), NFC, "NFC of " + nfdStr);
  assertEq(NFKC.normalize(), NFKC, "NFC of " + nfkcStr);
  assertEq(NFKD.normalize(), NFKC, "NFC of " + nfkdStr);

  assertEq(source.normalize(undefined), NFC, "NFC of " + sourceStr);
  assertEq(NFC.normalize(undefined), NFC, "NFC of " + nfcStr);
  assertEq(NFD.normalize(undefined), NFC, "NFC of " + nfdStr);
  assertEq(NFKC.normalize(undefined), NFKC, "NFC of " + nfkcStr);
  assertEq(NFKD.normalize(undefined), NFKC, "NFC of " + nfkdStr);

  assertEq(source.normalize("NFC"), NFC, "NFC of " + sourceStr);
  assertEq(NFC.normalize("NFC"), NFC, "NFC of " + nfcStr);
  assertEq(NFD.normalize("NFC"), NFC, "NFC of " + nfdStr);
  assertEq(NFKC.normalize("NFC"), NFKC, "NFC of " + nfkcStr);
  assertEq(NFKD.normalize("NFC"), NFKC, "NFC of " + nfkdStr);

  /* NFD */
  assertEq(source.normalize("NFD"), NFD, "NFD of " + sourceStr);
  assertEq(NFC.normalize("NFD"), NFD, "NFD of " + nfcStr);
  assertEq(NFD.normalize("NFD"), NFD, "NFD of " + nfdStr);
  assertEq(NFKC.normalize("NFD"), NFKD, "NFD of " + nfkcStr);
  assertEq(NFKD.normalize("NFD"), NFKD, "NFD of " + nfkdStr);

  /* NFKC */
  assertEq(source.normalize("NFKC"), NFKC, "NFKC of " + sourceStr);
  assertEq(NFC.normalize("NFKC"), NFKC, "NFKC of " + nfcStr);
  assertEq(NFD.normalize("NFKC"), NFKC, "NFKC of " + nfdStr);
  assertEq(NFKC.normalize("NFKC"), NFKC, "NFKC of " + nfkcStr);
  assertEq(NFKD.normalize("NFKC"), NFKC, "NFKC of " + nfkdStr);

  /* NFKD */
  assertEq(source.normalize("NFKD"), NFKD, "NFKD of " + sourceStr);
  assertEq(NFC.normalize("NFKD"), NFKD, "NFKD of " + nfcStr);
  assertEq(NFD.normalize("NFKD"), NFKD, "NFKD of " + nfdStr);
  assertEq(NFKC.normalize("NFKD"), NFKD, "NFKD of " + nfkcStr);
  assertEq(NFKD.normalize("NFKD"), NFKD, "NFKD of " + nfkdStr);
}

/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*- */
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
 * Date: 14 Mar 2001
 *
 * SUMMARY: Utility functions for testing objects -
 *
 * Suppose obj is an instance of a native type, e.g. Number.
 * Then obj.toString() invokes Number.prototype.toString().
 * We would also like to access Object.prototype.toString().
 *
 * The difference is this: suppose obj = new Number(7).
 * Invoking Number.prototype.toString() on this just returns 7.
 * Object.prototype.toString() on this returns '[object Number]'.
 *
 * The getJSType() function below will return '[object Number]' for us.
 * The getJSClass() function returns 'Number', the [[Class]] property of obj.
 * See ECMA-262 Edition 3,  13-Oct-1999,  Section 8.6.2 
 */
//-----------------------------------------------------------------------------


var cnNoObject = 'Unexpected Error!!! Parameter to this function must be an object';
var cnNoClass = 'Unexpected Error!!! Cannot find Class property';
var cnObjectToString = Object.prototype.toString;
var GLOBAL = 'global';

// checks that it's safe to call findType()
function getJSType(obj)
{
  if (isObject(obj))
    return findType(obj);
  return cnNoObject;
}


// checks that it's safe to call findType()
function getJSClass(obj)
{
  if (isObject(obj))
    return findClass(findType(obj));
  return cnNoObject;
}


function findType(obj)
{
  return cnObjectToString.apply(obj);
}


// given '[object Number]',  return 'Number'
function findClass(sType)
{
  var re =  /^\[.*\s+(\w+)\s*\]$/;
  var a = sType.match(re);
 
  if (a && a[1])
    return a[1];
  return cnNoClass;
}


function isObject(obj)
{
  return obj instanceof Object;
}


/* -*- tab-width: 2; indent-tabs-mode: nil; js-indent-level: 2 -*- */
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
 * Date: 07 February 2001
 *
 * Functionality common to RegExp testing -
 */
//-----------------------------------------------------------------------------


var MSG_PATTERN = '\nregexp = ';
var MSG_STRING = '\nstring = ';
var MSG_EXPECT = '\nExpect: ';
var MSG_ACTUAL = '\nActual: ';
var ERR_LENGTH = '\nERROR !!! match arrays have different lengths:';
var ERR_MATCH = '\nERROR !!! regexp failed to give expected match array:';
var ERR_NO_MATCH = '\nERROR !!! regexp FAILED to match anything !!!';
var ERR_UNEXP_MATCH = '\nERROR !!! regexp MATCHED when we expected it to fail !!!';
var CHAR_LBRACKET = '[';
var CHAR_RBRACKET = ']';
var CHAR_QT_DBL = '"';
var CHAR_QT = "'";
var CHAR_NL = '\n';
var CHAR_COMMA = ',';
var CHAR_SPACE = ' ';
var TYPE_STRING = typeof 'abc';



function testRegExp(statuses, patterns, strings, actualmatches, expectedmatches)
{
  var status = '';
  var pattern = new RegExp();
  var string = '';
  var actualmatch = new Array();
  var expectedmatch = new Array();
  var state = '';
  var lActual = -1;
  var lExpect = -1;


  for (var i=0; i != patterns.length; i++)
  {
    status = statuses[i];
    pattern = patterns[i];
    string = strings[i];
    actualmatch=actualmatches[i];
    expectedmatch=expectedmatches[i];
    state = getState(status, pattern, string);

    description = status;

    if(actualmatch)
    {
      actual = formatArray(actualmatch);
      if(expectedmatch)
      {
        // expectedmatch and actualmatch are arrays -
        lExpect = expectedmatch.length;
        lActual = actualmatch.length;

        var expected = formatArray(expectedmatch);

        if (lActual != lExpect)
        {
          reportCompare(lExpect, lActual,
                        state + ERR_LENGTH +
                        MSG_EXPECT + expected +
                        MSG_ACTUAL + actual +
                        CHAR_NL
	    );
          continue;
        }

        // OK, the arrays have same length -
        if (expected != actual)
        {
          reportCompare(expected, actual,
                        state + ERR_MATCH +
                        MSG_EXPECT + expected +
                        MSG_ACTUAL + actual +
                        CHAR_NL
	    );
        }
        else
        {
          reportCompare(expected, actual, state)
	    }

      }
      else //expectedmatch is null - that is, we did not expect a match -
      {
        expected = expectedmatch;
        reportCompare(expected, actual,
                      state + ERR_UNEXP_MATCH +
                      MSG_EXPECT + expectedmatch +
                      MSG_ACTUAL + actual +
                      CHAR_NL
	  );
      }

    }
    else // actualmatch is null
    {
      if (expectedmatch)
      {
        actual = actualmatch;
        reportCompare(expected, actual,
                      state + ERR_NO_MATCH +
                      MSG_EXPECT + expectedmatch +
                      MSG_ACTUAL + actualmatch +
                      CHAR_NL
	  );
      }
      else // we did not expect a match
      {
        // Being ultra-cautious. Presumably expectedmatch===actualmatch===null
        expected = expectedmatch;
        actual   = actualmatch;
        reportCompare (expectedmatch, actualmatch, state);
      }
    }
  }
}


function getState(status, pattern, string)
{
  /*
   * Escape \n's, etc. to make them LITERAL in the presentation string.
   * We don't have to worry about this in |pattern|; such escaping is
   * done automatically by pattern.toString(), invoked implicitly below.
   *
   * One would like to simply do: string = string.replace(/(\s)/g, '\$1').
   * However, the backreference $1 is not a literal string value,
   * so this method doesn't work.
   *
   * Also tried string = string.replace(/(\s)/g, escape('$1'));
   * but this just inserts the escape of the literal '$1', i.e. '%241'.
   */
  string = string.replace(/\n/g, '\\n');
  string = string.replace(/\r/g, '\\r');
  string = string.replace(/\t/g, '\\t');
  string = string.replace(/\v/g, '\\v');
  string = string.replace(/\f/g, '\\f');

  return (status + MSG_PATTERN + pattern + MSG_STRING + singleQuote(string));
}


/*
 * If available, arr.toSource() gives more detail than arr.toString()
 *
 * var arr = Array(1,2,'3');
 *
 * arr.toSource()
 * [1, 2, "3"]
 *
 * arr.toString()
 * 1,2,3
 *
 * But toSource() doesn't exist in Rhino, so use our own imitation, below -
 *
 */
function formatArray(arr)
{
  try
  {
    return arr.toSource();
  }
  catch(e)
  {
    return toSource(arr);
  }
}


/*
 * Imitate SpiderMonkey's arr.toSource() method:
 *
 * a) Double-quote each array element that is of string type
 * b) Represent |undefined| and |null| by empty strings
 * c) Delimit elements by a comma + single space
 * d) Do not add delimiter at the end UNLESS the last element is |undefined|
 * e) Add square brackets to the beginning and end of the string
 */
function toSource(arr)
{
  var delim = CHAR_COMMA + CHAR_SPACE;
  var elt = '';
  var ret = '';
  var len = arr.length;

  for (i=0; i<len; i++)
  {
    elt = arr[i];

    switch(true)
    {
    case (typeof elt === TYPE_STRING) :
      ret += doubleQuote(elt);
      break;

    case (elt === undefined || elt === null) :
      break; // add nothing but the delimiter, below -

    default:
      ret += elt.toString();
    }

    if ((i < len-1) || (elt === undefined))
      ret += delim;
  }

  return  CHAR_LBRACKET + ret + CHAR_RBRACKET;
}


function doubleQuote(text)
{
  return CHAR_QT_DBL + text + CHAR_QT_DBL;
}


function singleQuote(text)
{
  return CHAR_QT + text + CHAR_QT;
}


/* -*- tab-width: 2; indent-tabs-mode: nil; js-indent-level: 2 -*- */
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
 * Date: 07 February 2001
 *
 * Functionality common to RegExp testing -
 */
//-----------------------------------------------------------------------------


var MSG_PATTERN = '\nregexp = ';
var MSG_STRING = '\nstring = ';
var MSG_EXPECT = '\nExpect: ';
var MSG_ACTUAL = '\nActual: ';
var ERR_LENGTH = '\nERROR !!! match arrays have different lengths:';
var ERR_MATCH = '\nERROR !!! regexp failed to give expected match array:';
var ERR_NO_MATCH = '\nERROR !!! regexp FAILED to match anything !!!';
var ERR_UNEXP_MATCH = '\nERROR !!! regexp MATCHED when we expected it to fail !!!';
var CHAR_LBRACKET = '[';
var CHAR_RBRACKET = ']';
var CHAR_QT_DBL = '"';
var CHAR_QT = "'";
var CHAR_NL = '\n';
var CHAR_COMMA = ',';
var CHAR_SPACE = ' ';
var TYPE_STRING = typeof 'abc';



function testRegExp(statuses, patterns, strings, actualmatches, expectedmatches)
{
  var status = '';
  var pattern = new RegExp();
  var string = '';
  var actualmatch = new Array();
  var expectedmatch = new Array();
  var state = '';
  var lActual = -1;
  var lExpect = -1;


  for (var i=0; i != patterns.length; i++)
  {
    status = statuses[i];
    pattern = patterns[i];
    string = strings[i];
    actualmatch=actualmatches[i];
    expectedmatch=expectedmatches[i];
    state = getState(status, pattern, string);

    description = status;

    if(actualmatch)
    {
      actual = formatArray(actualmatch);
      if(expectedmatch)
      {
        // expectedmatch and actualmatch are arrays -
        lExpect = expectedmatch.length;
        lActual = actualmatch.length;

        var expected = formatArray(expectedmatch);

        if (lActual != lExpect)
        {
          reportCompare(lExpect, lActual,
                        state + ERR_LENGTH +
                        MSG_EXPECT + expected +
                        MSG_ACTUAL + actual +
                        CHAR_NL
	    );
          continue;
        }

        // OK, the arrays have same length -
        if (expected != actual)
        {
          reportCompare(expected, actual,
                        state + ERR_MATCH +
                        MSG_EXPECT + expected +
                        MSG_ACTUAL + actual +
                        CHAR_NL
	    );
        }
        else
        {
          reportCompare(expected, actual, state)
	    }

      }
      else //expectedmatch is null - that is, we did not expect a match -
      {
        expected = expectedmatch;
        reportCompare(expected, actual,
                      state + ERR_UNEXP_MATCH +
                      MSG_EXPECT + expectedmatch +
                      MSG_ACTUAL + actual +
                      CHAR_NL
	  );
      }

    }
    else // actualmatch is null
    {
      if (expectedmatch)
      {
        actual = actualmatch;
        reportCompare(expected, actual,
                      state + ERR_NO_MATCH +
                      MSG_EXPECT + expectedmatch +
                      MSG_ACTUAL + actualmatch +
                      CHAR_NL
	  );
      }
      else // we did not expect a match
      {
        // Being ultra-cautious. Presumably expectedmatch===actualmatch===null
        expected = expectedmatch;
        actual   = actualmatch;
        reportCompare (expectedmatch, actualmatch, state);
      }
    }
  }
}


function getState(status, pattern, string)
{
  /*
   * Escape \n's, etc. to make them LITERAL in the presentation string.
   * We don't have to worry about this in |pattern|; such escaping is
   * done automatically by pattern.toString(), invoked implicitly below.
   *
   * One would like to simply do: string = string.replace(/(\s)/g, '\$1').
   * However, the backreference $1 is not a literal string value,
   * so this method doesn't work.
   *
   * Also tried string = string.replace(/(\s)/g, escape('$1'));
   * but this just inserts the escape of the literal '$1', i.e. '%241'.
   */
  string = string.replace(/\n/g, '\\n');
  string = string.replace(/\r/g, '\\r');
  string = string.replace(/\t/g, '\\t');
  string = string.replace(/\v/g, '\\v');
  string = string.replace(/\f/g, '\\f');

  return (status + MSG_PATTERN + pattern + MSG_STRING + singleQuote(string));
}


/*
 * If available, arr.toSource() gives more detail than arr.toString()
 *
 * var arr = Array(1,2,'3');
 *
 * arr.toSource()
 * [1, 2, "3"]
 *
 * arr.toString()
 * 1,2,3
 *
 * But toSource() doesn't exist in Rhino, so use our own imitation, below -
 *
 */
function formatArray(arr)
{
  try
  {
    return arr.toSource();
  }
  catch(e)
  {
    return toSource(arr);
  }
}


/*
 * Imitate SpiderMonkey's arr.toSource() method:
 *
 * a) Double-quote each array element that is of string type
 * b) Represent |undefined| and |null| by empty strings
 * c) Delimit elements by a comma + single space
 * d) Do not add delimiter at the end UNLESS the last element is |undefined|
 * e) Add square brackets to the beginning and end of the string
 */
function toSource(arr)
{
  var delim = CHAR_COMMA + CHAR_SPACE;
  var elt = '';
  var ret = '';
  var len = arr.length;

  for (i=0; i<len; i++)
  {
    elt = arr[i];

    switch(true)
    {
    case (typeof elt === TYPE_STRING) :
      ret += doubleQuote(elt);
      break;

    case (elt === undefined || elt === null) :
      break; // add nothing but the delimiter, below -

    default:
      ret += elt.toString();
    }

    if ((i < len-1) || (elt === undefined))
      ret += delim;
  }

  return  CHAR_LBRACKET + ret + CHAR_RBRACKET;
}


function doubleQuote(text)
{
  return CHAR_QT_DBL + text + CHAR_QT_DBL;
}


function singleQuote(text)
{
  return CHAR_QT + text + CHAR_QT;
}


/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
 * Test402 tests all pass unless they throw, and there are no @negative tests.
 * Once Test262 includes @negative support, and this call in test262-shell.js is
 * removed, this'll need to be uncommented.
 */
//testPassesUnlessItThrows();

/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*- */
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */


var TZ_DIFF = getTimeZoneDiff();

/*
 * Originally, the test suite used a hard-coded value TZ_DIFF = -8.
 * But that was only valid for testers in the Pacific Standard Time Zone!
 * We calculate the proper number dynamically for any tester. We just
 * have to be careful to use a date not subject to Daylight Savings Time...
 */
function getTimeZoneDiff()
{
  return -((new Date(2000, 1, 1)).getTimezoneOffset())/60;
}

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
 * The current crop of Test262 test cases that we run are expected to pass
 * unless they crash or throw.  (This isn't true for all Test262 test cases --
 * for the ones marked @negative the logic is inverted.  We'll have to deal with
 * that concern eventually, but for now we're punting so we can run subsets of
 * Test262 tests.)
 */
/*
 * Test262 function $ERROR throws an error with the message provided. Test262
 * test cases call it to indicate failure.
 */
function $ERROR(msg)
{
  throw new Error("Test262 error: " + msg);
}

/*
 * Test262 function $INCLUDE loads a file with support functions for the tests.
 * This function is replaced in browser.js.
 */
function $INCLUDE(file)
{
  load("supporting/" + file);
}

/*
 * Test262 function fnGlobalObject returns the global object.
 */
var fnGlobalObject = (function()
{
  var global = Function("return this")();
  return function fnGlobalObject() { return global; };
})();

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
 * Test402 tests all pass unless they throw, and there are no @negative tests.
 * Once Test262 includes @negative support, and this call in test262-shell.js is
 * removed, this'll need to be uncommented.
 */
//testPassesUnlessItThrows();

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */


if (typeof assertThrowsInstanceOf === 'undefined') {
    var assertThrowsInstanceOf = function assertThrowsInstanceOf(f, ctor, msg) {
        var fullmsg;
        try {
            f();
        } catch (exc) {
            if (exc instanceof ctor)
                return;
            fullmsg = "Assertion failed: expected exception " + ctor.name + ", got " + exc;
        }
        if (fullmsg === undefined)
            fullmsg = "Assertion failed: expected exception " + ctor.name + ", no exception thrown";
        if (msg !== undefined)
            fullmsg += " - " + msg;
        throw new Error(fullmsg);
    };
}

if (typeof assertThrowsValue === 'undefined') {
    var assertThrowsValue = function assertThrowsValue(f, val, msg) {
        var fullmsg;
        try {
            f();
        } catch (exc) {
            if ((exc === val) === (val === val) && (val !== 0 || 1 / exc === 1 / val))
                return;
            fullmsg = "Assertion failed: expected exception " + val + ", got " + exc;
        }
        if (fullmsg === undefined)
            fullmsg = "Assertion failed: expected exception " + val + ", no exception thrown";
        if (msg !== undefined)
            fullmsg += " - " + msg;
        throw new Error(fullmsg);
    };
}

if (typeof assertDeepEq === 'undefined') {
    var assertDeepEq = (function(){
        var call = Function.prototype.call,
            Array_isArray = Array.isArray,
            Map_ = Map,
            Error_ = Error,
            Map_has = call.bind(Map.prototype.has),
            Map_get = call.bind(Map.prototype.get),
            Map_set = call.bind(Map.prototype.set),
            Object_toString = call.bind(Object.prototype.toString),
            Function_toString = call.bind(Function.prototype.toString),
            Object_getPrototypeOf = Object.getPrototypeOf,
            Object_hasOwnProperty = call.bind(Object.prototype.hasOwnProperty),
            Object_getOwnPropertyDescriptor = Object.getOwnPropertyDescriptor,
            Object_isExtensible = Object.isExtensible,
            Object_getOwnPropertyNames = Object.getOwnPropertyNames,
            uneval_ = uneval            

        // Return true iff ES6 Type(v) isn't Object.
        // Note that `typeof document.all === "undefined"`.
        function isPrimitive(v) {
            return (v === null ||
                    v === undefined ||
                    typeof v === "boolean" ||
                    typeof v === "number" ||
                    typeof v === "string" ||
                    typeof v === "symbol");
        }

        function assertSameValue(a, b, msg) {
            try {
                assertEq(a, b);
            } catch (exc) {
                throw Error_(exc.message + (msg ? " " + msg : ""));
            }
        }

        function assertSameClass(a, b, msg) {
            var ac = Object_toString(a), bc = Object_toString(b);
            assertSameValue(ac, bc, msg);
            switch (ac) {
            case "[object Function]":
                assertSameValue(Function_toString(a), Function_toString(b), msg);
            }
        }

        function at(prevmsg, segment) {
            return prevmsg ? prevmsg + segment : "at _" + segment;
        }

        // Assert that the arguments a and b are thoroughly structurally equivalent.
        //
        // For the sake of speed, we cut a corner:
        //        var x = {}, y = {}, ax = [x];
        //        assertDeepEq([ax, x], [ax, y]);  // passes (?!)
        //
        // Technically this should fail, since the two object graphs are different.
        // (The graph of [ax, y] contains one more object than the graph of [ax, x].)
        //
        // To get technically correct behavior, pass {strictEquivalence: true}.
        // This is slower because we have to walk the entire graph, and Object.prototype
        // is big.
        //
        return function assertDeepEq(a, b, options) {
            var strictEquivalence = options ? options.strictEquivalence : false;

            function assertSameProto(a, b, msg) {
                check(Object_getPrototypeOf(a), Object_getPrototypeOf(b), at(msg, ".__proto__"));
            }

            function failPropList(na, nb, msg) {
                throw Error_("got own properties " + uneval_(na) + ", expected " + uneval_(nb) +
                             (msg ? " " + msg : ""));
            }

            function assertSameProps(a, b, msg) {
                var na = Object_getOwnPropertyNames(a),
                    nb = Object_getOwnPropertyNames(b);
                if (na.length !== nb.length)
                    failPropList(na, nb, msg);

                // Ignore differences in whether Array elements are stored densely.
                if (Array_isArray(a)) {
                    na.sort();
                    nb.sort();
                }

                for (var i = 0; i < na.length; i++) {
                    var name = na[i];
                    if (name !== nb[i])
                        failPropList(na, nb, msg);
                    var da = Object_getOwnPropertyDescriptor(a, name),
                        db = Object_getOwnPropertyDescriptor(b, name);
                    var pmsg = at(msg, /^[_$A-Za-z0-9]+$/.test(name)
                                       ? /0|[1-9][0-9]*/.test(name) ? "[" + name + "]" : "." + name
                                       : "[" + uneval_(name) + "]");
                    assertSameValue(da.configurable, db.configurable, at(pmsg, ".[[Configurable]]"));
                    assertSameValue(da.enumerable, db.enumerable, at(pmsg, ".[[Enumerable]]"));
                    if (Object_hasOwnProperty(da, "value")) {
                        if (!Object_hasOwnProperty(db, "value"))
                            throw Error_("got data property, expected accessor property" + pmsg);
                        check(da.value, db.value, pmsg);
                    } else {
                        if (Object_hasOwnProperty(db, "value"))
                            throw Error_("got accessor property, expected data property" + pmsg);
                        check(da.get, db.get, at(pmsg, ".[[Get]]"));
                        check(da.set, db.set, at(pmsg, ".[[Set]]"));
                    }
                }
            };

            var ab = new Map_();
            var bpath = new Map_();

            function check(a, b, path) {
                if (typeof a === "symbol") {
                    // Symbols are primitives, but they have identity.
                    // Symbol("x") !== Symbol("x") but
                    // assertDeepEq(Symbol("x"), Symbol("x")) should pass.
                    if (typeof b !== "symbol") {
                        throw Error_("got " + uneval_(a) + ", expected " + uneval_(b) + " " + path);
                    } else if (uneval_(a) !== uneval_(b)) {
                        // We lamely use uneval_ to distinguish well-known symbols
                        // from user-created symbols. The standard doesn't offer
                        // a convenient way to do it.
                        throw Error_("got " + uneval_(a) + ", expected " + uneval_(b) + " " + path);
                    } else if (Map_has(ab, a)) {
                        assertSameValue(Map_get(ab, a), b, path);
                    } else if (Map_has(bpath, b)) {
                        var bPrevPath = Map_get(bpath, b) || "_";
                        throw Error_("got distinct symbols " + at(path, "") + " and " +
                                     at(bPrevPath, "") + ", expected the same symbol both places");
                    } else {
                        Map_set(ab, a, b);
                        Map_set(bpath, b, path);
                    }
                } else if (isPrimitive(a)) {
                    assertSameValue(a, b, path);
                } else if (isPrimitive(b)) {
                    throw Error_("got " + Object_toString(a) + ", expected " + uneval_(b) + " " + path);
                } else if (Map_has(ab, a)) {
                    assertSameValue(Map_get(ab, a), b, path);
                } else if (Map_has(bpath, b)) {
                    var bPrevPath = Map_get(bpath, b) || "_";
                    throw Error_("got distinct objects " + at(path, "") + " and " + at(bPrevPath, "") +
                                 ", expected the same object both places");
                } else {
                    Map_set(ab, a, b);
                    Map_set(bpath, b, path);
                    if (a !== b || strictEquivalence) {
                        assertSameClass(a, b, path);
                        assertSameProto(a, b, path);
                        assertSameProps(a, b, path);
                        assertSameValue(Object_isExtensible(a),
                                        Object_isExtensible(b),
                                        at(path, ".[[Extensible]]"));
                    }
                }
            }

            check(a, b, "");
        };
    })();
}

// Checks that |a_orig| and |b_orig| are:
//   1. Both instances of |type|, and
//   2. Are structurally equivalent (as dictated by the structure of |type|).
function assertTypedEqual(type, a_orig, b_orig) {
  try {
    recur(type, a_orig, b_orig);
  } catch (e) {
    print("failure during "+
          "assertTypedEqual("+type.toSource()+", "+a_orig.toSource()+", "+b_orig.toSource()+")");
    throw e;
  }

  function recur(type, a, b) {
    if (type instanceof ArrayType) {
      assertEq(a.length, type.length);
      assertEq(b.length, type.length);
      for (var i = 0; i < type.length; i++)
        recur(type.elementType, a[i], b[i]);
      } else if (type instanceof StructType) {
        var fieldNames = Object.getOwnPropertyNames(type.fieldTypes);
        for (var i = 0; i < fieldNames.length; i++) {
          var fieldName = fieldNames[i];
          recur(type.fieldTypes[fieldName], a[fieldName], b[fieldName]);
        }
      } else {
        assertEq(a, b);
      }
  }
}

/* -*- indent-tabs-mode: nil; js-indent-level: 4 -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// NOTE: This only turns on 1.6 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js.
if (typeof version != 'undefined')
{
  version(160);
}


/* -*- indent-tabs-mode: nil; js-indent-level: 4 -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// NOTE: This only turns on 1.8.0 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js.
if (typeof version != 'undefined')
{
  version(180);
}


/* -*- indent-tabs-mode: nil; js-indent-level: 4 -*- */
/*
 * Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/licenses/publicdomain/
 */

// Note, copied from elsewhere
if (typeof assertThrowsInstanceOf === 'undefined') {
    var assertThrowsInstanceOf = function assertThrowsInstanceOf(f, ctor, msg) {
        var fullmsg;
        try {
            f();
        } catch (exc) {
            if (exc instanceof ctor)
                return;
            fullmsg = "Assertion failed: expected exception " + ctor.name + ", got " + exc;
        }
        if (fullmsg === undefined)
            fullmsg = "Assertion failed: expected exception " + ctor.name + ", no exception thrown";
        if (msg !== undefined)
            fullmsg += " - " + msg;
        throw new Error(fullmsg);
    };
}

// NOTE: This only turns on 1.8.5 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js (which only turns on 1.8, the most
//       the browser supports).
if (typeof version != 'undefined')
{
  version(185);
}


// various combinations of identifiers and destructuring patterns:
function makePatternCombinations(id, destr)
{
    return [
      [ id(1)                                            ],
      [ id(1),    id(2)                                  ],
      [ id(1),    id(2),    id(3)                        ],
      [ id(1),    id(2),    id(3),    id(4)              ],
      [ id(1),    id(2),    id(3),    id(4),    id(5)    ],

      [ destr(1)                                         ],
      [ destr(1), destr(2)                               ],
      [ destr(1), destr(2), destr(3)                     ],
      [ destr(1), destr(2), destr(3), destr(4)           ],
      [ destr(1), destr(2), destr(3), destr(4), destr(5) ],

      [ destr(1), id(2)                                  ],

      [ destr(1), id(2),    id(3)                        ],
      [ destr(1), id(2),    id(3),    id(4)              ],
      [ destr(1), id(2),    id(3),    id(4),    id(5)    ],
      [ destr(1), id(2),    id(3),    id(4),    destr(5) ],
      [ destr(1), id(2),    id(3),    destr(4)           ],
      [ destr(1), id(2),    id(3),    destr(4), id(5)    ],
      [ destr(1), id(2),    id(3),    destr(4), destr(5) ],

      [ destr(1), id(2),    destr(3)                     ],
      [ destr(1), id(2),    destr(3), id(4)              ],
      [ destr(1), id(2),    destr(3), id(4),    id(5)    ],
      [ destr(1), id(2),    destr(3), id(4),    destr(5) ],
      [ destr(1), id(2),    destr(3), destr(4)           ],
      [ destr(1), id(2),    destr(3), destr(4), id(5)    ],
      [ destr(1), id(2),    destr(3), destr(4), destr(5) ],

      [ id(1),    destr(2)                               ],

      [ id(1),    destr(2), id(3)                        ],
      [ id(1),    destr(2), id(3),    id(4)              ],
      [ id(1),    destr(2), id(3),    id(4),    id(5)    ],
      [ id(1),    destr(2), id(3),    id(4),    destr(5) ],
      [ id(1),    destr(2), id(3),    destr(4)           ],
      [ id(1),    destr(2), id(3),    destr(4), id(5)    ],
      [ id(1),    destr(2), id(3),    destr(4), destr(5) ],

      [ id(1),    destr(2), destr(3)                     ],
      [ id(1),    destr(2), destr(3), id(4)              ],
      [ id(1),    destr(2), destr(3), id(4),    id(5)    ],
      [ id(1),    destr(2), destr(3), id(4),    destr(5) ],
      [ id(1),    destr(2), destr(3), destr(4)           ],
      [ id(1),    destr(2), destr(3), destr(4), id(5)    ],
      [ id(1),    destr(2), destr(3), destr(4), destr(5) ]
    ];
}

function runtest(main) {
    try {
        main();
        if (typeof reportCompare === 'function')
            reportCompare(true, true);
    } catch (exc) {
        print(exc.stack);
        throw exc;
    }
}

/* -*- indent-tabs-mode: nil; js-indent-level: 4 -*- */
/*
 * Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/licenses/publicdomain/
 */


// The Worker constructor can take a relative URL, but different test runners
// run in different enough environments that it doesn't all just automatically
// work. For the shell, we use just a filename; for the browser, see browser.js.
var workerDir = '';

// explicitly turn on js185
// XXX: The browser currently only supports up to version 1.8
if (typeof version != 'undefined')
{
  version(185);
}


var BUGNUMBER;
var summary;

function runDSTOffsetCachingTestsFraction(part, parts)
{
  BUGNUMBER = 563938;
  summary = 'Cache DST offsets to improve SunSpider score';

  print(BUGNUMBER + ": " + summary);

  var MAX_UNIX_TIMET = 2145859200;
  var RANGE_EXPANSION_AMOUNT = 30 * 24 * 60 * 60;

  /**
   * Computes the time zone offset in minutes at the given timestamp.
   */
  function tzOffsetFromUnixTimestamp(timestamp)
  {
    var d = new Date(NaN);
    d.setTime(timestamp); // local slot = NaN, UTC slot = timestamp
    return d.getTimezoneOffset(); // get UTC, calculate local => diff in minutes
  }

  /**
   * Clear the DST offset cache, leaving it initialized to include a timestamp
   * completely unlike the provided one (i.e. one very, very far away in time
   * from it).  Thus an immediately following lookup for the provided timestamp
   * will cache-miss and compute a clean value.
   */
  function clearDSTOffsetCache(undesiredTimestamp)
  {
    var opposite = (undesiredTimestamp + MAX_UNIX_TIMET / 2) % MAX_UNIX_TIMET;

    // Generic purge to known, but not necessarily desired, state
    tzOffsetFromUnixTimestamp(0);
    tzOffsetFromUnixTimestamp(MAX_UNIX_TIMET);

    // Purge to desired state.  Cycle 2x in case opposite or undesiredTimestamp
    // is close to 0 or MAX_UNIX_TIMET.
    tzOffsetFromUnixTimestamp(opposite);
    tzOffsetFromUnixTimestamp(undesiredTimestamp);
    tzOffsetFromUnixTimestamp(opposite);
    tzOffsetFromUnixTimestamp(undesiredTimestamp);
  }

  function computeCanonicalTZOffset(timestamp)
  {
    clearDSTOffsetCache(timestamp);
    return tzOffsetFromUnixTimestamp(timestamp);
  }

  var TEST_TIMESTAMPS_SECONDS =
    [
     // Special-ish timestamps
     0,
     RANGE_EXPANSION_AMOUNT,
     MAX_UNIX_TIMET,
    ];

  var ONE_DAY = 24 * 60 * 60;
  var EIGHTY_THREE_HOURS = 83 * 60 * 60;
  var NINETY_EIGHT_HOURS = 98 * 60 * 60;
  function nextIncrement(i)
  {
    return i === EIGHTY_THREE_HOURS ? NINETY_EIGHT_HOURS : EIGHTY_THREE_HOURS;
  }

  // Now add a long sequence of non-special timestamps, from a fixed range, that
  // overlaps a DST change by "a bit" on each side.  67 days should be enough
  // displacement that we can occasionally exercise the implementation's
  // thirty-day expansion and the DST-offset-change logic.  Use two different
  // increments just to be safe and catch something a single increment might not.
  var DST_CHANGE_DATE = 1268553600; // March 14, 2010
  for (var t = DST_CHANGE_DATE - 67 * ONE_DAY,
           i = nextIncrement(NINETY_EIGHT_HOURS),
           end = DST_CHANGE_DATE + 67 * ONE_DAY;
       t < end;
       i = nextIncrement(i), t += i)
  {
    TEST_TIMESTAMPS_SECONDS.push(t);
  }

  var TEST_TIMESTAMPS =
    TEST_TIMESTAMPS_SECONDS.map(function(v) { return v * 1000; });

  /**************
   * BEGIN TEST *
   **************/

  // Compute the correct time zone offsets for all timestamps to be tested.
  var CORRECT_TZOFFSETS = TEST_TIMESTAMPS.map(computeCanonicalTZOffset);

  // Intentionally and knowingly invoking every single logic path in the cache
  // isn't easy for a human to get right (and know he's gotten it right), so
  // let's do it the easy way: exhaustively try all possible four-date sequences
  // selecting from our array of possible timestamps.

  var sz = TEST_TIMESTAMPS.length;
  var start = Math.floor((part - 1) / parts * sz);
  var end = Math.floor(part / parts * sz);

  print("Exhaustively testing timestamps " +
        "[" + start + ", " + end + ") of " + sz + "...");

  try
  {
    for (var i = start; i < end; i++)
    {
      print("Testing timestamp " + i + "...");

      var t1 = TEST_TIMESTAMPS[i];
      for (var j = 0; j < sz; j++)
      {
        var t2 = TEST_TIMESTAMPS[j];
        for (var k = 0; k < sz; k++)
        {
          var t3 = TEST_TIMESTAMPS[k];
          for (var w = 0; w < sz; w++)
          {
            var t4 = TEST_TIMESTAMPS[w];

            clearDSTOffsetCache(t1);

            var tzo1 = tzOffsetFromUnixTimestamp(t1);
            var tzo2 = tzOffsetFromUnixTimestamp(t2);
            var tzo3 = tzOffsetFromUnixTimestamp(t3);
            var tzo4 = tzOffsetFromUnixTimestamp(t4);

            assertEq(tzo1, CORRECT_TZOFFSETS[i]);
            assertEq(tzo2, CORRECT_TZOFFSETS[j]);
            assertEq(tzo3, CORRECT_TZOFFSETS[k]);
            assertEq(tzo4, CORRECT_TZOFFSETS[w]);
          }
        }
      }
    }
  }
  catch (e)
  {
    assertEq(true, false,
             "Error when testing with timestamps " +
             i + ", " + j + ", " + k + ", " + w +
             " (" + t1 + ", " + t2 + ", " + t3 + ", " + t4 + ")!");
  }

  reportCompare(true, true);
  print("All tests passed!");
}

/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*- */
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
 * Date: 07 February 2001
 *
 * Functionality common to Array testing -
 */
//-----------------------------------------------------------------------------


var CHAR_LBRACKET = '[';
var CHAR_RBRACKET = ']';
var CHAR_QT_DBL = '"';
var CHAR_QT = "'";
var CHAR_NL = '\n';
var CHAR_COMMA = ',';
var CHAR_SPACE = ' ';
var TYPE_STRING = typeof 'abc';


/*
 * If available, arr.toSource() gives more detail than arr.toString()
 *
 * var arr = Array(1,2,'3');
 *
 * arr.toSource()
 * [1, 2, "3"]
 *
 * arr.toString()
 * 1,2,3
 *
 * But toSource() doesn't exist in Rhino, so use our own imitation, below -
 *
 */
function formatArray(arr)
{
  try
  {
    return arr.toSource();
  }
  catch(e)
  {
    return toSource(arr);
  }
}



/*
 * Imitate SpiderMonkey's arr.toSource() method:
 *
 * a) Double-quote each array element that is of string type
 * b) Represent |undefined| and |null| by empty strings
 * c) Delimit elements by a comma + single space
 * d) Do not add delimiter at the end UNLESS the last element is |undefined|
 * e) Add square brackets to the beginning and end of the string
 */
function toSource(arr)
{
  var delim = CHAR_COMMA + CHAR_SPACE;
  var elt = '';
  var ret = '';
  var len = arr.length;

  for (i=0; i<len; i++)
  {
    elt = arr[i];

    switch(true)
    {
    case (typeof elt === TYPE_STRING) :
      ret += doubleQuote(elt);
      break;

    case (elt === undefined || elt === null) :
      break; // add nothing but the delimiter, below -

    default:
      ret += elt.toString();
    }

    if ((i < len-1) || (elt === undefined))
      ret += delim;
  }

  return  CHAR_LBRACKET + ret + CHAR_RBRACKET;
}


function doubleQuote(text)
{
  return CHAR_QT_DBL + text + CHAR_QT_DBL;
}


function singleQuote(text)
{
  return CHAR_QT + text + CHAR_QT;
}


/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*- */

/*
 * Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/licenses/publicdomain/
 */


/*
 * Return true if both of these return true:
 * - LENIENT_PRED applied to CODE
 * - STRICT_PRED applied to CODE with a use strict directive added to the front
 *
 * Run STRICT_PRED first, for testing code that affects the global environment
 * in loose mode, but fails in strict mode.
 */
function testLenientAndStrict(code, lenient_pred, strict_pred) {
  return (strict_pred("'use strict'; " + code) && 
          lenient_pred(code));
}

/*
 * completesNormally(CODE) returns true if evaluating CODE (as eval
 * code) completes normally (rather than throwing an exception).
 */
function completesNormally(code) {
  try {
    eval(code);
    return true;
  } catch (exception) {
    return false;
  }
}

/*
 * returns(VALUE)(CODE) returns true if evaluating CODE (as eval code)
 * completes normally (rather than throwing an exception), yielding a value
 * strictly equal to VALUE.
 */
function returns(value) {
  return function(code) {
    try {
      return eval(code) === value;
    } catch (exception) {
      return false;
    }
  }
}

/*
 * returnsCopyOf(VALUE)(CODE) returns true if evaluating CODE (as eval code)
 * completes normally (rather than throwing an exception), yielding a value
 * that is deepEqual to VALUE.
 */
function returnsCopyOf(value) {
  return function(code) {
    try {
      return deepEqual(eval(code), value);
    } catch (exception) {
      return false;
    }
  }
}

/*
 * raisesException(EXCEPTION)(CODE) returns true if evaluating CODE (as
 * eval code) throws an exception object that is an instance of EXCEPTION,
 * and returns false if it throws any other error or evaluates
 * successfully. For example: raises(TypeError)("0()") == true.
 */
function raisesException(exception) {
  return function (code) {
    try {
      eval(code);
      return false;
    } catch (actual) {
      return actual instanceof exception;
    }
  };
};

/*
 * parsesSuccessfully(CODE) returns true if CODE parses as function
 * code without an error.
 */
function parsesSuccessfully(code) {
  try {
    Function(code);
    return true;
  } catch (exception) {
    return false;
  }
};

/*
 * parseRaisesException(EXCEPTION)(CODE) returns true if parsing CODE
 * as function code raises EXCEPTION.
 */
function parseRaisesException(exception) {
  return function (code) {
    try {
      Function(code);
      return false;
    } catch (actual) {
      return actual instanceof exception;
    }
  };
};

/*
 * Return the result of applying uneval to VAL, and replacing all runs
 * of whitespace with a single horizontal space (poor man's
 * tokenization).
 */
function clean_uneval(val) {
  return uneval(val).replace(/\s+/g, ' ');
}

/*
 * Return true if A is equal to B, where equality on arrays and objects
 * means that they have the same set of enumerable properties, the values
 * of each property are deep_equal, and their 'length' properties are
 * equal. Equality on other types is ==.
 */
function deepEqual(a, b) {
    if (typeof a != typeof b)
        return false;

    if (typeof a == 'object') {
        var props = {};
        // For every property of a, does b have that property with an equal value?
        for (var prop in a) {
            if (!deepEqual(a[prop], b[prop]))
                return false;
            props[prop] = true;
        }
        // Are all of b's properties present on a?
        for (var prop in b)
            if (!props[prop])
                return false;
        // length isn't enumerable, but we want to check it, too.
        return a.length == b.length;
    }

    if (a === b) {
        // Distinguish 0 from -0, even though they are ===.
        return a !== 0 || 1/a === 1/b;
    }

    // Treat NaNs as equal, even though NaN !== NaN.
    // NaNs are the only non-reflexive values, i.e., if a !== a, then a is a NaN.
    // isNaN is broken: it converts its argument to number, so isNaN("foo") => true
    return a !== a && b !== b;
}

gTestsubsuite='JSON';

function testJSON(str, expectSyntaxError)
{
  // Leading and trailing whitespace never affect parsing, so test the string
  // multiple times with and without whitespace around it as it's easy and can
  // potentially detect bugs.

  // Try the provided string
  try
  {
    JSON.parse(str);
    reportCompare(false, expectSyntaxError,
                  "string <" + str + "> " +
                  "should" + (expectSyntaxError ? "n't" : "") + " " +
                  "have parsed as JSON");
  }
  catch (e)
  {
    if (!(e instanceof SyntaxError))
    {
      reportCompare(true, false,
                    "parsing string <" + str + "> threw a non-SyntaxError " +
                    "exception: " + e);
    }
    else
    {
      reportCompare(true, expectSyntaxError,
                    "string <" + str + "> " +
                    "should" + (expectSyntaxError ? "n't" : "") + " " +
                    "have parsed as JSON, exception: " + e);
    }
  }

  // Now try the provided string with trailing whitespace
  try
  {
    JSON.parse(str + " ");
    reportCompare(false, expectSyntaxError,
                  "string <" + str + " > " +
                  "should" + (expectSyntaxError ? "n't" : "") + " " +
                  "have parsed as JSON");
  }
  catch (e)
  {
    if (!(e instanceof SyntaxError))
    {
      reportCompare(true, false,
                    "parsing string <" + str + " > threw a non-SyntaxError " +
                    "exception: " + e);
    }
    else
    {
      reportCompare(true, expectSyntaxError,
                    "string <" + str + " > " +
                    "should" + (expectSyntaxError ? "n't" : "") + " " +
                    "have parsed as JSON, exception: " + e);
    }
  }

  // Now try the provided string with leading whitespace
  try
  {
    JSON.parse(" " + str);
    reportCompare(false, expectSyntaxError,
                  "string < " + str + "> " +
                  "should" + (expectSyntaxError ? "n't" : "") + " " +
                  "have parsed as JSON");
  }
  catch (e)
  {
    if (!(e instanceof SyntaxError))
    {
      reportCompare(true, false,
                    "parsing string < " + str + "> threw a non-SyntaxError " +
                    "exception: " + e);
    }
    else
    {
      reportCompare(true, expectSyntaxError,
                    "string < " + str + "> " +
                    "should" + (expectSyntaxError ? "n't" : "") + " " +
                    "have parsed as JSON, exception: " + e);
    }
  }

  // Now try the provided string with whitespace surrounding it
  try
  {
    JSON.parse(" " + str + " ");
    reportCompare(false, expectSyntaxError,
                  "string < " + str + " > " +
                  "should" + (expectSyntaxError ? "n't" : "") + " " +
                  "have parsed as JSON");
  }
  catch (e)
  {
    if (!(e instanceof SyntaxError))
    {
      reportCompare(true, false,
                    "parsing string < " + str + " > threw a non-SyntaxError " +
                    "exception: " + e);
    }
    else
    {
      reportCompare(true, expectSyntaxError,
                    "string < " + str + " > " +
                    "should" + (expectSyntaxError ? "n't" : "") + " " +
                    "have parsed as JSON, exception: " + e);
    }
  }
}

function makeExpectedMatch(arr, index, input) {
    var expectedMatch = {
        index: index,
        input: input,
        length: arr.length,
    };

    for (var i = 0; i < arr.length; ++i)
        expectedMatch[i] = arr[i];

    return expectedMatch;
}

function checkRegExpMatch(actual, expected) {
    assertEq(actual.length, expected.length);
    for (var i = 0; i < actual.length; ++i)
        assertEq(actual[i], expected[i]);

    assertEq(actual.index, expected.index);
    assertEq(actual.input, expected.input);
}

/* -*- indent-tabs-mode: nil; js-indent-level: 4 -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// NOTE: This only turns on 1.7 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js.
if (typeof version != 'undefined')
{
  version(170);
}


/* -*- indent-tabs-mode: nil; js-indent-level: 4 -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// NOTE: This only turns on 1.8.1 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js (which only turns on 1.8, the most
//       the browser supports).
if (typeof version != 'undefined')
{
  version(181);
}


/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*- */

/*
 * Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/licenses/publicdomain/
 */


/*
 * Return true if both of these return true:
 * - LENIENT_PRED applied to CODE
 * - STRICT_PRED applied to CODE with a use strict directive added to the front
 *
 * Run STRICT_PRED first, for testing code that affects the global environment
 * in loose mode, but fails in strict mode.
 */
function testLenientAndStrict(code, lenient_pred, strict_pred) {
  return (strict_pred("'use strict'; " + code) && 
          lenient_pred(code));
}

/*
 * completesNormally(CODE) returns true if evaluating CODE (as eval
 * code) completes normally (rather than throwing an exception).
 */
function completesNormally(code) {
  try {
    eval(code);
    return true;
  } catch (exception) {
    return false;
  }
}

/*
 * raisesException(EXCEPTION)(CODE) returns true if evaluating CODE (as eval
 * code) throws an exception object whose prototype is
 * EXCEPTION.prototype, and returns false if it throws any other error
 * or evaluates successfully. For example: raises(TypeError)("0()") ==
 * true.
 */
function raisesException(exception) {
  return function (code) {
    try {
      eval(code);
      return false;
    } catch (actual) {
      return exception.prototype.isPrototypeOf(actual);
    }
  };
};

/*
 * parsesSuccessfully(CODE) returns true if CODE parses as function
 * code without an error.
 */
function parsesSuccessfully(code) {
  try {
    Function(code);
    return true;
  } catch (exception) {
    return false;
  }
};

/*
 * parseRaisesException(EXCEPTION)(CODE) returns true if parsing CODE
 * as function code raises EXCEPTION.
 */
function parseRaisesException(exception) {
  return function (code) {
    try {
      Function(code);
      return false;
    } catch (actual) {
      return exception.prototype.isPrototypeOf(actual);
    }
  };
};

/*
 * Return the result of applying uneval to VAL, and replacing all runs
 * of whitespace with a single horizontal space (poor man's
 * tokenization).
 */
function clean_uneval(val) {
  return uneval(val).replace(/\s+/g, ' ');
}

/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*- */
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/* all files in this dir need version(120) called before they are *loaded* */


version(120);

// NOTE: This only turns on 1.8.5 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js (which only turns on 1.8, the most
//       the browser supports).
if (typeof version != 'undefined')
  version(185);

function assertThrownErrorContains(thunk, substr) {
    try {
        thunk();
    } catch (e) {
        if (e.message.indexOf(substr) !== -1)
            return;
        throw new Error("Expected error containing " + substr + ", got " + e);
    }
    throw new Error("Expected error containing " + substr + ", no exception thrown");
}

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */


if (typeof assertThrowsInstanceOf === 'undefined') {
    var assertThrowsInstanceOf = function assertThrowsInstanceOf(f, ctor, msg) {
        var fullmsg;
        try {
            f();
        } catch (exc) {
            if (exc instanceof ctor)
                return;
            fullmsg = "Assertion failed: expected exception " + ctor.name + ", got " + exc;
        }
        if (fullmsg === undefined)
            fullmsg = "Assertion failed: expected exception " + ctor.name + ", no exception thrown";
        if (msg !== undefined)
            fullmsg += " - " + msg;
        throw new Error(fullmsg);
    };
}

if (typeof assertThrowsValue === 'undefined') {
    var assertThrowsValue = function assertThrowsValue(f, val, msg) {
        var fullmsg;
        try {
            f();
        } catch (exc) {
            if ((exc === val) === (val === val) && (val !== 0 || 1 / exc === 1 / val))
                return;
            fullmsg = "Assertion failed: expected exception " + val + ", got " + exc;
        }
        if (fullmsg === undefined)
            fullmsg = "Assertion failed: expected exception " + val + ", no exception thrown";
        if (msg !== undefined)
            fullmsg += " - " + msg;
        throw new Error(fullmsg);
    };
}

if (typeof assertDeepEq === 'undefined') {
    var assertDeepEq = (function(){
        var call = Function.prototype.call,
            Array_isArray = Array.isArray,
            Map_ = Map,
            Error_ = Error,
            Symbol_ = Symbol,
            Map_has = call.bind(Map.prototype.has),
            Map_get = call.bind(Map.prototype.get),
            Map_set = call.bind(Map.prototype.set),
            Object_toString = call.bind(Object.prototype.toString),
            Function_toString = call.bind(Function.prototype.toString),
            Object_getPrototypeOf = Object.getPrototypeOf,
            Object_hasOwnProperty = call.bind(Object.prototype.hasOwnProperty),
            Object_getOwnPropertyDescriptor = Object.getOwnPropertyDescriptor,
            Object_isExtensible = Object.isExtensible,
            Object_getOwnPropertyNames = Object.getOwnPropertyNames,
            uneval_ = uneval;

        // Return true iff ES6 Type(v) isn't Object.
        // Note that `typeof document.all === "undefined"`.
        function isPrimitive(v) {
            return (v === null ||
                    v === undefined ||
                    typeof v === "boolean" ||
                    typeof v === "number" ||
                    typeof v === "string" ||
                    typeof v === "symbol");
        }

        function assertSameValue(a, b, msg) {
            try {
                assertEq(a, b);
            } catch (exc) {
                throw Error_(exc.message + (msg ? " " + msg : ""));
            }
        }

        function assertSameClass(a, b, msg) {
            var ac = Object_toString(a), bc = Object_toString(b);
            assertSameValue(ac, bc, msg);
            switch (ac) {
            case "[object Function]":
                assertSameValue(Function_toString(a), Function_toString(b), msg);
            }
        }

        function at(prevmsg, segment) {
            return prevmsg ? prevmsg + segment : "at _" + segment;
        }

        // Assert that the arguments a and b are thoroughly structurally equivalent.
        //
        // For the sake of speed, we cut a corner:
        //        var x = {}, y = {}, ax = [x];
        //        assertDeepEq([ax, x], [ax, y]);  // passes (?!)
        //
        // Technically this should fail, since the two object graphs are different.
        // (The graph of [ax, y] contains one more object than the graph of [ax, x].)
        //
        // To get technically correct behavior, pass {strictEquivalence: true}.
        // This is slower because we have to walk the entire graph, and Object.prototype
        // is big.
        //
        return function assertDeepEq(a, b, options) {
            var strictEquivalence = options ? options.strictEquivalence : false;

            function assertSameProto(a, b, msg) {
                check(Object_getPrototypeOf(a), Object_getPrototypeOf(b), at(msg, ".__proto__"));
            }

            function failPropList(na, nb, msg) {
                throw Error_("got own properties " + uneval_(na) + ", expected " + uneval_(nb) +
                             (msg ? " " + msg : ""));
            }

            function assertSameProps(a, b, msg) {
                var na = Object_getOwnPropertyNames(a),
                    nb = Object_getOwnPropertyNames(b);
                if (na.length !== nb.length)
                    failPropList(na, nb, msg);

                // Ignore differences in whether Array elements are stored densely.
                if (Array_isArray(a)) {
                    na.sort();
                    nb.sort();
                }

                for (var i = 0; i < na.length; i++) {
                    var name = na[i];
                    if (name !== nb[i])
                        failPropList(na, nb, msg);
                    var da = Object_getOwnPropertyDescriptor(a, name),
                        db = Object_getOwnPropertyDescriptor(b, name);
                    var pmsg = at(msg, /^[_$A-Za-z0-9]+$/.test(name)
                                       ? /0|[1-9][0-9]*/.test(name) ? "[" + name + "]" : "." + name
                                       : "[" + uneval_(name) + "]");
                    assertSameValue(da.configurable, db.configurable, at(pmsg, ".[[Configurable]]"));
                    assertSameValue(da.enumerable, db.enumerable, at(pmsg, ".[[Enumerable]]"));
                    if (Object_hasOwnProperty(da, "value")) {
                        if (!Object_hasOwnProperty(db, "value"))
                            throw Error_("got data property, expected accessor property" + pmsg);
                        check(da.value, db.value, pmsg);
                    } else {
                        if (Object_hasOwnProperty(db, "value"))
                            throw Error_("got accessor property, expected data property" + pmsg);
                        check(da.get, db.get, at(pmsg, ".[[Get]]"));
                        check(da.set, db.set, at(pmsg, ".[[Set]]"));
                    }
                }
            };

            var ab = new Map_();
            var bpath = new Map_();

            function check(a, b, path) {
                if (typeof a === "symbol") {
                    // Symbols are primitives, but they have identity.
                    // Symbol("x") !== Symbol("x") but
                    // assertDeepEq(Symbol("x"), Symbol("x")) should pass.
                    if (typeof b !== "symbol") {
                        throw Error_("got " + uneval_(a) + ", expected " + uneval_(b) + " " + path);
                    } else if (uneval_(a) !== uneval_(b)) {
                        // We lamely use uneval_ to distinguish well-known symbols
                        // from user-created symbols. The standard doesn't offer
                        // a convenient way to do it.
                        throw Error_("got " + uneval_(a) + ", expected " + uneval_(b) + " " + path);
                    } else if (Map_has(ab, a)) {
                        assertSameValue(Map_get(ab, a), b, path);
                    } else if (Map_has(bpath, b)) {
                        var bPrevPath = Map_get(bpath, b) || "_";
                        throw Error_("got distinct symbols " + at(path, "") + " and " +
                                     at(bPrevPath, "") + ", expected the same symbol both places");
                    } else {
                        Map_set(ab, a, b);
                        Map_set(bpath, b, path);
                    }
                } else if (isPrimitive(a)) {
                    assertSameValue(a, b, path);
                } else if (isPrimitive(b)) {
                    throw Error_("got " + Object_toString(a) + ", expected " + uneval_(b) + " " + path);
                } else if (Map_has(ab, a)) {
                    assertSameValue(Map_get(ab, a), b, path);
                } else if (Map_has(bpath, b)) {
                    var bPrevPath = Map_get(bpath, b) || "_";
                    throw Error_("got distinct objects " + at(path, "") + " and " + at(bPrevPath, "") +
                                 ", expected the same object both places");
                } else {
                    Map_set(ab, a, b);
                    Map_set(bpath, b, path);
                    if (a !== b || strictEquivalence) {
                        assertSameClass(a, b, path);
                        assertSameProto(a, b, path);
                        assertSameProps(a, b, path);
                        assertSameValue(Object_isExtensible(a),
                                        Object_isExtensible(b),
                                        at(path, ".[[Extensible]]"));
                    }
                }
            }

            check(a, b, "");
        };
    })();
}

if (typeof assertWarning === 'undefined') {
    function assertWarning(func, name) {
        enableLastWarning();
        func();
        var warning = getLastWarning();
        assertEq(warning !== null, true);
        assertEq(warning.name, name);
        disableLastWarning();
    }
}

// The nearest representable values to +1.0.

{
    var fail = function (msg) {
        var exc = new Error(msg);
        try {
            // Try to improve on exc.fileName and .lineNumber; leave exc.stack
            // alone. We skip two frames: fail() and its caller, an assertX()
            // function.
            var frames = exc.stack.trim().split("\n");
            if (frames.length > 2) {
                var m = /@([^@:]*):([0-9]+)$/.exec(frames[2]);
                if (m) {
                    exc.fileName = m[1];
                    exc.lineNumber = +m[2];
                }
            }
        } catch (ignore) { throw ignore;}
        throw exc;
    };

    var ENDIAN;  // 0 for little-endian, 1 for big-endian.

    // Return the difference between the IEEE 754 bit-patterns for a and b.
    //
    // This is meaningful when a and b are both finite and have the same
    // sign. Then the following hold:
    //
    //   * If a === b, then diff(a, b) === 0.
    //
    //   * If a !== b, then diff(a, b) === 1 + the number of representable values
    //                                         between a and b.
    //
    var f = new Float64Array([0, 0]);
    var u = new Uint32Array(f.buffer);
    var diff = function (a, b) {
        f[0] = a;
        f[1] = b;
        //print(u[1].toString(16) + u[0].toString(16) + " " + u[3].toString(16) + u[2].toString(16));
        return Math.abs((u[3-ENDIAN] - u[1-ENDIAN]) * 0x100000000 + u[2+ENDIAN] - u[0+ENDIAN]);
    };

    // Set ENDIAN to the platform's endianness.
    ENDIAN = 0;  // try little-endian first
    if (diff(2, 4) === 0x100000)  // exact wrong answer we'll get on a big-endian platform
        ENDIAN = 1;
    assertEq(diff(2,4), 0x10000000000000);
    assertEq(diff(0, Number.MIN_VALUE), 1);
    assertEq(diff(1, ONE_PLUS_EPSILON), 1);
    assertEq(diff(1, ONE_MINUS_EPSILON), 1);

    var assertNear = function assertNear(a, b, tolerance=1) {
        if (!Number.isFinite(b)) {
            fail("second argument to assertNear (expected value) must be a finite number");
        } else if (Number.isNaN(a)) {
            fail("got NaN, expected a number near " + b);
        } else if (!Number.isFinite(a)) {
            if (b * Math.sign(a) < Number.MAX_VALUE)
                fail("got " + a + ", expected a number near " + b);
        } else {
            // When the two arguments do not have the same sign bit, diff()
            // returns some huge number. So if b is positive or negative 0,
            // make target the zero that has the same sign bit as a.
            var target = b === 0 ? a * 0 : b;
            var err = diff(a, target);
            if (err > tolerance) {
                fail("got " + a + ", expected a number near " + b +
                     " (relative error: " + err + ")");
            }
        }
    };
}

// NOTE: This only turns on 1.8.5 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js (which only turns on 1.8, the most
//       the browser supports).
if (typeof version != 'undefined')
  version(185);

// List of a few values that are not objects.
var SOME_PRIMITIVE_VALUES = [
    undefined, null,
    false,
    -Infinity, -1.6e99, -1, -0, 0, Math.pow(2, -1074), 1, 4294967295,
    Number.MAX_SAFE_INTEGER, Number.MAX_SAFE_INTEGER + 1, 1.6e99, Infinity, NaN,
    "", "Phaedo",
    Symbol(), Symbol("iterator"), Symbol.for("iterator"), Symbol.iterator
];



// NOTE: This only turns on 1.8.5 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js (which only turns on 1.8, the most
//       the browser supports).
if (typeof version != 'undefined')
  version(185);

// Synthesize a constructor for a shared memory array from the
// constructor for unshared memory.  This has "good enough" fidelity
// for many uses.  In cases where it's not good enough, use the
// __isShared__ flags or call isSharedConstructor for local workarounds.

function sharedConstructor(constructor) {
    var c = function(...args) {
	if (!new.target)
	    throw new TypeError("Not callable");
	var array = new constructor(...args);
	var buffer = array.buffer;
	var offset = array.byteOffset;
	var length = array.length;
	var sharedBuffer = new SharedArrayBuffer(buffer.byteLength);
	var sharedArray = new constructor(sharedBuffer, offset, length);
	for ( var i=0 ; i < length ; i++ )
	    sharedArray[i] = array[i];
	assertEq(sharedArray.buffer, sharedBuffer);
	return sharedArray;
    };
    c.prototype = Object.create(constructor.prototype);
    c.__isShared__ = true;
    c.__baseConstructor__ = constructor;
    c.from = constructor.from;
    c.of = constructor.of;
    return c;
}

function isSharedConstructor(x) {
    return typeof x == "function" && x.__isShared__;
}

function isFloatingConstructor(c) {
    return c == Float32Array ||
	c == Float64Array ||
	(c.hasOwnProperty("__baseConstructor__") &&
	 isFloatingConstructor(c.__baseConstructor__));
}

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

function assertFalse(a) { assertEq(a, false) }
function assertTrue(a) { assertEq(a, true) }
function assertNotEq(found, not_expected) { assertFalse(found === expected) }
function assertIteratorResult(result, value, done) {
    assertDeepEq(result.value, value);
    assertEq(result.done, done);
}
function assertIteratorNext(iter, value) {
    assertIteratorResult(iter.next(), value, false);
}
function assertIteratorDone(iter, value) {
    assertIteratorResult(iter.next(), value, true);
}

function runNormalizeTest(test) {
  function codePointsToString(points) {
    return points.map(x => String.fromCodePoint(x)).join("");
  }
  function stringify(points) {
    return points.map(x => x.toString(16)).join();
  }

  var source = codePointsToString(test.source);
  var NFC = codePointsToString(test.NFC);
  var NFD = codePointsToString(test.NFD);
  var NFKC = codePointsToString(test.NFKC);
  var NFKD = codePointsToString(test.NFKD);
  var sourceStr = stringify(test.source);
  var nfcStr = stringify(test.NFC);
  var nfdStr = stringify(test.NFD);
  var nfkcStr = stringify(test.NFKC);
  var nfkdStr = stringify(test.NFKD);

  /* NFC */
  assertEq(source.normalize(), NFC, "NFC of " + sourceStr);
  assertEq(NFC.normalize(), NFC, "NFC of " + nfcStr);
  assertEq(NFD.normalize(), NFC, "NFC of " + nfdStr);
  assertEq(NFKC.normalize(), NFKC, "NFC of " + nfkcStr);
  assertEq(NFKD.normalize(), NFKC, "NFC of " + nfkdStr);

  assertEq(source.normalize(undefined), NFC, "NFC of " + sourceStr);
  assertEq(NFC.normalize(undefined), NFC, "NFC of " + nfcStr);
  assertEq(NFD.normalize(undefined), NFC, "NFC of " + nfdStr);
  assertEq(NFKC.normalize(undefined), NFKC, "NFC of " + nfkcStr);
  assertEq(NFKD.normalize(undefined), NFKC, "NFC of " + nfkdStr);

  assertEq(source.normalize("NFC"), NFC, "NFC of " + sourceStr);
  assertEq(NFC.normalize("NFC"), NFC, "NFC of " + nfcStr);
  assertEq(NFD.normalize("NFC"), NFC, "NFC of " + nfdStr);
  assertEq(NFKC.normalize("NFC"), NFKC, "NFC of " + nfkcStr);
  assertEq(NFKD.normalize("NFC"), NFKC, "NFC of " + nfkdStr);

  /* NFD */
  assertEq(source.normalize("NFD"), NFD, "NFD of " + sourceStr);
  assertEq(NFC.normalize("NFD"), NFD, "NFD of " + nfcStr);
  assertEq(NFD.normalize("NFD"), NFD, "NFD of " + nfdStr);
  assertEq(NFKC.normalize("NFD"), NFKD, "NFD of " + nfkcStr);
  assertEq(NFKD.normalize("NFD"), NFKD, "NFD of " + nfkdStr);

  /* NFKC */
  assertEq(source.normalize("NFKC"), NFKC, "NFKC of " + sourceStr);
  assertEq(NFC.normalize("NFKC"), NFKC, "NFKC of " + nfcStr);
  assertEq(NFD.normalize("NFKC"), NFKC, "NFKC of " + nfdStr);
  assertEq(NFKC.normalize("NFKC"), NFKC, "NFKC of " + nfkcStr);
  assertEq(NFKD.normalize("NFKC"), NFKC, "NFKC of " + nfkdStr);

  /* NFKD */
  assertEq(source.normalize("NFKD"), NFKD, "NFKD of " + sourceStr);
  assertEq(NFC.normalize("NFKD"), NFKD, "NFKD of " + nfcStr);
  assertEq(NFD.normalize("NFKD"), NFKD, "NFKD of " + nfdStr);
  assertEq(NFKC.normalize("NFKD"), NFKD, "NFKD of " + nfkcStr);
  assertEq(NFKD.normalize("NFKD"), NFKD, "NFKD of " + nfkdStr);
}

/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*- */
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
 * Date: 14 Mar 2001
 *
 * SUMMARY: Utility functions for testing objects -
 *
 * Suppose obj is an instance of a native type, e.g. Number.
 * Then obj.toString() invokes Number.prototype.toString().
 * We would also like to access Object.prototype.toString().
 *
 * The difference is this: suppose obj = new Number(7).
 * Invoking Number.prototype.toString() on this just returns 7.
 * Object.prototype.toString() on this returns '[object Number]'.
 *
 * The getJSType() function below will return '[object Number]' for us.
 * The getJSClass() function returns 'Number', the [[Class]] property of obj.
 * See ECMA-262 Edition 3,  13-Oct-1999,  Section 8.6.2 
 */
//-----------------------------------------------------------------------------


var cnNoObject = 'Unexpected Error!!! Parameter to this function must be an object';
var cnNoClass = 'Unexpected Error!!! Cannot find Class property';
var cnObjectToString = Object.prototype.toString;
var GLOBAL = 'global';

// checks that it's safe to call findType()
function getJSType(obj)
{
  if (isObject(obj))
    return findType(obj);
  return cnNoObject;
}


// checks that it's safe to call findType()
function getJSClass(obj)
{
  if (isObject(obj))
    return findClass(findType(obj));
  return cnNoObject;
}


function findType(obj)
{
  return cnObjectToString.apply(obj);
}


// given '[object Number]',  return 'Number'
function findClass(sType)
{
  var re =  /^\[.*\s+(\w+)\s*\]$/;
  var a = sType.match(re);
 
  if (a && a[1])
    return a[1];
  return cnNoClass;
}


function isObject(obj)
{
  return obj instanceof Object;
}


/* -*- tab-width: 2; indent-tabs-mode: nil; js-indent-level: 2 -*- */
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
 * Date: 07 February 2001
 *
 * Functionality common to RegExp testing -
 */
//-----------------------------------------------------------------------------


var MSG_PATTERN = '\nregexp = ';
var MSG_STRING = '\nstring = ';
var MSG_EXPECT = '\nExpect: ';
var MSG_ACTUAL = '\nActual: ';
var ERR_LENGTH = '\nERROR !!! match arrays have different lengths:';
var ERR_MATCH = '\nERROR !!! regexp failed to give expected match array:';
var ERR_NO_MATCH = '\nERROR !!! regexp FAILED to match anything !!!';
var ERR_UNEXP_MATCH = '\nERROR !!! regexp MATCHED when we expected it to fail !!!';
var CHAR_LBRACKET = '[';
var CHAR_RBRACKET = ']';
var CHAR_QT_DBL = '"';
var CHAR_QT = "'";
var CHAR_NL = '\n';
var CHAR_COMMA = ',';
var CHAR_SPACE = ' ';
var TYPE_STRING = typeof 'abc';



function testRegExp(statuses, patterns, strings, actualmatches, expectedmatches)
{
  var status = '';
  var pattern = new RegExp();
  var string = '';
  var actualmatch = new Array();
  var expectedmatch = new Array();
  var state = '';
  var lActual = -1;
  var lExpect = -1;


  for (var i=0; i != patterns.length; i++)
  {
    status = statuses[i];
    pattern = patterns[i];
    string = strings[i];
    actualmatch=actualmatches[i];
    expectedmatch=expectedmatches[i];
    state = getState(status, pattern, string);

    description = status;

    if(actualmatch)
    {
      actual = formatArray(actualmatch);
      if(expectedmatch)
      {
        // expectedmatch and actualmatch are arrays -
        lExpect = expectedmatch.length;
        lActual = actualmatch.length;

        var expected = formatArray(expectedmatch);

        if (lActual != lExpect)
        {
          reportCompare(lExpect, lActual,
                        state + ERR_LENGTH +
                        MSG_EXPECT + expected +
                        MSG_ACTUAL + actual +
                        CHAR_NL
	    );
          continue;
        }

        // OK, the arrays have same length -
        if (expected != actual)
        {
          reportCompare(expected, actual,
                        state + ERR_MATCH +
                        MSG_EXPECT + expected +
                        MSG_ACTUAL + actual +
                        CHAR_NL
	    );
        }
        else
        {
          reportCompare(expected, actual, state)
	    }

      }
      else //expectedmatch is null - that is, we did not expect a match -
      {
        expected = expectedmatch;
        reportCompare(expected, actual,
                      state + ERR_UNEXP_MATCH +
                      MSG_EXPECT + expectedmatch +
                      MSG_ACTUAL + actual +
                      CHAR_NL
	  );
      }

    }
    else // actualmatch is null
    {
      if (expectedmatch)
      {
        actual = actualmatch;
        reportCompare(expected, actual,
                      state + ERR_NO_MATCH +
                      MSG_EXPECT + expectedmatch +
                      MSG_ACTUAL + actualmatch +
                      CHAR_NL
	  );
      }
      else // we did not expect a match
      {
        // Being ultra-cautious. Presumably expectedmatch===actualmatch===null
        expected = expectedmatch;
        actual   = actualmatch;
        reportCompare (expectedmatch, actualmatch, state);
      }
    }
  }
}


function getState(status, pattern, string)
{
  /*
   * Escape \n's, etc. to make them LITERAL in the presentation string.
   * We don't have to worry about this in |pattern|; such escaping is
   * done automatically by pattern.toString(), invoked implicitly below.
   *
   * One would like to simply do: string = string.replace(/(\s)/g, '\$1').
   * However, the backreference $1 is not a literal string value,
   * so this method doesn't work.
   *
   * Also tried string = string.replace(/(\s)/g, escape('$1'));
   * but this just inserts the escape of the literal '$1', i.e. '%241'.
   */
  string = string.replace(/\n/g, '\\n');
  string = string.replace(/\r/g, '\\r');
  string = string.replace(/\t/g, '\\t');
  string = string.replace(/\v/g, '\\v');
  string = string.replace(/\f/g, '\\f');

  return (status + MSG_PATTERN + pattern + MSG_STRING + singleQuote(string));
}


/*
 * If available, arr.toSource() gives more detail than arr.toString()
 *
 * var arr = Array(1,2,'3');
 *
 * arr.toSource()
 * [1, 2, "3"]
 *
 * arr.toString()
 * 1,2,3
 *
 * But toSource() doesn't exist in Rhino, so use our own imitation, below -
 *
 */
function formatArray(arr)
{
  try
  {
    return arr.toSource();
  }
  catch(e)
  {
    return toSource(arr);
  }
}


/*
 * Imitate SpiderMonkey's arr.toSource() method:
 *
 * a) Double-quote each array element that is of string type
 * b) Represent |undefined| and |null| by empty strings
 * c) Delimit elements by a comma + single space
 * d) Do not add delimiter at the end UNLESS the last element is |undefined|
 * e) Add square brackets to the beginning and end of the string
 */
function toSource(arr)
{
  var delim = CHAR_COMMA + CHAR_SPACE;
  var elt = '';
  var ret = '';
  var len = arr.length;

  for (i=0; i<len; i++)
  {
    elt = arr[i];

    switch(true)
    {
    case (typeof elt === TYPE_STRING) :
      ret += doubleQuote(elt);
      break;

    case (elt === undefined || elt === null) :
      break; // add nothing but the delimiter, below -

    default:
      ret += elt.toString();
    }

    if ((i < len-1) || (elt === undefined))
      ret += delim;
  }

  return  CHAR_LBRACKET + ret + CHAR_RBRACKET;
}


function doubleQuote(text)
{
  return CHAR_QT_DBL + text + CHAR_QT_DBL;
}


function singleQuote(text)
{
  return CHAR_QT + text + CHAR_QT;
}


/* -*- tab-width: 2; indent-tabs-mode: nil; js-indent-level: 2 -*- */
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
 * Date: 07 February 2001
 *
 * Functionality common to RegExp testing -
 */
//-----------------------------------------------------------------------------


var MSG_PATTERN = '\nregexp = ';
var MSG_STRING = '\nstring = ';
var MSG_EXPECT = '\nExpect: ';
var MSG_ACTUAL = '\nActual: ';
var ERR_LENGTH = '\nERROR !!! match arrays have different lengths:';
var ERR_MATCH = '\nERROR !!! regexp failed to give expected match array:';
var ERR_NO_MATCH = '\nERROR !!! regexp FAILED to match anything !!!';
var ERR_UNEXP_MATCH = '\nERROR !!! regexp MATCHED when we expected it to fail !!!';
var CHAR_LBRACKET = '[';
var CHAR_RBRACKET = ']';
var CHAR_QT_DBL = '"';
var CHAR_QT = "'";
var CHAR_NL = '\n';
var CHAR_COMMA = ',';
var CHAR_SPACE = ' ';
var TYPE_STRING = typeof 'abc';



function testRegExp(statuses, patterns, strings, actualmatches, expectedmatches)
{
  var status = '';
  var pattern = new RegExp();
  var string = '';
  var actualmatch = new Array();
  var expectedmatch = new Array();
  var state = '';
  var lActual = -1;
  var lExpect = -1;


  for (var i=0; i != patterns.length; i++)
  {
    status = statuses[i];
    pattern = patterns[i];
    string = strings[i];
    actualmatch=actualmatches[i];
    expectedmatch=expectedmatches[i];
    state = getState(status, pattern, string);

    description = status;

    if(actualmatch)
    {
      actual = formatArray(actualmatch);
      if(expectedmatch)
      {
        // expectedmatch and actualmatch are arrays -
        lExpect = expectedmatch.length;
        lActual = actualmatch.length;

        var expected = formatArray(expectedmatch);

        if (lActual != lExpect)
        {
          reportCompare(lExpect, lActual,
                        state + ERR_LENGTH +
                        MSG_EXPECT + expected +
                        MSG_ACTUAL + actual +
                        CHAR_NL
	    );
          continue;
        }

        // OK, the arrays have same length -
        if (expected != actual)
        {
          reportCompare(expected, actual,
                        state + ERR_MATCH +
                        MSG_EXPECT + expected +
                        MSG_ACTUAL + actual +
                        CHAR_NL
	    );
        }
        else
        {
          reportCompare(expected, actual, state)
	    }

      }
      else //expectedmatch is null - that is, we did not expect a match -
      {
        expected = expectedmatch;
        reportCompare(expected, actual,
                      state + ERR_UNEXP_MATCH +
                      MSG_EXPECT + expectedmatch +
                      MSG_ACTUAL + actual +
                      CHAR_NL
	  );
      }

    }
    else // actualmatch is null
    {
      if (expectedmatch)
      {
        actual = actualmatch;
        reportCompare(expected, actual,
                      state + ERR_NO_MATCH +
                      MSG_EXPECT + expectedmatch +
                      MSG_ACTUAL + actualmatch +
                      CHAR_NL
	  );
      }
      else // we did not expect a match
      {
        // Being ultra-cautious. Presumably expectedmatch===actualmatch===null
        expected = expectedmatch;
        actual   = actualmatch;
        reportCompare (expectedmatch, actualmatch, state);
      }
    }
  }
}


function getState(status, pattern, string)
{
  /*
   * Escape \n's, etc. to make them LITERAL in the presentation string.
   * We don't have to worry about this in |pattern|; such escaping is
   * done automatically by pattern.toString(), invoked implicitly below.
   *
   * One would like to simply do: string = string.replace(/(\s)/g, '\$1').
   * However, the backreference $1 is not a literal string value,
   * so this method doesn't work.
   *
   * Also tried string = string.replace(/(\s)/g, escape('$1'));
   * but this just inserts the escape of the literal '$1', i.e. '%241'.
   */
  string = string.replace(/\n/g, '\\n');
  string = string.replace(/\r/g, '\\r');
  string = string.replace(/\t/g, '\\t');
  string = string.replace(/\v/g, '\\v');
  string = string.replace(/\f/g, '\\f');

  return (status + MSG_PATTERN + pattern + MSG_STRING + singleQuote(string));
}


/*
 * If available, arr.toSource() gives more detail than arr.toString()
 *
 * var arr = Array(1,2,'3');
 *
 * arr.toSource()
 * [1, 2, "3"]
 *
 * arr.toString()
 * 1,2,3
 *
 * But toSource() doesn't exist in Rhino, so use our own imitation, below -
 *
 */
function formatArray(arr)
{
  try
  {
    return arr.toSource();
  }
  catch(e)
  {
    return toSource(arr);
  }
}


/*
 * Imitate SpiderMonkey's arr.toSource() method:
 *
 * a) Double-quote each array element that is of string type
 * b) Represent |undefined| and |null| by empty strings
 * c) Delimit elements by a comma + single space
 * d) Do not add delimiter at the end UNLESS the last element is |undefined|
 * e) Add square brackets to the beginning and end of the string
 */
function toSource(arr)
{
  var delim = CHAR_COMMA + CHAR_SPACE;
  var elt = '';
  var ret = '';
  var len = arr.length;

  for (i=0; i<len; i++)
  {
    elt = arr[i];

    switch(true)
    {
    case (typeof elt === TYPE_STRING) :
      ret += doubleQuote(elt);
      break;

    case (elt === undefined || elt === null) :
      break; // add nothing but the delimiter, below -

    default:
      ret += elt.toString();
    }

    if ((i < len-1) || (elt === undefined))
      ret += delim;
  }

  return  CHAR_LBRACKET + ret + CHAR_RBRACKET;
}


function doubleQuote(text)
{
  return CHAR_QT_DBL + text + CHAR_QT_DBL;
}


function singleQuote(text)
{
  return CHAR_QT + text + CHAR_QT;
}

