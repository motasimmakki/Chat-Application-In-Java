package com.hp.gagawa.test.elements;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hp.gagawa.java.Node;
import com.hp.gagawa.java.elements.Br;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Hr;




public class DivTest {

	@Test
	public void test() throws Exception {
		Div div = new Div().appendText("Hello World");
		assertEquals("","<div>Hello World</div>",div.write());
		
		div.setCSSClass("myClass").appendText("!!!");
		assertEquals("","<div class=\"myClass\">Hello World!!!</div>",div.write());
		
		div.removeChildren();
		assertEquals("","<div class=\"myClass\"></div>",div.write());
		
		Hr hr = new Hr();
		div.appendChild(new Br(),hr, new Br());
		assertEquals("","<div class=\"myClass\"><br><hr><br></div>",div.write());
		
		div.removeChild(hr);
		assertEquals("","<div class=\"myClass\"><br><br></div>",div.write());
		
		div.removeChildren();
		assertEquals("","<div class=\"myClass\"></div>",div.write());
		
		List<Node> children = new ArrayList<Node>();
		children.add(new Br());
		children.add(hr);
		children.add(new Br());
		div.appendChild(children);
		assertEquals("","<div class=\"myClass\"><br><hr><br></div>",div.write());
		
		div.removeChildren();
		assertEquals("","<div class=\"myClass\"></div>",div.write());
		
		div.appendChild(new Div().appendChild(new Div()));
		assertEquals("","<div class=\"myClass\"><div><div></div></div></div>",div.write());
		
		div.removeChildren();
		assertEquals("","<div class=\"myClass\"></div>",div.write());
	}
	
}
