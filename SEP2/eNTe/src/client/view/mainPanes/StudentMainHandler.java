package client.view.mainPanes;

import java.io.IOException;
import java.util.ArrayList;

import client.controller.ClientController;
import client.view.IconImage;
import client.view.managingPosts.DiscussionCommentsHandler;
import client.view.managingPosts.DiscussionListHandler;
import client.view.managingPosts.HomeworkReplyHandler;
import client.view.managingPosts.HomeworkListHandler;
import client.view.ClientViewManager;
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
import model.*;

import javax.swing.*;

public class StudentMainHandler {
    @FXML
    private VBox VBox;
    private ClientController controller;
    private Stage stage;
    private FXMLLoader backLoader = new FXMLLoader(getClass().getResource("/client/view/fxml/mainPaneStudent.fxml"));

    public StudentMainHandler() {
        controller = ClientController.getInstance();
        stage = ClientViewManager.getStage();
    }

    @FXML
    public void initialize() {
        loadPosts();
    }

    private void addPane(Pane pane) {
        VBox.getChildren().add(pane);
    }

    public void reload() {
        VBox.getChildren().clear();
        loadPosts();
    }


	private void loadPosts() {
		ArrayList<Post> posts = controller.getAllPosts();
		for (Post p : posts) {
			switch (p.getClass().getSimpleName()) {
				case "Homework":
					if (controller.checkHomeworkClass((Homework) p)) {
						loadHomework((Homework) p);
					}
					break;
				case "Announcement":
					loadAnnouncement((Announcement) p);
					break;
                case "Discussion":
                    loadDiscussion((Discussion) p);
				default:
					break;
			}
		}
	}

    public void submit(Homework homework) {
        Parent mainPane;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/homeworkReply.fxml"));
            mainPane = loader.load();
            ((HomeworkReplyHandler) loader.getController()).setHomework(homework);
            mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
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
        Text separator2 = new Text("\n" + "\n");

        HomeworkReply reply = homework.getStudentReply(controller.getCurrentUserId());
        Button solution;
        if (reply != null)
            solution = new Button("edit solution");
        else
            solution = new Button("submit");

        solution.addEventHandler(MouseEvent.MOUSE_CLICKED, new SubmitHomeworkHandler(homework));
        solution.getStyleClass().add("smallButton");

        TextFlow textFlow = new TextFlow(IconImage.getHomIcon(), title, separator, content, separator1, deadline, separator2, solution);
        textFlow.setTextAlignment(TextAlignment.JUSTIFY);
        textFlow.setAccessibleText(homework.getContent());
        textFlow.setPrefWidth(830);
	    textFlow.getStyleClass().add("textPane");

		addPane(textFlow);
    }

    private void loadAnnouncement(Announcement announcement) {
        Text title = new Text(announcement.getTitle());
        title.setId("title");
        Text content = new Text(announcement.getContent());
        content.setId("content");
        Text separator = new Text("\n" + "\n");

        TextFlow textFlow;
        if (announcement.getSpecialType().toString().toLowerCase().equals("important")) {
            textFlow = new TextFlow(IconImage.getImpIcon(), IconImage.getAnnIcon(),title, separator, content); }
        else {textFlow = new TextFlow(IconImage.getAnnIcon(),title, separator, content); }

        textFlow.setTextAlignment(TextAlignment.JUSTIFY);
        textFlow.setAccessibleText(announcement.getContent());
        textFlow.setPrefWidth(830);
        textFlow.getStyleClass().add("textPane");

        addPane(textFlow);

    }

    private void loadDiscussion(Discussion discussion) {
        Text title = new Text(discussion.getTitle());
        title.setId("title");
        Text content = new Text(discussion.getContent());
        content.setId("content");
        Button showComments = new Button("comments");
        showComments.getStyleClass().add("smallButton");
        showComments.addEventHandler(MouseEvent.MOUSE_CLICKED, new StudentMainHandler.ShowComments(discussion));

        Text separator = new Text("\n" + "\n");
        Text separator1 = new Text("\n" + "\n");

        TextFlow textFlow = new TextFlow(IconImage.getDisIcon(),title,separator, content, separator1, showComments);
        textFlow.setTextAlignment(TextAlignment.JUSTIFY);
        textFlow.setAccessibleText(discussion.getContent());
        textFlow.setPrefWidth(830);
        textFlow.getStyleClass().add("textPane");

        addPane(textFlow);
    }

    public void homeworkHandler() {
        Parent mainPane;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/homeworkHandler.fxml"));
            mainPane = loader.load();
            mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
            stage.getScene().setRoot(mainPane);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class SubmitHomeworkHandler implements EventHandler<Event> {

        private Homework homework;

        private SubmitHomeworkHandler(Homework homework) {
            this.homework = homework;
        }

        @Override
        public void handle(Event event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/homeworkReply.fxml"));
                Parent mainPane = loader.load();
                ((HomeworkReplyHandler) loader.getController()).setHomework(homework);
                mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
                stage.getScene().setRoot(mainPane);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

}
