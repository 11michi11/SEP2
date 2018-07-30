package client.view.managingPosts;

import java.io.IOException;

import client.controller.ClientController;
import client.view.ClientViewManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import client.view.GoBackMap;
import model.Homework;
import model.HomeworkReply;

public class HomeworkReplyHandler {

    private ClientController controller;
    private Stage stage;
    @FXML
    private TextArea area;
    private Homework homework;
    private HomeworkReply reply;

    public HomeworkReplyHandler() {
        controller = ClientController.getInstance();
        System.out.println("TextAreaForHomeworkHandler");
        stage = ClientViewManager.getStage();
    }

    public void submit() {
        controller.submitHomework(homework, area.getText());
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

    public void setReply(HomeworkReply reply) {
        this.reply = reply;
        area.setText(reply.getContent());
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
        HomeworkReply reply = homework.getStudentReply(controller.getCurrentUserId());
        if (reply != null) {
            area.setText(reply.getContent());
        }
    }

}
