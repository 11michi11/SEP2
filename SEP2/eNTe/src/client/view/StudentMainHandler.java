package client.view;

import java.io.IOException;

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
		Post[] posts = controller.getPosts();

		Text title = new Text(posts[0].getTitle());
		title.setId("title");
		Text content = new Text(posts[0].getContent());
		content.setId("content");
		Text separator = new Text("\n" + "\n");
		Text separator2 = new Text("\n" + "\n" + " ");

		Button submit = new Button("submit");
		submit.getStyleClass().add("smallButton");
		
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				submit();
			}
		});
	            
		
		TextFlow textFlow = new TextFlow(title, separator, content, separator2, submit);
		textFlow.setTextAlignment(TextAlignment.JUSTIFY);
		textFlow.setAccessibleText(posts[0].getContent());
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
}
