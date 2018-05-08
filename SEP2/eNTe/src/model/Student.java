package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable{
	private String historyOfActivity;
	private Classs classs;
	private ArrayList<String> parentsIDs;

	public Student(String name, String login, String pwd, Classs classs, ArrayList<String> parentsIDs) {
		super(name, login, pwd);
		initializeStudent(classs, parentsIDs);
	}
	
	public Student(String name, String login, String pwd, String id, Classs classs, ArrayList<String> parentsIDs) {
		super(name, login, pwd, id);
		initializeStudent(classs, parentsIDs);
	}
	
	private void initializeStudent(Classs classs, ArrayList<String> parentsIDs) {
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

	public void setClasss(Classs classs) {
		this.classs = classs;
	}

	public Classs getClasss() {
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
//	private Classs classs;
//	//private Family family;
//
//	// public Student(String name, String login, String pwd, Classs classs, Family
//	// family) {
//	public Student(String name, String login, String pwd, Classs classs, ArrayList<String> ids) {
//		super(name, login, pwd);
//		// this.family=family;
//	}
//
//	// public Student(String name, String login, String pwd, String id, Classs
//	// classs, Family family) {
//	public Student(String name, String login, String pwd, String id, Classs classs,ArrayList<String> ids) {
//		super(name, login, pwd, id);
//		//this.family = family;
//		initializeStudent(classs);
//	}
//
//	private void initializeStudent(Classs classs) {
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
//	public void setClasss(Classs classs) {
//		this.classs = classs;
//	}
//
//	public Classs getClasss() {
//		return classs;
//	}
//
//}
