package client.view;

import java.util.Arrays;
import java.util.Scanner;

import client.controller.ClientController;
import model.Post;

public class ClientViewManager implements ClientView {
	
	private ClientController controller;
	
	public ClientViewManager(ClientController controller) {
		this.controller = controller;
		Thread t = new Thread(() -> start());
		t.start();
	}
	
	public void start() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter smth to test");
		String smth = in.nextLine();
		controller.login("login", "pwd");
	}

	@Override
	public void showPosts(Post[] posts) {
		System.out.println(Arrays.toString(posts));
	}

}
