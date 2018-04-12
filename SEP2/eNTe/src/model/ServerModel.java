package model;

import model.proxy.Auth;
import model.proxy.LoginStatus;

public interface ServerModel {

	public Post getPost();
	public LoginStatus authenticate(Auth auth);
	
}
