package client.view;

import javafx.beans.property.SimpleStringProperty;
import model.Family;

public class FamilyDT {

	private SimpleStringProperty familyName;

	
	public FamilyDT(Family family) {
		this.familyName = new SimpleStringProperty();

	}

	public SimpleStringProperty getFamilyName() {
		return familyName;
	}



}
