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
import model.Teacher;

public class CreateTeacherHandler {

	@FXML
	private TextField name, email, responsibility;
	private ClientController controller;
	private Stage stage;
	private Teacher teacher;

	public CreateTeacherHandler() {
		controller = ClientController.getInstance();
		System.out.println("CreateTeacherHandler");
		stage = ClientViewManager.getStage();

	}

	public void save() {
		checkForNull();
		if(checkForNull()) {
			warningDialog();
		} else {
			String id = null, pwd = null;
			if (teacher != null) {
				id = teacher.getId();
				pwd = teacher.getPwd();
			}
			controller.addTeacher(name.getText(), email.getText(), responsibility.getText(), id, pwd);
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

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
		name.setText(teacher.getName());
		email.setText(teacher.getEmail());
	}

	private boolean checkForNull() {
		return name.getText().equals("") || email.getText().equals("") || responsibility.getText().equals("");
	}

	private void warningDialog() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning Dialog");
		alert.setHeaderText("Look, unfinished selection");
		alert.setContentText("Fill out everything, please");
		alert.showAndWait();
	}

}
