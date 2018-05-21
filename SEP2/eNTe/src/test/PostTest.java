package test;

import model.MyDate;
import model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PostTest {

	private MyDate date;

	@BeforeEach
	void setUp() {
		MyDate date = new MyDate(2018, 8, 15);
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

	@Test
	void constructorTest() {
		Post post = new Post("id","title", "content", "author", date);
		assertEquals("id", post.getPostId());
		assertEquals("title", post.getTitle());
		assertEquals("content", post.getContent());
		assertEquals("author", post.getAuthor());
		assertEquals(date, post.getPubDate());
	}

	@Test
	void getTitleTest() {
		Post post = new Post("title", "content", "author", date);
		assertEquals("title", post.getTitle());
	}

	@Test
	void getContentTest() {
		Post post = new Post("title", "content", "author", date);
		assertEquals("content", post.getContent());
	}

	@Test
	void getAuthorTest() {
		Post post = new Post("title", "content", "author", date);
		assertEquals("author", post.getAuthor());
	}

	@Test
	void getPubDateTest() {
		Post post = new Post("title", "content", "author", date);
		assertEquals(date, post.getPubDate());
	}

	@Test
	void postIdTest() {
		Post post = new Post("id","title", "content", "author", date);
		assertEquals("id", post.getPostId());

	}

}
