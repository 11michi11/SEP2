package client.controller;

import java.util.ArrayList;

import client.view.ClientView;
import model.Administrator;
import model.Class;
import model.ClientModel;
import model.Parent;
import model.Post;
import model.Student;
import model.Teacher;
import model.User;
import model.communication.Login;
import model.communication.Message;
import model.communication.WelcomingData;

public class ClientController {

	private ClientModel model;
	private ClientView view;
	private static ClientController instance;

	private ClientController(ClientModel model, ClientView view) {
		instance = this;
		this.model = model;
		this.view = view;
		this.view.startView();
	}

	public static ClientController getInstance(ClientModel model, ClientView view) {
		if (instance == null)
			instance = new ClientController(model, view);
		return instance;
	}

	public static ClientController getInstance() {
		if (instance == null)
			throw new IllegalStateException("There is no instance");
		return instance;
	}

	public Post[] getPosts() {
		Post[] posts = new Post[1];
		posts[0] = model.getPost();
		return posts;
	}

	public void login(String login, String pwd) {
		model.login(login, pwd);
	}

	public void handleMessage(Message msg) {
		switch (msg.getType()) {
		case Login:
			handleLogin(msg);
			break;
		case Fail:
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
			view.showPosts(login.getUserType());
			break;
		case FAILURE_LOGIN:
			break;
		case FAILURE_PWD:
			break;
		}
	}

	public void addTeacher(String name, String email, String password, Boolean admin) {
		User user;
		if (admin)
			user = new Administrator(name, email, password);
		else
			user = new Teacher(name, email, password);

		model.addOrUpdateUser(user);
	}

	public void addStudent(String name, String email, String password, Class classs,
			ArrayList<Parent> parents) {
		Student student = new Student(name, email, password, classs, parents);
		model.addOrUpdateUser(student);
		for (Parent p : parents)
			model.addOrUpdateUser(p);
	}

	public void addParent(String name, String email, String password, ArrayList<Student> children) {
		Parent parent = new Parent(name, email, password, children);
		model.addOrUpdateUser(parent);
	}
	
	public void deleteUser(String id) {
		model.deleteUser(id);
	}

	public void addParent(String name, String email, String password) {
		// TODO Auto-generated method stub
		
	}

	public String[][] getParentsForView() {
		String[][] parents = {{"Michal Pompa", "pompa@polska.pl", "Luke Pompa", "false"}};
		return parents;
	}

}
