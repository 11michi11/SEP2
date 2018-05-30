package client.view;

import client.controller.ClientController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ClassNo;
import model.MyDate;

import java.io.IOException;
import java.time.LocalDate;

public class CreateHomeworkHandler {
	private ClientController controller;
	private Stage stage;
	private Parent mainPane;

	@FXML
	public Button save;
	@FXML
	private ImageView ente;
	@FXML
	private TextArea title;
	@FXML
	private TextArea content;
	@FXML
	private DatePicker deadline;
	@FXML
	private ChoiceBox classesChoice;
	@FXML
	private TextField group;


	public CreateHomeworkHandler() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
		System.out.println("HomeworkHandler");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/mainPaneAdmin.fxml"));
		try {
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void initialize() {
		classesChoice.setItems(FXCollections.observableArrayList(ClassNo.getClasses()));
	}

	public void addHomework() {
		LocalDate localDate = deadline.getValue();
		MyDate deadlineDate = new MyDate(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());

		controller.addHomework(title.getText(), content.getText(), deadlineDate, classesChoice.getItems(), Integer.valueOf(group.getText()));
	}

	public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}
}
