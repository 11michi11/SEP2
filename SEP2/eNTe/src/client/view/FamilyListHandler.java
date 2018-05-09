package client.view;

import client.controller.ClientController;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FamilyListHandler {

	@FXML
	private TreeTableView<FamilyDT> familyTable;
	@FXML
	private TreeTableColumn<FamilyDT, String> familyName;
	@FXML
	private TreeTableColumn<FamilyDT, String> name;
	@FXML
	private TreeTableColumn<FamilyDT, String> email;
	@FXML
	private TreeTableColumn<FamilyDT, String> className;
	@FXML
	private TreeTableColumn<FamilyDT, String> userType;
	private ClientController controller;
	private Stage stage;

	public FamilyListHandler() {
		controller = ClientController.getInstance();
		System.out.println("FamilyListHandler");
		stage = ClientViewManager.getStage();
	}

	@FXML 
	public void initialize() {
		
		familyName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<FamilyDT,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<FamilyDT, String> param) {
				return param.getValue().getValue().getFamilyName();
			}
		});
		
//		name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<FamilyDT,String>, ObservableValue<String>>() {
//			@Override
//			public ObservableValue<String> call(CellDataFeatures<FamilyDT, String> param) {
//				return param.getValue().getValue().getName();
//			}
//		});
//		email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<FamilyDT,String>, ObservableValue<String>>() {
//			@Override
//			public ObservableValue<String> call(CellDataFeatures<FamilyDT, String> param) {
//				return param.getValue().getValue().getEmail();
//			}
//		});
//		className.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<FamilyDT,String>, ObservableValue<String>>() {
//			@Override
//			public ObservableValue<String> call(CellDataFeatures<FamilyDT, String> param) {
//				return param.getValue().getValue().getClassName();
//			}
//		});
//		userType.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<FamilyDT,String>, ObservableValue<String>>() {
//			@Override
//			public ObservableValue<String> call(CellDataFeatures<FamilyDT, String> param) {
//				return param.getValue().getValue().getUserType();
//			}
//		});
		
		//familyTable.getColumns().addAll(familyName, name, email, className, userType);

	}
}
