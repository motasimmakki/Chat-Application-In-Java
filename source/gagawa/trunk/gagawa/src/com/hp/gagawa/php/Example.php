<?php

/*
 * (c) Copyright 2008 Hewlett-Packard Development Company, L.P.
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * 
 */

require_once("Gagawa.php");

$a = new A();

// Note you can daisy chain attribute setters.
$a->setHref( "http://kolich.com" )
	->setTarget("_blank")
	->setCSSClass("linkclass")
	/*->setAttribute("","") <-- will generate an expected exception */
	->setId("myid")
	->setAttribute("otherattr","other");

// Note you can daisy chain children setters.
$a->appendChild( new Text("random text") )->appendChild( new Br() )
	->appendChild( new Text("more text") )->appendChild( new Br() )
	/*->appendChild() <-- will generate an expected exception */
	->appendChild( new Text("gagawa!" ) );

echo $a->write() . "\n";

// Example of creating a new FertileNode without the helper classes
$div = new Div();
$div->setCSSClass("dog")->setId("mydiv");
$div->appendChild( new Text("inside of a div") );

$ab = new A();
$ab->setHref("http://example.com");
$ab->appendChild( new Text("link inside of a div!") );
$div->appendChild( $ab );

echo $div->write() . "\n";

?>
