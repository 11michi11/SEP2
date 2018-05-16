package server.model.persistance;

import model.*;

import java.util.LinkedList;

public interface DBPersistence {

	LinkedList<Post> getPosts(UsersList users);
	LinkedList<User> getUsers(FamilyList families);
	void addUser(User user);
	void updateUser(User user);
	void deleteUser(String id);
	void addFamily(Family family);
	void deleteFamily(Family family);
	LinkedList<Family> getFamilies();
}
