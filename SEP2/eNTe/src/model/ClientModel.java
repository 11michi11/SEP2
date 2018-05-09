package model;

import java.util.ArrayList;

import client.controller.ClientController;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.communication.WelcomingData;

public interface ClientModel {

	void storePost(Post post);
	Post getPost();
	void saveData(WelcomingData data);
	void addOrUpdateUser(User user);
	void deleteUser(String id);
	void login(String login, String pwd);
	ArrayList<Parent> getParents();
	void setController(ClientController controller);
    void addPost(String title, String content);
    ArrayList<Family> getAllFamilies();
}
