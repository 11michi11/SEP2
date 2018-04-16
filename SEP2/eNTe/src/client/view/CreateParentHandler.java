package client.view;

import java.io.IOException;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CreateParentHandler {

	private ClientController controller;
	private Stage stage;
	@FXML
	private TextField name, email;
	@FXML
	private ImageView ente;
	private Parent mainPane;
	private FXMLLoader loader;

	public CreateParentHandler() {
		controller = ClientController.getInstance();
		System.out.println("CreateParentHandler");
		stage = ClientViewManager.getStage();
		loader = new FXMLLoader(getClass().getResource("/client/view/parentList.fxml"));
		try {
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void save() {
		ParentListHandler handler = loader.getController();
		String[] parentInfo = {name.getText(), email.getText()};
		handler.passParent(parentInfo);
		goBack();
	}

	public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}

}
