package client.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientProxy {
	
	private Socket client;
	private PrintWriter out;
	private BufferedReader in;

	public void startConnection(String ip, int port) {
		try {
			//System.out.println("Trying to connect..");
			String message, response;
			client = new Socket(ip, port);
			System.out.println("Connected!");
			out = new PrintWriter(client.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String sendMessage() throws IOException {
		out.println("dupa");
		return in.readLine();
	}
	
	public void close() {
		out.close();
		try {
			in.close();
			client.close();
		} catch (IOException e) {
			System.out.println("Could not close clien connection");
			e.printStackTrace();
		}
	}

}
