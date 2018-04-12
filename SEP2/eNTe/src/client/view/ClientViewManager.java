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
import model.User;

public class ClientViewManager extends Application implements ClientView {

	private ClientController controller;
	private LoginHandler handler;
	private ParentMainHandler parentHandler;
	private AdminMainHandler adminHandler;
	private FXMLLoader loader;
	private static Stage stage;

	public ClientViewManager() {

	}

	public void startView() {
		Application.launch(getClass());
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			loader = new FXMLLoader(getClass().getResource("/client/view/loginPane.fxml"));
			Pane root = (Pane) loader.load();
			Scene scene = new Scene(root, 1280, 780);
			scene.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showPosts(User user) {
		Parent mainPane;
		if (user instanceof model.Parent) {
			try {
				loader = new FXMLLoader(getClass().getResource("/client/view/mainPaneParent.fxml"));
				mainPane = loader.load();
				parentHandler = new ParentMainHandler();
				loader.setController(parentHandler);
				mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
				stage.getScene().setRoot(mainPane);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (user instanceof model.Administrator)
			try {
				loader = new FXMLLoader(getClass().getResource("/client/view/mainPaneAdmin.fxml"));
				mainPane = loader.load();
				parentHandler = new ParentMainHandler();
				loader.setController(adminHandler);
				mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
				stage.getScene().setRoot(mainPane);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if (user instanceof model.Student)
			try {
				loader = new FXMLLoader(getClass().getResource("/client/view/mainPaneStudent.fxml"));
				mainPane = loader.load();
				parentHandler = new ParentMainHandler();
				// loader.setController(studentHandler);
				mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
				stage.getScene().setRoot(mainPane);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if (user instanceof model.Teacher)
			try {
				loader = new FXMLLoader(getClass().getResource("/client/view/mainPaneTeacher.fxml"));
				mainPane = loader.load();
				parentHandler = new ParentMainHandler();
				//loader.setController(teacherHandler);
				mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
				stage.getScene().setRoot(mainPane);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
