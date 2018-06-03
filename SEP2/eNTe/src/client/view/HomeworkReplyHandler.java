package client.view;

import java.io.IOException;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Homework;
import model.HomeworkReply;

public class HomeworkReplyHandler {
	
	private ClientController controller;
	private Stage stage;
	@FXML
	private ImageView ente;
	@FXML
	private TextArea area;
	private Parent mainPane;
	private FXMLLoader loader;
	private Homework homework;
	private HomeworkReply reply;
	
	public HomeworkReplyHandler() {
		controller = ClientController.getInstance();
		System.out.println("TextAreaForHomeworkHandler");
		stage = ClientViewManager.getStage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/mainPaneStudent.fxml"));
		try {
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void submit() {
		controller.submitHomework(homework, area.getText());
		goBack();
	}
	
	public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}

	public void setReply(HomeworkReply reply) {
		this.reply = reply;
		area.setText(reply.getContent());
	}
	public void setHomework(Homework homework) {
		this.homework = homework;
		HomeworkReply reply = homework.getStudentReply(controller.getCurrentUserId());
		if(reply != null){
			area.setText(reply.getContent());
		}
	}

}
