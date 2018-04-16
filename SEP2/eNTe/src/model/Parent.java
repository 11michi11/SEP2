package model;

import java.util.ArrayList;

public class Parent extends User {
	private ArrayList<Student> children;
	private ArrayList<Class> classes;

	public Parent(String name, String login, String pwd, Student child) {
		super(name, login, pwd);
		children = new ArrayList<>();
		children.add(child);
		classes=new ArrayList<>();
		classes.add(child.getClasss());
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
