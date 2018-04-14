package model;

import model.communication.Auth;
import model.communication.LoginStatus;

public interface ServerModel {

	public Post getPost();
	public LoginStatus authenticate(Auth auth);
	
}
