package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Parent extends User implements Serializable {

	private ArrayList<Class> classes;
	private Family family;

	public Parent(String name, String login, String pwd, Family family) {
		super(name, login, pwd);
		this.family=family;
	}
	
	public Parent(String name, String login, String pwd, Family family, String id) {
		super(name, login, pwd, id);
		this.family=family;
	}

}
