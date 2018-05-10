package server.model.persistance;

import java.sql.SQLException;
import java.util.LinkedList;

import client.model.FamiliesList;
import model.Administrator;
import model.Family;
import model.Parent;
import model.Post;
import model.Student;
import model.Teacher;
import model.User;
import model.UsersList;

public interface DBPersistance {

	public LinkedList<Post> getPosts(UsersList users) throws SQLException;
	public LinkedList<User> getUsers(FamiliesList families) throws SQLException;
	public void addUser(User user) throws SQLException;
	public void updateUser(User user) throws SQLException;
	public void deleteUser(String id) throws SQLException;
	public void addFamily(Family family) throws SQLException;
	public void deleteFamily(Family family) throws SQLException;
	public LinkedList<Family> getFamilies() throws SQLException;
}
