package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable{
	private String historyOfActivity;
	private Class classs;
	private Family family;

	public Student(String name, String login, String pwd, Class classs, Family family) {
		super(name, login, pwd);
		this.family=family;
	}
	
	public Student(String name, String login, String pwd, String id, Class classs, Family family) {
		super(name, login, pwd, id);
		this.family=family;
		initializeStudent(classs);
	}
	
	private void initializeStudent(Class classs) {
		this.classs = classs;
		historyOfActivity = "";
	}
		
	public void addHistoryOfActivity(String text) {
		historyOfActivity += text + "\n";
	}

	public String getHistoryOfActivity() {
		return historyOfActivity;
	}

	public void setClasss(Class classs) {
		this.classs = classs;
	}

	public Class getClasss() {
		return classs;
	}


}
