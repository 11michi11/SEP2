package client.view.managingUsers;

import javafx.beans.property.SimpleStringProperty;
import model.Teacher;

public class TeacherDT {
	
	private SimpleStringProperty name;
	private SimpleStringProperty email;
	private SimpleStringProperty responsibility;
	Teacher teacher;

	public TeacherDT(Teacher teacher) {
		this.name = new SimpleStringProperty(teacher.getName());
		this.email = new SimpleStringProperty(teacher.getEmail());
		this.responsibility = new SimpleStringProperty(teacher.getResponsibility());
		this.teacher = teacher;
	}
	
	public String getName() {
		return name.get();
	}
	public String getEmail() {
		return email.get();
	}
	public String getResponsibility() {return responsibility.get();}

}
