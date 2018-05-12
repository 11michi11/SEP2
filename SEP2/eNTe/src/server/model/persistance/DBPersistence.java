package server.model.persistance;

import client.model.FamiliesList;
import model.Family;
import model.Post;
import model.User;
import model.UsersList;

import java.util.LinkedList;

public interface DBPersistence {

	LinkedList<Post> getPosts(UsersList users);
	LinkedList<User> getUsers(FamiliesList families);
	void addUser(User user);
	void updateUser(User user);
	void deleteUser(String id);
	void addFamily(Family family);
	void deleteFamily(Family family);
	LinkedList<Family> getFamilies();
}
