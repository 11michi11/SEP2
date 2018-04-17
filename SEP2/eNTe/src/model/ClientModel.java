package model;

import java.util.ArrayList;

import client.controller.ClientController;
import model.communication.WelcomingData;

public interface ClientModel {

	public void storePost(Post post);
	public Post getPost();
	public void saveData(WelcomingData data);
	public void addOrUpdateUser(User user);
	public void deleteUser(String id);
	public void login(String login, String pwd);
	public ArrayList<Parent> getParents();
	public void setController(ClientController controller);
	
}
