package model;

import java.util.ArrayList;

public class Student extends User {
	private String historyOfActivity;
	private Class classs;
	private ArrayList<String> parentsIDs;

	public Student(String name, String login, String pwd, Class classs, ArrayList<String> parentsIDs) {
		super(name, login, pwd);
		this.classs = classs;
		historyOfActivity = null;
		this.parentsIDs = parentsIDs;
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

	public void addParentId(String id) {
		parentsIDs.add(id);
	}

}
