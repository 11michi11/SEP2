package model;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import client.proxy.Auth;
import client.proxy.LoginStatus;

public class UsersList {

	private LinkedList<User> users;
	
	public UsersList() {
		users = new LinkedList<User>();
	}

	public LoginStatus authenticate(Auth auth) {
		try {
			User user = getUserByLogin(auth.login);
			if (user.getPwd().equals(auth.pwd))
				return LoginStatus.SUCCESS;
			else
				return LoginStatus.FAILURE_PWD;
		} catch (NoSuchElementException e) {
			return LoginStatus.FAILURE_LOGIN;
		}
	}

	public User getUserByLogin(String login) {
		return users.stream().filter(u -> u.getLogin().equals(login)).findFirst()
				.orElseThrow(NoSuchElementException::new);
	}

	public void add(User user) {
		users.add(user);
	}
	
	public void add(List<User> list) {
		for(User e :list)
			users.add(e);
	}

}
