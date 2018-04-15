package server.model.persistance;

import java.sql.SQLException;
import java.util.LinkedList;

import model.Post;
import model.User;

public interface DBAdapter {

	public LinkedList<Post> getPosts() throws SQLException;
	public LinkedList<User> getUsers() throws SQLException;
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(String string);
	 
}
