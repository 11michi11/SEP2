package model;

import java.io.Serializable;

public class Administrator extends User implements Serializable {

	public Administrator(String name, String login, String pwd) {
		super(name, login, pwd);
	}
	
	public Administrator(String name, String login, String pwd, String id) {
		super(name, login, pwd, id);
	}

	@Override
	public String toString() {
		return super.toString();
	}


	
	
	
}
