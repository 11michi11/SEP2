package model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class Parent extends User {
	private ArrayList<Student> children;
	private String childrenNames;
	private ArrayList<Class> classes;

	public Parent(String name, String login, String pwd, ArrayList<Student> children) {
		super(name, login, pwd);
		this.children = children;
		classes = new ArrayList<Class>();
		for (Student s : children)
			classes.add(s.getClasss());

		childrenNames = "";
		for (Student s : children)
			childrenNames += s.getName() + ", ";
	}

	public String getChildrenNames() {
		return childrenNames;
	}

	public void addChild(Student child) {
		children.add(child);
		classes.add(child.getClasss());
	}

	public void removeChild(Student child) {
		children.remove(child);
	}

	public ArrayList<Student> getChildren() {
		return children;
	}
}
