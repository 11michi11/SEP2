package client.view.Administrator;

import java.io.IOException;
import java.util.ArrayList;

import client.controller.ClientController;
import client.view.ClientViewManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Discussion;
import model.Homework;
import model.Post;

public class AdminMainHandler {

    @FXML
    private VBox box;
    private ClientController controller;
    private Stage stage;
    private FXMLLoader backLoader = new FXMLLoader(getClass().getResource("/client/view/fxml/mainPaneAdmin.fxml"));

    public AdminMainHandler() {
        controller = ClientController.getInstance();
        stage = ClientViewManager.getStage();
        System.out.println("adminMainHandler");
    }

    @FXML
    public void initialize() {
        System.out.println("second");
        System.out.println(box);
        loadPosts();

    }

    private void loadPosts() {
        ArrayList<Post> posts = controller.getAllPosts();
        for (Post p : posts) {
            switch (p.getClass().getSimpleName()) {
                case "Homework":
                    loadHomework((Homework) p);
                    break;
                case "Post":
                    loadPost(p);
                    break;
                case "Discussion":
                    loadDiscussion((Discussion) p);
                default:
                    break;
            }
        }
    }

    private void loadHomework(Homework homework) {

        Text title = new Text(homework.getTitle());
        title.setId("title");
        Text content = new Text(homework.getContent());
        content.setId("content");
        Text deadline = new Text(homework.getDeadline().toString());
        Text separator = new Text("\n" + "\n");
        Text separator1 = new Text("\n" + "\n" + " ");

        TextFlow textFlow = new TextFlow(title, separator, content, separator1, deadline);
        textFlow.setTextAlignment(TextAlignment.JUSTIFY);
        textFlow.setAccessibleText(homework.getContent());
        textFlow.setPrefWidth(830);
        textFlow.getStyleClass().add("textPane");

        addPane(textFlow);
    }

    private void loadPost(Post post) {
        Text title = new Text(post.getTitle());
        title.setId("title");
        Text content = new Text(post.getContent());
        content.setId("content");
        Text separator = new Text("\n" + "\n");

        TextFlow textFlow = new TextFlow(title, separator, content);
        textFlow.setTextAlignment(TextAlignment.JUSTIFY);
        textFlow.setAccessibleText(post.getContent());
        textFlow.setPrefWidth(830);
        textFlow.getStyleClass().add("textPane");

        addPane(textFlow);
    }

    private void loadDiscussion(Discussion discussion) {
        Text title = new Text(discussion.getTitle());
        title.setId("title");
        Text content = new Text(discussion.getContent());
        content.setId("content");
        Text separator = new Text("\n" + "\n");

        TextFlow textFlow = new TextFlow(title,separator, content);
        textFlow.setTextAlignment(TextAlignment.JUSTIFY);
        textFlow.setAccessibleText(discussion.getContent());
        textFlow.setPrefWidth(830);
        textFlow.getStyleClass().add("textPane");

        addPane(textFlow);
    }


    private void addPane(Pane pane) {
        box.getChildren().add(pane);
    }

    public void familyList() {
        Parent mainPane;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/familyList.fxml"));
            mainPane = loader.load();
            mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void teacherList() {
        Parent mainPane;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/teacherList.fxml"));
            mainPane = loader.load();
            mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void announcementHandler() {
        Parent mainPane;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/postHandler.fxml"));
            mainPane = loader.load();
            ((AnnouncementListHandler) loader.getController()).setBackLoader(backLoader);
            mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void homeworkHandler() {
        Parent mainPane;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/homeworkHandler.fxml"));
            mainPane = loader.load();
            ((HomeworkListHandler) loader.getController()).setBackLoader(backLoader);
            mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void discussionHandler() {
        Parent mainPane;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/discussionHandler.fxml"));
            mainPane = loader.load();
            ((DiscussionListHandler) loader.getController()).setBackLoader(backLoader);
            mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




