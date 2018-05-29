package server.controller;

import model.communication.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ServerProxy {

    private ServerSocket serverSocket;
    private final int PORT = 7777;
    private LinkedList<Thread> clientThreads;
    private ServerController controller;

    public ServerProxy(ServerController controller) {
        clientThreads = new LinkedList<>();
        this.controller = controller;
    }

    public void start() {
        System.out.println("Starting Server...");
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
                Thread clientThread = new Thread(new HandleClient(client));
                clientThread.start();
                clientThreads.add(clientThread);
            } catch (IOException e) {
                System.out.println("Connection error " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    public void close() {
        clientThreads.forEach(Thread::interrupt);
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Could not close server");
            e.printStackTrace();
        }
    }

    private class HandleClient implements Runnable {

        private Socket client;

        HandleClient(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            String clientIP;
            Message response, request;
            ObjectInputStream in = null;
            ObjectOutputStream out = null;
            try {
                clientIP = client.getInetAddress().getHostAddress();
                System.out.println("Welcome " + clientIP);
                out = new ObjectOutputStream(client.getOutputStream());
                in = new ObjectInputStream(client.getInputStream());
                while (!client.isClosed()) {
                    if(Thread.interrupted())
                        throw new InterruptedException();

                    request = (Message) in.readObject();
                    System.out.println(request);
                    response = controller.handleMessage(request);
                    out.writeObject(response);
                    System.out.println("Send");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                //System.exit(0);
            } catch (InterruptedException e){
                try {
                    in.close();
                    out.close();
                    client.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
