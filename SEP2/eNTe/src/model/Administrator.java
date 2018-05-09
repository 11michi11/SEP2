package model;

import java.io.Serializable;

public class Administrator extends User implements Serializable {

	public Administrator(String name, String email, String pwd) {
		super(name, email, pwd);
	}
	
	public Administrator(String name, String email, String pwd, String id) {
		super(name, email, pwd, id);
	}

	@Override
	public String toString() {
		return super.toString();
	}


	
	
	
}
