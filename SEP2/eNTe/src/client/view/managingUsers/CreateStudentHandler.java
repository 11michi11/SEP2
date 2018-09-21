package client.view.managingUsers;

import java.io.IOException;

import client.controller.ClientController;
import client.view.ClientViewManager;
import client.view.GoBackMap;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ClassNo;
import model.Family;
import model.Student;

public class CreateStudentHandler {

    private ClientController controller;
    private Stage stage;
    @FXML
    private TextField name, email;
    @FXML
    private ChoiceBox<ClassNo> classSelector;
    private Family family;
    private Student student;

    public CreateStudentHandler() {
        controller = ClientController.getInstance();
        System.out.println("CreateStudentHandler");
        stage = ClientViewManager.getStage();
    }

    @FXML
    public void initialize() {
        classSelector.setItems(FXCollections.observableArrayList(ClassNo.getClasses()));
    }

    private boolean checkForNull() {
        return name.getText().equals("") || email.getText().equals("") || classSelector.getItems() == null;
    }

    private void warningDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, unfinished selection");
        alert.setContentText("Fill out everything, please");
        alert.showAndWait();
    }

    public void save() {
        if (checkForNull()) {
            warningDialog();
        } else {
            String id = null, pwd = null;
            if (student != null)
                id = student.getId();
            if (student != null)
                pwd = student.getPwd();
            controller.addStudent(name.getText(), email.getText(), classSelector.getValue(), family, id, pwd);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/familyList.fxml"));
            try {
                Parent mainPane = loader.load();
                mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
            } catch (IOException e) {
                e.printStackTrace();
            }
            goBack();
        }
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

    public void setFamily(Family family) {
        this.family = family;
    }

    public void setStudent(Student student) {
        this.student = student;
        family = student.getFamily();
        name.setText(student.getName());
        email.setText(student.getEmail());
        classSelector.setValue(student.getClassNo());
    }
}
