package client.view;

import client.controller.ClientController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ClientViewHandler {

	private ClientController controller;
	
	public ClientViewHandler() {
		controller = ClientController.getInstance();
		System.out.println("client controller successfull");
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
	
	
	
}
