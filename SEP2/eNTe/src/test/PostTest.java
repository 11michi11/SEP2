package test;

import model.Post;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

class PostTest {

	@Test
	void testGetContent() {
		Post post = new Post("title", "content");
		assertEquals("content", post.getContent());
	}
	
	@Test
	void testEquals() {
		Post post1 = new Post("title", "content");
		Post post2 = new Post("title", "content");
		Post post3 = new Post("title1", "content");
		Post post4 = new Post("title", "content1");
		
		
		assertEquals(true, post1.equals(post2));
		assertEquals(true, post2.equals(post1));

		assertEquals(false, post1.equals(post3));
		assertEquals(false, post1.equals(post4));
		assertEquals(false, post4.equals(post3));
		assertEquals(false, post1.equals(new Object()));
	}

}
