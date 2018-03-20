package client.view;

import client.controller.ClientController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LoginHandler {

	private ClientController controller;
	
	public LoginHandler() {
		controller = ClientController.getInstance();
	}
	
	@FXML
	public void initialize() {
		
	}
	
	@FXML
	private TextField loginField;

	@FXML
	private TextField passwordField;

	@FXML
	private Button loginButton;
	@FXML
	private Pane root;
	@FXML
	private VBox box;
	

	@FXML
	private void loginHandler() {
		String name = loginField.getText();
		String password = passwordField.getText();
		controller.login(name, password);
	}

	public void loginFieldInitialize() {
		loginField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (newPropertyValue) {
					System.out.println("");
				} else {
					System.out.println("username");
				}
			}
		});
	}
	
	public void loadPanes(Pane pane) {
		//TextFlow flow = (TextFlow) pane.getChildren().get(0);
		System.out.printf("%b%n", root);
//		box = new VBox();
//		box.getChildren().add(this.pane);
		//scroll.getChildrenUnmodifiable().add(box);	
	}
	
	
	
}
