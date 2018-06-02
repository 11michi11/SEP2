package client.view;

import java.io.IOException;
import java.util.ArrayList;

import client.controller.ClientController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Homework;
import model.HomeworkReply;
import model.Post;

public class StudentMainHandler {
	@FXML
	private VBox VBox;

	private ClientController controller;
	private Stage stage;
	private Parent mainPane;

	public StudentMainHandler() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
		System.out.println("studentMainHandler");

	}

	@FXML
	public void initialize() {
		System.out.println(VBox);

		ArrayList<Post> posts = controller.getAllPosts();
		for (Post p : posts) {
			switch (p.getClass().getSimpleName()) {
				case "Homework":
					loadHomework((Homework) p);
					break;
				case "Post":
					loadPost(p);
					break;
				default:
					break;
			}
		}
	}

	public void loadPanes(Pane pane) {
		VBox.getChildren().clear();
		VBox.getChildren().add(pane);
	}

	public void submit(Homework homework) {
		Parent mainPane;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/homeworkReply.fxml"));
			mainPane = loader.load();
			((HomeworkReplyHandler) loader.getController()).setHomework(homework);
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
			stage.getScene().setRoot(mainPane);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadHomework(Homework homework) {

		HomeworkReply reply = homework.getStudentReply(controller.getCurrentUserId());
		Text title = new Text(homework.getTitle());
		title.setId("title");
		Text content = new Text(homework.getContent());
		content.setId("content");
		Text deadline = new Text(homework.getDeadline().toString());
		Text separator = new Text("\n" + "\n");
		Text separator1 = new Text("\n" + "\n" + " ");
		Text separator2 = new Text("\n" + "\n" + " ");

		Button solution;
		if (reply != null)
			solution = new Button("EDIT SOLUTION");
		else
			solution = new Button("SUBMIT");

		solution.addEventHandler(MouseEvent.MOUSE_CLICKED, new SubmitHomeworkHandler(homework));
		solution.getStyleClass().add("smallButton");

		TextFlow textFlow = new TextFlow(title, separator, content, separator1, deadline, separator2, solution);
		textFlow.setTextAlignment(TextAlignment.JUSTIFY);
		textFlow.setAccessibleText(homework.getContent());
		textFlow.setPrefWidth(842);

		Pane pane = new Pane() {
			@Override
			protected void layoutChildren() {
				super.layoutChildren();
				TextFlow textFlow = (TextFlow) getChildren().get(0);
				setMinHeight(textFlow.getHeight() + 5);
				autosize();
			}
		};
		pane.getChildren().addAll(textFlow);
		pane.getStyleClass().add("textPane");
		loadPanes(pane);

	}

	private void loadPost(Post post) {

		Text titleOfPost = new Text(post.getTitle());
		titleOfPost.setId("title");
		Text contentOfPost = new Text(post.getContent());
		contentOfPost.setId("content");
		Text separator = new Text("\n" + "\n");

		TextFlow textFlow = new TextFlow(titleOfPost, separator, contentOfPost);
		textFlow.setTextAlignment(TextAlignment.JUSTIFY);
		textFlow.setAccessibleText(post.getContent());
		textFlow.setPrefWidth(842);

		Pane postPane = new Pane() {
			@Override
			protected void layoutChildren() {
				super.layoutChildren();
				TextFlow textFlow = (TextFlow) getChildren().get(0);
				setMinHeight(textFlow.getHeight() + 5);
				autosize();
			}
		};
		postPane.getChildren().add(textFlow);
		postPane.getStyleClass().add("textPane");
		loadPanes(postPane);
	}

	private class SubmitHomeworkHandler implements EventHandler<Event> {

		private Homework homework;

		private SubmitHomeworkHandler(Homework homework) {
			this.homework = homework;
		}

		@Override
		public void handle(Event event) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/homeworkReply.fxml"));
				mainPane = loader.load();
				((HomeworkReplyHandler) loader.getController()).setHomework(homework);
				mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
				stage.getScene().setRoot(mainPane);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
