package client.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import model.Family;
import model.Post;
import model.User;
import model.communication.*;

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
			Platform.runLater(this::showErrorNotConnected);
		}
	}

	private void showErrorNotConnected() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Connection error");
		alert.setContentText("Couldn't connect to the server, try again later");
		alert.showAndWait();
		System.exit(0);
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

	public Message login(Auth auth) {
		Message msg = Message.createAuth(auth), response = null;
		try {
			response = sendMessage(msg);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return response;
	}

    boolean checkEmailForPwdReset(String email) {
    	Message msg= Message.createCheckEmail(email), response = null;
		try {
			response = sendMessage(msg);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return response != null && response.getEmailStatus().equals(EmailStatus.EXIST);
	}

	void changePwdWithEmail(String email, String newPwd) {
		Message msg = Message.createChangePwdWithEmail(email, newPwd);
		try {
			sendMessage(msg);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

    void manageFamily(String action, Family family) {
		ManageFamily manageFamily = new ManageFamily(action, family);
		Message msg = Message.createMangeFamily(manageFamily);
		try {
			sendMessage(msg);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
    }

	void manageUser(String action, User user) {
		ManageUser manageUser = new ManageUser(action, user);
		Message msg = Message.createMangeUser(manageUser);
		try {
			sendMessage(msg);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

    public void managePost(String action, Post post) {
		ManagePost managePost = new ManagePost(action, post);
		Message msg = Message.createMangePost(managePost);
		try {
			sendMessage(msg);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

    }

	public WelcomingData requestUpdate() {
		Message msg = Message.createRequestUpdate(), response = null;
		try {
			response = sendMessage(msg);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		assert response != null;
		return response.getUpdate().getData();
	}
}
