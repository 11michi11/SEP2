package client.proxy;

import java.util.LinkedList;

import model.Post;

public class WelcomingData {
	
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
	
	
	
}
