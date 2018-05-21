package model.communication;

import model.Family;

import java.io.Serializable;

public class ManageFamily implements Serializable {

    private String action;
    public static final String ADD = "ADD";
    public static final String DELETE = "DELETE";
    public static final String EDIT = "EDIT";
    private Family family;

    public ManageFamily(String action, Family family) {
        this.family = family;
        this.action = action;
    }

    public String getAction() {
        return action;
    }


    public Family getFamily() {
        return family;
    }
}
