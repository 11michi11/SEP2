package client.view;

import javafx.beans.property.SimpleStringProperty;
import model.Family;

public class FamilyDT {

	private SimpleStringProperty familyName;
	private SimpleStringProperty name;
	private SimpleStringProperty email;
	private SimpleStringProperty className;
	private SimpleStringProperty userType;

	public FamilyDT(Family family) {
		this.familyName = new SimpleStringProperty();
		this.name = new SimpleStringProperty();
		this.email = new SimpleStringProperty();
		this.className = new SimpleStringProperty();
		this.userType = new SimpleStringProperty();

	}

	public SimpleStringProperty getFamilyName() {
		return familyName;
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

	public SimpleStringProperty getUserType() {
		return userType;

	}
}
