package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Parent extends User implements Serializable {
	private ArrayList<Student> children;
	private String childrenNames;
	private ArrayList<Class> classes;

	public Parent(String name, String login, String pwd, ArrayList<Student> children) {
		super(name, login, pwd);
		initializeChildren(children);
	}
	
	public Parent(String name, String login, String pwd, ArrayList<Student> children, String id) {
		super(name, login, pwd, id);
		initializeChildren(children);
	}

	private void initializeChildren(ArrayList<Student> children) {
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


//New version

//package model;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//
//public class Parent extends User implements Serializable {
//
//	private ArrayList<Class> classes;
//	// private Family family;
//
//	// public Parent(String name, String login, String pwd, Family family) {
//	public Parent(String name, String login, String pwd, ArrayList<Student> list) {
//		super(name, login, pwd);
//		// this.family=family;
//	}
//
//	// public Parent(String name, String login, String pwd, Family family, String
//	// id) {
//	public Parent(String name, String login, String pwd,  ArrayList<Student> list, String id) {
//		super(name, login, pwd, id);
//		// this.family=family;
//	}
//
//	public String getChildrenNames() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Object getChildren() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
