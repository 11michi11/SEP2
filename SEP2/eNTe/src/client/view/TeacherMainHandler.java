package client.view;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Homework;
import model.Post;

import java.util.ArrayList;

public class TeacherMainHandler {
	@FXML
	private VBox box;

	private ClientController controller;
	private Stage stage;

	public TeacherMainHandler() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
		System.out.println("teacherMainHandler");
	}

	@FXML
	public void initialize() {
		System.out.println("second");
		System.out.println(box);
		ArrayList<Post> posts = controller.getAllPosts();
		for(Post p : posts) {
			switch(p.getClass().getSimpleName()) {
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
		box.getChildren().add(pane);
	}
	private void loadHomework(Homework homework) {

		Text title = new Text(homework.getTitle());
		title.setId("title");
		Text content = new Text(homework.getContent());
		content.setId("content");
		Text deadline = new Text(homework.getDeadline().toString());
		Text separator = new Text("\n" + "\n");
		Text separator1 = new Text("\n" + "\n" + " ");
		Text separator2 = new Text("\n" + "\n" + " ");

		Button list = new Button("DONE BY:");
		list.getStyleClass().add("smallButton");


		TextFlow textFlow = new TextFlow(title, separator, content, separator1, deadline, separator2, list);
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
}
