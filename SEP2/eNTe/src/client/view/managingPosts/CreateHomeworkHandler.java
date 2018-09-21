package client.view.managingPosts;

import client.controller.ClientController;
import client.view.ClientViewManager;
import client.view.GoBackMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.ClassNo;
import model.Homework;
import model.MyDate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateHomeworkHandler {
    private ClientController controller;
    private Stage stage;

    @FXML
    private TextArea title;
    @FXML
    private ChoiceBox minute, hour;
    @FXML
    private TextArea content;
    @FXML
    private DatePicker deadline;
    @FXML
    private TextField numberOfStudentsToDeliver;
    @FXML
    private CheckBox first, second, third, fourth, fifth, sixth, seventh, eight;
    private Homework homework;
    private FXMLLoader backLoader;


    public CreateHomeworkHandler() {
        controller = ClientController.getInstance();
        stage = ClientViewManager.getStage();
        System.out.println("HomeworkListHandler");
    }

    @FXML
    public void initialize() {
        hour.getItems().addAll(0, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        minute.getItems().addAll(0, 15, 30, 45);
        content.setWrapText(true);
    }

    public void setHomework(Homework homework) {
        MyDate date = homework.getDeadline();
        LocalDate localDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
        this.homework = homework;
        title.setText(homework.getTitle());
        content.setText(homework.getContent());
        deadline.setValue(localDate);
        numberOfStudentsToDeliver.setText(String.valueOf(homework.getNumberOfStudentsToDeliver()));
        hour.setValue(date.getHour());
        minute.setValue(date.getMinute());
        List<ClassNo> classesNumbers= homework.getClasses();
        if (classesNumbers.contains(ClassNo.First))
            first.setSelected(true);
        if(classesNumbers.contains(ClassNo.Second))
            second.setSelected(true);
        if(classesNumbers.contains(ClassNo.Third))
            third.setSelected(true);
        if(classesNumbers.contains(ClassNo.Fourth))
            fourth.setSelected(true);
        if(classesNumbers.contains(ClassNo.Fifth))
            fifth.setSelected(true);
        if(classesNumbers.contains(ClassNo.Sixth))
            sixth.setSelected(true);
        if(classesNumbers.contains(ClassNo.Seventh))
            seventh.setSelected(true);
        if(classesNumbers.contains(ClassNo.Eighth))
            eight.setSelected(true);

    }

    public void addHomework() {
        checkForNull();
        if (checkForNull()) {
            warningDialog();
        } else {
            LocalDate localDate = deadline.getValue();
            MyDate deadlineDate = new MyDate(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());

            if (homework != null) {
                controller.editHomework(homework.getPostId(),title.getText(), content.getText(), deadlineDate, getClasses(), homework.getReplies(), Integer.valueOf(numberOfStudentsToDeliver.getText()));
                System.out.println("homework edited" + title.getText() + content.getText() + deadlineDate + getClasses() + Integer.valueOf(numberOfStudentsToDeliver.getText()));
            } else {
                controller.addHomework(title.getText(), content.getText(), deadlineDate, getClasses(), Integer.valueOf(numberOfStudentsToDeliver.getText()));
                System.out.println("homework added" + title.getText() + content.getText() + deadlineDate + getClasses() + Integer.valueOf(numberOfStudentsToDeliver.getText()));
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/homeworkHandler.fxml"));
                Parent mainPane = loader.load();
                mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
                stage.getScene().setRoot(mainPane);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkForNull() {
        return title.getText().equals("") || content.getText().equals("") || deadline.getValue() == null || getClasses() == null || numberOfStudentsToDeliver.getText().equals("");
    }

    private void warningDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, unfinished selection");
        alert.setContentText("Fill out everything, please");
        alert.showAndWait();
    }


    private List<ClassNo> getClasses() {
        return getClassNos(first, second, third, fourth, fifth, sixth, seventh, eight);
    }

    private static List<ClassNo> getClassNos(CheckBox first, CheckBox second, CheckBox third, CheckBox fourth, CheckBox fifth, CheckBox sixth, CheckBox seventh, CheckBox eight) {
        ArrayList<ClassNo> classes = new ArrayList<>();
        if (first.isSelected())
            classes.add(ClassNo.First);
        if (second.isSelected())
            classes.add(ClassNo.Second);
        if (third.isSelected())
            classes.add(ClassNo.Third);
        if (fourth.isSelected())
            classes.add(ClassNo.Fourth);
        if (fifth.isSelected())
            classes.add(ClassNo.Fifth);
        if (sixth.isSelected())
            classes.add(ClassNo.Sixth);
        if (seventh.isSelected())
            classes.add(ClassNo.Seventh);
        if (eight.isSelected())
            classes.add(ClassNo.Eighth);

        return classes;
    }

    public void setBackLoader(FXMLLoader backLoader) {
        this.backLoader = backLoader;
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

}
