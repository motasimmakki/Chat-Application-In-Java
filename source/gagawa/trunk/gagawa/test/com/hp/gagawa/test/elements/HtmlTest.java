package com.hp.gagawa.test.elements;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Html;

public class HtmlTest {

	/**
	 * If this is failing, it probably means that the builder ran and did not create the proper constructor and/or write method
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception{
		Html html = new Html();
		html.appendChild(new Div());
		assertEquals("<html xmlns=\"http://www.w3.org/1999/xhtml\"><div></div></html>", html.write());
	}
}
