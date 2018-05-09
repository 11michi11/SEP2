package model;

import java.io.Serializable;
import java.util.UUID;

public abstract class User implements Serializable{

	private String login;
	private String pwd;
	private String name;
	private boolean changePassword;
	protected String id;

	public User(String name, String login, String pwd) {
		this.name = name;
		this.login = login;
		this.pwd = pwd;
		id = UUID.randomUUID().toString();
		changePassword = false;
	}
	
	public User(String name, String login, String pwd, String id) {
		this.name = name;
		this.login = login;
		this.pwd = pwd;
		this.id = id;
		changePassword = false;
	}
	
	public String getName() {
		return name;
	}

	public String getLogin() {
		return login;
	}

	public String getPwd() {
		return pwd;
	}

	public String getId() {
		return id;
	}

	public void changePassword(){
		changePassword = true;
	}

	public void setChangePassword(boolean changePassword){
		this.changePassword = changePassword;
	}

	public boolean isPasswordChangeNeeded(){
		return changePassword;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User) {
			User other = (User)obj;
			return id.equals(other.id) && login.equals(other.login) && name.equals(other.name) && pwd.equals(other.pwd);
		}
		return false;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", pwd=" + pwd + ", name=" + name + ", id=" + id + "]";
	}
}
