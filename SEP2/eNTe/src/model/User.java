package model;

public abstract class User {

	private String login;
	private String pwd;
	private String name;

	public User(String name, String login, String pwd) {
		this.name=name;
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
