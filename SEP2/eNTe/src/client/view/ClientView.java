package client.view;

import client.controller.ClientController;

public interface ClientView {

	void showPosts(String user);
	void startView();
	void showMessage(String message);
	boolean showDeleteMessage(String message);
	void changePasswordDialog();
	void setController(ClientController controller);
}
