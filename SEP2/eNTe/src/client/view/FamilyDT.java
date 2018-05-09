package client.view;

import javafx.beans.property.SimpleStringProperty;
import model.Family;

public class FamilyDT implements TableDataType {

    private SimpleStringProperty familyName;

    public FamilyDT(Family family) {
        this.familyName = new SimpleStringProperty(family.getMembersNames());
    }

    @Override
    public SimpleStringProperty getName() {
        return familyName;
    }


}
