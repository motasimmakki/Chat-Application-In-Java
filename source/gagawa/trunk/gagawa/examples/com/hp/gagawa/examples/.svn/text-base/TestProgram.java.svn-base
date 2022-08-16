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
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Br;
import com.hp.gagawa.java.elements.Comment;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Tr;


public class TestProgram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Document document = new Document(DocumentType.XHTMLStrict);

		Comment c1 = new Comment("This is part 1.");
		c1.appendChild(new Text("This is a comment!\n"));
		c1.appendChild(new Text("This is part 2!"));
		document.body.appendChild(c1);
		document.body.setBgcolor("#CCC");
		Div d1 = new Div().setId("myDiv");
		Div d2 = new Div().setCSSClass("mainClass");
		d1.appendChild(d2);
		d2.appendChild(new Text("Inside of div2"));
		d2.appendChild(new Br());
		d2.appendChild(new A("http://www.jumppage.com","_blank").appendChild(new Text("jumppage")));
		Img img = new Img("http://www.w3schools.com/tags/angry.gif","");
		d2.appendChild(new Br());
		d2.appendChild(img);
		document.body.appendChild(d1);
		
		Table table = new Table();
		
		int count = 0;
		for(int row = 0; row < 10; row++){
			Tr tr = new Tr();
			table.appendChild(tr);
			for(int col = 0; col < 10; col++){
				Td td = new Td();
				tr.appendChild(td);
				td.appendChild(new Text(count++));
			}
		}
		document.body.appendChild(table);
		
		d1.setStyle("float:left");
		
		/*try {
			File output = new File("test/test.html");
			PrintWriter out = new PrintWriter(new FileOutputStream(output));
			out.println(document.write());
			System.out.println(document.write());
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
