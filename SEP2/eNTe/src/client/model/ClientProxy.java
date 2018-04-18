package client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.User;
import model.communication.Auth;
import model.communication.ManageUser;
import model.communication.Message;

public class ClientProxy {

	private Socket client;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public void startConnection(String ip, int port) {
		try {
			System.out.println("Trying to connect..");
			client = new Socket(ip, port);
			System.out.println("Connected!");
			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Message sendMessage(Message msg) throws IOException, ClassNotFoundException {
		out.writeObject(msg);
		out.flush();
		Message response = (Message) in.readObject();
		System.out.println(response);
		return response;
	}

	public void close() {
		try {
			out.close();
			in.close();
			client.close();
		} catch (IOException e) {
			System.out.println("Could not close client connection");
			e.printStackTrace();
		}
	}

	public boolean manageUser(String action, User id) {
		ManageUser manageUser = new ManageUser(action, id);
		Message msg = Message.createMangeUser(manageUser);
		try {
			Message response = sendMessage(msg);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Message login(Auth auth) {
		Message msg = Message.createAuth(auth), response = null;
		try {
			response = sendMessage(msg);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}

}
