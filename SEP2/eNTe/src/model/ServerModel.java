package model;

import client.proxy.Auth;
import client.proxy.LoginStatus;

public interface ServerModel {

	public Post getPost();
	public LoginStatus authenticate(Auth auth);
	
}
