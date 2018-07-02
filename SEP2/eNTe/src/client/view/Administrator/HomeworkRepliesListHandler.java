package client.view.Administrator;

import client.controller.ClientController;
import client.view.ClientViewManager;
import client.view.Teacher.HomeworkReplyTeacherHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Homework;
import model.HomeworkReply;

import java.io.IOException;


public class HomeworkRepliesListHandler {

    @FXML
    private TableView<HomeworkReplyDT> replyList;
    @FXML
    private TableColumn<HomeworkReplyDT, String> name;
    @FXML
    private TableColumn<HomeworkReplyDT, String> classNo;
    @FXML
    private TableColumn<HomeworkReplyDT, String> handIn;
    @FXML
    private ImageView ente;
    private ClientController controller;
    private Stage stage;
    private Parent mainPane;
    private Homework homework;

    public HomeworkRepliesListHandler() {
        controller = ClientController.getInstance();
        System.out.println("HomeworkRepliesListHandler");
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
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        classNo.setCellValueFactory(new PropertyValueFactory<>("classNo"));
        handIn.setCellValueFactory(new PropertyValueFactory<>("handIn date"));

        replyList.getColumns().clear();
        replyList.getColumns().addAll(name, classNo, handIn);
        replyList.setRowFactory(tv -> {
            TableRow<HomeworkReplyDT> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    openReply(row.getTableView().getSelectionModel().getSelectedItem().getReply());
                }
            });
            return row;
        });
    }

    private void openReply(HomeworkReply reply) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/homeworkReplyTeacher.fxml"));
            mainPane = loader.load();
            ((HomeworkReplyTeacherHandler) loader.getController()).setReply(reply);
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

    public void setHomework(Homework homework) {
        this.homework = homework;
    }
}