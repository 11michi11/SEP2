package client.view;

import java.awt.TextArea;
import java.io.IOException;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TextAreaHandler {
	
	private ClientController controller;
	private Stage stage;
	@FXML
	private ImageView ente;
	@FXML
	private TextArea text;
	private Parent mainPane;
	private FXMLLoader loader;
	
	public TextAreaHandler() {
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
		controller.submitHomework(text.getText());
		loader = new FXMLLoader(getClass().getResource("/client/view/fxml/mainPaneStudent.fxml"));
		try {
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}
		goBack();
	}
	
	public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}

}
