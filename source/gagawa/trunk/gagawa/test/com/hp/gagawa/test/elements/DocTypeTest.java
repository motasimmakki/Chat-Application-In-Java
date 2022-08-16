package com.hp.gagawa.test.elements;

import static org.junit.Assert.assertEquals;

import com.hp.gagawa.java.elements.Doctype;
import com.hp.gagawa.java.DocumentType;
import org.junit.Test;

public class DocTypeTest {

	/**
	 * If this is failing, it probably means that the builder ran and did not create the proper constructor and/or write method
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception{
		Doctype doctype = new Doctype(DocumentType.HTMLTransitional);
		assertEquals("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">",doctype.write());
	}
}
