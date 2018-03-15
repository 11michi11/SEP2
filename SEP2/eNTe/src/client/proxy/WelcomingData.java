package client.proxy;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;

import model.Post;

public class WelcomingData implements Serializable {
	
	private Post[] posts;
	private LinkedList<ChatMsg> unreadMessages;
	
	public WelcomingData() {
		posts = new Post[10];
		unreadMessages = new LinkedList<ChatMsg>();
	}
	
	public Post[] getPosts() {
		return posts;
	}

	public LinkedList<ChatMsg> getUnreadMessages(){
		return unreadMessages;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WelcomingData other = (WelcomingData) obj;
		if (!Arrays.equals(posts, other.posts))
			return false;
		if (unreadMessages == null) {
			if (other.unreadMessages != null)
				return false;
		} else if (!unreadMessages.equals(other.unreadMessages))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WelcomingData [posts=" + Arrays.toString(posts) + ", unreadMessages=" + unreadMessages + "]";
	}
	
}
