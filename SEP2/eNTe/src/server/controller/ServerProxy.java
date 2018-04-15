package server.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.communication.Message;

public class ServerProxy {

	private ServerSocket serverSocket;
	private final int PORT = 7777;
	private ExecutorService executor;
	private Thread listeningThread;
	private ServerController controller;
	
	public ServerProxy(ServerController controller) {
		executor = Executors.newFixedThreadPool(10);
		this.controller = controller;
	}

	public void start() {
		// Listening in server in new thread
//		listeningThread = new Thread(new Runnable() {
//
//			@Override
//			public void run() {
				try {
					serverSocket = new ServerSocket(PORT);
				} catch (IOException e) {
					System.out.println("Unable to connect with the given port");
					System.exit(1);
				}
				while (!serverSocket.isClosed()) {
					if (Thread.interrupted())
						break;

					System.out.println("Waiting for a client...");
					try {
						Socket client = serverSocket.accept();
						executor.submit(new HandleClient(client));
					} catch (IOException e) {
						System.out.println("Connection error " + e.getMessage());
						e.printStackTrace();
					}
				}
//			}
//		});
//		listeningThread.start();
		System.out.println("Starting Server...");

	}

	public void close() {
		listeningThread.interrupt();
		executor.shutdown();
		try {
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("Could not close server");
			e.printStackTrace();
		}
	}

	class HandleClient implements Runnable {

		private Socket client;

		public HandleClient(Socket client) {
			this.client = client;
		}

		@Override
		public void run() {
			String clientIP;
			try {
				clientIP = client.getInetAddress().getHostAddress();
				System.out.println("Welcome " + clientIP);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(client.getInputStream());

				Message request = (Message) in.readObject();
				System.out.println(request);
				Message response = controller.handleMessage(request);

				out.writeObject(response);
				System.out.println("Send");

			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
	}

}
