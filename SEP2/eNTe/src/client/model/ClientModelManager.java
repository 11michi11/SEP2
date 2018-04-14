package client.model;

import java.util.Arrays;

import model.ClientModel;
import model.Post;
import model.PostsList;
import model.communication.WelcomingData;

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
