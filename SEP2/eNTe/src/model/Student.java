package model;

import java.util.ArrayList;

public class Student extends User {
	private String historyOfActivity;
	private Class classs;
	private ArrayList<Parent> parents;

	public Student(String name, String login, String pwd, Class classs, ArrayList<Parent> parents) {
		super(name, login, pwd);
		this.classs = classs;
		this.parents = parents;
		historyOfActivity = null;
	}

	public Class getClasss() {
		return classs;
	}
}
