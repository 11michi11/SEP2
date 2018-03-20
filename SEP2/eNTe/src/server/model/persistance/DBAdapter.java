package server.model.persistance;

import java.sql.SQLException;
import java.util.LinkedList;

import model.Post;
import model.User;

public interface DBAdapter {

	public LinkedList<Post> getPosts() throws SQLException;
	public LinkedList<User> getUsers() throws SQLException;
	 
}
