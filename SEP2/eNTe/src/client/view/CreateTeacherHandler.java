package client.view;

import java.io.IOException;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CreateTeacherHandler {
	
	@FXML
	private ImageView ente;
	@FXML
	private TextField name, email;
	@FXML
	private CheckBox admin;
    
    private ClientController controller;
    private Stage stage;
    private Parent mainPane;
    
    public CreateTeacherHandler() {
    	 controller = ClientController.getInstance();
         System.out.println("CreateTeacherHandler");
         stage = ClientViewManager.getStage();
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/TeacherList.fxml"));
 		try {
 			mainPane = loader.load();
 			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
    }
    
    public void save() {
    	controller.addTeacher(name.getText(), email.getText(), admin.isSelected());
    	goBack();
    }
    
    public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}

}
