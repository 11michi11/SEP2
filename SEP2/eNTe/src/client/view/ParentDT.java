package client.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Parent;

public class ParentDT {
	

	private SimpleStringProperty login;
	private SimpleStringProperty childrenNames;
	private SimpleStringProperty name;
	private SimpleBooleanProperty selected;
	
	public ParentDT(Parent parent) {
		this.name = new SimpleStringProperty(parent.getName());
		this.login = new SimpleStringProperty(parent.getLogin());
		this.childrenNames = new SimpleStringProperty(parent.getChildrenNames());
		this.selected = new SimpleBooleanProperty(false);
	}
	
	public ParentDT(String name, String login) {
		this.name = new SimpleStringProperty(name);
		this.login = new SimpleStringProperty(login);
		this.childrenNames = new SimpleStringProperty("");
		this.selected = new SimpleBooleanProperty(false);
	}
	
	
	public String getName() {
		return name.get();
	}

	public String getLogin() {
		return login.get();
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
