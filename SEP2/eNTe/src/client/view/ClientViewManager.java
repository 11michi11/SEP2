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
	private ClientViewHandler handler;
	private ClientMainPaneParentHandler parentHandler;
	private FXMLLoader loader;
	private static Stage stage;
	
	

	public ClientViewManager() {
		this.loader = new FXMLLoader();
	}

	@FXML
	public void initialize(ClientController controller) {
		this.controller = controller;
		this.handler = new ClientViewHandler();
		System.out.println("init controller");
	}

	public void startView() {
		Application.launch(getClass());
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			stage  = primaryStage;
			loader.setLocation(getClass().getResource("/client/view/loginPane.fxml"));
			loader.setController(handler);
			Pane root = (Pane) loader.load();
			Scene scene = new Scene(root, 1280, 780);
			// scene.getStylesheets().add(getClass().getResource("client/view/login.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startHandlers() {
		handler.loginFieldInitialize();
	}

	@Override
	public void showPosts(Post[] posts) {
		Parent mainPane;
		
		try {
			loader = new FXMLLoader(getClass().getResource("/client/view/mainPaneParent.fxml"));
			mainPane = loader.load();
			Scene menuPane = new Scene(mainPane);
			
			stage.setScene(menuPane);
			stage.show();
			
			TextFlow textpane = new TextFlow();
			textpane.setAccessibleText(posts[0].getContent());
			textpane.setPrefSize(842, 150);

			Pane pane = new Pane();
			pane.getChildren().add(textpane);
			parentHandler = new ClientMainPaneParentHandler();
			loader.setController(parentHandler);
			parentHandler.loadPanes(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
