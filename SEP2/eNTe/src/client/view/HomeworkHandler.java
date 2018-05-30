package client.view;

import java.io.IOException;
import java.util.ArrayList;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Homework;
import model.Post;

public class HomeworkHandler {

	@FXML
	private VBox box;
	@FXML
	private ImageView ente;
	private ClientController controller;
	private Stage stage;
	private Parent mainPane;

	public HomeworkHandler() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
		System.out.println("HomeworkHandler");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/mainPaneAdmin.fxml"));
		try {
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void initialize() {
		System.out.println("second");
		System.out.println(box);

		ArrayList<Post> posts = controller.getAllPosts();
		for(Post p : posts) {
			switch(p.getClass().getSimpleName()) {
				case "Homework":
					loadHomework();
					break;
				case "Post":
					loadPost();
					break;
				default:
					break;
			}
		}

	}
	private void loadHomework() {
		Homework[] homework = controller.getHomework();

		Text title = new Text(homework[0].getTitle());
		title.setId("title");
		Text content = new Text(homework[0].getContent());
		content.setId("content");
		Text deadline = new Text(homework[0].getDeadline().toString());
		Text separator = new Text("\n" + "\n");
		Text separator1 = new Text("\n" + "\n" + " ");
		Text separator2 = new Text("\n" + "\n" + " ");
		Text separator3 = new Text(" ");
		Text separator4 = new Text(" ");

		Button list = new Button("DONE BY:");
		Button edit = new Button("EDIT");
		Button delete = new Button("DELETE");
		delete.getStyleClass().add("smallButton");
		list.getStyleClass().add("smallButton");
		edit.getStyleClass().add("smallButton");


		TextFlow textFlow = new TextFlow(title, separator, content, separator1, deadline, separator2, list, separator3, edit, separator4, delete);
		textFlow.setTextAlignment(TextAlignment.JUSTIFY);
		textFlow.setAccessibleText(homework[0].getContent());
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

	private void loadPost() {
		Post[] posts = controller.getPosts();

		Text title = new Text(posts[0].getTitle());
		title.setId("title");
		Text content = new Text(posts[0].getContent());
		content.setId("content");
		Text separator = new Text("\n" + "\n");

		TextFlow textFlow = new TextFlow(title, separator, content);
		textFlow.setTextAlignment(TextAlignment.JUSTIFY);
		textFlow.setAccessibleText(posts[0].getContent());
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
	public void loadPanes(Pane pane) {
		box.getChildren().clear();
		box.getChildren().add(pane);
	}

	public void createHomework() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/createHomework.fxml"));
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
			stage.getScene().setRoot(mainPane);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void editHomework() {

	}
	public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}
}

