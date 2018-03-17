package server.model;

import client.proxy.Auth;
import client.proxy.LoginStatus;
import model.Post;
import model.PostsList;
import model.ServerModel;
import model.UsersList;
import server.model.persistance.DBAdapter;
import server.model.persistance.DBPersistance;

public class ServerModelManager implements ServerModel {

	private PostsList posts;
	private UsersList users;
	private DBAdapter db;

	public ServerModelManager() {
		posts = new PostsList();
		users = new UsersList();
		db = new DBPersistance();
		restoreState();
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
		posts.add(db.getPosts());
		users.add(db.getUsers());
	}

}
