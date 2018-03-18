package client.view;

import java.util.Arrays;

import client.controller.ClientController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Post;

public class ClientViewManager extends Application implements ClientView {

	private ClientController controller;
	private ClientViewHandler handler;

	public ClientViewManager(ClientController controller) {
		this.controller = controller;
	}
	
	public ClientViewManager() {};

	public void startView() {
		Application.launch(getClass());
	}

	@Override
	public void start(Stage primaryStage) {

		try {
			FXMLLoader loader = new FXMLLoader();
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
		// Scanner in = new Scanner(System.in);
		// System.out.println("Enter smth to test");
		// String smth = in.nextLine();
		// controller.login("login", "pwd");
	}

	@Override
	public void showPosts(Post[] posts) {
		System.out.println(Arrays.toString(posts));
	}

}
