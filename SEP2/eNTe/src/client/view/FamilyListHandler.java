package client.view;

import client.controller.ClientController;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;

import java.util.stream.Collectors;

public class FamilyListHandler {

    @FXML
    private TreeTableView<TableDataType> familyTable;
    @FXML
    private TreeTableColumn<TableDataType, String> familyName;
    @FXML
    private TreeTableColumn<TableDataType, String> name;
    @FXML
    private TreeTableColumn<TableDataType, String> email;
    @FXML
    private TreeTableColumn<TableDataType, String> className;
    @FXML
    private TreeTableColumn<TableDataType, String> userType;
    private ClientController controller;
    private Stage stage;

    public FamilyListHandler() {
        controller = ClientController.getInstance();
        System.out.println("FamilyListHandler");
        stage = ClientViewManager.getStage();
    }

    @FXML
    public void initialize() {

        TreeItem<TableDataType> rows = new TreeItem<>();
        controller.getFamilies().forEach(family -> {
            TreeItem<TableDataType> parents = new TreeItem<>(new UserDT("Parents"));
            parents.getChildren().addAll(family.getParents().stream()
                    .map(parent -> new TreeItem<TableDataType>(new UserDT(parent, ""))).collect(Collectors.toList()));

            TreeItem<TableDataType> children = new TreeItem<>(new UserDT("Students"));
            children.getChildren().addAll(family.getChildren().stream()
                    .map(child -> new TreeItem<TableDataType>(new UserDT(child, child.getClasss().toString()))).collect(Collectors.toList()));

            TreeItem<TableDataType> familyRoot = new TreeItem<>(new FamilyDT(family));
            familyRoot.getChildren().addAll(parents, children);
            rows.getChildren().add(familyRoot);
        });

        familyName.setCellValueFactory(param -> param.getValue().getValue().getName());
        name.setCellValueFactory(param -> param.getValue().getValue().getName());
        email.setCellValueFactory(param -> param.getValue().getValue().getEmail());
        className.setCellValueFactory(param -> param.getValue().getValue().getClassName());
        userType.setCellValueFactory(param -> param.getValue().getValue().getType());

        familyTable.getColumns().addAll(familyName, name, email, className, userType);
        familyTable.setRoot(rows);

    }
}
