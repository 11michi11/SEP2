package client.view.managingPosts;

import client.controller.ClientController;
import client.view.ClientViewManager;
import client.view.GoBackMap;
import client.view.IconImage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Discussion;
import model.Homework;
import model.Post;

import java.io.IOException;
import java.util.ArrayList;

public class DiscussionListHandler {

    @FXML
    private VBox box;
    private ClientController controller;
    private Stage stage;
    private FXMLLoader backLoader;

    public DiscussionListHandler() {
        controller = ClientController.getInstance();
        stage = ClientViewManager.getStage();
        System.out.println("DiscussionListHandler");

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
        Button showComments = new Button("COMMENTS");
        showComments.getStyleClass().add("smallButton");
        showComments.addEventHandler(MouseEvent.MOUSE_CLICKED, new ShowComments(discussion));
        Button delete = new Button("DELETE");
        delete.getStyleClass().add("smallButton");
        delete.addEventHandler(MouseEvent.MOUSE_CLICKED, new DeleteDiscussionHandler(discussion));
        Text separator = new Text("\n" + "\n");
        Text separator1 = new Text("\n" + "\n");
        Text separator2 = new Text(" ");

        TextFlow textFlow;
        if (discussion.getSpecialType().toString().toLowerCase().equals("parental")) {
            textFlow = new TextFlow(IconImage.getDisIcon(), IconImage.getParIcon(), title, separator, content, separator1, showComments, separator2, delete);
        } else {
            textFlow = new TextFlow(IconImage.getDisIcon(), title, separator, content, separator1, showComments, separator2, delete);
        }
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/createDiscussion.fxml"));
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
                ((DiscussionCommentsHandler) loader.getController()).loadComments(discussion);
                mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
                stage.getScene().setRoot(mainPane);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class DeleteDiscussionHandler implements EventHandler<Event> {

        private Discussion discussion;

        private DeleteDiscussionHandler(Discussion discussion) {
            this.discussion = discussion;
        }

        @Override
        public void handle(Event event) {
            controller.deletePost(discussion);
            box.getChildren().clear();
            loadPosts();
        }
    }
}
