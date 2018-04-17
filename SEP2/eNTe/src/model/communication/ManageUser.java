package model.communication;

import model.User;

public class ManageUser {
	
	private String action;
	private User user;
	public static final String ADD = "ADD";
	public static final String DELETE = "DELETE";
	public static final String EDIT = "EDIT";
	
	public ManageUser(String action, User user) {
		this.user = user;
		this.action = action;
	}
	
	public String getAction() {
		return action;
	}
	
	public User getUser() {
		return user;
	}
	

}
