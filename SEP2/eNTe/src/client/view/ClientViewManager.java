package client.view;

import java.io.IOException;

import client.controller.ClientController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Post;

public class ClientViewManager extends Application implements ClientView {

	private ClientController controller;
	private LoginHandler handler;
	private ParentMainHandler parentHandler;
	private FXMLLoader loader;
	private static Stage stage;
	
	

	public ClientViewManager() {

	}
/*
	@FXML
	public void initialize() {
		this.controller = ClientController.getInstance();
		this.handler = new LoginHandler();
		System.out.println("init controller");
		handler.loginFieldInitialize();
	}*/

	public void startView() {
		Application.launch(getClass());
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			stage  = primaryStage;
			loader = new FXMLLoader(getClass().getResource("/client/view/loginPane.fxml"));
			Pane root = (Pane) loader.load();
			Scene scene = new Scene(root, 1280, 780);
			// scene.getStylesheets().add(getClass().getResource("client/view/login.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startHandlers() {
		handler.loginFieldInitialize();
	}

	@Override
	public void showPosts() {
		Parent mainPane;
		
		try {
			loader = new FXMLLoader(getClass().getResource("/client/view/mainPaneParent.fxml"));
			mainPane = loader.load();
			parentHandler = new ParentMainHandler();
			loader.setController(parentHandler);
			//Scene menuPane = new Scene(mainPane);
			
			stage.getScene().setRoot(mainPane);;
			stage.show();
			


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
