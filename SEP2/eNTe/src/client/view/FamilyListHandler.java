package client.view;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Family;
import model.Student;
import model.User;

import java.io.IOException;
import java.util.stream.Collectors;

public class FamilyListHandler {

	@FXML
	private TreeTableView<TableDataType> familyTable;
	@FXML
	private TreeTableColumn<TableDataType, String> name;
	@FXML
	private TreeTableColumn<TableDataType, String> email;
	@FXML
	private TreeTableColumn<TableDataType, String> className;
	@FXML
	private ImageView ente;

	private ClientController controller;
	private Stage stage;
	private Parent mainPane;

	public FamilyListHandler() {
		controller = ClientController.getInstance();
		System.out.println("FamilyListHandler");
		stage = ClientViewManager.getStage();
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

		name.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
		email.setCellValueFactory(new TreeItemPropertyValueFactory<>("email"));
		className.setCellValueFactory(new TreeItemPropertyValueFactory<>("className"));

		familyTable.setRoot(dataForTable());
		familyTable.setShowRoot(false);
	}


	public void deleteFamily() {
		if(controller.showDeleteMessage("family")) {
			Family family = ((FamilyDT) familyTable.getSelectionModel().getSelectedItem().getValue()).family;
			controller.deleteFamily(family);
			familyTable.setRoot(dataForTable());
		}
	}


	public void deleteUser() {
		if(controller.showDeleteMessage("user")) {
			User user = ((UserDT) familyTable.getSelectionModel().getSelectedItem().getValue()).user;
			controller.deleteUser(user);
		}
	}

	public void editUser() {
		User user = ((UserDT) familyTable.getSelectionModel().getSelectedItem().getValue()).user;
		if (user.getClass().getSimpleName().equals("Student"))
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/createStudent.fxml"));
				mainPane = loader.load();
				((CreateStudentHandler) loader.getController()).setStudent((Student) user);
				mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
				stage.getScene().setRoot(mainPane);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if (user.getClass().getSimpleName().equals("Parent"))
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/createParent.fxml"));
				mainPane = loader.load();
				((CreateParentHandler) loader.getController()).setParent((model.Parent) user);
				mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
				stage.getScene().setRoot(mainPane);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void addStudent() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/createStudent.fxml"));
			mainPane = loader.load();
			((CreateStudentHandler) loader.getController()).setFamily(((FamilyDT) familyTable.getSelectionModel().getSelectedItem().getValue()).family);
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
			stage.getScene().setRoot(mainPane);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addParent() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/createParent.fxml"));
			mainPane = loader.load();
			((CreateParentHandler) loader.getController()).setFamily(((FamilyDT) familyTable.getSelectionModel().getSelectedItem().getValue()).family);
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

	public void createFamily() {
		controller.createFamily();
		familyTable.setRoot(dataForTable());
		familyTable.refresh();
	}


	private TreeItem<TableDataType> dataForTable() {
		TreeItem<TableDataType> rows = new TreeItem<>();
		controller.getFamilies().forEach(family -> {
			TreeItem<TableDataType> parents = new TreeItem<>(new UserDT("Parents"));
			parents.getChildren().addAll(family.getParents().stream()
					.map(parent -> new TreeItem<TableDataType>(new UserDT(parent, ""))).collect(Collectors.toList()));

			TreeItem<TableDataType> children = new TreeItem<>(new UserDT("Students"));
			children.getChildren().addAll(family.getChildren().stream()
					.map(child -> new TreeItem<TableDataType>(new UserDT(child, child.getClassNo().toString()))).collect(Collectors.toList()));

			TreeItem<TableDataType> familyRoot = new TreeItem<>(new FamilyDT(family));
			familyRoot.getChildren().addAll(parents, children);
			rows.getChildren().add(familyRoot);
		});
		return rows;
	}
}
