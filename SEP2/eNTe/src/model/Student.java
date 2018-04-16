package model;

import java.util.ArrayList;

public class Student extends User {
	private String historyOfActivity;
	private Class classs;

	public Student(String name, String login, String pwd, Class classs) {
		super(name, login, pwd);
		this.classs = classs;
		historyOfActivity = null;
	}
	
	public void addHistoryOfActivity(String text)
	{
		historyOfActivity+=text+"\n";
	}
	public String getHistoryOfActivity()
	{
		return historyOfActivity;
	}
	public void setClasss(Class classs)
	{
		this.classs=classs;
	}
	public Class getClasss() {
		return classs;
	}
}
