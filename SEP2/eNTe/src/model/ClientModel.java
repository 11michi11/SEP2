package model;

import model.communication.WelcomingData;

public interface ClientModel {

	public void storePost(Post post);
	public Post getPost();
	public void saveData(WelcomingData data);
	public void addUser(User user);
	public void deleteUser(User user);
	public void login(String login, String pwd);
	
}
