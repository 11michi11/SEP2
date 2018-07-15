package client.view.managingUsers;

import java.io.IOException;

import client.controller.ClientController;
import client.view.ClientViewManager;
import client.view.GoBackMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Teacher;

public class CreateTeacherHandler {

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
        String path = GoBackMap.getLoader(this.getClass(), controller.getCurrentUserType());
        FXMLLoader backLoader = new FXMLLoader(getClass().getResource(path));
        try {
            Parent mainPane = backLoader.load();
            mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        name.setText(teacher.getName());
        email.setText(teacher.getEmail());
    }

}
