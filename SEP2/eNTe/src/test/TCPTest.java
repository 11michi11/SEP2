package test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import client.proxy.ClientProxy;
import server.ServerProxy;
class TCPTest {
	
	private final String IP = "localhost";
	private final int PORT = 7777;
	private ClientProxy client;
	private ServerProxy server;
	
	@BeforeEach
	public void setupServerClient() {
		server = new ServerProxy();
		server.start();
		System.out.println("!@#");
		client = new ClientProxy();
		client.startConnection(IP, PORT);
	}

	@Test
	void sendPlainMessageTest() {
		try {
			System.out.println("Sending...");
			assertThat(client.sendMessage(), is("dupa"));
			System.out.println("Received");
		} catch (IOException e) {
			fail("Exception shoudn't has been thrown");
			e.printStackTrace();
		}
	}
	
	@AfterEach
	public void closeServerClient() {
		server.close();
		client.close();
	}

}
