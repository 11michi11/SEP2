package server.model.persistance;

import java.sql.SQLException;
import java.util.LinkedList;

import model.Administrator;
import model.Parent;
import model.Post;
import model.Student;
import model.Teacher;
import model.User;

public interface DBPersistance {

	public LinkedList<Post> getPosts() throws SQLException;
	public LinkedList<User> getUsers() throws SQLException;
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(String id);
	 
}
