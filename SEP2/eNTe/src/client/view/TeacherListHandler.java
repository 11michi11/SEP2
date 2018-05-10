package client.view;

import java.io.IOException;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TeacherListHandler {
	
	@FXML
    private TableView<TeacherDT> teacherList;
    @FXML
    private TableColumn<TeacherDT, String> nameColumn;
    @FXML
    private TableColumn<TeacherDT, String> emailColumn;
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
    
    @FXML
    public void initialize() {
    	nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        teacherList.getColumns().clear();
        teacherList.getColumns().addAll(nameColumn, emailColumn);
        teacherList.setItems(controller.getTeachersForView());
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