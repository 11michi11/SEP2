package model.communication;

import model.User;

public enum LoginStatus {

	SUCCESS("Success"),
	FAILURE_PWD("Wrong password"),
	FAILURE_LOGIN("Wrong email, user not found");
	
	private String msg;
	public User currentUser;

	LoginStatus(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
	
}
