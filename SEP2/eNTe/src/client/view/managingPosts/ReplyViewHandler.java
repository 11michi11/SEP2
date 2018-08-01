package client.view.managingPosts;

import client.controller.ClientController;
import client.view.ClientViewManager;
import client.view.GoBackMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Homework;
import model.HomeworkReply;

import java.io.IOException;


public class ReplyViewHandler {

	@FXML
	private TextArea content;
	private ClientController controller;
	private Stage stage;
	private HomeworkReply reply;

	public ReplyViewHandler() {
		controller = ClientController.getInstance();
		System.out.println("ReplyViewHandler");
		stage = ClientViewManager.getStage();
	}

	@FXML
	public void initialize() {
		content.setWrapText(true);
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

	public void setReply(HomeworkReply reply) {
		this.reply = reply;
		content.setText(reply.getContent());
	}

}
