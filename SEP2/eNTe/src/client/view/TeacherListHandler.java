package client.view;

import java.io.IOException;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TeacherListHandler {
	
	@FXML
	private ImageView ente;  
    private ClientController controller;
    private Stage stage;
    private Parent mainPane;
    
    
    public TeacherListHandler() {
    	 controller = ClientController.getInstance();
         System.out.println("TeacherListHandler");
         stage = ClientViewManager.getStage();
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/mainPaneAdmin.fxml"));
 		try {
 			mainPane = loader.load();
 			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
    }
    
    public void createTeacher() {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/createTeacher.fxml"));
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
			stage.getScene().setRoot(mainPane);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}

}
