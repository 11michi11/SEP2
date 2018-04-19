package client.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import client.controller.ClientController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import client.view.ParentDT;

public class ParentListHandler {

	@FXML
	private TableView<ParentDT> parentsList;
	@FXML
	private TableColumn<ParentDT, String> nameColumn;
	@FXML
	private TableColumn<ParentDT, String> emailColumn;
	@FXML
	private TableColumn<ParentDT, String> childrenColumn;
	@FXML
	private TableColumn<ParentDT, CheckBox> selectedColumn;
	private ClientController controller;
	private Stage stage;
	private ArrayList<String[]> parentsInfo;

	public ParentListHandler() {
		controller = ClientController.getInstance();
		System.out.println("ParentListHandler");
		stage = ClientViewManager.getStage();
		parentsInfo = new ArrayList<>();
	}

	@FXML
	public void initialize() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<ParentDT, String>("name"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<ParentDT, String>("login"));
		childrenColumn.setCellValueFactory(new PropertyValueFactory<ParentDT, String>("childrenNames"));

		selectedColumn
				.setCellValueFactory(new Callback<CellDataFeatures<ParentDT, CheckBox>, ObservableValue<CheckBox>>() {

					@Override
					public ObservableValue<CheckBox> call(CellDataFeatures<ParentDT, CheckBox> arg0) {
						CheckBox checkBox = new CheckBox();
						ParentDT parent = arg0.getValue();

						checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
							public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue,
									Boolean newValue) {
								parent.setSelected(newValue);
							}
						});

						checkBox.selectedProperty().setValue(parent.getSelected());

						return new SimpleObjectProperty<CheckBox>(checkBox);
					}

				});
		parentsList.getColumns().clear();
		parentsList.getColumns().addAll(nameColumn, emailColumn, childrenColumn, selectedColumn);
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
		System.out.println(Arrays.deepToString(parentsInfo.toArray()));
		// open create user fxml

		// Get controller
		// UserListHandler handler = loader.getController();
		// handler.save
	}

	private ArrayList<String[]> getSelectedParents() {
		ArrayList<String[]> parentsInfo = new ArrayList<>();

		for (ParentDT p : parentsList.getItems()) {
			if (p.getSelected()) {
				String[] parent = new String[3];
				parent[0] = p.getName();
				parent[1] = p.getLogin();
				parent[2] = p.getChildrenNames();

				parentsInfo.add(parent);
			}
		}

		return parentsInfo;
	}

	public void passParent(String[] parentInfo) {
		ParentDT parent = new ParentDT(parentInfo[0], parentInfo[1]);
		parent.setSelected(true);
		parentsList.getItems().add(0,parent);
	}

}
