package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PostsList {

	private LinkedList<Post> posts;
	private LinkedList<Homework> homeworks;
	private LinkedList<Discussion> discussions;

	public PostsList() {
		posts = new LinkedList<>();
	}

	public Post getNextPost() {
		//i don't like this method :/
		return posts.removeFirst();
	}

	public Post getFirstPost() {
		return posts.getFirst();
	}

	public void add(Post post) {
		posts.add(post);
	}

	public void addAll(List<Post> list) {
		posts.addAll(list);
	}

	public ArrayList<Post> getAll() {
		return new ArrayList<>(posts);
	}

	public Homework getFirstHomework() {
		return homeworks.getFirst();
	}

	public Discussion getFirstDiscussion() {
		return discussions.getFirst();
	}

}
