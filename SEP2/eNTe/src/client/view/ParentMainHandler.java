package client.view;

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

		TextFlow textFlow = new TextFlow(title, content);
		textFlow.setAccessibleText(posts[0].getContent());
		textFlow.setPrefSize(842, 150);
		
		Pane pane = new Pane();
		pane.getChildren().add(textFlow);
		loadPanes(pane);

	}

	public void loadPanes(Pane pane) {
		box.getChildren().clear();
		box.getChildren().add(pane);

		// scrollPane.getChildrenUnmodifiable().add(box);

		System.out.println(box.getChildren() + "!@#");
	}

}
