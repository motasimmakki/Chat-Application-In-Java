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

import com.hp.gagawa.java.Document;
import com.hp.gagawa.java.DocumentType;
import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.Br;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Form;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Input;
import com.hp.gagawa.java.elements.Option;
import com.hp.gagawa.java.elements.Select;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Title;


/**
 * A more complex example using Gagawa.  Illustrates
 * the usage of several element types and methods
 * including:
 *  
 *  Body, Br, Div, Form, Head, Html,
 *  Img, Input, Option, Select, Text,
 *  and Title
 * 
 * @author kolichko Mark Kolich
 *
 */
public class MoreComplexGagawaExample {
	
	// Some dropdown options.
	private static final String [][] DROPDOWN_OPTIONS = new String [][] {
		{"value 1","text 1"},
		{"value 2","text 2"},
		{"value 3","text 3"},
		{"value 4","text 4"},
		{"value 5","text 5"}
	};

	public static void main ( String [] args ) {
		
		Document document = new Document(DocumentType.XHTMLTransitional);
		
		document.head.appendChild( new Title().appendChild( new Text("Complex Example Title") ) );
		
		Body body = document.body;
		
		Div wrap = goAheadMakeMyDiv();
		body.appendChild( wrap );
		
		Img headerImage = new Img( "alt text",
				"http://www.example.com/someimage.jpg" );
		
		wrap.appendChild( headerImage );
		wrap.appendChild( new Br() );
		
		// A form with no action, err, a # action.
		Form form = new Form( "#" );
		form.setId( "myform" );
		form.setMethod( "post" );
		
		wrap.appendChild( form );
		
		form.appendChild( new Text("Make a selection:") );
		form.appendChild( new Br() );
		
		form.appendChild( buildDropDown( DROPDOWN_OPTIONS ) );
		form.appendChild( new Br() );
		
		// A submit button!
		Input submit = new Input();
		submit.setType( "submit" );
		submit.setValue( "Submit!" );
		form.appendChild( submit );
		
		// Output the HTML.
		System.out.println( document.write() );
		
	}
	
	
	/**
	 * Builds a select drop down, illustrating the usage of
	 * Select and Option.
	 * @param options
	 * @return
	 */
	public static Select buildDropDown ( String [][] options ) {
		
		Select select = new Select();
		
		select.setCSSClass( "someclass" );
		select.setName( "myselect" );
		select.setSize( "1" );
		select.setId( "myselectid" );
		
		for ( String [] option : options ) {
			
			String value = option[0];
			String text = option[1];
			
			Option opt = new Option();
			opt.setValue( value );
			opt.appendChild( new Text(text) );
			
			select.appendChild( opt );
			
		} /* for */
		
		return select;
		
	}
	
	
	/**
	 * Makes a wrapper div.
	 * @return
	 */
	public static Div goAheadMakeMyDiv ( ) {
		
		Div wrap = new Div();
		wrap.setCSSClass( "wrapper" );
		wrap.setId( "wrap" );
		
		return wrap;
		
	}
	

}
