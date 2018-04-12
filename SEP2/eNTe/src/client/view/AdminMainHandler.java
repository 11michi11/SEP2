package client.view;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Post;

public class AdminMainHandler {

	@FXML
	private VBox box;

	private ClientController controller;

	public AdminMainHandler() {
		controller = ClientController.getInstance();
		System.out.println("adminMainHandler");
	}

	@FXML
	public void initialize() {
		System.out.println("second");
		System.out.println(box);

		Post[] posts = controller.getPosts();

		Text title = new Text(posts[0].getTitle());
		title.setId("title");
		Text content = new Text(posts[0].getContent());
		content.setId("content");
		Text separator = new Text("\n");

		TextFlow textFlow = new TextFlow(title, separator, content);
		textFlow.setAccessibleText(posts[0].getContent());
		textFlow.setPrefWidth(842);
		//textFlow.setPrefSize(842, 200);
		
		Pane pane = new Pane();
		System.out.println(textFlow.getHeight());
		pane.setPrefSize(842, textFlow.getHeight());
		pane.getChildren().add(textFlow);
		pane.getStyleClass().add("textPane");
		loadPanes(pane);

	}

	public void loadPanes(Pane pane) {
		box.getChildren().clear();
		box.getChildren().add(pane);
	}


}
