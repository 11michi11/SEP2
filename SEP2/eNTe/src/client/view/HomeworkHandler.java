package client.view;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeworkHandler {

	@FXML
	private Button createPost, editPost;
	@FXML
	private VBox box;
	private ClientController controller;
	private Stage stage;

	public HomeworkHandler() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
		System.out.println("HomeworkHandler");
	}
	public void loadPanes(Pane pane) {
		box.getChildren().clear();
		box.getChildren().add(pane);
	}

	public void createPost() {
		TextField title = new TextField();
		title.setText("Title");
		title.setId("title");
		TextArea content = new TextArea();
		content.setText("Write a content");
		content.getStyleClass().add("content");

		Button list = new Button("homework done by:");
		list.setId("menuButtonSmall");

		VBox text = new VBox();
		text.getChildren().addAll(title, content, list);

		box.getChildren().add(0, text);
	}

	public void addPost() {
		VBox text = (VBox) box.getChildren().get(0);
		TextField title = (TextField) text.getChildren().get(0);
		TextArea content = (TextArea) text.getChildren().get(1);

		controller.addPost(title.getText(), content.getText());
	}
}

