package model.communication;

import java.io.Serializable;

public class Auth implements Serializable{
	
	public String login;
	public String pwd;

	public Auth(String login, String pwd) {
		this.login = login;
		this.pwd = pwd;
	}
}
