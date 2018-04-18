package model;

import java.io.Serializable;
import java.util.UUID;

public abstract class User  implements Serializable{

	private String login;
	private String pwd;
	private String name;
	private String id;

	public User(String name, String login, String pwd) {
		this.name = name;
		this.login = login;
		this.pwd = pwd;
		id = UUID.randomUUID().toString();
	}
	
	public User(String name, String login, String pwd, String id) {
		this.name = name;
		this.login = login;
		this.pwd = pwd;
		this.id = id;
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
