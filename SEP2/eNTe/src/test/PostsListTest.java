package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import model.MyDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Post;
import model.PostsList;

class PostsListTest {

	private PostsList posts;
	
	@BeforeEach
	void setUp() {
		posts = new PostsList();
	}
	
	
	@Test
	void testGetFirstPost() {
		MyDate pubDate = MyDate.now();
		Post post1 = new Post("title", "content", "Author", pubDate);
		Post post2 = new Post("title", "content", "Author", pubDate);
		Post post3 = new Post("title", "content", "Author", pubDate);
		
		posts.add(post1);
		posts.add(post2);
		posts.add(post3);
		
		assertEquals(post1, posts.getFirstPost());
		assertEquals(post1, posts.getFirstPost());	
	}
	
	@Test
	void testGetNextPost() {
		MyDate pubDate = MyDate.now();
		Post post1 = new Post("title", "content", "Author", pubDate);
		posts.add(post1);
		assertEquals(post1, posts.getNextPost());
		assertThrows(NoSuchElementException.class, () -> posts.getNextPost());
	}
	
	@Test
	void testAddAll() {
		MyDate pubDate = MyDate.now();
		Post post1 = new Post("title", "content", "Author", pubDate);
		Post post2 = new Post("title", "content", "Author", pubDate);
		Post post3 = new Post("title", "content", "Author", pubDate);
		ArrayList<Post> list = new ArrayList<>();
		list.add(post1);
		list.add(post2);
		list.add(post3);
		
		posts.addAll(list);
		
		assertEquals(list, posts.getAll());
	}

}
