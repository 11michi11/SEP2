package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.NoSuchElementException;

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
		Post post1 = new Post("title", "content");
		Post post2 = new Post("title", "content");
		Post post3 = new Post("title", "content");
		
		posts.add(post1);
		posts.add(post2);
		posts.add(post3);
		
		assertEquals(post1, posts.getFirstPost());
		assertEquals(post1, posts.getFirstPost());	
	}
	
	@Test
	void testGetNextPost() {
		Post post1 = new Post("title", "content");
		posts.add(post1);
		assertEquals(post1, posts.getNextPost());
		assertThrows(NoSuchElementException.class, () -> posts.getNextPost());
	}
	
	@Test
	void testAddAll() {
		Post post1 = new Post("title", "content");
		Post post2 = new Post("title", "content");
		Post post3 = new Post("title", "content");
		ArrayList<Post> list = new ArrayList<>();
		list.add(post1);
		list.add(post2);
		list.add(post3);
		
		posts.addAll(list);
		
		assertEquals(list, posts.getAll());
	}

}
