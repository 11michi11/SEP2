package client.view.Teacher;

import client.controller.ClientController;
import client.view.ClientViewManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.HomeworkReply;

import java.awt.*;
import java.io.IOException;

public class HomeworkReplyTeacherHandler {

	@FXML
	private TextArea replyText;
	@FXML
	private ImageView ente;
	private ClientController controller;
	private Stage stage;
	private Parent mainPane;
	private HomeworkReply reply;

	public HomeworkReplyTeacherHandler() {
		controller = ClientController.getInstance();
		System.out.println("HomeworkRepliesListHandler");
		stage = ClientViewManager.getStage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/homeworkRepliesList.fxml"));
		try {
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}

	public void setReply(HomeworkReply reply) {
		this.reply = reply;
		replyText.setText(reply.getContent());
	}


}
