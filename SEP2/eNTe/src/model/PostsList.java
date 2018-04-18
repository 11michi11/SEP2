package model;

import java.util.ArrayList;
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

	public Post getFirstPost() {
		return posts.getFirst();
	}

	public void add(Post post) {
		posts.add(post);
	}

	public void addAll(List<Post> list) {
		for (Post e : list)
			posts.add(e);
	}

	public ArrayList<Post> getAll() {
		ArrayList<Post> list = new ArrayList<>();
		list.addAll(posts);
		return list;
	}

}
