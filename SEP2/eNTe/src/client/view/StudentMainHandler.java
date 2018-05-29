package client.view;

import java.io.IOException;
import java.util.ArrayList;

import client.controller.ClientController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Discussion;
import model.Homework;
import model.Post;

public class StudentMainHandler {
	@FXML
	private VBox VBox;

	private ClientController controller;
	private Stage stage;

	public StudentMainHandler() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
		System.out.println("studentMainHandler");
	}

	@FXML
	public void initialize() {
		System.out.println(VBox);
		
		ArrayList<Post> posts = controller.getAllPosts();
		for(Post p : posts) {
			switch(p.getClass().getSimpleName()) {
			case "Homework":
				loadHomework();
				break;				
			case "Post":
				loadPost();
				break;
			case "Discussion":
				loadDiscussion();
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
	
	public void submit() {
		Parent mainPane;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/textArea.fxml"));
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
			stage.getScene().setRoot(mainPane);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
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

		Button submit = new Button("submit");
		submit.getStyleClass().add("smallButton");				
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				submit();
			}
		});
		
		TextFlow textFlow = new TextFlow(title, separator, content, separator1, deadline, separator2, submit);
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
		Post[] post = controller.getPosts();

		Text titleOfPost = new Text(post[0].getTitle());
		titleOfPost.setId("title");
		Text contentOfPost = new Text(post[0].getContent());
		contentOfPost.setId("content");
		Text separator = new Text("\n" + "\n");

		TextFlow textFlow = new TextFlow(titleOfPost, separator, contentOfPost);
		textFlow.setTextAlignment(TextAlignment.JUSTIFY);
		textFlow.setAccessibleText(post[0].getContent());
		textFlow.setPrefWidth(842);
		
		Pane postPane = new Pane() {
			@Override
			protected void layoutChildren() {
				super.layoutChildren();
				TextFlow textFlow = (TextFlow) getChildren().get(0);
				setMinHeight(textFlow.getHeight()+5);
				autosize();
			}
		};
		postPane.getChildren().add(textFlow);
		postPane.getStyleClass().add("textPane");
		loadPanes(postPane);
	}
	
	private void loadDiscussion() {
		Discussion[] discussion = controller.getDiscussion();

		Text title = new Text(discussion[0].getTitle());
		title.setId("title");
		Text content = new Text(discussion[0].getContent());
		content.setId("content");
		Text separator = new Text("\n" + "\n");

		TextFlow textFlow = new TextFlow(title, separator, content);
		textFlow.setTextAlignment(TextAlignment.JUSTIFY);
		textFlow.setAccessibleText(discussion[0].getContent());
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
