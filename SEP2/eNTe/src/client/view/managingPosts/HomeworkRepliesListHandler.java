package client.view.managingPosts;

import client.controller.ClientController;
import client.view.ClientViewManager;
import client.view.GoBackMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.stream.Collectors;


public class HomeworkRepliesListHandler {

    @FXML
    private TableView<HomeworkReplyDT> replyList;
    @FXML
    private TableColumn<HomeworkReplyDT, String> name;
    @FXML
    private TableColumn<HomeworkReplyDT, String> classNo;
    @FXML
    private TableColumn<HomeworkReplyDT, String> handInDate;
    @FXML
    private TableColumn<HomeworkReplyDT, Boolean> late;

    private ClientController controller;
    private Stage stage;
    private Homework homework;

    public HomeworkRepliesListHandler() {
        controller = ClientController.getInstance();
        System.out.println("HomeworkRepliesListHandler");
        stage = ClientViewManager.getStage();
    }

    @FXML
    public void initialize() {

    }

    private void openReply(HomeworkReply reply) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/ReplyViewHandler.fxml"));
            Parent mainPane = loader.load();
            mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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

    public void loadReplies(Homework homework) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        classNo.setCellValueFactory(new PropertyValueFactory<>("classNo"));
        handInDate.setCellValueFactory(new PropertyValueFactory<>("handInDate"));
        late.setCellValueFactory(new PropertyValueFactory<>("late"));

        replyList.getColumns().clear();
        replyList.getColumns().addAll(name, classNo, handInDate, late);
        replyList.setRowFactory(tv -> {
            TableRow<HomeworkReplyDT> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    openReply(row.getTableView().getSelectionModel().getSelectedItem().getReply());
                }
            });
            return row;
        });
        ObservableList<HomeworkReplyDT> replies = FXCollections.observableArrayList(homework.getReplies().stream().map(HomeworkReplyDT::new).collect(Collectors.toList()));
        replyList.setItems(replies);
    }
}