package server.controller;

import java.util.LinkedList;

import model.Post;
import model.communication.Login;
import model.communication.LoginStatus;
import model.communication.ManageUser;
import model.communication.Message;
import model.communication.WelcomingData;
import server.model.ServerModelManager;

public class ServerController {

	private ServerModelManager model;
	private ServerProxy server;

	public ServerController(ServerModelManager model) {
		this.model = model;
		server = new ServerProxy(this);
		server.start();
	}

	public void closeServer() {
		server.close();
	}

	public Message handleMessage(Message msg) {
		Message response;

		switch (msg.getType()) {
		case Auth:
			response = handleAuthentication(msg);
			break;
		case ManageUser:
			ManageUser manageUser = msg.getManageUser();
			response = handleManageUser(manageUser);
			break;
		default:
			response = Message.createFail();
			break;
		}
		return response;

	}

	private Message handleManageUser(ManageUser manageUser) {
		Message response = null;
		switch (manageUser.getAction()) {
		case ManageUser.ADD:
			model.addUser(manageUser.getUser());
			response = Message.createSuccessfulResponse();
			break;
		case ManageUser.EDIT:
			model.editUser(manageUser.getUser());
			response = Message.createSuccessfulResponse();
			break;
		case ManageUser.DELETE:
			model.deleteUser(manageUser.getUser());
			response = Message.createSuccessfulResponse();
			break;
		default:
			response = Message.createFail();
			break;
		}

		return response;
	}

	private Message handleAuthentication(Message msg) {
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
			break;
		case FAILURE_LOGIN:
			data = new WelcomingData();
			login = new Login(LoginStatus.FAILURE_LOGIN, data);
			break;
		case FAILURE_PWD:
			data = new WelcomingData();
			login = new Login(LoginStatus.FAILURE_PWD, data);
			break;
		}
		return Message.createLogin(login);
	}
}
