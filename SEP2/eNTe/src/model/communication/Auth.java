package model.communication;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Auth implements Serializable{
	
	public String email;
	public String pwd;

	public Auth(String email, String pwd) {
		this.email = email;
		this.pwd = encryptPwd(pwd);
	}
	
	private String encryptPwd(String pwd) {
		MessageDigest dig;
		String encrypted = "";
		try {
			dig = MessageDigest.getInstance("SHA-256");
			dig.update(pwd.getBytes());
			encrypted = toHex(dig.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encrypted;
	}

	private String toHex(byte[] byteData) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
