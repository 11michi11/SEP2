package model;

import model.communication.Auth;
import model.communication.LoginStatus;

import java.util.List;

public interface ServerModel {

	public Post getPost();
	public LoginStatus authenticate(Auth auth);
	public void addUser(User user);
	public void editUser(User user);
	public void deleteUser(User user);
	List<Family> getAllFamilies();
	void deleteFamily(Family family);
	void addFamily(Family family);
	
}
