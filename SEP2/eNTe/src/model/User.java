package model;

import java.util.UUID;

import javafx.beans.property.SimpleStringProperty;

public class User {

	private SimpleStringProperty login;
	private SimpleStringProperty pwd;
	private SimpleStringProperty name;
	private String id;

	public User(String name, String login, String pwd) {
		this.name = new SimpleStringProperty(name);
		this.login = new SimpleStringProperty(login);
		this.pwd = new SimpleStringProperty(pwd);
		id = UUID.randomUUID().toString();
	}
	
	public String getName() {
		return name.get();
	}

	public String getLogin() {
		return login.get();
	}

	public String getPwd() {
		return pwd.get();
	}

	public String getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", pwd=" + pwd + ", name=" + name + ", id=" + id + "]";
	}

}
