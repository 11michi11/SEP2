package client.view.Administrator;

import java.io.IOException;
import java.util.ArrayList;

import client.controller.ClientController;
import client.view.ClientViewManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Homework;
import model.Post;

public class HomeworkListHandler {

    @FXML
    private VBox box;
    @FXML
    private Button createHomework;
    private ClientController controller;
    private Stage stage;
    private FXMLLoader backLoader;

    public HomeworkListHandler() {
        controller = ClientController.getInstance();
        stage = ClientViewManager.getStage();
    }

    @FXML
    public void initialize() {
        String userType = controller.getCurrentUserType();
        if(userType.equals("Parent") || userType.equals("Student"))
            createHomework.setVisible(false);
        loadPosts();
    }

    public void setBackLoader(FXMLLoader backLoader) {
        this.backLoader = backLoader;
    }

    public void goBack() {
        try {
            Parent mainPane = backLoader.load();
            mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPosts() {
        ArrayList<Post> posts = controller.getAllPosts();
        for (Post p : posts) {
            if (p.getClass().getSimpleName().equals("Homework")) {
                loadHomework((Homework) p);
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
        Text separator2 = new Text("\n" + "\n" + " ");
        Text separator3 = new Text(" ");
        Text separator4 = new Text(" ");


        Button list = new Button("DONE BY:");
        list.addEventHandler(MouseEvent.MOUSE_CLICKED, new ListOfHomeworkHandler(homework));
        list.getStyleClass().add("smallButton");
        Button edit = new Button("EDIT");
        edit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EditHomeworkHandler(homework));
        edit.getStyleClass().add("smallButton");
        Button delete = new Button("DELETE");
        delete.addEventHandler(MouseEvent.MOUSE_CLICKED, new DeleteHomeworkHandler(homework));
        delete.getStyleClass().add("smallButton");


        TextFlow textFlow = new TextFlow(title, separator, content, separator1, deadline, separator2, list, separator3, edit, separator4, delete);
        textFlow.setTextAlignment(TextAlignment.JUSTIFY);
        textFlow.setAccessibleText(homework.getContent());
        textFlow.setPrefWidth(830);
        textFlow.getStyleClass().add("textPane");

        addPane(textFlow);

        String userType = controller.getCurrentUserType();
        if(userType.equals("Parent") || userType.equals("Student")) {
            list.setVisible(false);
            edit.setVisible(false);
            delete.setVisible(false);
        }

    }

    private void addPane(Pane pane) {
        box.getChildren().add(pane);
    }

    public void createHomework() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/createHomework.fxml"));
            Parent mainPane = loader.load();
            ((CreateHomeworkHandler) loader.getController()).setBackLoader(loader);
            mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private class EditHomeworkHandler implements EventHandler<Event> {

        private Homework homework;

        private EditHomeworkHandler(Homework homework) {
            this.homework = homework;
        }

        @Override
        public void handle(Event event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/createHomework.fxml"));
                Parent mainPane = loader.load();
                ((CreateHomeworkHandler) loader.getController()).setHomework(homework);
                mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
                stage.getScene().setRoot(mainPane);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private class DeleteHomeworkHandler implements EventHandler<Event> {

        private Homework homework;

        private DeleteHomeworkHandler(Homework homework) {
            this.homework = homework;
        }

        @Override
        public void handle(Event event) {
            controller.deletePost(homework);
            box.getChildren().clear();
            loadPosts();
        }
    }

    private class ListOfHomeworkHandler implements EventHandler<Event> {

        private Homework homework;

        private ListOfHomeworkHandler(Homework homework) {
            this.homework = homework;
        }

        @Override
        public void handle(Event event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/homeworkRepliesList.fxml"));
                Parent mainPane = loader.load();
                ((HomeworkRepliesListHandler) loader.getController()).loadReplies(homework);
                mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
                stage.getScene().setRoot(mainPane);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}



