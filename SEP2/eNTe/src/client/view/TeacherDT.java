package client.view;

import javafx.beans.property.SimpleStringProperty;
import model.Teacher;

public class TeacherDT {
	
	private SimpleStringProperty name;
	private SimpleStringProperty email;
	Teacher teacher;

	public TeacherDT(Teacher teacher) {
		this.name = new SimpleStringProperty(teacher.getName());
		this.email = new SimpleStringProperty(teacher.getEmail());
		this.teacher = teacher;
	}
	
	public TeacherDT(String name, String email) {
		this.name = new SimpleStringProperty(name);
		this.email = new SimpleStringProperty(email);
	}
	
	public String getName() {
		return name.get();
	}
	public String getEmail() {
		return email.get();
	}

}
