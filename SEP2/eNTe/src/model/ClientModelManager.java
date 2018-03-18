package model;

public class ClientModelManager implements ClientModel{

	private PostsList posts;
	

	@Override
	public Post getPost() {
		return posts.getNextPost();
	}

	@Override
	public void storePost(Post post) {
		posts.add(post);
	}

}
