package client.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.controller.ClientController;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.*;
import model.communication.Auth;
import model.communication.ManageUser;
import model.communication.Message;
import model.communication.WelcomingData;

public class ClientModelManager implements ClientModel {

	private ClientProxy server;
	private PostsList posts;
	private UsersList users;
	private FamiliesList families;
	private ClientController controller;
	

	public ClientModelManager() {
		posts = new PostsList();
		users = new UsersList();
		server = new ClientProxy();
		families = new FamiliesList();
		server.startConnection("localhost", 7777);
	}

	public void closeServer() {
		server.close();
	}
	
	public void setController(ClientController controller) {
		this.controller = controller;
	}

	@Override
	public void addPost(String title, String content) {
		posts.add(new Post(title, content));
	}

	@Override
	public ArrayList<Family> getAllFamilies() {
		return families.getAll();
	}

	@Override
	public Post getPost() {
		return posts.getFirstPost();
	}

	@Override
	public void storePost(Post post) {
		posts.add(post);
	}

	@Override
	public void saveData(WelcomingData data) {
		posts.addAll(Arrays.asList(data.getPosts()));
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
	public void deleteUser(User user) {
		server.manageUser(ManageUser.DELETE, users.getUserById(user.getId()));
		users.delete(user.getId());
	}

	@Override
	public void login(String email, String pwd) {
		Auth auth = new Auth(email, pwd);
		Message response = server.login(auth);
		controller.handleMessage(response);
	}

	@Override
	public ArrayList<Parent> getParents() {
		return users.getParents();
	}
	
	public User getUserById(String id) {
		return users.getUserById(id);
	}

	@Override
	public void deleteFamily(Family family) {
		
	}

	@Override
	public List<Teacher> getTeachers() {
		return users.getAllTeachers();
	}

}
