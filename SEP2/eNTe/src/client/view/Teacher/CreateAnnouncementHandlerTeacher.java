package client.view.Teacher;

import client.controller.ClientController;
import client.view.ClientViewManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAnnouncementHandlerTeacher {

	private ClientController controller;
	private Stage stage;
	private Parent mainPane;

	@FXML
	private ImageView ente;
	@FXML
	private TextArea title;
	@FXML
	private TextArea content;
	@FXML
	private RadioButton parental, important, normal;

	public CreateAnnouncementHandlerTeacher() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
		System.out.println("HomeworkHandler");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/postHandler.fxml"));
		try {
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void initialize() {
		ToggleGroup group = new ToggleGroup();
		parental.setToggleGroup(group);
		important.setToggleGroup(group);
		normal.setToggleGroup(group);
		normal.setSelected(true);
	}

	public void addAnnouncement() {
		checkForNull();
		controller.addPost(title.getText(), content.getText(), selectedValue());
		System.out.println("post added" + title.getText() + content.getText());
		goToPost();
	}

	private String selectedValue() {
		String value = null;
		if (important.isSelected()) {
			value = important.getText();
		}
		if (parental.isSelected()) {
			value = parental.getText();
		}
		if (normal.isSelected()) {
			value = parental.getText();
		}

		return value;
	}

	private void checkForNull() {
		if (title.getText() == null || content.getText() == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Look, unfinished selection");
			alert.setContentText("Please fill everything!");
			alert.showAndWait();
		}
	}

	private void goToPost() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/postHandler.fxml"));
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
			stage.getScene().setRoot(mainPane);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}
}
