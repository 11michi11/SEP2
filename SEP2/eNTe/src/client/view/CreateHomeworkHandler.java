package client.view;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ClassNo;
import model.Homework;
import model.MyDate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateHomeworkHandler {
	private ClientController controller;
	private Stage stage;
	private Parent mainPane;

	@FXML
	private ImageView ente;
	@FXML
	private TextArea title;
	@FXML
	private ChoiceBox minute, hour;
	@FXML
	private TextArea content;
	@FXML
	private DatePicker deadline;
	@FXML
	private TextField group;
	@FXML
	private CheckBox first, second, third, fourth, fifth, sixth, seventh, eight;
	private Homework homework;


	public CreateHomeworkHandler() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
		System.out.println("HomeworkHandler");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/homeworkCreation.fxml"));
		try {
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void initialize() {
		hour.getItems().addAll(0, 23, 22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1);
		minute.getItems().addAll(0, 15, 30, 45);
		content.setWrapText(true);
	}

	public void setHomework(Homework homework) {
		MyDate date = homework.getDeadline();
		LocalDate localDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
		this.homework = homework;
		title.setText(homework.getTitle());
		content.setText(homework.getContent());
		deadline.setValue(localDate);
		group.setText(String.valueOf(homework.getNumberOfStudentsToDeliver()));
		hour.setValue(date.getHour());
		minute.setValue(date.getMinute());

	}

	public void addHomework() {
		LocalDate localDate = deadline.getValue();
		MyDate deadlineDate = new MyDate(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
		controller.addHomework(title.getText(), content.getText(), deadlineDate, getClasses(), Integer.valueOf(group.getText()));
		System.out.println("homework added" + title.getText() + content.getText() + deadlineDate + getClasses() + Integer.valueOf(group.getText()));
		goBack();
	}

	private List<ClassNo> getClasses() {
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

	public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}
}
