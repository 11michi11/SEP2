package client.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ParentListHandler {

	@FXML
	private TableView parentsList;
	@FXML
	private TableColumn nameColumn;
	@FXML
	private TableColumn emailColumn;
	@FXML
	private TableColumn childrenColumn;
	@FXML
	private TableColumn selectedColumn;
	private ClientController controller;
	private Stage stage;
	private ArrayList<String[]> parentsInfo;

	public ParentListHandler() {
		controller = ClientController.getInstance();
		System.out.println("ParentListHandler");
		stage = ClientViewManager.getStage();
	}

	@FXML
	public void initialize() {
		parentsList.setItems(controller.getParentsForView());

	}

	private void insertRow(String[] row) {
	}

	public void createParent() {
		Parent mainPane;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/createParent.fxml"));
			mainPane = loader.load();
			mainPane.getStylesheets().add(getClass().getResource("/client/view/login.css").toExternalForm());
			stage.getScene().setRoot(mainPane);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void confirm() {
		parentsInfo.addAll(getSelectedParents());
		// open create user fxml

		// Get controller
		// UserListHandler handler = loader.getController();
		// handler.save
	}

	private ArrayList<String[]> getSelectedParents() {
		// collect information from selected rows in table
		return null;
	}

	public void passParent(String[] parentInfo) {
		parentsInfo.add(parentInfo);
	}

}
