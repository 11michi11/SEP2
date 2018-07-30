package server.model.persistance;

import model.*;

import java.util.List;

public interface DBPersistence {

	List<User> getUsers();
	List<Post> getPosts();
	void addFamily(Family family);
	void addUser(User user);
	void addPost(Post post);
	void addHomeworkReply(HomeworkReply reply);
	void updateUser(User user);
	void updatePost(Post post);
	void updateHomeworkReply(HomeworkReply reply);
	void deleteFamily(Family family);
	void deleteUser(User user);
	void deletePost(Post post);
}
