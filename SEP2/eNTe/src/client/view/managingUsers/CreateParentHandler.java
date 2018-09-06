package client.view.managingUsers;

import java.io.IOException;

import client.controller.ClientController;
import client.view.ClientViewManager;
import client.view.GoBackMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Family;

public class CreateParentHandler {

	private ClientController controller;
	private Stage stage;
	@FXML
	private TextField name, email;
	private Family family;
	private model.Parent parent;
	
	public CreateParentHandler() {
		controller = ClientController.getInstance();
		System.out.println("CreateParentHandler");
		stage = ClientViewManager.getStage();
	}

	public void save() {
		if (checkForNull()) {
			warningDialog();
		} else {
			String id = null, pwd = null;
			if (parent != null)
				id = parent.getId();
			if (parent != null)
				pwd = parent.getPwd();
			controller.addParent(name.getText(), email.getText(), family, id, pwd);
			goBack();
		}
	}

	public void goBack() {
		String path = GoBackMap.getLoader(this.getClass(), controller.getCurrentUserType());
		FXMLLoader backLoader = new FXMLLoader(getClass().getResource(path));
		try {
			Parent mainPane = backLoader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
			stage.getScene().setRoot(mainPane);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	private boolean checkForNull() {
		if (name.getText() == null || email.getText() == null) {
			return true;
		} else {
			return false;
		}
	}

	private void warningDialog() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning Dialog");
		alert.setHeaderText("Look, unfinished selection");
		alert.setContentText("Fill out everything, please");
		alert.showAndWait();
	}

}
