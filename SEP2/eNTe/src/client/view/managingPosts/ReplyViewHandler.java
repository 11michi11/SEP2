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

	public void setReply(HomeworkReply reply) {
		this.reply = reply;
		content.setText(reply.getContent());
	}

}
