package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import model.communication.Auth;
import model.communication.LoginStatus;

public class UsersList {

	private ArrayList<User> users;

	public UsersList() {
		users = new ArrayList<>();
	}

	public LoginStatus authenticate(Auth auth) {
		try {
			User user = getUserByLogin(auth.email);
			if (user.getPwd().equals(auth.pwd)) {
				LoginStatus status = LoginStatus.SUCCESS;
				status.currentUser = user;
				return status;
			}else
				return LoginStatus.FAILURE_PWD;
		} catch (NoSuchElementException e) {
			return LoginStatus.FAILURE_LOGIN;
		}
	}

	public User getUserByLogin(String login) {
		for(User u : users)
			if(u.getEmail().equals(login))
				return u;
			
		throw new NoSuchElementException();
	}
	
	public User getUserById(String id) {
		users.forEach(user -> System.out.println(user.getId()));

		return users.stream().filter(u -> u.getId().equals(id)).findFirst()
				.orElseThrow(NoSuchElementException::new);
	}

	public void add(User user) {
		users.add(user);
	}

	public void add(List<User> list) {
		users.addAll(list);
	}

	public boolean contains(User user) {
		for (User u : users)
			if (u.getId().equals(user.getId()))
				return true;
		return false;
	}

	public void delete(String id) {
		try {
			users.remove(getUserById(id));			
		}catch(NoSuchElementException e) {
			//if user not found, do nothing
		}
	}

	public void updateUser(User user) {
		for (int i = 0, usersSize = users.size(); i < usersSize; i++) {
			User u = users.get(i);
			if (u.getId().equals(user.getId())) {
				users.set(i, user);
			}
		}
	}

	public ArrayList<Parent> getParents() {
		ArrayList<Parent> parents = new ArrayList<>();
		for(User u : users)
			if(u instanceof Parent)
				parents.add((Parent) u);
		return parents;
	}

	public ArrayList<User> getAll() {
		return users;
	}

	public List<Teacher> getAllTeachers() {
		return users.stream().filter(u -> u instanceof Teacher).map(u -> (Teacher)u).collect(Collectors.toList());
	}

}
