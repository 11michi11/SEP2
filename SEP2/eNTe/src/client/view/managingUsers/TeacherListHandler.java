package client.view.managingUsers;

import java.io.IOException;

import client.controller.ClientController;
import client.view.ClientViewManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Teacher;

public class TeacherListHandler {

    @FXML
    private TableView<TeacherDT> teacherList;
    @FXML
    private TableColumn<TeacherDT, String> nameColumn;
    @FXML
    private TableColumn<TeacherDT, String> emailColumn;
    private ClientController controller;
    private Stage stage;
    private Parent mainPane;


    public TeacherListHandler() {
        controller = ClientController.getInstance();
        System.out.println("TeacherListHandler");
        stage = ClientViewManager.getStage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/mainPaneAdmin.fxml"));
        try {
            mainPane = loader.load();
            mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/createTeacher.fxml"));
            mainPane = loader.load();
            mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTeacher() {
        Teacher teacher = teacherList.getSelectionModel().getSelectedItem().teacher;
        System.out.println("teacher:" + teacher);
        controller.deleteUser(teacher);
        teacherList.getItems().clear();
        teacherList.setItems(controller.getTeachersForView());

    }

    public void editTeacher() {
        Teacher teacher = teacherList.getSelectionModel().getSelectedItem().teacher;
        System.out.println("teacher:" + teacher);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/createTeacher.fxml"));
            mainPane = loader.load();
            ((CreateTeacherHandler) loader.getController()).setTeacher(teacher);
            mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
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
