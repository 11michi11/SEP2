package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PostsList {

	private LinkedList<Post> posts;
	private transient Iterator<Post> iterator;
	
	public PostsList() {
		posts = new LinkedList<Post>();
		iterator = posts.iterator(); 
	}
	
	public Post getNextPost() {
		return iterator.next();
	}

	public void add(Post post) {
		posts.add(post);
	}

	public void add(List<Post> list) {
		for(Post e : list)
			posts.add(e);
	}
	
}
