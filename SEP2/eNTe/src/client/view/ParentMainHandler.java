package client.view;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

public class ParentMainHandler {
	
	
	@FXML
	private VBox box;

	private ClientController controller;
	
	public ParentMainHandler() {
		controller = ClientController.getInstance();
		System.out.println("parentMainHandler");
		System.out.println(box);
	}

	@FXML
	public void initialize() {
		System.out.println("second");
		System.out.println(box);

		TextFlow textpane = new TextFlow();
		textpane.setAccessibleText(posts[0].getContent());
		textpane.setPrefSize(842, 150);
		Pane pane = new Pane();
		pane.getChildren().add(textpane);
		loadPanes(pane);

	}

	public void loadPanes(Pane pane) {
		box = new VBox();
		box.getChildren().add(pane);

		//scrollPane.getChildrenUnmodifiable().add(box);

		System.out.println(box.getChildren());
	}

}
