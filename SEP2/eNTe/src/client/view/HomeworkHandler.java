package client.view;

import java.io.IOException;
import java.util.ArrayList;

import client.controller.ClientController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

public class HomeworkHandler {

	@FXML
	private VBox box;
	@FXML
	private ImageView ente;
	private ClientController controller;
	private Stage stage;
	private Parent mainPane;

	public HomeworkHandler() {
		controller = ClientController.getInstance();
		stage = ClientViewManager.getStage();
		System.out.println("HomeworkHandler");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/mainPaneAdmin.fxml"));
		try {
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
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
			if(p.getClass().getSimpleName().equals("Homework")) {
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

		//Pane pane = new Pane();
		//pane.setMinHeight(100);
//		Pane pane = new Pane() {
//			@Override
//			protected void layoutChildren() {
//				super.layoutChildren();
//				TextFlow textFlow = (TextFlow) getChildren().get(0);
//				setMinHeight(textFlow.getHeight() + 5);
//				//autosize();
//			}
//		};
//		pane.getChildren().addAll(textFlow);
//		pane.getStyleClass().add("textPane");
		addPane(textFlow);
	}

	private void addPane(Pane pane) {
		box.getChildren().add(pane);
	}

	public void createHomework() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/createHomework.fxml"));
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
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

	private class EditHomeworkHandler implements EventHandler<Event>{

		private Homework homework;

		private EditHomeworkHandler(Homework homework) {
			this.homework = homework;
		}

		@Override
		public void handle(Event event) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/createHomework.fxml"));
				mainPane = loader.load();
				((CreateHomeworkHandler) loader.getController()).setHomework(homework);
				mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
				stage.getScene().setRoot(mainPane);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}
	private class DeleteHomeworkHandler implements EventHandler<Event>{

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
	private class ListOfHomeworkHandler implements EventHandler<Event>{

		private Homework homework;

		private ListOfHomeworkHandler(Homework homework) {
			this.homework = homework;
		}

		@Override
		public void handle(Event event) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/homeworkRepliesList.fxml"));
				mainPane = loader.load();
				((HomeworkRepliesListHandler) loader.getController()).setHomework(homework);
				mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
				stage.getScene().setRoot(mainPane);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}
}



