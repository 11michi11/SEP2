package client.view.managingUsers;

import java.io.IOException;

import client.controller.ClientController;
import client.view.ClientViewManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Family;

public class CreateParentHandler {

	private ClientController controller;
	private Stage stage;
	@FXML
	private TextField name, email;
	@FXML
	private ImageView ente;
	private Parent mainPane;
	private FXMLLoader loader;
	private Family family;
	private model.Parent parent;
	
	public CreateParentHandler() {
		controller = ClientController.getInstance();
		System.out.println("CreateParentHandler");
		stage = ClientViewManager.getStage();
		loader = new FXMLLoader(getClass().getResource("/client/view/fxml/familyList.fxml"));
		try {
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		String id = null;
		if(parent != null)
			id = parent.getId();
		controller.addParent(name.getText(), email.getText(), family, id);
		goBack();
	}

	public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}
	
	public void setFamily(Family family) {
		this.family = family;
	}

	public void setParent(model.Parent parent) {
		this.parent = parent;
		family = parent.getFamily();
		name.setText(parent.getName());
		email.setText(parent.getEmail());
	}

}
