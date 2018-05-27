
package model;

import java.util.ArrayList;
import java.util.List;

import client.controller.ClientController;
import model.communication.WelcomingData;

public interface ClientModel {

	void setController(ClientController controller);
	void storePost(Post post);
	void addPost(String title, String content, String author, MyDate publicationDate);
	Post getPost();
	Homework getHomework();
	Discussion getDiscussion();
	void saveData(WelcomingData data);
	void addOrUpdateUser(User user);
	void deleteUser(String id);
	void deleteUser(User user);
	void login(String email, String pwd);
	ArrayList<Parent> getParents();
	ArrayList<Family> getAllFamilies();
    void deleteFamily(Family family);
	List<Teacher> getTeachers();
	void addFamily(Family family);
    boolean checkEmailForPwdReset(String email);
    void changePwdWithEmail(String email, String newPwd);
	void submitHomework(String text);
	ArrayList<Post> getAllPosts();
}
