package client.view.Administrator;

import client.controller.ClientController;
import client.view.ClientViewManager;
import client.view.Student.HomeworkReplyHandler;
import client.view.Student.StudentMainHandler;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Discussion;
import model.Post;

import java.io.IOException;
import java.util.ArrayList;

public class DiscussionHandler {

    @FXML
    private Button createPost, editPost;
    @FXML
    private VBox box;
    @FXML
    private ImageView ente;
    private ClientController controller;
    private Stage stage;
    private Parent mainPane;

    public DiscussionHandler() {
        controller = ClientController.getInstance();
        stage = ClientViewManager.getStage();
        System.out.println("DiscussionHandler");
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
        System.out.println("second");
        System.out.println(box);
        loadPosts();
    }
    private void loadPosts() {

        ArrayList<Post> posts = controller.getAllPosts();
        for(Post p : posts) {
            if(p.getClass().getSimpleName().equals("Discussion")) {
                loadDiscussion((Discussion) p);
            }
        }
    }

    private void loadDiscussion(Discussion discussion) {
        Text title = new Text(discussion.getTitle());
        title.setId("title");
        Text content = new Text(discussion.getContent());
        content.setId("content");
        Button showComments = new Button("comments");
        showComments.getStyleClass().add("smallButton");
        showComments.addEventHandler(MouseEvent.MOUSE_CLICKED, new ShowComments(discussion));

        Text separator = new Text("\n" + "\n");
        Text separator1 = new Text("\n" + "\n");

        TextFlow textFlow = new TextFlow(title,separator, content, separator1, showComments);
        textFlow.setTextAlignment(TextAlignment.JUSTIFY);
        textFlow.setAccessibleText(discussion.getContent());
        textFlow.setPrefWidth(830);
        textFlow.getStyleClass().add("textPane");

        addPane(textFlow);
    }

    private void addPane(Pane pane) {
        box.getChildren().add(pane);
    }


    public void createDiscussion() {
        TextField title = new TextField();
        title.setText("Title");
        title.setId("title");
        TextArea content = new TextArea();
        content.setText("Write a content");
        content.getStyleClass().add("content");
        Button save = new Button("save");
        save.getStyleClass().add("smallButton");
        save.setOnAction(e -> addDiscussion());

        VBox text = new VBox();
        text.getChildren().addAll(title, content, save);

        box.getChildren().add(0, text);
    }

    private void addDiscussion() {
        VBox text = (VBox) box.getChildren().get(0);
        TextField title = (TextField) text.getChildren().get(0);
        TextArea content = (TextArea) text.getChildren().get(1);
        controller.addDiscussion(title.getText(), content.getText());
        reloadDiscussion();
    }

    private void reloadDiscussion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/discussionHandler.fxml"));
            mainPane = loader.load();
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

    private class ShowComments implements EventHandler<Event> {

        private Discussion discussion;
        private ShowComments(Discussion discussion) {
            this.discussion = discussion;
        }

        @Override
        public void handle(Event event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/discussionCommentsHandler.fxml"));
                Parent mainPane = loader.load();
                ((DiscussionCommentsHandler) loader.getController()).setDiscussion(discussion);
                mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
                stage.getScene().setRoot(mainPane);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
