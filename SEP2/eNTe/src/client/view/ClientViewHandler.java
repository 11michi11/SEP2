package client.view;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ClientViewHandler {
	
	private ClientController controller;
	
	@FXML 
	private Label loginLabel;
	
	@FXML 
	private Label passwordLabel;
	
	@FXML 
	private Button loginButton;
	
	
	@FXML
	private void loginHandler() {
		String name = loginLabel.getText();
		String password = passwordLabel.getText();
		controller.login(name, password);
		
		
	}

}
