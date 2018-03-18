package server.model.persistance;

import java.util.LinkedList;

import model.Post;
import model.User;

public interface DBAdapter {

	public LinkedList<Post> getPosts();
	public LinkedList<User> getUsers();
	 
}
