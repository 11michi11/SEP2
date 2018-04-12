package client.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import client.model.ClientModelManager;
import client.proxy.Auth;
import client.proxy.ClientProxy;
import client.proxy.Login;
import client.proxy.Message;
import client.proxy.WelcomingData;
import client.view.ClientView;
import client.view.ClientViewManager;
import model.ClientModel;
import model.Post;

public class ClientController {

	private ClientProxy server;
	private ClientModel model;
	private ClientView view;
	private static ClientController instance;

	private ClientController(ClientModel model, ClientView view) {
		instance = this;
		server = new ClientProxy();
		server.startConnection("localhost", 7777);
		this.model = model;
		this.view = view;
		this.view.startView();
	}
	
	public static ClientController getInstance(ClientModel model, ClientView view) {
		if(instance == null)
			instance = new ClientController(model, view);
		return instance	;
	}
	
	public static ClientController getInstance() {
		if(instance == null)
			throw new IllegalStateException("There is no instance");
		return instance	;
	}

	public void close() {
		server.close();
	}
	
	public Post[] getPosts() {
		Post[] posts = new Post[1];
		posts[0] =  model.getPost();
		return posts;
	}
	

	public void login(String login, String pwd) {
		Auth auth = new Auth(login, encryptPwd(pwd));
		Message msg = new Message(), response;

		msg.createAuth(auth);

		try {
			response = server.sendMessage(msg);
			handleMessage(response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handleMessage(Message msg) {
		switch (msg.getType()) {
		case "login":
			handleLogin(msg);
			break;
		case "fail":
		default:
			System.out.println("Error!!!");
			break;
		}
	}

	private void handleLogin(Message msg) {
		Login login = msg.getLogin();
		switch (login.getLoginStatus()) {
		case SUCCESS:
			WelcomingData data = login.getData();
			model.saveData(data);
			view.showPosts();
			break;
		case FAILURE_LOGIN:
			break;
		case FAILURE_PWD:
			break;
		}
	}

	private String encryptPwd(String pwd) {
		MessageDigest dig;
		String encrypted = "";
		try {
			dig = MessageDigest.getInstance("SHA-256");
			dig.update("pwd".getBytes());
			encrypted = toHex(dig.digest());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encrypted;
	}

	private String toHex(byte[] byteData) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
