package model;

import utility.SendEmail;

import java.io.Serializable;
import java.util.UUID;

public abstract class User implements Serializable{

	private String email;
	private String pwd;
	private String name;
	private boolean changePassword;
	protected String id;

	public User(String name, String email, String pwd) {
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		id = UUID.randomUUID().toString();
		changePassword = false;
	}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
		this.pwd = RandomPassword.generatePassword(8);
		id = UUID.randomUUID().toString();
		changePassword = true;
	}
	
	public User(String name, String email, String pwd, String id) {
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.id = id;
		changePassword = false;
	}
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPwd() {
		return pwd;
	}

	public String getId() {
		return id;
	}

	public void changePassword(){
		changePassword = true;
		SendEmail.sendPasswordEmail(email, pwd);
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
			return id.equals(other.id) && email.equals(other.email) && name.equals(other.name) && pwd.equals(other.pwd);
		}
		return false;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", pwd=" + pwd + ", name=" + name + ", id=" + id + "]";
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
		changePassword = false;
	}
}
