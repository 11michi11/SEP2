package model.communication;

import java.io.Serializable;

public abstract class Manage implements Serializable {

    private String action;
    public static final String ADD = "ADD";
    public static final String DELETE = "DELETE";
    public static final String EDIT = "EDIT";

    protected Manage(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
