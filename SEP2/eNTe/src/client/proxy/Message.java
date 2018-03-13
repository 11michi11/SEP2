package client.proxy;

import java.util.HashMap;
import java.util.Map;

public class Message {
	
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
	
	public Login getLogin() throws NullPointerException, IllegalStateException{
		if(!data.get("type").equals("login"))
			throw new IllegalStateException("Message does not contain login information");
		
		LoginStatus status = (LoginStatus) data.get("loginStatus");
		WelcomingData welcomingData = (WelcomingData) data.get("welcomingData");
		return new Login(status, welcomingData);
	}

}
