/**
(c) Copyright 2008 Hewlett-Packard Development Company, L.P.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

package com.hp.gagawa.examples;

import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.Head;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Title;

/**
 * Uses Gagawa to build a very very simple web-page.
 * Demonstrates the usage of Html, Head, Title, Body,
 * and H1.
 * @author kolichko Mark Kolich
 *
 */
public class SimpleGagawaExample {

	/**
	 * @param args
	 */
	public static void main ( String [] args ) {
		
		Html html = new Html();
		Head head = new Head();
		
		html.appendChild( head );
		
		Title title = new Title();
		title.appendChild( new Text("Example Page Title") );
		head.appendChild( title );
		
		Body body = new Body();
		
		html.appendChild( body );
		
		H1 h1 = new H1();
		h1.appendChild( new Text("Example Page Header") );
		body.appendChild( h1 );
		
		System.out.println( html.write() );

	}

}
