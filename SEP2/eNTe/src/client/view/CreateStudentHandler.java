package client.view;

import java.io.IOException;

import client.controller.ClientController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Classs;
import model.Family;

public class CreateStudentHandler {

	private ClientController controller;
	private Stage stage;
	@FXML
	private TextField name, email;
	@FXML
	private ChoiceBox<Classs> classSelector;
	@FXML
	private ImageView ente;
	private Parent mainPane;
	private FXMLLoader loader;
	private Family family;

	public CreateStudentHandler() {
		controller = ClientController.getInstance();
		System.out.println("CreateStudentHandler");
		stage = ClientViewManager.getStage();
	}
	
	@FXML
	public void initialize() {
		classSelector.setItems(FXCollections.observableArrayList(Classs.getClasses()));
	}

	public void save() {
		controller.addStudent(name.getText(), email.getText(), classSelector.getValue(), family);
		loader = new FXMLLoader(getClass().getResource("/client/view/fxml/familyList.fxml"));
		try {
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}
		goBack();
	}

	public void goBack() {
		loader = new FXMLLoader(getClass().getResource("/client/view/fxml/familyList.fxml"));
		try {
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.getScene().setRoot(mainPane);
		stage.show();
	}
	
	public void setFamily(Family family){
		this.family = family;
	}
}
