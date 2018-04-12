package model;

import model.proxy.WelcomingData;

public interface ClientModel {

	public void storePost(Post post);
	public Post getPost();
	public void saveData(WelcomingData data);
	
}
