package server.controller;

import java.sql.SQLException;
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

	public Message handleMessage(Message msg) throws SQLException {
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

	private Message handleManageUser(ManageUser manageUser) throws SQLException {
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
		WelcomingData data;
		Login login = null;

		LoginStatus status = model.authenticate(msg.getAuth());
		switch (status) {
		case SUCCESS:
			data = new WelcomingData();
			Post post = model.getPost();
			LinkedList<Post> list = new LinkedList<>();
			list.add(post);
			data.insertPosts(list);
			status.currentUser.changePassword();
			login = new Login(LoginStatus.SUCCESS, data, status.currentUser);
			break;
		case FAILURE_LOGIN:
			data = new WelcomingData();
			login = new Login(LoginStatus.FAILURE_LOGIN, data, null);
			break;
		case FAILURE_PWD:
			data = new WelcomingData();
			login = new Login(LoginStatus.FAILURE_PWD, data, null);
			break;
		}
		return Message.createLogin(login);
	}
}
