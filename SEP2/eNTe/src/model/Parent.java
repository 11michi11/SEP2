package model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class Parent extends User {
	private ArrayList<Student> children;
	private SimpleStringProperty childrenNames;
	private ArrayList<Class> classs;

	public Parent(String name, String login, String pwd, Student child) {
		super(name, login, pwd);
		this.children = children;
		classs = new ArrayList<Class>();
		for (Student s : children)
			classs.add(s.getClasss());
		
		String names = "";
		for(Student s : children)
			names += s.getName() + ", ";
		
		childrenNames = new SimpleStringProperty(names);
	}
	
	public String getChildrenNames() {
		return childrenNames.get();
	}
	public void addChild(Student child)
	{
		children.add(child);
		classes.add(child.getClasss());
	}
	public void removeChild(Student child)
	{
		children.remove(child);
	}
	public ArrayList<Student> getChildren()
	{
		return children;
	}
}
