package client.model;

import java.util.Arrays;

import client.controller.ClientController;
import model.ClientModel;
import model.Post;
import model.PostsList;
import model.User;
import model.UsersList;
import model.communication.Auth;
import model.communication.ClientProxy;
import model.communication.ManageUser;
import model.communication.Message;
import model.communication.WelcomingData;

public class ClientModelManager implements ClientModel {

	private ClientProxy server;
	private PostsList posts;
	private UsersList users;
	private ClientController controller;
	

	public ClientModelManager() {
		posts = new PostsList();
		users = new UsersList();
		server = new ClientProxy();
		server.startConnection("localhost", 7777);
	}

	public void closeServer() {
		server.close();
	}
	
	public void setController(ClientController controller) {
		this.controller = controller;
	}

	@Override
	public Post getPost() {
		return posts.getNextPost();
	}

	@Override
	public void storePost(Post post) {
		posts.add(post);
	}

	@Override
	public void saveData(WelcomingData data) {
		posts.add(Arrays.asList(data.getPosts()));
	}

	@Override
	public void addOrUpdateUser(User user) {
		if (!users.contains(user)) {
			users.add(user);
			server.manageUser(ManageUser.ADD, user);
		}else {
			users.updateUser(user);
		}
	}

	@Override
	public void deleteUser(String id) {		
		server.manageUser(ManageUser.DELETE, users.getUserById(id));
		users.delete(id);
	}

	@Override
	public void login(String login, String pwd) {
		Auth auth = new Auth(login, pwd);
		Message response = server.login(auth);
		controller.handleMessage(response);
	}

}
