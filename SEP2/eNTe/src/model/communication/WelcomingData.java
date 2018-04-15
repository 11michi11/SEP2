package model.communication;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;

import model.Post;

public class WelcomingData implements Serializable {
	
	private static final long serialVersionUID = 1L;
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

	public void insertPosts(LinkedList<Post> list) {
		int size = list.size();
		if(list.size() > 10) //load only 10 first posts
			for(int i = 0;i<=10;i++)
				posts[i] = list.get(i);
		else 
			for(int i = 0;i<size;i++)
				posts[i] = list.get(i);
		
	}
	
}
