package model;

import java.util.ArrayList;

public class Parent extends User{
	private ArrayList<Student> children;
	private ArrayList<Class> classs;
	
	public Parent(String name, String login, String pwd, ArrayList<Student> children)
	{
		super(name, login, pwd);
		this.children=children;
		for(int i=0;i<children.size();i++)
			classs.add(children.get(i).getClasss());
	}
}
