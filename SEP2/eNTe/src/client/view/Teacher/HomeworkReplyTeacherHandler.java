package client.view.Teacher;

import client.controller.ClientController;
import client.view.Administrator.TeacherDT;
import client.view.ClientViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Homework;
import model.HomeworkReply;

import java.awt.*;
import java.io.IOException;

public class HomeworkReplyTeacherHandler {

	@FXML
	private TextArea replyText;
	@FXML
	private ImageView ente;
	@FXML
	private TableView<HomeworkReply> replyList;
	@FXML
	private TableColumn<HomeworkReply, String> name;
	@FXML
	private TableColumn<HomeworkReply, String> classNo;
	@FXML
	private TableColumn<HomeworkReply, String> handInDate;
	@FXML
	private TableColumn<HomeworkReply, Boolean> late;

	private ClientController controller;
	private Stage stage;
	private Parent mainPane;

	public HomeworkReplyTeacherHandler() {
		controller = ClientController.getInstance();
		System.out.println("HomeworkRepliesListHandler");
		stage = ClientViewManager.getStage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/homeworkRepliesList.fxml"));
		try {
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/fxml/login.css").toExternalForm());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void goBack() {
		stage.getScene().setRoot(mainPane);
		stage.show();
	}

	public void loadReplies(Homework homework){
//		name.setCellValueFactory(new PropertyValueFactory<>("name"));
//		classNo.setCellValueFactory(new PropertyValueFactory<>("classNo"));
//		handInDate.setCellValueFactory(new PropertyValueFactory<>("handInDate"));
//		late.setCellValueFactory(new PropertyValueFactory<>("late"));
//		replyList.getColumns().clear();
//		replyList.getColumns().addAll(name, classNo, handInDate, late);
//
//		ObservableList<HomeworkReply> replies = FXCollections.observableArrayList(homework.getReplies());
//
//		replyList.setItems(replies);
	}




}
