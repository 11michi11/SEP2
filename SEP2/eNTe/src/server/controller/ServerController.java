package server.controller;

import java.util.LinkedList;

import client.proxy.Login;
import client.proxy.LoginStatus;
import client.proxy.Message;
import client.proxy.WelcomingData;
import model.Post;
import server.model.ServerModelManager;

public class ServerController {

	private ServerModelManager model;
	private ServerProxy server;

	public ServerController() {
		model = new ServerModelManager();
		server = new ServerProxy(this);
		server.start();
	}
	
	public void closeServer() {
		server.close();
	}

	public Message handleMessage(Message msg) {
		Message response;

		switch (msg.getType()) {
		case "auth":
			response = handleAuthentication(msg);
			break;
		default:
			response = new Message();
			response.put("type", "FAIL");
			break;
		}
		return response;

	}

	private Message handleAuthentication(Message msg) {
		Message response = new Message();
		WelcomingData data = null;
		Login login = null;
		switch (model.authenticate(msg.getAuth())) {
		case SUCCESS:
			data = new WelcomingData();
			Post post = model.getPost();
			LinkedList<Post> list = new LinkedList<Post>();
			list.add(post);
			data.insertPosts(list);
			login = new Login(LoginStatus.SUCCESS, data);
			response.put("type", "login");
			response.put("login", login);
			break;
		case FAILURE_LOGIN:
			data = new WelcomingData();
			new Login(LoginStatus.FAILURE_LOGIN, data);
			response.put("type", "login");
			response.put("login", login);
			break;
		case FAILURE_PWD:
			data = new WelcomingData();
			new Login(LoginStatus.FAILURE_PWD, data);
			response.put("type", "login");
			response.put("login", login);
			break;
		}
		return response;
	}
}
