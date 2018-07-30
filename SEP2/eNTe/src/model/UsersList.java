package model;

import java.util.*;
import java.util.stream.Collectors;

import model.communication.Auth;
import model.communication.LoginStatus;

public class UsersList {

	private LinkedList<User> users;

	public UsersList() {
		users = new LinkedList<>();
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
		for (User u : users) {
			if (u.getId().equals(user.getId())) {
				updateUserData(u, user);
			}
		}
	}

	private void updateUserData(User oldUser, User newUser){
		oldUser.updateUserFields(newUser);
		switch(oldUser.getClass().getSimpleName()){
			case "Administrator":
				((Administrator)oldUser).updateAdminFields((Administrator)newUser);
				break;
			case "Teacher":
				((Teacher)oldUser).updateTeacherFields((Teacher)newUser);
				break;
			case "Student":
				((Student)oldUser).updateStudentFields((Student)newUser);
				break;
			case "Parent":
				((Parent)oldUser).updateParentFields((Parent)newUser);
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

	public LinkedList<User> getAll() {
		return users;
	}

	public List<Teacher> getAllTeachers() {
		return users.stream().filter(u -> u instanceof Teacher).map(u -> (Teacher)u).collect(Collectors.toList());
	}

    public boolean checkIfEmailExist(String email) {
    	return users.stream().anyMatch(u -> u.getEmail().equals(email));
	}

	public boolean checkIfIdExist(String id) {
		return users.stream().anyMatch(u -> u.getId().equals(id));
	}

	public void clear() {
		users.clear();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UsersList)) return false;
		UsersList usersList = (UsersList) o;
		return Objects.equals(users, usersList.users);
	}

}
