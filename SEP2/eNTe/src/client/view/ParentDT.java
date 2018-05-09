package client.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Parent;

public class ParentDT {
	

	private SimpleStringProperty email;
	private SimpleStringProperty childrenNames;
	private SimpleStringProperty name;
	private SimpleBooleanProperty selected;
	
	public ParentDT(Parent parent) {
		this.name = new SimpleStringProperty(parent.getName());
		this.email = new SimpleStringProperty(parent.getEmail());
		this.childrenNames = new SimpleStringProperty(parent.getChildrenNames());
		this.selected = new SimpleBooleanProperty(false);
	}
	
	public ParentDT(String name, String email) {
		this.name = new SimpleStringProperty(name);
		this.email = new SimpleStringProperty(email);
		this.childrenNames = new SimpleStringProperty("");
		this.selected = new SimpleBooleanProperty(false);
	}
	
	
	public String getName() {
		return name.get();
	}

	public String getEmail() {
		return email.get();
	}

	public String getChildrenNames() {
		return childrenNames.get();
	}
	
	public Boolean getSelected() {
		return selected.getValue();
	}


	public void setSelected(Boolean newValue) {
		selected = new SimpleBooleanProperty(newValue);
	}


}
