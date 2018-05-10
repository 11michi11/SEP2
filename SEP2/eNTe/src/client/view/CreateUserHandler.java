package client.view;

import java.io.IOException;

import client.controller.ClientController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Classs;

public class CreateUserHandler {

	@FXML
	private RadioButton studentBox, teacherBox;
	@FXML
	private HBox choiceBox;
	@FXML
	private VBox textBox;
	@FXML
	private Label userLabel;
	@FXML
	private ChoiceBox<String> classSelector;
	@FXML
	private Button parentSelector, saveButton;
	@FXML
	private TextField name, email, password;
	private CheckBox adminBox;
	private ClientController controller;
	private Stage stage;

	public CreateUserHandler() {
		controller = ClientController.getInstance();
		System.out.println("CreateUserHandler");
		stage = ClientViewManager.getStage();
		adminBox = new CheckBox();
		adminBox.setText("admin");
		adminBox.setId("adminText");
		
	}

	@FXML
	public void initialize() {
		final ToggleGroup group = new ToggleGroup();
		studentBox.setToggleGroup(group);
		teacherBox.setToggleGroup(group);
		classSelector.setItems(FXCollections.observableArrayList(Classs.getClassesInStrings()));
		
	}

	public void studentChoose() {
		if (studentBox.isSelected()) {
			System.out.println(userLabel);
			userLabel.setText("create student");

			textBox.getChildren().remove(adminBox);
			classSelector.setVisible(true);
			classSelector.setManaged(true);
			parentSelector.setVisible(true);
			parentSelector.setManaged(true);
		}

	}

	public void teacherChoose() {
		if (teacherBox.isSelected()) {
			System.out.println(userLabel);
			userLabel.setText("create teacher");
			classSelector.setVisible(false);
			classSelector.setManaged(false);
			parentSelector.setVisible(false);
			parentSelector.setManaged(false);
			textBox.getChildren().add(adminBox);
		}
	}

	public void parentSelected() {
		Parent mainPane;
		try {
			// FXMLLoader loader = new FXMLLoader();
			mainPane = FXMLLoader.load(getClass().getResource("/client/view/parentList.fxml"));
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
			stage.getScene().setRoot(mainPane);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void save() {
		if (studentBox.isSelected())
			controller.addStudent(name.getText(), email.getText(), Classs.First, null);
		else if (teacherBox.isSelected()) {
			controller.addTeacher(name.getText(), email.getText(), password.getText(),
					adminBox.isSelected());
		}
	}

	public void delete() {
		// here will be some choiceBox.isSelected() if yes it call method deleteUser()
		// controller.deleteUser();
	}

}
