package client.view.managingUsers;

import model.Family;

public class FamilyDT extends TableDataType {

	Family family;
	
    FamilyDT(Family family) {
        this.name = family.getMembersNames();
        this.family = family;
    }

    @Override
    public String getName() {
        return name;
    }

}
