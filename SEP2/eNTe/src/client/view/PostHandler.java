package client.view;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Post;


public class PostHandler {

    @FXML
    private Button createPost, editPost;
    @FXML
    private VBox box;
    private ClientController controller;
    private Stage stage;

    public PostHandler() {
        controller = ClientController.getInstance();
        stage = ClientViewManager.getStage();
        System.out.println("PostHandler");
    }

    @FXML
    public void initialize() {
//        Post[] posts = controller.getPosts();
//
//        Text title = new Text(posts[0].getTitle());
//        title.setId("title");
//        Text content = new Text(posts[0].getContent());
//        content.setId("content");
//        Text separator = new Text("\n");
//
//        TextFlow textFlow = new TextFlow(title, separator, content);
//        textFlow.setTextAlignment(TextAlignment.JUSTIFY);
//        textFlow.setAccessibleText(posts[0].getContent());
//        textFlow.setPrefWidth(842);
//
//        Pane pane = new Pane() {
//            @Override
//            protected void layoutChildren() {
//                super.layoutChildren();
//                TextFlow textFlow = (TextFlow) getChildren().get(0);
//                setMinHeight(textFlow.getHeight()+5);
//                autosize();
//            }
//        };
//        pane.getChildren().add(textFlow);
//        pane.getStyleClass().add("textPane");
//        loadPanes(pane);

    }
    public void loadPanes(Pane pane) {
        box.getChildren().clear();
        box.getChildren().add(pane);
    }

    public void createPost() {
        TextField title = new TextField();
        title.setText("Title");
        title.setId("title");
        TextArea content = new TextArea();
        content.setText("Write a content");
        content.setId("content");

        VBox text = new VBox();
        text.getChildren().addAll(title, content);

        box.getChildren().add(0, text);
    }

    public void addPost() {
        VBox text = (VBox) box.getChildren().get(0);
        TextField title = (TextField) text.getChildren().get(0);
        TextArea content = (TextArea) text.getChildren().get(1);

        controller.addPost(title.getText(), content.getText());
    }
}
