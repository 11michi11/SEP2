package model.communication;

import java.io.Serializable;

import model.User;

public class ManageUser extends Manage implements Serializable{

	private User user;
	
	public ManageUser(String action, User user) {
		super(action);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	

}
