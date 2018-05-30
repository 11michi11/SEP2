package model;

import java.util.ArrayList;
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
			User user = getUserByEmail(auth.email);
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

	public User getUserByEmail(String email) {
		for(User u : users)
			if(u.getEmail().equals(email))
				return u;
			
		throw new NoSuchElementException();
	}
	
	public User getUserById(String id) {
		return users.stream().filter(u -> u.getId().equals(id)).findFirst()
				.orElseThrow(NoSuchElementException::new);
	}

	public void add(User user) {
		users.add(user);
	}

	public void addAll(List<User> list) {
		users.addAll(list);
	}

	public boolean contains(User user) {
//		for (User u : users)
//			if (u.getId().equals(user.getId()))
//				return true;
//		return false;
		return users.contains(user);
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
				updateUserData(u, user);
			}
		}
	}

	private void updateUserData(User oldUser, User newUser){
		oldUser.updateUserFields(newUser);
		switch(oldUser.getClass().getSimpleName()){
			case "Administrator":
				((Administrator)oldUser).updateAdminFileds((Administrator)newUser);
				break;
			case "Teacher":
				((Teacher)oldUser).updateTeacherFileds((Teacher)newUser);
				break;
			case "Student":
				((Administrator)oldUser).updateAdminFileds((Student)newUser);
				break;
			case "Parent":
				((Administrator)oldUser).updateAdminFileds((Administrator)newUser);
				break;
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

    public boolean checkIfEmailExist(String email) {
    	return users.stream().anyMatch(u -> u.getEmail().equals(email));
	}

	public void clear() {
		users.clear();;
	}

	public boolean checkIfIdExist(String id) {
		return users.stream().anyMatch(u -> u.getId().equals(id));
	}
}
