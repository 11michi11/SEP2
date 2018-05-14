package client.view;

import client.controller.ClientController;

public interface ClientView {

	public void showPosts(String user);
	public void startView();
	public void showMessage(String message);
	void changePasswordDialog();
	void setController(ClientController controller);
}
