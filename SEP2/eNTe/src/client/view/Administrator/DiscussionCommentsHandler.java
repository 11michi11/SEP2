package client.view.Administrator;

import client.controller.ClientController;
import client.view.ClientViewManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Discussion;
import model.Post;
import model.DiscussionComment;

import java.io.IOException;
import java.util.ArrayList;

public class DiscussionCommentsHandler {

	@FXML
	private VBox box;
	@FXML
	private ImageView ente;
	@FXML
	private Label title;
	private ClientController controller;
	private Stage stage;
	private Parent mainPane;
	private Discussion discussion;
	private DiscussionComment comment;

	public DiscussionCommentsHandler() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
		System.out.println("DiscussionHandler");
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
		System.out.println("second");
		System.out.println(box);
		loadComments();
	}
	private void loadComments() {

		ArrayList<Post> posts = controller.getAllPosts();
		for(Post p : posts) {
			if(p.getClass().getSimpleName().equals("DiscussionComments")) {
//				loadUserComments((DiscussionComment) p);
			}
		}
	}

	private void loadUserComments(DiscussionComment comment) {
		Text content = new Text(comment.getContent());
		content.setId("content");
		String name = comment.getUser().getName();
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

	public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}

	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
		title.setText(discussion.getTitle());
	}
}
