package client.view;

import client.controller.ClientController;
import model.Post;

public interface ClientView {

	public void showPosts(Post[] posts);
	public void startView();
	public void initialize(ClientController controller);
	public void startHandlers();
	
}
