package model.communication;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import model.communication.Message.Type;

public class Message implements Serializable{
	
	private Map<Type, Object> data;
	
	public enum Type{
		Type, Login, Auth, ManageUser, NoType, Fail
	}
	
	public Message() {
		data = new HashMap<Type, Object>();
	}
	
	public void put(Type key, Object value) {
		data.put(key, value);
	}
	
	public Object get(String key) {
		return data.get(key);
	}
	
	public Type getType() {
		return (Type) data.getOrDefault(Type.Type, Type.NoType);
	}
	
	public Login getLogin() throws NullPointerException, IllegalStateException{
		if(!data.get(Type.Type).equals(Type.Login))
			throw new IllegalStateException("Message does not contain login information");
		return (Login) data.get(Type.Login);
	}
	
	public Auth getAuth() {
		if(!data.get(Type.Type).equals(Type.Auth))
			throw new IllegalStateException("Message does not contain authentication information");
		Auth auth = (Auth) data.get(Type.Auth);
		return auth;
	}
	
	public ManageUser getManageUser() {
		if(!data.get(Type.Type).equals(Type.ManageUser))
			throw new IllegalStateException("Message does not contain managing user information");
		ManageUser manageUser =(ManageUser) data.get(Type.ManageUser);
		return manageUser;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [data=" + data + "]";
	}

	public void createAuth(Auth auth) {
		data.put(Type.Type, Type.Auth);
		data.put(Type.Auth, auth);
	}

	public void createLogin(Login login) {
		data.put(Type.Type, Type.Login);
		data.put(Type.Login, login);
	}
	
	public void createMangeUser(ManageUser manageUser) {
		data.put(Type.Type, Type.ManageUser);
		data.put(Type.ManageUser, manageUser);
	}
	
	

}
