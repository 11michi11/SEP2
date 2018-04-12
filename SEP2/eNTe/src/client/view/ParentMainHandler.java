package client.view;

import com.sun.prism.paint.Color;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Post;

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

		Post[] posts = controller.getPosts();

		Text title = new Text(posts[0].getTitle());
		Text content = new Text(posts[0].getContent());
		Text separator = new Text("\n");

		TextFlow textFlow = new TextFlow(title, separator,  content);
		textFlow.setAccessibleText(posts[0].getContent());
		textFlow.setPrefSize(842, 150);
		
		Pane pane = new Pane();
		pane.getChildren().add(textFlow);
		pane.getStyleClass().add("text-pane");
		loadPanes(pane);

	}

	public void loadPanes(Pane pane) {
		box.getChildren().clear();
		box.getChildren().add(pane);
	}

}
