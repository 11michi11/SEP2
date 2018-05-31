package client.view;

import java.io.IOException;
import java.util.Optional;

import client.controller.ClientController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ClientViewManager extends Application implements ClientView {

	private ClientController controller;
	private LoginHandler handler;
	private ParentMainHandler parentHandler;
	private AdminMainHandler adminHandler;
	private StudentMainHandler studentHandler;
	private FXMLLoader loader;
	private static Stage stage;

	public ClientViewManager() {
	}
	@Override
	public void setController(ClientController controller) {
		this.controller = controller;
	}

	public void startView() {
		Application.launch(getClass());
	}
	
	public static Stage getStage() {
		return stage;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			loader = new FXMLLoader(getClass().getResource("/client/view/fxml/loginPane.fxml"));
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
	public void showPosts(String user) {
		Parent mainPane;
		switch (user) {
		case "Parent":
			try {
				loader = new FXMLLoader(getClass().getResource("/client/view/fxml/mainPaneParent.fxml"));
				mainPane = loader.load();
				parentHandler = new ParentMainHandler();
				loader.setController(parentHandler);
				mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
				stage.getScene().setRoot(mainPane);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "Administrator":
			try {
				loader = new FXMLLoader(getClass().getResource("/client/view/fxml/mainPaneAdmin.fxml"));
				mainPane = loader.load();
				parentHandler = new ParentMainHandler();
				loader.setController(adminHandler);
				mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
				stage.getScene().setRoot(mainPane);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "Student":
			try {
				loader = new FXMLLoader(getClass().getResource("/client/view/fxml/mainPaneStudent.fxml"));
				mainPane = loader.load();
				parentHandler = new ParentMainHandler();
				loader.setController(studentHandler);
				mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
				stage.getScene().setRoot(mainPane);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "Teacher":
			try {
				loader = new FXMLLoader(getClass().getResource("/client/view/fxml/mainPaneTeacher.fxml"));
				mainPane = loader.load();
				parentHandler = new ParentMainHandler();
				// loader.setController(teacherHandler);
				mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
				stage.getScene().setRoot(mainPane);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	public void showMessage(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}

	@Override
	public boolean showDeleteMessage(String message) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Delete " + message);
		alert.setContentText("Are sure that you want to delete this " + message + " ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES){
			return true;
		} else {
			return false;
		}
	}

	public void changePasswordDialog() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Change password");
		dialog.setHeaderText("Create a new password");
		dialog.setContentText("Please enter your password:");
		
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(s -> controller.changePassword(s));
	}


}
