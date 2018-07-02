package client.view.Administrator;

import java.io.IOException;

import client.controller.ClientController;
import client.view.ClientViewManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Teacher;

public class CreateTeacherHandler {

    @FXML
    private ImageView ente;
    @FXML
    private TextField name, email;
    private ClientController controller;
    private Stage stage;
    private Parent mainPane;
    private Teacher teacher;

    public CreateTeacherHandler() {
        controller = ClientController.getInstance();
        System.out.println("CreateTeacherHandler");
        stage = ClientViewManager.getStage();

    }

    public void save() {
        String id = null;
        if (teacher != null)
            id = teacher.getId();
        controller.addTeacher(name.getText(), email.getText(), id);
        goBack();
    }

    public void goBack() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/teacherList.fxml"));
        try {
            mainPane = loader.load();
            mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.getScene().setRoot(mainPane);
        stage.show();
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        name.setText(teacher.getName());
        email.setText(teacher.getEmail());
    }

}
