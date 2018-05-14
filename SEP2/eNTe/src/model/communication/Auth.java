package model.communication;

import model.Password;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Auth implements Serializable{
	
	public String email;
	public String pwd;

	public Auth(String email, String pwd) {
		this.email = email;
		this.pwd = Password.encryptPwd(pwd);
	}
}
