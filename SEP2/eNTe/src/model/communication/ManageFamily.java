package model.communication;

import model.Family;

import java.io.Serializable;

public class ManageFamily extends Manage implements Serializable {

    private Family family;

    public ManageFamily(String action, Family family) {
        super(action);
        this.family = family;
    }

    public Family getFamily() {
        return family;
    }
}
