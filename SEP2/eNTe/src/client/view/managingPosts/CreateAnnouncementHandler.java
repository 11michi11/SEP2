package client.view.managingPosts;

import client.controller.ClientController;
import client.view.ClientViewManager;
import client.view.GoBackMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Announcement;
import model.ClassNo;
import model.MyDate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateAnnouncementHandler {
	private ClientController controller;
	private Stage stage;

	@FXML
	private TextArea title, content;
	@FXML
	private RadioButton parental, important, normal;
	@FXML
	private CheckBox first, second, third, fourth, fifth, sixth, seventh, eight;
	@FXML
	private DatePicker expirationDate;
	private Announcement announcement;

	public CreateAnnouncementHandler() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
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
		if (checkForNull()) {
			warningDialog();
		} else {
			LocalDate localDate = expirationDate.getValue();
			MyDate expiration = new MyDate(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
			controller.addAnnouncement(title.getText(), content.getText(), selectedValue(), getClasses(), expiration);
			System.out.println("post added" + title.getText() + content.getText());
			goToPost();
		}
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

	private boolean checkForNull() {
		return title.getText() == null || content.getText() == null || getClasses().size() == 0 || expirationDate.getValue() == null;
	}

	private void warningDialog() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Warning Dialog");
		alert.setHeaderText("Look, unfinished selection");
		alert.setContentText("Fill out everything, please");
		alert.showAndWait();
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
			Parent mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
			stage.getScene().setRoot(mainPane);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
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

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
		title.setText(announcement.getTitle());
		content.setText(announcement.getContent());
		switch (announcement.getSpecialType().toString().toLowerCase()) {
			case "parental":
				parental.setSelected(true);
				break;
			case "important":
				important.setSelected(true);
				break;
			case "normal":
				normal.setSelected(true);
				break;
		}
		MyDate date = announcement.getExpirationDate();
		LocalDate localDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
		expirationDate.setValue(localDate);

	}

}
