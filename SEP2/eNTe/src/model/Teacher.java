package model;

import java.io.Serializable;

public class Teacher extends User implements Serializable {

	public Teacher(String name, String email, String pwd) {
		super(name, email, pwd);
	}
	
	public Teacher(String name, String email, String pwd, String id) {
		super(name, email, pwd, id);
	}
}
