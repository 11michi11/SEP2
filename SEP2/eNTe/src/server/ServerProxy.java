package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerProxy {

	private ServerSocket serverSocket;
	private final int PORT = 7777;
	private ExecutorService executor;
	private Thread listeningThread;

	public ServerProxy() {
		executor = Executors.newFixedThreadPool(10);
	}

	public void start() {
		//Listening in server in new thread
		System.out.println("$%^");
		listeningThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					serverSocket = new ServerSocket(PORT);
				} catch (IOException e) {
					// System.out.println("Unable to connect with the given port");
					System.exit(1);
				}
				while (!serverSocket.isClosed()) {
					if (Thread.interrupted())  
				        return;
				    
					// System.out.println("Waiting for a client...");
					try {
						Socket client = serverSocket.accept();
						executor.submit(new HandleClient(client));
					} catch (IOException e) {
						// System.out.println("Connection error");
						e.printStackTrace();
					}
				} 
			}
		});
		listeningThread.start();
		// System.out.println("Starting Server...");
		
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
			String request, response, clientIP;
			try {
				clientIP = client.getInetAddress().getHostAddress();
				// System.out.println("Welcome " + clientIP);
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

				request = in.readLine();

				out.println(request);
				System.out.println("Send");

			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}

		}

	}

}
