package model;

import java.io.Serializable;

public class Teacher extends User implements Serializable {

	public Teacher(String name, String login, String pwd) {
		super(name, login, pwd);
	}
	
	public Teacher(String name, String login, String pwd, String id) {
		super(name, login, pwd, id);
	}
	
	
}
