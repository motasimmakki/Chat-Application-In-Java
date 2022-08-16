package com.hp.gagawa.test.elements;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.hp.gagawa.java.elements.Comment;

public class CommentTest {

	/**
	 * If this is failing, it probably means that the builder ran and did not create the proper constructor and/or write method
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception{
		Comment comment = new Comment();
		comment.appendText("Testing");
		assertEquals("<!-- >Testing< -->",comment.write());
		
		Comment comment1 = new Comment("Testing");
		assertEquals("<!-- >Testing< -->",comment1.write());
	}
}
