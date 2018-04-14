package model;

import java.util.LinkedList;
import java.util.List;

public class PostsList {

	private LinkedList<Post> posts;
	
	public PostsList() {
		posts = new LinkedList<Post>();
	}
	
	public Post getNextPost() {
		return posts.removeFirst();
	}

	public void add(Post post) {
		posts.add(post);
	}

	public void add(List<Post> list) {
		for(Post e : list)
			posts.add(e);
	}
	
}
