package client.proxy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.google.gson.Gson;

public class ClientProxy {

	private Socket client;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Gson gson;

	public ClientProxy() {
		gson = new Gson();
	}

	public void startConnection(String ip, int port) {
		try {
			// System.out.println("Trying to connect..");
			String message, response;
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
			System.out.println("Could not close clien connection");
			e.printStackTrace();
		}
	}

}
