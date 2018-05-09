package client.view;

import javafx.beans.property.SimpleStringProperty;
import model.Classs;
import model.Student;
import model.User;

public class UserDT implements TableDataType {

    private SimpleStringProperty name;
    private SimpleStringProperty email;
    private SimpleStringProperty className;
    private SimpleStringProperty type;

    public UserDT(User user, String classs) {
        this.name = new SimpleStringProperty(user.getName());
        this.email = new SimpleStringProperty(user.getLogin());
        this.className = new SimpleStringProperty(classs);
        this.type = new SimpleStringProperty(user.getClass().getName().substring(5, user.getClass().getName().length() - 5));
    }

    public UserDT(String name) {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty("");
        this.className = new SimpleStringProperty("");
        this.type = new SimpleStringProperty("");
    }

    public SimpleStringProperty getName() {
        return name;
    }

    public SimpleStringProperty getEmail() {
        return email;
    }

    public SimpleStringProperty getClassName() {
        return className;
    }

    public SimpleStringProperty getType() {
        return type;
    }

}
