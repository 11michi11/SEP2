package client.model;

import java.util.Arrays;

import client.proxy.WelcomingData;
import model.ClientModel;
import model.Post;
import model.PostsList;

public class ClientModelManager implements ClientModel{

	private PostsList posts;
	
	public ClientModelManager() {
		posts = new PostsList();
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

}
