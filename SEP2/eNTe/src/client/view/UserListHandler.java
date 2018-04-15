package client.view;

import java.io.IOException;

import client.controller.ClientController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class UserListHandler {
	
	private ClientController controller;
	private Stage stage;
	
	public UserListHandler() {
		controller = ClientController.getInstance();
		System.out.println("UserListHandler");
		stage = ClientViewManager.getStage();
	}
	

	public void loadCreateUserPane() {
		Parent mainPane;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/createUser.fxml"));
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
			stage.getScene().setRoot(mainPane);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
}

}
