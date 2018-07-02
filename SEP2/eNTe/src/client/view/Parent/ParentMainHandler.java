package client.view.Parent;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import model.Post;

import java.util.ArrayList;

public class ParentMainHandler {

	@FXML
	private VBox box;

	private ClientController controller;

	public ParentMainHandler() {
		controller = ClientController.getInstance();
	}

	@FXML
	public void initialize() {
		controller.getAllPosts().forEach(this::loadPost);
	}

	private void loadPost(Post post) {
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

		loadPanes(textFlow);

	}

	private void loadPanes(Pane pane) {
		box.getChildren().add(pane);
	}

}