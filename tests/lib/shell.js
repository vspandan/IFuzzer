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

/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*- */
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/* all files in this dir need version(120) called before they are *loaded* */


version(120);

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



function assertEqX2(v, arr) {
    try {
        assertEq(v.x, arr[0]);
        assertEq(v.y, arr[1]);
    } catch (e) {
        print("stack trace:", e.stack);
        throw e;
    }
}

function assertEqX4(v, arr) {
    try {
        assertEq(v.x, arr[0]);
        assertEq(v.y, arr[1]);
        assertEq(v.z, arr[2]);
        assertEq(v.w, arr[3]);
    } catch (e) {
        print("stack trace:", e.stack);
        throw e;
    }
}

function simdLength(v) {
    var pt = Object.getPrototypeOf(v);
    if (pt === SIMD.int32x4.prototype || pt === SIMD.float32x4.prototype) {
        return 4;
    } else if (pt === SIMD.float64x2.prototype) {
        return 2;
    } else {
        throw new TypeError("Unknown SIMD kind.");
    }
}

function assertEqVec(v, arr) {
    var lanes = simdLength(v);
    if (lanes == 4)
        assertEqX4(v, arr);
    else if (lanes == 2)
        assertEqX2(v, arr);
    else
        throw new TypeError("Unknown SIMD kind.");
}

function simdToArray(v) {
    var lanes = simdLength(v);
    if (lanes == 4)
        return [v.x, v.y, v.z, v.w];
    else if (lanes == 2)
        return [v.x, v.y];
    else
        throw new TypeError("Unknown SIMD kind.");
}

const INT32_MAX = Math.pow(2, 31) - 1;
const INT32_MIN = -Math.pow(2, 31);
assertEq(INT32_MAX + 1 | 0, INT32_MIN);

function testUnaryFunc(v, simdFunc, func) {
    var varr = simdToArray(v);

    var observed = simdToArray(simdFunc(v));
    var expected = varr.map(function(v, i) { return func(varr[i]); });

    for (var i = 0; i < observed.length; i++)
        assertEq(observed[i], expected[i]);
}

function testBinaryFunc(v, w, simdFunc, func) {
    var varr = simdToArray(v);
    var warr = simdToArray(w);

    var observed = simdToArray(simdFunc(v, w));
    var expected = varr.map(function(v, i) { return func(varr[i], warr[i]); });

    for (var i = 0; i < observed.length; i++)
        assertEq(observed[i], expected[i]);
}

function testBinaryCompare(v, w, simdFunc, func) {
    var varr = simdToArray(v);
    var warr = simdToArray(w);

    var inLanes = simdLength(v);
    var observed = simdToArray(simdFunc(v, w));
    assertEq(observed.length, 4);
    for (var i = 0; i < 4; i++) {
        var j = ((i * inLanes) / 4) | 0;
        assertEq(observed[i], func(varr[j], warr[j]));
    }
}

