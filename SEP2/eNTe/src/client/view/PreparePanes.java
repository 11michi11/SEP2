package client.view;

import client.controller.ClientController;
import client.view.managingPosts.CreateHomeworkHandler;
import client.view.managingPosts.HomeworkRepliesListHandler;
import client.view.managingPosts.HomeworkReplyHandler;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Homework;
import model.HomeworkReply;
import model.User;

import java.io.IOException;

public class PreparePanes {

	private static ClientController controller;
	private static Stage stage;

	public PreparePanes() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
	}

	public static void loadHomework(Homework homework, User user) {

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

		Button list = new Button();
		list.getStyleClass().add("smallButton");
		Button edit = new Button();
		edit.getStyleClass().add("smallButton");
		Button delete = new Button();
		delete.getStyleClass().add("smallButton");

		if(user.toString().equals("Teacher") || user.toString().equals("Administrator")) {

			list.setText("DONE BY:");
			list.addEventHandler(MouseEvent.MOUSE_CLICKED, new PreparePanes.ListOfHomeworkHandler(homework));
			edit.setText("EDIT");
			edit.addEventHandler(MouseEvent.MOUSE_CLICKED, new PreparePanes.EditHomeworkHandler(homework));
			delete.setText("DELETE");
			delete.addEventHandler(MouseEvent.MOUSE_CLICKED, new PreparePanes.DeleteHomeworkHandler(homework));
		}

		if(user.toString().equals("Student")) {
			HomeworkReply reply = homework.getStudentReply(controller.getCurrentUserId());
			if (reply != null)
				list.setText("EDIT SOLUTION");
			else
				list.setText("SUBMIT");

			list.addEventHandler(MouseEvent.MOUSE_CLICKED, new PreparePanes.SubmitHomeworkHandler(homework));
			list.getStyleClass().add("smallButton");
			edit.setVisible(false);
			delete.setVisible(false);
		}

		if(user.toString().equals("Parent")) {
			list.setVisible(false);
			edit.setVisible(false);
			delete.setVisible(false);
		}

		TextFlow textFlow = new TextFlow(title, separator, content, separator1, deadline, separator2, list, separator3, edit, separator4, delete);
		textFlow.setTextAlignment(TextAlignment.JUSTIFY);
		textFlow.setAccessibleText(homework.getContent());
		textFlow.setPrefWidth(830);
		textFlow.getStyleClass().add("textPane");

	}

	public static class EditHomeworkHandler implements EventHandler<Event> {

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

	public static class DeleteHomeworkHandler implements EventHandler<Event> {

		private Homework homework;

		private DeleteHomeworkHandler(Homework homework) {
			this.homework = homework;
		}

		@Override
		public void handle(Event event) {
			controller.deletePost(homework);
			//box.getChildren().clear();
			//loadPosts();
		}
	}

	public static class ListOfHomeworkHandler implements EventHandler<Event> {

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


		public static class SubmitHomeworkHandler implements EventHandler<Event> {

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


	}

