package client.view;

import java.io.IOException;
import java.util.ArrayList;

import client.controller.ClientController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Homework;
import model.Post;

public class AdminMainHandler {

    @FXML
    private VBox box;

    private ClientController controller;
    private Stage stage;

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
        textFlow.setPrefWidth(842);

        Pane pane = new Pane() {
            @Override
            protected void layoutChildren() {
                super.layoutChildren();
                TextFlow textFlow = (TextFlow) getChildren().get(0);
                setMinHeight(textFlow.getHeight() + 50);
                autosize();
            }
        };
        pane.getChildren().addAll(textFlow);
        pane.getStyleClass().add("textPane");
        addPane(pane);
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
        textFlow.setPrefWidth(842);

        Pane pane = new Pane() {
            @Override
            protected void layoutChildren() {
                super.layoutChildren();
                TextFlow textFlow = (TextFlow) getChildren().get(0);
                setMinHeight(textFlow.getHeight() + 5);
                autosize();
            }
        };
        pane.getChildren().add(textFlow);
        pane.getStyleClass().add("textPane");
        addPane(pane);
    }

    private void addPane(Pane pane) {
        box.getChildren().add(pane);
    }

    public void familyList() {
        Parent mainPane;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/familyList.fxml"));
            mainPane = loader.load();
            mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
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
            mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void postCreation() {
        Parent mainPane;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/postCreation.fxml"));
            mainPane = loader.load();
            mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void homeworkCreation() {
        Parent mainPane;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/homeworkCreation.fxml"));
            mainPane = loader.load();
            mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void discussionCreation() {
        Parent mainPane;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/discussionCreation.fxml"));
            mainPane = loader.load();
            mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




