package client.view;

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
		System.out.println("parentMainHandler");
	}

	@FXML
	public void initialize() {
		System.out.println("second");
		System.out.println(box);
		ArrayList<Post> posts = controller.getAllPosts();
		for(Post p : posts) {
			loadPost(p);
		}

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
		textFlow.setPrefWidth(842);

		Pane pane = new Pane() {
			@Override
			protected void layoutChildren() {
				super.layoutChildren();
				TextFlow textFlow = (TextFlow) getChildren().get(0);
				setMinHeight(textFlow.getHeight()+5);
				autosize();
			}
		};
		pane.getChildren().add(textFlow);
		pane.getStyleClass().add("textPane");
		loadPanes(pane);

	}

	private void loadPanes(Pane pane) {
		box.getChildren().add(pane);
	}

}
