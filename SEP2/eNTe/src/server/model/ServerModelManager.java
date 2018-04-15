package server.model;

import java.sql.SQLException;
import java.util.LinkedList;

import model.Administrator;
import model.Post;
import model.PostsList;
import model.ServerModel;
import model.User;
import model.UsersList;
import model.communication.Auth;
import model.communication.LoginStatus;
import server.model.persistance.DBAdapter;
import server.model.persistance.DBPersistance;

public class ServerModelManager implements ServerModel {

	private PostsList posts;
	private UsersList users;
	private DBAdapter db;

	public ServerModelManager() {
		posts = new PostsList();
		users = new UsersList();
		try {
			db = new DBPersistance();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		restoreState();
		System.out.println(users.getAll());
	}

	@Override
	public Post getPost() {
		return posts.getNextPost();
	}

	@Override
	public LoginStatus authenticate(Auth auth) {
		return users.authenticate(auth);
	}

	private void restoreState() {
		//Commented for testing GUI part, when DB sharing problem is resolved - uncomment
//		try {
//			posts.add(db.getPosts());
//			users.add(db.getUsers());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		users.add(getUsers());
		posts.add(getPosts());
	}

	private LinkedList<User> getUsers() {
		User user = new Administrator("dupa", "login", "a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8");

		LinkedList<User> list = new LinkedList<>();
		list.add(user);
		return list;
	}
	
	private LinkedList<Post> getPosts() { 
	    Post post = new Post("Lorem ipsum dolor", 
	        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " 
	            + "Proin mattis at dolor sed aliquam. Nulla facilisi. " 
	            + "Maecenas sodales urna quis risus sollicitudin, " 
	            + "eget posuere neque aliquet. Nulla lacinia maximus " 
	            + "risus non elementum. Donec egestas sit amet lacus vitae" 
	            + " efficitur. Nulla ac mauris in turpis condimentum tincidunt " 
	            + "sed id metus. Cras vel lectus rutrum, interdum tellus non," 
	            + " venenatis eros. Etiam posuere tempus est non maximus." 
	            + " Pellentesque diam tortor, fringilla eget cursus pretium," 
	            + " dictum posuere dolor. Donec non eros commodo," + " ultrices risus sed, fermentum dolor." 
	            + " Cras facilisis neque at scelerisque placerat."); 
	     
	    LinkedList<Post> list = new LinkedList<>(); 
	    list.add(post); 
	    return list; 
	  }

	public void addUser(User user) {
		users.add(user);
		db.addUser(user);
	}

	public void editUser(User user) {
		users.updateUser(user);
		db.updateUser(user);
	}

	public void deleteUser(User user) {
		users.delete(user.getId());
		db.deleteUser(user.getId());
	} 

}
