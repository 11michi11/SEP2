package server.model.persistance;

import model.*;

import java.util.LinkedList;

public interface DBPersistence {

	LinkedList<Family> getFamilies();
	LinkedList<User> getUsers(FamilyList families);
	LinkedList<Post> getPosts(UsersList users);
	void addFamily(Family family);
	void addUser(User user);
	void addPost(Post post);
	void addHomeworkReply(String homeworkId, HomeworkReply reply);
	void updateUser(User user);
	void updatePost(Post post);
	void updateHomeworkReply(HomeworkReply reply);
	void deleteFamily(Family family);
	void deleteUser(String id);
	void deletePost(String postID);
}
