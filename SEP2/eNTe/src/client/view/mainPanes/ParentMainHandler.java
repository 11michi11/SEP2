package client.view.mainPanes;

import client.controller.ClientController;
import client.view.ClientViewManager;
import client.view.managingPosts.DiscussionCommentsHandler;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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

import java.io.IOException;
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
		Button showComments = new Button("comments");
		showComments.getStyleClass().add("smallButton");
		showComments.addEventHandler(MouseEvent.MOUSE_CLICKED, new ParentMainHandler.ShowComments(discussion));

		Text separator = new Text("\n" + "\n");
		Text separator1 = new Text("\n" + "\n");

		TextFlow textFlow = new TextFlow(title,separator, content, separator1, showComments);
		textFlow.setTextAlignment(TextAlignment.JUSTIFY);
		textFlow.setAccessibleText(discussion.getContent());
		textFlow.setPrefWidth(830);
		textFlow.getStyleClass().add("textPane");

		addPane(textFlow);
	}

	private void addPane(Pane pane) {
		box.getChildren().add(pane);
	}

	private class ShowComments implements EventHandler<Event> {

		private Discussion discussion;
		private ShowComments(Discussion discussion) {
			this.discussion = discussion;
		}

		@Override
		public void handle(Event event) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/discussionCommentsHandler.fxml"));
				javafx.scene.Parent mainPane = loader.load();
				((DiscussionCommentsHandler) loader.getController()).loadComments(discussion);
				mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
				stage.getScene().setRoot(mainPane);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	}

