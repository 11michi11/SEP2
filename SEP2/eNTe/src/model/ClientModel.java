package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import client.controller.ClientController;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.communication.WelcomingData;

public interface ClientModel {

	void setController(ClientController controller);
	void storePost(Post post);
	void addPost(String title, String content);
	Post getPost();
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
}
