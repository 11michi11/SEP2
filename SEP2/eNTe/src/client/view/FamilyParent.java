package client.view;

import javafx.beans.property.SimpleStringProperty;
import model.Parent;

public class FamilyParent {
	
	private SimpleStringProperty name;
	private SimpleStringProperty email;
	
	public FamilyParent(Parent parent) {
		this.name = new SimpleStringProperty(parent.getName());
		this.email = new SimpleStringProperty(parent.getLogin());		
	}

	public SimpleStringProperty getName() {
		return name;
	}

	public SimpleStringProperty getEmail() {
		return email;
	}
	

}
