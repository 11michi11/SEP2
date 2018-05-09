package client.view;

import javafx.beans.property.SimpleStringProperty;

public interface TableDataType {

    default SimpleStringProperty getName() {
        return new SimpleStringProperty("");
    }

    default SimpleStringProperty getEmail() {
        return new SimpleStringProperty("");
    }

    default SimpleStringProperty getClassName() {
        return new SimpleStringProperty("");
    }

    default SimpleStringProperty getType() {return new SimpleStringProperty("");}
}
