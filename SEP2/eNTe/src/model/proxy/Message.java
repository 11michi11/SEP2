package model.proxy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable{
	
	private Map<String, Object> data;
	
	public Message() {
		data = new HashMap<String, Object>();
	}
	
	public void put(String key, Object value) {
		data.put(key, value);
	}
	
	public Object get(String key) {
		return data.get(key);
	}
	
	public String getType() {
		return (String) data.getOrDefault("type", "NOTYPE");
	}
	
	public Login getLogin() throws NullPointerException, IllegalStateException{
		if(!data.get("type").equals("login"))
			throw new IllegalStateException("Message does not contain login information");
		return (Login) data.get("login");
	}
	
	public Auth getAuth() {
		if(!data.get("type").equals("auth"))
			throw new IllegalStateException("Message does not contain login information");
		Auth auth = (Auth) data.get("auth");
		return auth;
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
		data.put("type", "auth");
		data.put("auth", auth);
	}
	
	

}
