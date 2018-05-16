package test;

import model.MyDate;
import model.Post;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PostTest {

	@Test
	void testGetContent() {
		MyDate pubDate = MyDate.now();
		Post post = new Post("title", "content", "Author", pubDate);
		assertEquals("content", post.getContent());
	}
	
	@Test
	void testEquals() {
		MyDate pubDate = MyDate.now();
		Post post1 = new Post("title", "content", "Author", pubDate);
		Post post2 = new Post("title", "content", "Author", pubDate);
		Post post3 = new Post("title1", "content", "Author", pubDate);
		Post post4 = new Post("title", "content1", "Author", pubDate);


		assertEquals(post1, post2);
		assertEquals(post2, post1);

		assertNotEquals(post1, post3);
		assertNotEquals(post1, post4);
		assertNotEquals(post4, post3);
		assertNotEquals(post1, new Object());
	}

}
