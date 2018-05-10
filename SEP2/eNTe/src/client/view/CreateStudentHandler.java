package client.view;

import java.io.IOException;
import java.net.PasswordAuthentication;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CreateStudentHandler {

	private ClientController controller;
	private Stage stage;
	@FXML
	private TextField name, email;
	@FXML
	private ChoiceBox classSelector;
	@FXML
	private ImageView ente;
	private Parent mainPane;
	private FXMLLoader loader;

	public CreateStudentHandler() {
		controller = ClientController.getInstance();
		System.out.println("CreateStudentHandler");
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
		
		controller.addStudent(name.getText(), email.getText(), classSelector.);
		FamilyListHandler handler = loader.getController();
		String[] studentInfo = {name.getText(), email.getText(), classSelector.getTypeSelector()};
		handler.passStudent(studentInfo);

		goBack();
	}

	public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}
	
	public void setFamily(){}
}
