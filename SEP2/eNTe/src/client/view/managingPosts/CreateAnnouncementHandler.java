package client.view.managingPosts;

import client.controller.ClientController;
import client.view.ClientViewManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.ClassNo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateAnnouncementHandler {
	private ClientController controller;
	private Stage stage;
	private Parent mainPane;

	@FXML
	private TextArea title, content;
	@FXML
	private RadioButton parental, important, normal;
	@FXML
	private CheckBox first, second, third, fourth, fifth, sixth, seventh, eight;

	public CreateAnnouncementHandler() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
		System.out.println("HomeworkListHandler");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/announcementHandler.fxml"));
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
		content.setWrapText(true);
	}

	public void addAnnouncement() {
		checkForNull();
		controller.addPost(title.getText(), content.getText(), selectedValue(), getClasses());
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
		if (title.getText() == null || content.getText() == null || getClasses() == null || selectedValue() == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Look, unfinished selection");
			alert.setContentText("Please fill everything!");
			alert.showAndWait();
		}
	}

	private List<ClassNo> getClasses() {
		return getClassNos(first, second, third, fourth, fifth, sixth, seventh, eight);
	}

	private static List<ClassNo> getClassNos(CheckBox first, CheckBox second, CheckBox third, CheckBox fourth, CheckBox fifth, CheckBox sixth, CheckBox seventh, CheckBox eight) {
		ArrayList<ClassNo> classes = new ArrayList<>();
		if (first.isSelected())
			classes.add(ClassNo.First);
		if (second.isSelected())
			classes.add(ClassNo.Second);
		if (third.isSelected())
			classes.add(ClassNo.Third);
		if (fourth.isSelected())
			classes.add(ClassNo.Fourth);
		if (fifth.isSelected())
			classes.add(ClassNo.Fifth);
		if (sixth.isSelected())
			classes.add(ClassNo.Sixth);
		if (seventh.isSelected())
			classes.add(ClassNo.Seventh);
		if (eight.isSelected())
			classes.add(ClassNo.Eighth);

		return classes;
	}

	private void goToPost() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/announcementHandler.fxml"));
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
