package model.communication;

import utility.Password;

import java.io.Serializable;

public class Auth implements Serializable{
	
	public String email;
	public String pwd;

	public Auth(String email, String pwd) {
		this.email = email;
		this.pwd = Password.encryptPwd(pwd);
	}
}
