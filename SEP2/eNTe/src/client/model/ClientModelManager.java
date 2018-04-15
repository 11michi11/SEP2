package client.model;

import java.util.Arrays;

import model.ClientModel;
import model.Post;
import model.PostsList;
import model.User;
import model.UsersList;
import model.communication.WelcomingData;

public class ClientModelManager implements ClientModel{

	private PostsList posts;
	private UsersList users;
	
	public ClientModelManager() {
		posts = new PostsList();
		users = new UsersList();
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
	public void addUser(User user) {
		users.add(user);
	}


	@Override
	public void deleteUser(User user) {
		users.delete(user);
	}

}
