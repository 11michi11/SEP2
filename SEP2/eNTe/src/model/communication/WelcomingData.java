package model.communication;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Post;

public class WelcomingData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Post> posts;
	private List<ChatMsg> unreadMessages;
	
	public WelcomingData() {
		posts = new LinkedList<>();
		unreadMessages = new LinkedList<>();
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public List<ChatMsg> getUnreadMessages(){
		return unreadMessages;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		WelcomingData that = (WelcomingData) o;

		if (posts != null ? !posts.equals(that.posts) : that.posts != null) return false;
		return unreadMessages != null ? unreadMessages.equals(that.unreadMessages) : that.unreadMessages == null;
	}


	@Override
	public String toString() {
		return "WelcomingData [posts=" + posts + ", unreadMessages=" + unreadMessages + "]";
	}

	public void insertPosts(List<Post> list) {
		posts.addAll(list);
	}
	
}
