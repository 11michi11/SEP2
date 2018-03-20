package client.view;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

public class ClientMainPaneParentHandler {
	
	@FXML
	private Pane root;
	@FXML
	private VBox box;
	@FXML
	private ScrollPane scroll;
	private ClientController controller;
	
	public ClientMainPaneParentHandler() {
		controller = ClientController.getInstance();
		System.out.println("parent");
	}
	
	
	public void loadPanes(Pane pane) {
		//TextFlow flow = (TextFlow) pane.getChildren().get(0);
		System.out.printf("%b%n", root);
//		box = new VBox();
//		box.getChildren().add(this.pane);
		//scroll.getChildrenUnmodifiable().add(box);	
	}

}
