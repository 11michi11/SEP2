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
		System.out.println("parent");
	}
	
	
	public void loadPanes(Pane pane) {
		box.getChildren().add(pane);
		System.out.println(box.getChildren());	
	}

}
