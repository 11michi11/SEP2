package client.view;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DiscussionHandler {

	@FXML
	private Button createPost, editPost;
	@FXML
	private VBox box;
	@FXML
	private ImageView ente;
	private ClientController controller;
	private Stage stage;
	private Parent mainPane;

	public DiscussionHandler() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
		System.out.println("DiscussionHandler");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/mainPaneAdmin.fxml"));
		try {
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

		VBox text = new VBox();
		text.getChildren().addAll(title, content);

		box.getChildren().add(0, text);
	}

	public void addPost() {
		VBox text = (VBox) box.getChildren().get(0);
		TextField title = (TextField) text.getChildren().get(0);
		TextArea content = (TextArea) text.getChildren().get(1);

		controller.addPost(title.getText(), content.getText());
	}
	public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}
}
