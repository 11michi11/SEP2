package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable{
	private String historyOfActivity;
	private Class classs;
	private ArrayList<String> parentsIDs;

	public Student(String name, String login, String pwd, Class classs, ArrayList<String> parentsIDs) {
		super(name, login, pwd);
		initializeStudent(classs, parentsIDs);
	}
	
	public Student(String name, String login, String pwd, String id, Class classs, ArrayList<String> parentsIDs) {
		super(name, login, pwd, id);
		initializeStudent(classs, parentsIDs);
	}
	
	private void initializeStudent(Class classs, ArrayList<String> parentsIDs) {
		this.classs = classs;
		historyOfActivity = "";
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
	
	public ArrayList<String> getParentsIDs(){
		return parentsIDs;
	}

}




//New version

//package model;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//
//public class Student extends User implements Serializable {
//	private String historyOfActivity;
//	private Class classs;
//	//private Family family;
//
//	// public Student(String name, String login, String pwd, Class classs, Family
//	// family) {
//	public Student(String name, String login, String pwd, Class classs, ArrayList<String> ids) {
//		super(name, login, pwd);
//		// this.family=family;
//	}
//
//	// public Student(String name, String login, String pwd, String id, Class
//	// classs, Family family) {
//	public Student(String name, String login, String pwd, String id, Class classs,ArrayList<String> ids) {
//		super(name, login, pwd, id);
//		//this.family = family;
//		initializeStudent(classs);
//	}
//
//	private void initializeStudent(Class classs) {
//		this.classs = classs;
//		historyOfActivity = "";
//	}
//
//	public void addHistoryOfActivity(String text) {
//		historyOfActivity += text + "\n";
//	}
//
//	public String getHistoryOfActivity() {
//		return historyOfActivity;
//	}
//
//	public void setClasss(Class classs) {
//		this.classs = classs;
//	}
//
//	public Class getClasss() {
//		return classs;
//	}
//
//}
