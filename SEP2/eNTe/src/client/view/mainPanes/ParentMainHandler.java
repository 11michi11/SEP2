package client.view.mainPanes;

import client.controller.ClientController;
import client.view.ClientViewManager;
import client.view.IconImage;
import client.view.managingPosts.DiscussionCommentsHandler;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
				case "Homework":
					if (controller.checkHomeworkClass((Homework) p)) {
						loadHomework((Homework) p);
					}
					break;
				case "Announcement":
					loadAnnouncement((Announcement) p);
					break;
				case "Discussion":
					loadDiscussion((Discussion) p);
				default:
					break;
			}
		}

	}

	private void loadAnnouncement(Announcement announcement) {
		Text title = new Text(announcement.getTitle());
		title.setId("title");
		Text content = new Text(announcement.getContent());
		content.setId("content");
		Text separator = new Text("\n" + "\n");

		Image img = new Image("/client/view/fxml/annIcon.png");
		ImageView imageView = new ImageView(img);
		imageView.setFitHeight(30);
		imageView.setFitWidth(30);

		Image imgParental = new Image("/client/view/fxml/pIcon.png");
		ImageView parentalView = new ImageView(imgParental);
		parentalView.setFitHeight(15);
		parentalView.setFitWidth(15);

		Image imgImportant = new Image("/client/view/fxml/importantIcon.png");
		ImageView importantView = new ImageView(imgImportant);
		importantView.setFitHeight(25);
		importantView.setFitWidth(25);
		TextFlow textFlow;
		switch (announcement.getSpecialType().toString().toLowerCase()) {
			case "important":
				textFlow = new TextFlow(IconImage.getImpIcon(), IconImage.getAnnIcon(), title, separator, content);
				break;
			case "parental":
				textFlow = new TextFlow(IconImage.getAnnIcon(), IconImage.getParIcon(), title, separator, content);
				break;
			default:
				textFlow = new TextFlow(IconImage.getAnnIcon(), title, separator, content);
				break;
		}

		textFlow.setTextAlignment(TextAlignment.JUSTIFY);
		textFlow.setAccessibleText(announcement.getContent());
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

		TextFlow textFlow;
		if (discussion.getSpecialType().toString().toLowerCase().equals("parental")) {
			textFlow = new TextFlow(IconImage.getDisIcon(), IconImage.getParIcon(), title, separator, content, separator1, showComments);
		} else {
			textFlow = new TextFlow(IconImage.getDisIcon(), title, separator, content, separator1, showComments);
		}
		textFlow.setTextAlignment(TextAlignment.JUSTIFY);
		textFlow.setAccessibleText(discussion.getContent());
		textFlow.setPrefWidth(830);
		textFlow.getStyleClass().add("textPane");

		addPane(textFlow);
	}

	private void loadHomework(Homework homework) {
		Text title = new Text(homework.getTitle());
		title.setId("title");
		Text content = new Text(homework.getContent());
		content.setId("content");
		Text separator = new Text("\n" + "\n");

		TextFlow textFlow = new TextFlow(IconImage.getHomIcon(), separator, content);
		textFlow.setTextAlignment(TextAlignment.JUSTIFY);
		textFlow.setAccessibleText(homework.getContent());
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

