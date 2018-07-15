package client.view.mainPanes;

import client.controller.ClientController;
import client.view.ClientViewManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Discussion;
import model.Homework;
import model.Parent;
import model.Post;

import java.util.ArrayList;

public class ParentMainHandler {

	@FXML
	private VBox box;
	private ClientController controller;
	private Stage stage;
	private FXMLLoader backLoader = new FXMLLoader(getClass().getResource("/client/view/fxml/mainPaneParent.fxml"));

	public ParentMainHandler() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
		System.out.println("parentMainHandler");
	}

	@FXML
	public void initialize() {
		loadPosts();
	}

	public void loadPosts() {
		ArrayList<Post> posts = controller.getAllPosts();
		for (Post p : posts) {
			switch (p.getClass().getSimpleName()) {
				case "Post":
					loadPost(p);
					break;
				case "Discussion":
					loadDiscussion((Discussion) p);
				default:
					break;
			}
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
		textFlow.setPrefWidth(830);
		textFlow.getStyleClass().add("textPane");

		addPane(textFlow);

	}

	private void loadDiscussion(Discussion discussion) {
		Text title = new Text(discussion.getTitle());
		title.setId("title");
		Text content = new Text(discussion.getContent());
		content.setId("content");
		Text separator = new Text("\n" + "\n");

		TextFlow textFlow = new TextFlow(title,separator, content);
		textFlow.setTextAlignment(TextAlignment.JUSTIFY);
		textFlow.setAccessibleText(discussion.getContent());
		textFlow.setPrefWidth(830);
		textFlow.getStyleClass().add("textPane");

		addPane(textFlow);
	}

	private void addPane(Pane pane) {
		box.getChildren().add(pane);
	}


	}

