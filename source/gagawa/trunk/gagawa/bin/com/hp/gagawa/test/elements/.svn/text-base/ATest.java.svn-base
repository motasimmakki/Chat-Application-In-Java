package com.hp.gagawa.test.elements;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;

public class ATest {

	@Test
	public void test() throws Exception{
		A a = new A();
		assertEquals("<a></a>", a.write());
		
		a = new A("href");
		assertEquals("<a href=\"href\"></a>", a.write());
		
		a = new A(new Div());
		assertEquals("<a><div></div></a>", a.write());

		a = new A("href", "target");
		assertEquals("<a href=\"href\" target=\"target\"></a>", a.write());
		
		a = new A("href", new Div());
		assertEquals("<a href=\"href\"><div></div></a>", a.write());

		a = new A("href", "target", new Div());
		assertEquals("<a href=\"href\" target=\"target\"><div></div></a>", a.write());
		
		a = new A("href", "target","content", "cssClass");
		assertEquals("<a href=\"href\" target=\"target\" class=\"cssClass\">content</a>", a.write());
		
	}
}
