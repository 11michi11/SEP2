package client.view;

import model.Family;

public class FamilyDT extends TableDataType {

    FamilyDT(Family family) {
        this.name = family.getMembersNames();
    }

    @Override
    public String getName() {
        return name;
    }


}
