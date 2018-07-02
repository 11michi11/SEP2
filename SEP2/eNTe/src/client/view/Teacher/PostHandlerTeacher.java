package client.view.Teacher;

import client.controller.ClientController;
import client.view.ClientViewManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Post;

import java.io.IOException;
import java.util.ArrayList;

public class PostHandlerTeacher {

	@FXML
	private VBox box;
	@FXML
	private ImageView ente;
	private ClientController controller;
	private Stage stage;
	private Parent mainPane;

	public PostHandlerTeacher() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
		System.out.println("PostHandler");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/mainPaneAdmin.fxml"));
		try {
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void initialize() {
		ArrayList<Post> posts = controller.getAllPosts();
		for(Post p : posts) {
			if(p.getClass().getSimpleName().equals("Post")) {
				loadPosts(p);
			}
		}
	}

	private void loadPosts(Post post) {
		Text title = new Text(post.getTitle());
		title.setId("title");
		Text content = new Text(post.getContent());
		content.setId("content");
		Text separator = new Text("\n" + "\n");
		TextFlow textFlow = new TextFlow(title, separator, content);
		textFlow.setTextAlignment(TextAlignment.JUSTIFY);
		textFlow.setAccessibleText(post.getContent());
		textFlow.setPrefWidth(830);
		textFlow.getStyleClass().add("textPane");
		addPane(textFlow);
	}

	private void addPane(Pane pane) {
		box.getChildren().add(pane);
	}

	public void createPost() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/createAnnoucement.fxml"));
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
			stage.getScene().setRoot(mainPane);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}
}
