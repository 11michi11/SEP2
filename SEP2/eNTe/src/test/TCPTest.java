package test;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import client.proxy.Auth;
import client.proxy.ClientProxy;
import client.proxy.Login;
import client.proxy.LoginStatus;
import client.proxy.Message;
import client.proxy.WelcomingData;
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
		client = new ClientProxy();
		client.startConnection(IP, PORT);
	}

	@Test
	void sendPlainMessageTest() {
		try {
			System.out.println("Sending...");
			Message msg = new Message();
			
			msg.put("type", "auth");
			Auth auth = new Auth("login", "pwd");
			msg.put("auth",  auth);
			
			Message response = new Message();
			
			WelcomingData data = new WelcomingData();
			Login login = new Login(LoginStatus.SUCCESS, data);
			response.put("type", "login");
			response.put("login", login);
			
			Message result = client.sendMessage(msg);
			System.out.println(result.equals(response));
			
			assertEquals(result, response);
			System.out.println("Received");
		} catch (IOException | ClassNotFoundException e) {
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