function testBinaryScalarFunc(v, scalar, simdFunc, func) {
    var varr = simdToArray(v);

    var observed = simdToArray(simdFunc(v, scalar));
    var expected = varr.map(function(v, i) { return func(varr[i], scalar); });

    for (var i = 0; i < observed.length; i++)
        assertEq(observed[i], expected[i]);
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


/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*- */
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */


/*
 * Date functions used by tests in Date suite
 *
 */
var msPerDay =   86400000;
var HoursPerDay =  24;
var MinutesPerHour = 60;
var SecondsPerMinute = 60;
var msPerSecond =  1000;
var msPerMinute =  60000;  // msPerSecond * SecondsPerMinute
var msPerHour =   3600000; // msPerMinute * MinutesPerHour
var TZ_DIFF = getTimeZoneDiff();  // offset of tester's timezone from UTC
var TZ_ADJUST = TZ_DIFF * msPerHour;
var TZ_PST = -8;  // offset of Pacific Standard Time from UTC
var PST_DIFF = TZ_DIFF - TZ_PST;  // offset of tester's timezone from PST
var TIME_1970  = 0;
var TIME_2000  = 946684800000;
var TIME_1900  = -2208988800000;
var UTC_29_FEB_2000 = TIME_2000 + 31*msPerDay + 28*msPerDay;
var UTC_1_JAN_2005 = TIME_2000 + TimeInYear(2000) + TimeInYear(2001) +
  TimeInYear(2002) + TimeInYear(2003) + TimeInYear(2004);
var now = new Date();
var TIME_NOW = now.valueOf();  //valueOf() is to accurate to the millisecond
                               //Date.parse() is accurate only to the second

/*
 * Originally, the test suite used a hard-coded value TZ_DIFF = -8.
 * But that was only valid for testers in the Pacific Standard Time Zone!
 * We calculate the proper number dynamically for any tester. We just
 * have to be careful not to use a date subject to Daylight Savings Time...
 */
function getTimeZoneDiff()
{
  return -((new Date(2000, 1, 1)).getTimezoneOffset())/60;
}


/*
 * Date test "ResultArrays" are hard-coded for Pacific Standard Time.
 * We must adjust them for the tester's own timezone -
 */
function adjustResultArray(ResultArray, msMode)
{
  // If the tester's system clock is in PST, no need to continue -
  if (!PST_DIFF) {return;}

  /* The date testcases instantiate Date objects in two different ways:
   *
   *        millisecond mode: e.g.   dt = new Date(10000000);
   *        year-month-day mode:  dt = new Date(2000, 5, 1, ...);
   *
   * In the first case, the date is measured from Time 0 in Greenwich (i.e. UTC).
   * In the second case, it is measured with reference to the tester's local timezone.
   *
   * In the first case we must correct those values expected for local measurements,
   * like dt.getHours() etc. No correction is necessary for dt.getUTCHours() etc.
   *
   * In the second case, it is exactly the other way around -
   */
  if (msMode)
  {
    // The hard-coded UTC milliseconds from Time 0 derives from a UTC date.
    // Shift to the right by the offset between UTC and the tester.
    var t = ResultArray[TIME]  +  TZ_DIFF*msPerHour;

    // Use our date arithmetic functions to determine the local hour, day, etc.
    ResultArray[HOURS] = HourFromTime(t);
    ResultArray[DAY] = WeekDay(t);
    ResultArray[DATE] = DateFromTime(t);
    ResultArray[MONTH] = MonthFromTime(t);
    ResultArray[YEAR] = YearFromTime(t); 
  }
  else
  {
    // The hard-coded UTC milliseconds from Time 0 derives from a PST date.
    // Shift to the left by the offset between PST and the tester.
    var t = ResultArray[TIME]  -  PST_DIFF*msPerHour;

    // Use our date arithmetic functions to determine the UTC hour, day, etc.
    ResultArray[TIME] = t;
    ResultArray[UTC_HOURS] = HourFromTime(t);
    ResultArray[UTC_DAY] = WeekDay(t);
    ResultArray[UTC_DATE] = DateFromTime(t);
    ResultArray[UTC_MONTH] = MonthFromTime(t);
    ResultArray[UTC_YEAR] = YearFromTime(t);
  }
}


function Day( t ) {
  return ( Math.floor(t/msPerDay ) );
}
function DaysInYear( y ) {
  if ( y % 4 != 0 ) {
    return 365;
  }
  if ( (y % 4 == 0) && (y % 100 != 0) ) {
    return 366;
  }
  if ( (y % 100 == 0) && (y % 400 != 0) ) {
    return 365;
  }
  if ( (y % 400 == 0) ){
    return 366;
  } else {
    return "ERROR: DaysInYear(" + y + ") case not covered";
  }
}
function TimeInYear( y ) {
  return ( DaysInYear(y) * msPerDay );
}
function DayNumber( t ) {
  return ( Math.floor( t / msPerDay ) );
}
function TimeWithinDay( t ) {

  var r = t % msPerDay;

  if (r < 0)
  {
    r += msPerDay;
  }
  return r;

}
function YearNumber( t ) {
}
function TimeFromYear( y ) {
  return ( msPerDay * DayFromYear(y) );
}
function DayFromYear( y ) {
  return ( 365*(y-1970) +
           Math.floor((y-1969)/4) -
           Math.floor((y-1901)/100) +
           Math.floor((y-1601)/400) );
}
function InLeapYear( t ) {
  if ( DaysInYear(YearFromTime(t)) == 365 ) {
    return 0;
  }
  if ( DaysInYear(YearFromTime(t)) == 366 ) {
    return 1;
  } else {
    return "ERROR:  InLeapYear("+ t + ") case not covered";
  }
}
function YearFromTime( t ) {
  t = Number( t );
  var sign = ( t < 0 ) ? -1 : 1;
  var year = ( sign < 0 ) ? 1969 : 1970;
  for ( var timeToTimeZero = t; ;  ) {
    // subtract the current year's time from the time that's left.
    timeToTimeZero -= sign * TimeInYear(year)

      // if there's less than the current year's worth of time left, then break.
      if ( sign < 0 ) {
        if ( sign * timeToTimeZero <= 0 ) {
          break;
        } else {
          year += sign;
        }
      } else {
        if ( sign * timeToTimeZero < 0 ) {
          break;
        } else {
          year += sign;
        }
      }
  }
  return ( year );
}
function MonthFromTime( t ) {
  // i know i could use switch but i'd rather not until it's part of ECMA
  var day = DayWithinYear( t );
  var leap = InLeapYear(t);

  if ( (0 <= day) && (day < 31) ) {
    return 0;
  }
  if ( (31 <= day) && (day < (59+leap)) ) {
    return 1;
  }
  if ( ((59+leap) <= day) && (day < (90+leap)) ) {
    return 2;
  }
  if ( ((90+leap) <= day) && (day < (120+leap)) ) {
    return 3;
  }
  if ( ((120+leap) <= day) && (day < (151+leap)) ) {
    return 4;
  }
  if ( ((151+leap) <= day) && (day < (181+leap)) ) {
    return 5;
  }
  if ( ((181+leap) <= day) && (day < (212+leap)) ) {
    return 6;
  }
  if ( ((212+leap) <= day) && (day < (243+leap)) ) {
    return 7;
  }
  if ( ((243+leap) <= day) && (day < (273+leap)) ) {
    return 8;
  }
  if ( ((273+leap) <= day) && (day < (304+leap)) ) {
    return 9;
  }
  if ( ((304+leap) <= day) && (day < (334+leap)) ) {
    return 10;
  }
  if ( ((334+leap) <= day) && (day < (365+leap)) ) {
    return 11;
  } else {
    return "ERROR: MonthFromTime("+t+") not known";
  }
}
function DayWithinYear( t ) {
  return( Day(t) - DayFromYear(YearFromTime(t)));
}
function DateFromTime( t ) {
  var day = DayWithinYear(t);
  var month = MonthFromTime(t);

  if ( month == 0 ) {
    return ( day + 1 );
  }
  if ( month == 1 ) {
    return ( day - 30 );
  }
  if ( month == 2 ) {
    return ( day - 58 - InLeapYear(t) );
  }
  if ( month == 3 ) {
    return ( day - 89 - InLeapYear(t));
  }
  if ( month == 4 ) {
    return ( day - 119 - InLeapYear(t));
  }
  if ( month == 5 ) {
    return ( day - 150- InLeapYear(t));
  }
  if ( month == 6 ) {
    return ( day - 180- InLeapYear(t));
  }
  if ( month == 7 ) {
    return ( day - 211- InLeapYear(t));
  }
  if ( month == 8 ) {
    return ( day - 242- InLeapYear(t));
  }
  if ( month == 9 ) {
    return ( day - 272- InLeapYear(t));
  }
  if ( month == 10 ) {
    return ( day - 303- InLeapYear(t));
  }
  if ( month == 11 ) {
    return ( day - 333- InLeapYear(t));
  }

  return ("ERROR:  DateFromTime("+t+") not known" );
}
function WeekDay( t ) {
  var weekday = (Day(t)+4) % 7;
  return( weekday < 0 ? 7 + weekday : weekday );
}

// missing daylight savings time adjustment

function HourFromTime( t ) {
  var h = Math.floor( t / msPerHour ) % HoursPerDay;
  return ( (h<0) ? HoursPerDay + h : h  );
}
function MinFromTime( t ) {
  var min = Math.floor( t / msPerMinute ) % MinutesPerHour;
  return( ( min < 0 ) ? MinutesPerHour + min : min  );
}
function SecFromTime( t ) {
  var sec = Math.floor( t / msPerSecond ) % SecondsPerMinute;
  return ( (sec < 0 ) ? SecondsPerMinute + sec : sec );
}
function msFromTime( t ) {
  var ms = t % msPerSecond;
  return ( (ms < 0 ) ? msPerSecond + ms : ms );
}
function LocalTZA() {
  return ( TZ_DIFF * msPerHour );
}
function UTC( t ) {
  return ( t - LocalTZA() - DaylightSavingTA(t - LocalTZA()) );
}

function DaylightSavingTA( t ) {
  t = t - LocalTZA();

  var dst_start = GetDSTStart(t);
  var dst_end   = GetDSTEnd(t);

  if ( t >= dst_start && t < dst_end )
    return msPerHour;

  return 0;
}

function GetFirstSundayInMonth( t, m ) {
  var year = YearFromTime(t);
  var leap = InLeapYear(t);

// month m 0..11
// april == 3
// march == 2

  // set time to first day of month m
  var time = TimeFromYear(year);
  for (var i = 0; i < m; ++i)
  {
    time += TimeInMonth(i, leap);
  }

  for ( var first_sunday = time; WeekDay(first_sunday) > 0;
        first_sunday += msPerDay )
  {
    ;
  }

  return first_sunday;
}

function GetLastSundayInMonth( t, m ) {
  var year = YearFromTime(t);
  var leap = InLeapYear(t);

// month m 0..11
// april == 3
// march == 2

  // first day of following month
  var time = TimeFromYear(year);
  for (var i = 0; i <= m; ++i)
  {
    time += TimeInMonth(i, leap);
  }
  // prev day == last day of month
  time -= msPerDay;

  for ( var last_sunday = time; WeekDay(last_sunday) > 0;
        last_sunday -= msPerDay )
  {
    ;
  }
  return last_sunday;
}

/*
  15.9.1.9 Daylight Saving Time Adjustment

  The implementation of ECMAScript should not try to determine whether
  the exact time was subject to daylight saving time, but just whether
  daylight saving time would have been in effect if the current
  daylight saving time algorithm had been used at the time. This avoids
  complications such as taking into account the years that the locale
  observed daylight saving time year round.
*/

/*
  US DST algorithm

  Before 2007, DST starts first Sunday in April at 2 AM and ends last
  Sunday in October at 2 AM

  Starting in 2007, DST starts second Sunday in March at 2 AM and ends
  first Sunday in November at 2 AM

  Note that different operating systems behave differently.

  Fully patched Windows XP uses the 2007 algorithm for all dates while
  fully patched Fedora Core 6 and RHEL 4 Linux use the algorithm in
  effect at the time.

  Since pre-2007 DST is a subset of 2007 DST rules, this only affects
  tests that occur in the period Mar-Apr and Oct-Nov where the two
  algorithms do not agree.

*/

function GetDSTStart( t )
{
  return (GetFirstSundayInMonth(t, 2) + 7*msPerDay + 2*msPerHour - LocalTZA());
}

function GetDSTEnd( t )
{
  return (GetFirstSundayInMonth(t, 10) + 2*msPerHour - LocalTZA());
}

function GetOldDSTStart( t )
{
  return (GetFirstSundayInMonth(t, 3) + 2*msPerHour - LocalTZA());
}

function GetOldDSTEnd( t )
{
  return (GetLastSundayInMonth(t, 9) + 2*msPerHour - LocalTZA());
}

function LocalTime( t ) {
  return ( t + LocalTZA() + DaylightSavingTA(t) );
}
function MakeTime( hour, min, sec, ms ) {
  if ( isNaN( hour ) || isNaN( min ) || isNaN( sec ) || isNaN( ms ) ) {
    return Number.NaN;
  }

  hour = ToInteger(hour);
  min  = ToInteger( min);
  sec  = ToInteger( sec);
  ms  = ToInteger( ms );

  return( (hour*msPerHour) + (min*msPerMinute) +
          (sec*msPerSecond) + ms );
}
function MakeDay( year, month, date ) {
  if ( isNaN(year) || isNaN(month) || isNaN(date) ) {
    return Number.NaN;
  }
  year = ToInteger(year);
  month = ToInteger(month);
  date = ToInteger(date );

  var sign = ( year < 1970 ) ? -1 : 1;
  var t =    ( year < 1970 ) ? 1 :  0;
  var y =    ( year < 1970 ) ? 1969 : 1970;

  var result5 = year + Math.floor( month/12 );
  var result6 = month % 12;

  if ( year < 1970 ) {
    for ( y = 1969; y >= year; y += sign ) {
      t += sign * TimeInYear(y);
    }
  } else {
    for ( y = 1970 ; y < year; y += sign ) {
      t += sign * TimeInYear(y);
    }
  }

  var leap = InLeapYear( t );

  for ( var m = 0; m < month; m++ ) {
    t += TimeInMonth( m, leap );
  }

  if ( YearFromTime(t) != result5 ) {
    return Number.NaN;
  }
  if ( MonthFromTime(t) != result6 ) {
    return Number.NaN;
  }
  if ( DateFromTime(t) != 1 ) {
    return Number.NaN;
  }

  return ( (Day(t)) + date - 1 );
}
function TimeInMonth( month, leap ) {
  // september april june november
  // jan 0  feb 1  mar 2 apr 3 may 4  june 5  jul 6
  // aug 7  sep 8  oct 9 nov 10 dec 11

  if ( month == 3 || month == 5 || month == 8 || month == 10 ) {
    return ( 30*msPerDay );
  }

  // all the rest
  if ( month == 0 || month == 2 || month == 4 || month == 6 ||
       month == 7 || month == 9 || month == 11 ) {
    return ( 31*msPerDay );
  }

  // save february
  return ( (leap == 0) ? 28*msPerDay : 29*msPerDay );
}
function MakeDate( day, time ) {
  if ( day == Number.POSITIVE_INFINITY ||
       day == Number.NEGATIVE_INFINITY ) {
    return Number.NaN;
  }
  if ( time == Number.POSITIVE_INFINITY ||
       time == Number.NEGATIVE_INFINITY ) {
    return Number.NaN;
  }
  return ( day * msPerDay ) + time;
}
function TimeClip( t ) {
  if ( isNaN( t ) ) {
    return ( Number.NaN );
  }
  if ( Math.abs( t ) > 8.64e15 ) {
    return ( Number.NaN );
  }

  return ( ToInteger( t ) );
}
function ToInteger( t ) {
  t = Number( t );

  if ( isNaN( t ) ){
    return ( Number.NaN );
  }
  if ( t == 0 || t == -0 ||
       t == Number.POSITIVE_INFINITY || t == Number.NEGATIVE_INFINITY ) {
    return 0;
  }

  var sign = ( t < 0 ) ? -1 : 1;

  return ( sign * Math.floor( Math.abs( t ) ) );
}
function Enumerate ( o ) {
  var p;
  for ( p in o ) {
    print( p +": " + o[p] );
  }
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

// A little pattern-matching library.
var Match =

(function() {

    function Pattern(template) {
        // act like a constructor even as a function
        if (!(this instanceof Pattern))
            return new Pattern(template);

        this.template = template;
    }

    Pattern.prototype = {
        match: function(act) {
            return match(act, this.template);
        },

        matches: function(act) {
            try {
                return this.match(act);
            }
            catch (e if e instanceof MatchError) {
                return false;
            }
        },

        assert: function(act, message) {
            try {
                return this.match(act);
            }
            catch (e if e instanceof MatchError) {
                throw new Error((message || "failed match") + ": " + e.message);
            }
        },

        toString: function() "[object Pattern]"
    };

    Pattern.ANY = new Pattern;
    Pattern.ANY.template = Pattern.ANY;

    var quote = uneval;

    function MatchError(msg) {
        this.message = msg;
    }

    MatchError.prototype = {
        toString: function() {
            return "match error: " + this.message;
        }
    };

    function isAtom(x) {
        return (typeof x === "number") ||
            (typeof x === "string") ||
            (typeof x === "boolean") ||
            (x === null) ||
            (typeof x === "object" && x instanceof RegExp);
    }

    function isObject(x) {
        return (x !== null) && (typeof x === "object");
    }

    function isFunction(x) {
        return typeof x === "function";
    }

    function isArrayLike(x) {
        return isObject(x) && ("length" in x);
    }

    function matchAtom(act, exp) {
        if ((typeof exp) === "number" && isNaN(exp)) {
            if ((typeof act) !== "number" || !isNaN(act))
                throw new MatchError("expected NaN, got: " + quote(act));
            return true;
        }

        if (exp === null) {
            if (act !== null)
                throw new MatchError("expected null, got: " + quote(act));
            return true;
        }

        if (exp instanceof RegExp) {
            if (!(act instanceof RegExp) || exp.source !== act.source)
                throw new MatchError("expected " + quote(exp) + ", got: " + quote(act));
            return true;
        }

        switch (typeof exp) {
        case "string":
            if (act !== exp)
                throw new MatchError("expected " + quote(exp) + ", got " + quote(act));
            return true;
        case "boolean":
        case "number":
            if (exp !== act)
                throw new MatchError("expected " + exp + ", got " + quote(act));
            return true;
        }

        throw new Error("bad pattern: " + exp.toSource());
    }

    function matchObject(act, exp) {
        if (!isObject(act))
            throw new MatchError("expected object, got " + quote(act));

        for (var key in exp) {
            if (!(key in act))
                throw new MatchError("expected property " + quote(key) + " not found in " + quote(act));
            match(act[key], exp[key]);
        }

        return true;
    }

    function matchFunction(act, exp) {
        if (!isFunction(act))
            throw new MatchError("expected function, got " + quote(act));

        if (act !== exp)
            throw new MatchError("expected function: " + exp +
                                 "\nbut got different function: " + act);
    }

    function matchArray(act, exp) {
        if (!isObject(act) || !("length" in act))
            throw new MatchError("expected array-like object, got " + quote(act));

        var length = exp.length;
        if (act.length !== exp.length)
            throw new MatchError("expected array-like object of length " + length + ", got " + quote(act));

        for (var i = 0; i < length; i++) {
            if (i in exp) {
                if (!(i in act))
                    throw new MatchError("expected array property " + i + " not found in " + quote(act));
                match(act[i], exp[i]);
            }
        }

        return true;
    }

    function match(act, exp) {
        if (exp === Pattern.ANY)
            return true;

        if (exp instanceof Pattern)
            return exp.match(act);

        if (isAtom(exp))
            return matchAtom(act, exp);

        if (isArrayLike(exp))
            return matchArray(act, exp);

        if (isFunction(exp))
            return matchFunction(act, exp);

        return matchObject(act, exp);
    }

    return { Pattern: Pattern,
             MatchError: MatchError };

})();

function referencesVia(from, edge, to) {
    edge = "edge: " + edge;
    var edges = findReferences(to);
    if (edge in edges && edges[edge].indexOf(from) != -1)
        return true;

    // Be nice: make it easy to fix if the edge name has just changed.
    var alternatives = [];
    for (var e in edges) {
        if (edges[e].indexOf(from) != -1)
            alternatives.push(e);
    }
    if (alternatives.length == 0) {
        print("referent not referred to by referrer after all");
    } else {
        print("referent is not referenced via: " + uneval(edge));
        print("but it is referenced via:       " + uneval(alternatives));
    }
    print("all incoming edges, from any object:");
    for (var e in edges)
        print(e);
    return false;
}

// Note that AsmJS ArrayBuffers have a minimum size, currently 4096 bytes. If a
// smaller size is given, a regular ArrayBuffer will be returned instead.
function AsmJSArrayBuffer(size) {
    var ab = new ArrayBuffer(size);
    (new Function('global', 'foreign', 'buffer', '' +
'        "use asm";' +
'        var i32 = new global.Int32Array(buffer);' +
'        function g() {};' +
'        return g;' +
''))(Function("return this")(),null,ab);
    return ab;
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


/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// Explicitly set the default version.
// See https://bugzilla.mozilla.org/show_bug.cgi?id=522760#c11
if (typeof version != 'undefined')
{
  version(0);
}

var STATUS = "STATUS: ";
var VERBOSE = false;
var SECT_PREFIX = 'Section ';
var SECT_SUFFIX = ' of test - ';
var callStack = new Array();

var gDelayTestDriverEnd = false;

var gTestcases = new Array();
var gTc = gTestcases.length;
var BUGNUMBER = '';
var summary = '';
var description = '';
var expected = '';
var actual = '';
var msg = '';

var SECTION = "";
var VERSION = "";
var BUGNUMBER = "";

/*
 * constant strings
 */
var GLOBAL = this + '';
var PASSED = " PASSED! ";
var FAILED = " FAILED! ";

var DEBUG = false;

var DESCRIPTION;
var EXPECTED;

/*
 * Signals to results.py that the current test case should be considered to
 * have passed if it doesn't throw an exception.
 *
 * When the test suite is run in the browser, this function gets overridden by
 * the same-named function in browser.js.
 */
function testPassesUnlessItThrows() {
  print(PASSED);
}

/*
 * wrapper for test case constructor that doesn't require the SECTION
 * argument.
 */

function AddTestCase( description, expect, actual ) {
  new TestCase( SECTION, description, expect, actual );
}

/*
 * Set up test environment.
 *
 */
function startTest() {
  // print out bugnumber

  if ( BUGNUMBER ) {
    print ("BUGNUMBER: " + BUGNUMBER );
  }
}

function TestCase(n, d, e, a)
{
  this.name = n;
  this.description = d;
  this.expect = e;
  this.actual = a;
  this.passed = getTestCaseResult(e, a);
  this.reason = '';
  this.bugnumber = typeof(BUGNUMER) != 'undefined' ? BUGNUMBER : '';
  this.type = (typeof window == 'undefined' ? 'shell' : 'browser');
  gTestcases[gTc++] = this;
}

gFailureExpected = false;

TestCase.prototype.dump = function () {
  // let reftest handle error reporting, otherwise
  // output a summary line.
  if (typeof document != "object" ||
      !document.location.href.match(/jsreftest.html/))
  {
    dump('\njstest: ' + this.path + ' ' +
         'bug: '         + this.bugnumber + ' ' +
         'result: '      + (this.passed ? 'PASSED':'FAILED') + ' ' +
         'type: '        + this.type + ' ' +
         'description: ' + toPrinted(this.description) + ' ' +
//       'expected: '    + toPrinted(this.expect) + ' ' +
//       'actual: '      + toPrinted(this.actual) + ' ' +
         'reason: '      + toPrinted(this.reason) + '\n');
  }
};

TestCase.prototype.testPassed = (function TestCase_testPassed() { return this.passed; });
TestCase.prototype.testFailed = (function TestCase_testFailed() { return !this.passed; });
TestCase.prototype.testDescription = (function TestCase_testDescription() { return this.description + ' ' + this.reason; });

function getTestCases()
{
  return gTestcases;
}

/*
 * The test driver searches for such a phrase in the test output.
 * If such phrase exists, it will set n as the expected exit code.
 */
function expectExitCode(n)
{
  print('--- NOTE: IN THIS TESTCASE, WE EXPECT EXIT CODE ' + n + ' ---');
}

/*
 * Statuses current section of a test
 */
function inSection(x)
{
  return SECT_PREFIX + x + SECT_SUFFIX;
}

/*
 * Report a failure in the 'accepted' manner
 */
function reportFailure (msg)
{
  var lines = msg.split ("\n");
  var l;
  var funcName = currentFunc();
  var prefix = (funcName) ? "[reported from " + funcName + "] ": "";

  for (var i=0; i<lines.length; i++)
    print (FAILED + prefix + lines[i]);
}

/*
 * Print a non-failure message.
 */
function printStatus (msg)
{
/* js1_6 had...
   msg = String(msg);
   msg = msg.toString();
*/
  msg = msg.toString();
  var lines = msg.split ("\n");
  var l;

  for (var i=0; i<lines.length; i++)
    print (STATUS + lines[i]);
}

/*
 * Print a bugnumber message.
 */
function printBugNumber (num)
{
  BUGNUMBER = num;
  print ('BUGNUMBER: ' + num);
}

function toPrinted(value)
{
  value = String(value);
  value = value.replace(/\\n/g, 'NL')
               .replace(/\n/g, 'NL')
               .replace(/\\r/g, 'CR')
               .replace(/[^\x20-\x7E]+/g, escapeString);
  return value;
}

function escapeString (str)
{
  var a, b, c, d;
  var len = str.length;
  var result = "";
  var digits = ["0", "1", "2", "3", "4", "5", "6", "7",
                "8", "9", "A", "B", "C", "D", "E", "F"];

  for (var i=0; i<len; i++)
  {
    var ch = str.charCodeAt(i);

    a = digits[ch & 0xf];
    ch >>= 4;
    b = digits[ch & 0xf];
    ch >>= 4;

    if (ch)
    {
      c = digits[ch & 0xf];
      ch >>= 4;
      d = digits[ch & 0xf];

      result += "\\u" + d + c + b + a;
    }
    else
    {
      result += "\\x" + b + a;
    }
  }

  return result;
}

/*
 * assertEq(actual, expected [, message])
 *   Throw if the two arguments are not the same.  The sameness of two values
 *   is determined as follows.  If both values are zero, they are the same iff
 *   their signs are the same.  Otherwise, if both values are NaN, they are the
 *   same.  Otherwise, they are the same if they compare equal using ===.
 * see https://bugzilla.mozilla.org/show_bug.cgi?id=480199 and
 *     https://bugzilla.mozilla.org/show_bug.cgi?id=515285
 */
if (typeof assertEq == 'undefined')
{
  var assertEq =
    function (actual, expected, message)
    {
      function SameValue(v1, v2)
      {
        if (v1 === 0 && v2 === 0)
          return 1 / v1 === 1 / v2;
        if (v1 !== v1 && v2 !== v2)
          return true;
        return v1 === v2;
      }
      if (!SameValue(actual, expected))
      {
        throw new TypeError('Assertion failed: got "' + actual + '", expected "' + expected +
                            (message ? ": " + message : ""));
      }
    };
}

/*
 * Compare expected result to actual result, if they differ (in value and/or
 * type) report a failure.  If description is provided, include it in the
 * failure report.
 */
function reportCompare (expected, actual, description) {
  var expected_t = typeof expected;
  var actual_t = typeof actual;
  var output = "";

  if (typeof description == "undefined")
  {
    description = '';
  }
  else if (VERBOSE)
  {
    printStatus ("Comparing '" + description + "'");
  }

  if (expected_t != actual_t)
  {
    output += "Type mismatch, expected type " + expected_t +
      ", actual type " + actual_t + " ";
  }
  else if (VERBOSE)
  {
    printStatus ("Expected type '" + expected_t + "' matched actual " +
                 "type '" + actual_t + "'");
  }

  if (expected != actual)
  {
    output += "Expected value '" + toPrinted(expected) +
      "', Actual value '" + toPrinted(actual) + "' ";
  }
  else if (VERBOSE)
  {
    printStatus ("Expected value '" + toPrinted(expected) +
                 "' matched actual value '" + toPrinted(actual) + "'");
  }

  var testcase = new TestCase("unknown-test-name", description, expected, actual);
  testcase.reason = output;

  // if running under reftest, let it handle result reporting.
  if (typeof document != "object" ||
      !document.location.href.match(/jsreftest.html/)) {
    if (testcase.passed)
    {
      print(PASSED + description);
    }
    else
    {
      reportFailure (description + " : " + output);
    }
  }
  return testcase.passed;
}

/*
 * Attempt to match a regular expression describing the result to
 * the actual result, if they differ (in value and/or
 * type) report a failure.  If description is provided, include it in the
 * failure report.
 */
function reportMatch (expectedRegExp, actual, description) {
  var expected_t = "string";
  var actual_t = typeof actual;
  var output = "";

  if (typeof description == "undefined")
  {
    description = '';
  }
  else if (VERBOSE)
  {
    printStatus ("Comparing '" + description + "'");
  }

  if (expected_t != actual_t)
  {
    output += "Type mismatch, expected type " + expected_t +
      ", actual type " + actual_t + " ";
  }
  else if (VERBOSE)
  {
    printStatus ("Expected type '" + expected_t + "' matched actual " +
                 "type '" + actual_t + "'");
  }

  var matches = expectedRegExp.test(actual);
  if (!matches)
  {
    output += "Expected match to '" + toPrinted(expectedRegExp) +
      "', Actual value '" + toPrinted(actual) + "' ";
  }
  else if (VERBOSE)
  {
    printStatus ("Expected match to '" + toPrinted(expectedRegExp) +
                 "' matched actual value '" + toPrinted(actual) + "'");
  }

  var testcase = new TestCase("unknown-test-name", description, true, matches);
  testcase.reason = output;

  // if running under reftest, let it handle result reporting.
  if (typeof document != "object" ||
      !document.location.href.match(/jsreftest.html/)) {
    if (testcase.passed)
    {
      print(PASSED + description);
    }
    else
    {
      reportFailure (description + " : " + output);
    }
  }
  return testcase.passed;
}

/*
 * Puts funcName at the top of the call stack.  This stack is used to show
 * a function-reported-from field when reporting failures.
 */
function enterFunc (funcName)
{
  if (!funcName.match(/\(\)$/))
    funcName += "()";

  callStack.push(funcName);
}

/*
 * Pops the top funcName off the call stack.  funcName is optional, and can be
 * used to check push-pop balance.
 */
function exitFunc (funcName)
{
  var lastFunc = callStack.pop();

  if (funcName)
  {
    if (!funcName.match(/\(\)$/))
      funcName += "()";

    if (lastFunc != funcName)
      reportCompare(funcName, lastFunc, "Test driver failure wrong exit function ");
  }
}

/*
 * Peeks at the top of the call stack.
 */
function currentFunc()
{
  return callStack[callStack.length - 1];
}

/*
  Calculate the "order" of a set of data points {X: [], Y: []}
  by computing successive "derivatives" of the data until
  the data is exhausted or the derivative is linear.
*/
function BigO(data)
{
  var order = 0;
  var origLength = data.X.length;

  while (data.X.length > 2)
  {
    var lr = new LinearRegression(data);
    if (lr.b > 1e-6)
    {
      // only increase the order if the slope
      // is "great" enough
      order++;
    }

    if (lr.r > 0.98 || lr.Syx < 1 || lr.b < 1e-6)
    {
      // terminate if close to a line lr.r
      // small error lr.Syx
      // small slope lr.b
      break;
    }
    data = dataDeriv(data);
  }

  if (2 == origLength - order)
  {
    order = Number.POSITIVE_INFINITY;
  }
  return order;

  function LinearRegression(data)
  {
    /*
      y = a + bx
      for data points (Xi, Yi); 0 <= i < n

      b = (n*SUM(XiYi) - SUM(Xi)*SUM(Yi))/(n*SUM(Xi*Xi) - SUM(Xi)*SUM(Xi))
      a = (SUM(Yi) - b*SUM(Xi))/n
    */
    var i;

    if (data.X.length != data.Y.length)
    {
      throw 'LinearRegression: data point length mismatch';
    }
    if (data.X.length < 3)
    {
      throw 'LinearRegression: data point length < 2';
    }
    var n = data.X.length;
    var X = data.X;
    var Y = data.Y;

    this.Xavg = 0;
    this.Yavg = 0;

    var SUM_X  = 0;
    var SUM_XY = 0;
    var SUM_XX = 0;
    var SUM_Y  = 0;
    var SUM_YY = 0;

    for (i = 0; i < n; i++)
    {
      SUM_X  += X[i];
      SUM_XY += X[i]*Y[i];
      SUM_XX += X[i]*X[i];
      SUM_Y  += Y[i];
      SUM_YY += Y[i]*Y[i];
    }

    this.b = (n * SUM_XY - SUM_X * SUM_Y)/(n * SUM_XX - SUM_X * SUM_X);
    this.a = (SUM_Y - this.b * SUM_X)/n;

    this.Xavg = SUM_X/n;
    this.Yavg = SUM_Y/n;

    var SUM_Ydiff2 = 0;
    var SUM_Xdiff2 = 0;
    var SUM_XdiffYdiff = 0;

    for (i = 0; i < n; i++)
    {
      var Ydiff = Y[i] - this.Yavg;
      var Xdiff = X[i] - this.Xavg;

      SUM_Ydiff2 += Ydiff * Ydiff;
      SUM_Xdiff2 += Xdiff * Xdiff;
      SUM_XdiffYdiff += Xdiff * Ydiff;
    }

    var Syx2 = (SUM_Ydiff2 - Math.pow(SUM_XdiffYdiff/SUM_Xdiff2, 2))/(n - 2);
    var r2   = Math.pow((n*SUM_XY - SUM_X * SUM_Y), 2) /
      ((n*SUM_XX - SUM_X*SUM_X)*(n*SUM_YY-SUM_Y*SUM_Y));

    this.Syx = Math.sqrt(Syx2);
    this.r = Math.sqrt(r2);

  }

  function dataDeriv(data)
  {
    if (data.X.length != data.Y.length)
    {
      throw 'length mismatch';
    }
    var length = data.X.length;

    if (length < 2)
    {
      throw 'length ' + length + ' must be >= 2';
    }
    var X = data.X;
    var Y = data.Y;

    var deriv = {X: [], Y: [] };

    for (var i = 0; i < length - 1; i++)
    {
      deriv.X[i] = (X[i] + X[i+1])/2;
      deriv.Y[i] = (Y[i+1] - Y[i])/(X[i+1] - X[i]);
    }
    return deriv;
  }

  return 0;
}

function compareSource(expect, actual, summary)
{
  // compare source
  var expectP = expect.
    replace(/([(){},.:\[\]])/mg, ' $1 ').
    replace(/(\w+)/mg, ' $1 ').
    replace(/<(\/)? (\w+) (\/)?>/mg, '<$1$2$3>').
    replace(/\s+/mg, ' ').
    replace(/new (\w+)\s*\(\s*\)/mg, 'new $1');

  var actualP = actual.
    replace(/([(){},.:\[\]])/mg, ' $1 ').
    replace(/(\w+)/mg, ' $1 ').
    replace(/<(\/)? (\w+) (\/)?>/mg, '<$1$2$3>').
    replace(/\s+/mg, ' ').
    replace(/new (\w+)\s*\(\s*\)/mg, 'new $1');

  print('expect:\n' + expectP);
  print('actual:\n' + actualP);

  reportCompare(expectP, actualP, summary);

  // actual must be compilable if expect is?
  try
  {
    var expectCompile = 'No Error';
    var actualCompile;

    eval(expect);
    try
    {
      eval(actual);
      actualCompile = 'No Error';
    }
    catch(ex1)
    {
      actualCompile = ex1 + '';
    }
    reportCompare(expectCompile, actualCompile,
                  summary + ': compile actual');
  }
  catch(ex)
  {
  }
}

function optionsInit() {

  // record initial values to support resetting
  // options to their initial values
  options.initvalues  = {};

  // record values in a stack to support pushing
  // and popping options
  options.stackvalues = [];

  var optionNames = options().split(',');

  for (var i = 0; i < optionNames.length; i++)
  {
    var optionName = optionNames[i];
    if (optionName)
    {
      options.initvalues[optionName] = '';
    }
  }
}

function optionsClear() {

  // turn off current settings
  // except jit.
  var optionNames = options().split(',');
  for (var i = 0; i < optionNames.length; i++)
  {
    var optionName = optionNames[i];
    if (optionName &&
        optionName != "methodjit" &&
        optionName != "methodjit_always" &&
        optionName != "ion")
    {
      options(optionName);
    }
  }
}

function optionsPush()
{
  var optionsframe = {};

  options.stackvalues.push(optionsframe);

  var optionNames = options().split(',');

  for (var i = 0; i < optionNames.length; i++)
  {
    var optionName = optionNames[i];
    if (optionName)
    {
      optionsframe[optionName] = '';
    }
  }

  optionsClear();
}

function optionsPop()
{
  var optionsframe = options.stackvalues.pop();

  optionsClear();

  for (optionName in optionsframe)
  {
    options(optionName);
  }

}

function optionsReset() {

  try
  {
    optionsClear();

    // turn on initial settings
    for (var optionName in options.initvalues)
    {
      if (!options.hasOwnProperty(optionName))
        continue;
      options(optionName);
    }
  }
  catch(ex)
  {
    print('optionsReset: caught ' + ex);
  }

}

if (typeof options == 'function')
{
  optionsInit();
  optionsClear();
}

function getTestCaseResult(expected, actual)
{
  if (typeof expected != typeof actual)
    return false;
  if (typeof expected != 'number')
    // Note that many tests depend on the use of '==' here, not '==='.
    return actual == expected;

  // Distinguish NaN from other values.  Using x != x comparisons here
  // works even if tests redefine isNaN.
  if (actual != actual)
    return expected != expected;
  if (expected != expected)
    return false;

  // Tolerate a certain degree of error.
  if (actual != expected)
    return Math.abs(actual - expected) <= 1E-10;

  // Here would be a good place to distinguish 0 and -0, if we wanted
  // to.  However, doing so would introduce a number of failures in
  // areas where they don't seem important.  For example, the WeekDay
  // function in ECMA-262 returns -0 for Sundays before the epoch, but
  // the Date functions in SpiderMonkey specified in terms of WeekDay
  // often don't.  This seems unimportant.
  return true;
}

if (typeof dump == 'undefined')
{
  if (typeof window == 'undefined' &&
      typeof print == 'function')
  {
    dump = print;
  }
  else
  {
    dump = (function () {});
  }
}

function test() {
  for ( gTc=0; gTc < gTestcases.length; gTc++ ) {
    // temporary hack to work around some unknown issue in 1.7
    try
    {
      gTestcases[gTc].passed = writeTestCaseResult(
        gTestcases[gTc].expect,
        gTestcases[gTc].actual,
        gTestcases[gTc].description +" = "+ gTestcases[gTc].actual );
      gTestcases[gTc].reason += ( gTestcases[gTc].passed ) ? "" : "wrong value ";
    }
    catch(e)
    {
      print('test(): empty testcase for gTc = ' + gTc + ' ' + e);
    }
  }
  stopTest();
  return ( gTestcases );
}

/*
 * Begin printing functions.  These functions use the shell's
 * print function.  When running tests in the browser, these
 * functions, override these functions with functions that use
 * document.write.
 */

function writeTestCaseResult( expect, actual, string ) {
  var passed = getTestCaseResult( expect, actual );
  // if running under reftest, let it handle result reporting.
  if (typeof document != "object" ||
      !document.location.href.match(/jsreftest.html/)) {
    writeFormattedResult( expect, actual, string, passed );
  }
  return passed;
}
function writeFormattedResult( expect, actual, string, passed ) {
  var s = ( passed ? PASSED : FAILED ) + string + ' expected: ' + expect;
  print(s);
  return passed;
}

function writeHeaderToLog( string ) {
  print( string );
}
/* end of print functions */


/*
 * When running in the shell, run the garbage collector after the
 * test has completed.
 */

function stopTest() {
  var gc;
  if ( gc != undefined ) {
    gc();
  }
}

/*
 * Convenience function for displaying failed test cases.  Useful
 * when running tests manually.
 *
 */
function getFailedCases() {
  for ( var i = 0; i < gTestcases.length; i++ ) {
    if ( ! gTestcases[i].passed ) {
      print( gTestcases[i].description + " = " +gTestcases[i].actual +
             " expected: " + gTestcases[i].expect );
    }
  }
}

function jsTestDriverEnd()
{
  // gDelayTestDriverEnd is used to
  // delay collection of the test result and
  // signal to Spider so that tests can continue
  // to run after page load has fired. They are
  // responsible for setting gDelayTestDriverEnd = true
  // then when completed, setting gDelayTestDriverEnd = false
  // then calling jsTestDriverEnd()

  if (gDelayTestDriverEnd)
  {
    return;
  }

  try
  {
    optionsReset();
  }
  catch(ex)
  {
    dump('jsTestDriverEnd ' + ex);
  }

  for (var i = 0; i < gTestcases.length; i++)
  {
    gTestcases[i].dump();
  }

}

function jit(on)
{
}

function assertEqArray(a1, a2) {
  assertEq(a1.length, a2.length);
  for (var i = 0; i < a1.length; i++) {
    try {
      assertEq(a1[i], a2[i]);
    } catch (e) {
      throw new Error("At index " + i + ": " + e);
    }
  }
}

function assertThrows(f) {
    var ok = false;
    try {
        f();
    } catch (exc) {
        ok = true;
    }
    if (!ok)
        throw new Error("Assertion failed: " + f + " did not throw as expected");
}

/*
 * Some tests need to know if we are in Rhino as opposed to SpiderMonkey
 */
function inRhino()
{
  return (typeof defineClass == "function");
}

/* these functions are useful for running tests manually in Rhino */

function GetContext() {
  return Packages.com.netscape.javascript.Context.getCurrentContext();
}
function OptLevel( i ) {
  i = Number(i);
  var cx = GetContext();
  cx.setOptimizationLevel(i);
}
/* end of Rhino functions */

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

/*
 * The current crop of Test262 test cases that we run are expected to pass
 * unless they crash or throw.  (This isn't true for all Test262 test cases --
 * for the ones marked @negative the logic is inverted.  We'll have to deal with
 * that concern eventually, but for now we're punting so we can run subsets of
 * Test262 tests.)
 */
testPassesUnlessItThrows();

/*
 * Test262 function $ERROR throws an error with the message provided. Test262
 * test cases call it to indicate failure.
 */
function $ERROR(msg)
{
  
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


// The loop count at which we trace
const RECORDLOOP = this.tracemonkey ? tracemonkey.HOTLOOP : 8;
// The loop count at which we run the trace
const RUNLOOP = RECORDLOOP + 1;

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


// NOTE: This only turns on 1.8.5 in shell builds.  The browser requires the
//       futzing in js/src/tests/browser.js (which only turns on 1.8, the most
//       the browser supports).
if (typeof version != 'undefined')
  version(185);

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

// The nearest representable values to +1.0.
const ONE_PLUS_EPSILON = 1 + Math.pow(2, -52);  // 0.9999999999999999
const ONE_MINUS_EPSILON = 1 - Math.pow(2, -53);  // 1.0000000000000002


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





/* -*- indent-tabs-mode: nil; js-indent-level: 2 -*- */
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */


/*
 * Date functions used by tests in Date suite
 *
 */
var msPerYear =  31536000000;
var msPerDay =   86400000;
var HoursPerDay =  24;
var MinutesPerHour = 60;
var SecondsPerMinute = 60;
var msPerSecond =  1000;
var msPerMinute =  60000;  // msPerSecond * SecondsPerMinute
var msPerHour =   3600000; // msPerMinute * MinutesPerHour

var TZ_PST = -8;  // offset of Pacific Standard Time from UTC
var TZ_DIFF_RAW = GetRawTimezoneOffset();  // raw offset of tester's timezone from UTC
var TZ_DIFF = GetTimezoneOffset();  // offset of tester's timezone from UTC
var PST_DIFF_RAW = TZ_DIFF_RAW - TZ_PST;  // raw offset of tester's timezone from PST
var PST_DIFF = TZ_DIFF - TZ_PST;  // offset of tester's timezone from PST
var TZ_ADJUST  = TZ_DIFF_RAW * msPerHour;
var PST_ADJUST = TZ_PST * msPerHour;

var DST_PERIOD = DaylightSavingPeriod();  // time period when DST is used
var DST_1970 = DaylightSavingObserved(1970);  // Was DST observed in 1970?
var DST_1971 = DaylightSavingObserved(1971);  // Was DST observed in 1971?

var TIME_0000  = (function ()
  { // calculate time for year 0
    for ( var time = 0, year = 1969; year >= 0; year-- ) {
      time -= TimeInYear(year);
    }
    return time;
  })();
var TIME_1970  = 0;
var TIME_2000  = 946684800000;
var TIME_1900  = -2208988800000;
var UTC_FEB_29_2000 = TIME_2000 + 31*msPerDay + 28*msPerDay;
var UTC_JAN_1_2005 = TIME_2000 + TimeInYear(2000) + TimeInYear(2001) +
  TimeInYear(2002) + TimeInYear(2003) + TimeInYear(2004);
var now = new Date();
var TIME_NOW = now.valueOf();  //valueOf() is to accurate to the millisecond
                               //Date.parse() is accurate only to the second

/*
 * Originally, the test suite used a hard-coded value TZ_DIFF = -8.
 * But that was only valid for testers in the Pacific Standard Time Zone!
 * We calculate the proper number dynamically for any tester. We just
 * have to be careful not to use a date subject to Daylight Savings Time...
 */
function GetRawTimezoneOffset()
{
  var t1 = new Date(2000, 1, 1).getTimezoneOffset();
  var t2 = new Date(2000, 1 + 6, 1).getTimezoneOffset();
  if ((t1 - t2) >= 0) {
    // 1) timezone without daylight saving time
    // 2) northern hemisphere with daylight saving time
    return -t1 / MinutesPerHour;
  } else {
    // 3) southern hemisphere with daylight saving time
    return -t2 / MinutesPerHour;
  }
}

/*
 * Returns the timezone offset, possibly including daylight saving time.
 * (This function is only used to obtain the relative timezone offset to PST,
 * see TZ_DIFF and PST_DIFF in adjustResultArray().)
 */
function GetTimezoneOffset()
{
  return -(new Date(2000, 1, 1).getTimezoneOffset()) / MinutesPerHour;
}

/*
 * Determine when daylight saving time is used in the current timezone.
 */
function DaylightSavingPeriod()
{
  var t1 = new Date(2000, 1, 1).getTimezoneOffset();
  var t2 = new Date(2000, 1 + 6, 1).getTimezoneOffset();
  if (t1 == t2) {
    // timezone without daylight saving time
    return 0;
  } else if ((t1 - t2) > 0) {
    // northern hemisphere with daylight saving time
    return 1;
  } else {
    // southern hemisphere with daylight saving time
    return -1;
  }
}

/*
 * Test whether daylight time saving was observed in the supplied year
 */
function DaylightSavingObserved(y)
{
  var t1 = new Date(y, 1, 1).getTimezoneOffset();
  var t2 = new Date(y, 1 + 6, 1).getTimezoneOffset();
  return (t1 != t2);
}

/*
 * Don't apply DST near start of epoch unless absolutely necessary
 */
function IgnoreDaylightSaving(t)
{
  if ((0 <= t && t < msPerYear) && !DST_1970) {
    return true;
  }
  if ((msPerYear <= t && t < 2*msPerYear) && !DST_1971) {
    return true;
  }
  return false;
}

/*
 * Date test "ResultArrays" are hard-coded for Pacific Standard Time.
 * We must adjust them for the tester's own timezone -
 */
function adjustResultArray(ResultArray, msMode)
{
  // If the tester's system clock is in PST, no need to continue -
//  if (!PST_DIFF) {return;}

  /* The date testcases instantiate Date objects in two different ways:
   *
   *        millisecond mode: e.g.   dt = new Date(10000000);
   *        year-month-day mode:  dt = new Date(2000, 5, 1, ...);
   *
   * In the first case, the date is measured from Time 0 in Greenwich (i.e. UTC).
   * In the second case, it is measured with reference to the tester's local timezone.
   *
   * In the first case we must correct those values expected for local measurements,
   * like dt.getHours() etc. No correction is necessary for dt.getUTCHours() etc.
   *
   * In the second case, it is exactly the other way around -
   */
  if (msMode)
  {
    // The hard-coded UTC milliseconds from Time 0 derives from a UTC date.
    // Shift to the right by the offset between UTC and the tester.
    if (IgnoreDaylightSaving(ResultArray[TIME])) {
      var t = ResultArray[TIME]  +  TZ_DIFF_RAW*msPerHour;
    } else {
      var t = ResultArray[TIME]  +  TZ_DIFF*msPerHour;
    }

    // Use our date arithmetic functions to determine the local hour, day, etc.
    ResultArray[HOURS] = HourFromTime(t);
    ResultArray[DAY] = WeekDay(t);
    ResultArray[DATE] = DateFromTime(t);
    ResultArray[MONTH] = MonthFromTime(t);
    ResultArray[YEAR] = YearFromTime(t);
  }
  else
  {
    // The hard-coded UTC milliseconds from Time 0 derives from a PST date.
    // Shift to the left by the offset between PST and the tester.
    var t = ResultArray[TIME]  -  PST_DIFF*msPerHour;

    // Use our date arithmetic functions to determine the UTC hour, day, etc.
    ResultArray[TIME] = t;
    ResultArray[UTC_HOURS] = HourFromTime(t);
    ResultArray[UTC_DAY] = WeekDay(t);
    ResultArray[UTC_DATE] = DateFromTime(t);
    ResultArray[UTC_MONTH] = MonthFromTime(t);
    ResultArray[UTC_YEAR] = YearFromTime(t);
  }
}

function Day( t ) {
  return ( Math.floor(t/msPerDay ) );
}
function DaysInYear( y ) {
  if ( y % 4 != 0 ) {
    return 365;
  }
  if ( (y % 4 == 0) && (y % 100 != 0) ) {
    return 366;
  }
  if ( (y % 100 == 0) && (y % 400 != 0) ) {
    return 365;
  }
  if ( (y % 400 == 0) ){
    return 366;
  } else {
    return "ERROR: DaysInYear(" + y + ") case not covered";
  }
}
function TimeInYear( y ) {
  return ( DaysInYear(y) * msPerDay );
}
function DayNumber( t ) {
  return ( Math.floor( t / msPerDay ) );
}
function TimeWithinDay( t ) {

  var r = t % msPerDay;

  if (r < 0)
  {
    r += msPerDay;
  }
  return r;

}
function YearNumber( t ) {
}
function TimeFromYear( y ) {
  return ( msPerDay * DayFromYear(y) );
}
function DayFromYear( y ) {
  return ( 365*(y-1970) +
           Math.floor((y-1969)/4) -
           Math.floor((y-1901)/100) +
           Math.floor((y-1601)/400) );
}
function InLeapYear( t ) {
  if ( DaysInYear(YearFromTime(t)) == 365 ) {
    return 0;
  }
  if ( DaysInYear(YearFromTime(t)) == 366 ) {
    return 1;
  } else {
    return "ERROR:  InLeapYear("+ t + ") case not covered";
  }
}
function YearFromTime( t ) {
  t = Number( t );
  var sign = ( t < 0 ) ? -1 : 1;
  var year = ( sign < 0 ) ? 1969 : 1970;
  for ( var timeToTimeZero = t; ;  ) {
    // subtract the current year's time from the time that's left.
    timeToTimeZero -= sign * TimeInYear(year)

      // if there's less than the current year's worth of time left, then break.
      if ( sign < 0 ) {
        if ( sign * timeToTimeZero <= 0 ) {
          break;
        } else {
          year += sign;
        }
      } else {
        if ( sign * timeToTimeZero < 0 ) {
          break;
        } else {
          year += sign;
        }
      }
  }
  return ( year );
}
function MonthFromTime( t ) {
  // i know i could use switch but i'd rather not until it's part of ECMA
  var day = DayWithinYear( t );
  var leap = InLeapYear(t);

  if ( (0 <= day) && (day < 31) ) {
    return 0;
  }
  if ( (31 <= day) && (day < (59+leap)) ) {
    return 1;
  }
  if ( ((59+leap) <= day) && (day < (90+leap)) ) {
    return 2;
  }
  if ( ((90+leap) <= day) && (day < (120+leap)) ) {
    return 3;
  }
  if ( ((120+leap) <= day) && (day < (151+leap)) ) {
    return 4;
  }
  if ( ((151+leap) <= day) && (day < (181+leap)) ) {
    return 5;
  }
  if ( ((181+leap) <= day) && (day < (212+leap)) ) {
    return 6;
  }
  if ( ((212+leap) <= day) && (day < (243+leap)) ) {
    return 7;
  }
  if ( ((243+leap) <= day) && (day < (273+leap)) ) {
    return 8;
  }
  if ( ((273+leap) <= day) && (day < (304+leap)) ) {
    return 9;
  }
  if ( ((304+leap) <= day) && (day < (334+leap)) ) {
    return 10;
  }
  if ( ((334+leap) <= day) && (day < (365+leap)) ) {
    return 11;
  } else {
    return "ERROR: MonthFromTime("+t+") not known";
  }
}
function DayWithinYear( t ) {
  return( Day(t) - DayFromYear(YearFromTime(t)));
}
function DateFromTime( t ) {
  var day = DayWithinYear(t);
  var month = MonthFromTime(t);

  if ( month == 0 ) {
    return ( day + 1 );
  }
  if ( month == 1 ) {
    return ( day - 30 );
  }
  if ( month == 2 ) {
    return ( day - 58 - InLeapYear(t) );
  }
  if ( month == 3 ) {
    return ( day - 89 - InLeapYear(t));
  }
  if ( month == 4 ) {
    return ( day - 119 - InLeapYear(t));
  }
  if ( month == 5 ) {
    return ( day - 150- InLeapYear(t));
  }
  if ( month == 6 ) {
    return ( day - 180- InLeapYear(t));
  }
  if ( month == 7 ) {
    return ( day - 211- InLeapYear(t));
  }
  if ( month == 8 ) {
    return ( day - 242- InLeapYear(t));
  }
  if ( month == 9 ) {
    return ( day - 272- InLeapYear(t));
  }
  if ( month == 10 ) {
    return ( day - 303- InLeapYear(t));
  }
  if ( month == 11 ) {
    return ( day - 333- InLeapYear(t));
  }

  return ("ERROR:  DateFromTime("+t+") not known" );
}
function WeekDay( t ) {
  var weekday = (Day(t)+4) % 7;
  return( weekday < 0 ? 7 + weekday : weekday );
}

// missing daylight savings time adjustment

function HourFromTime( t ) {
  var h = Math.floor( t / msPerHour ) % HoursPerDay;
  return ( (h<0) ? HoursPerDay + h : h  );
}
function MinFromTime( t ) {
  var min = Math.floor( t / msPerMinute ) % MinutesPerHour;
  return( ( min < 0 ) ? MinutesPerHour + min : min  );
}
function SecFromTime( t ) {
  var sec = Math.floor( t / msPerSecond ) % SecondsPerMinute;
  return ( (sec < 0 ) ? SecondsPerMinute + sec : sec );
}
function msFromTime( t ) {
  var ms = t % msPerSecond;
  return ( (ms < 0 ) ? msPerSecond + ms : ms );
}
function LocalTZA() {
  return ( TZ_DIFF_RAW * msPerHour );
}
function UTC( t ) {
  return ( t - LocalTZA() - DaylightSavingTA(t - LocalTZA()) );
}
function LocalTime( t ) {
  return ( t + LocalTZA() + DaylightSavingTA(t) );
}
function DaylightSavingTA( t ) {
  if (IgnoreDaylightSaving(t)) {
    return 0;
  }

  if (DST_PERIOD > 0) {
    // northern hemisphere
    var dst_start = GetDSTStart(t);
    var dst_end   = GetDSTEnd(t);

    if ( t >= dst_start && t < dst_end )
      return msPerHour;
  } else if (DST_PERIOD < 0) {
    // southern hemisphere
    var dst_start = GetDSTStart_Southern(t);
    var dst_end   = GetDSTEnd_Southern(t);

    if ( t >= dst_start && t < GetDSTEnd_Southern(t + msPerYear) )
      return msPerHour;
    if ( t < dst_end && t >= GetDSTEnd_Southern(t - msPerYear) )
      return msPerHour;
  }

  return 0;
}

function GetFirstSundayInMonth( t, m ) {
  var year = YearFromTime(t);
  var leap = InLeapYear(t);

// month m 0..11
// april == 3
// march == 2

  // set time to first day of month m
  var time = TimeFromYear(year);
  for (var i = 0; i < m; ++i)
  {
    time += TimeInMonth(i, leap);
  }

  for ( var first_sunday = time; WeekDay(first_sunday) > 0;
        first_sunday += msPerDay )
  {
    ;
  }

  return first_sunday;
}

function GetLastSundayInMonth( t, m ) {
  var year = YearFromTime(t);
  var leap = InLeapYear(t);

// month m 0..11
// april == 3
// march == 2

  // first day of following month
  var time = TimeFromYear(year);
  for (var i = 0; i <= m; ++i)
  {
    time += TimeInMonth(i, leap);
  }
  // prev day == last day of month
  time -= msPerDay;

  for ( var last_sunday = time; WeekDay(last_sunday) > 0;
        last_sunday -= msPerDay )
  {
    ;
  }
  return last_sunday;
}

/*
  15.9.1.9 Daylight Saving Time Adjustment

  The implementation of ECMAScript should not try to determine whether
  the exact time was subject to daylight saving time, but just whether
  daylight saving time would have been in effect if the current
  daylight saving time algorithm had been used at the time. This avoids
  complications such as taking into account the years that the locale
  observed daylight saving time year round.
*/

/*
  US DST algorithm

  Before 2007, DST starts first Sunday in April at 2 AM and ends last
  Sunday in October at 2 AM

  Starting in 2007, DST starts second Sunday in March at 2 AM and ends
  first Sunday in November at 2 AM

  Note that different operating systems behave differently.

  Fully patched Windows XP uses the 2007 algorithm for all dates while
  fully patched Fedora Core 6 and RHEL 4 Linux use the algorithm in
  effect at the time.

  Since pre-2007 DST is a subset of 2007 DST rules, this only affects
  tests that occur in the period Mar-Apr and Oct-Nov where the two
  algorithms do not agree.

*/

function GetDSTStart( t )
{
  return (GetFirstSundayInMonth(t, 2) + 7*msPerDay + 2*msPerHour - LocalTZA());
}

function GetDSTEnd( t )
{
  return (GetFirstSundayInMonth(t, 10) + 2*msPerHour - LocalTZA());
}

function GetOldDSTStart( t )
{
  return (GetFirstSundayInMonth(t, 3) + 2*msPerHour - LocalTZA());
}

function GetOldDSTEnd( t )
{
  return (GetLastSundayInMonth(t, 9) + 2*msPerHour - LocalTZA());
}

/*
 * Daylight saving time start/end date for 'Australia'
 * (arbitrarily chosen as a representative for the southern hemisphere)
 */

function GetDSTStart_Southern( t )
{
  return (GetFirstSundayInMonth(t, 9) + 2*msPerHour - LocalTZA());
}

function GetDSTEnd_Southern( t )
{
  return (GetFirstSundayInMonth(t, 3) + 2*msPerHour - LocalTZA());
}

function MakeTime( hour, min, sec, ms ) {
  if ( isNaN( hour ) || isNaN( min ) || isNaN( sec ) || isNaN( ms ) ) {
    return Number.NaN;
  }

  hour = ToInteger(hour);
  min  = ToInteger( min);
  sec  = ToInteger( sec);
  ms  = ToInteger( ms );

  return( (hour*msPerHour) + (min*msPerMinute) +
          (sec*msPerSecond) + ms );
}
function MakeDay( year, month, date ) {
  if ( isNaN(year) || isNaN(month) || isNaN(date) ) {
    return Number.NaN;
  }
  year = ToInteger(year);
  month = ToInteger(month);
  date = ToInteger(date );

  var sign = ( year < 1970 ) ? -1 : 1;
  var t =    ( year < 1970 ) ? 1 :  0;
  var y =    ( year < 1970 ) ? 1969 : 1970;

  var result5 = year + Math.floor( month/12 );
  var result6 = month % 12;

  if ( year < 1970 ) {
    for ( y = 1969; y >= year; y += sign ) {
      t += sign * TimeInYear(y);
    }
  } else {
    for ( y = 1970 ; y < year; y += sign ) {
      t += sign * TimeInYear(y);
    }
  }

  var leap = InLeapYear( t );

  for ( var m = 0; m < month; m++ ) {
    t += TimeInMonth( m, leap );
  }

  if ( YearFromTime(t) != result5 ) {
    return Number.NaN;
  }
  if ( MonthFromTime(t) != result6 ) {
    return Number.NaN;
  }
  if ( DateFromTime(t) != 1 ) {
    return Number.NaN;
  }

  return ( (Day(t)) + date - 1 );
}
function TimeInMonth( month, leap ) {
  // september april june november
  // jan 0  feb 1  mar 2 apr 3 may 4  june 5  jul 6
  // aug 7  sep 8  oct 9 nov 10 dec 11

  if ( month == 3 || month == 5 || month == 8 || month == 10 ) {
    return ( 30*msPerDay );
  }

  // all the rest
  if ( month == 0 || month == 2 || month == 4 || month == 6 ||
       month == 7 || month == 9 || month == 11 ) {
    return ( 31*msPerDay );
  }

  // save february
  return ( (leap == 0) ? 28*msPerDay : 29*msPerDay );
}
function MakeDate( day, time ) {
  if ( day == Number.POSITIVE_INFINITY ||
       day == Number.NEGATIVE_INFINITY ) {
    return Number.NaN;
  }
  if ( time == Number.POSITIVE_INFINITY ||
       time == Number.NEGATIVE_INFINITY ) {
    return Number.NaN;
  }
  return ( day * msPerDay ) + time;
}
function TimeClip( t ) {
  if ( isNaN( t ) ) {
    return ( Number.NaN );
  }
  if ( Math.abs( t ) > 8.64e15 ) {
    return ( Number.NaN );
  }

  return ( ToInteger( t ) );
}
function ToInteger( t ) {
  t = Number( t );

  if ( isNaN( t ) ){
    return ( Number.NaN );
  }
  if ( t == 0 || t == -0 ||
       t == Number.POSITIVE_INFINITY || t == Number.NEGATIVE_INFINITY ) {
    return 0;
  }

  var sign = ( t < 0 ) ? -1 : 1;

  return ( sign * Math.floor( Math.abs( t ) ) );
}
function Enumerate ( o ) {
  var p;
  for ( p in o ) {
    print( p +": " + o[p] );
  }
}

/* these functions are useful for running tests manually in Rhino */

function GetContext() {
  return Packages.com.netscape.javascript.Context.getCurrentContext();
}
function OptLevel( i ) {
  i = Number(i);
  var cx = GetContext();
  cx.setOptimizationLevel(i);
}
/* end of Rhino functions */



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

