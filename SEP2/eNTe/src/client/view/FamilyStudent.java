package client.view;

import javafx.beans.property.SimpleStringProperty;
import model.Student;

public class FamilyStudent {
	
	private SimpleStringProperty name;
	private SimpleStringProperty email;
	private SimpleStringProperty className;
	
	public FamilyStudent(Student student) {
		this.name = new SimpleStringProperty(student.getName());
		this.email = new SimpleStringProperty(student.getLogin());
		this.className = new SimpleStringProperty(student.getClasss().toString());
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
	

}
