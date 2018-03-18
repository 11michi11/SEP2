package model;

public class User {

	private String login;
	private String pwd;

	public User(String login, String pwd) {
		super();
		this.login = login;
		this.pwd = pwd;
	}

	public String getLogin() {
		return login;
	}

	public String getPwd() {
		return pwd;
	}

}
