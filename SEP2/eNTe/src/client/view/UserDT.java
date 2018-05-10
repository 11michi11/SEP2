package client.view;

import model.User;

public class UserDT extends TableDataType {

    UserDT(User user, String classs) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.className = classs;
        this.type = user.getClass().getName().substring(6, user.getClass().getName().length());
    }

    UserDT(String name) {
        this.name = name;
        this.email = "";
        this.className = "";
        this.type = "";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getClassName() {
        return className;
    }

    public String getType() {
        return type;
    }

}
