package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import client.proxy.Auth;
import client.proxy.ClientProxy;
import client.proxy.Login;
import client.proxy.LoginStatus;
import client.proxy.Message;
import client.proxy.WelcomingData;
import server.controller.ServerController;
class TCPTest {
	
	private final String IP = "localhost";
	private final int PORT = 7777;
	private ClientProxy client;
	private ServerController server;
	
	@BeforeEach
	public void setupServerClient() {
		server = new ServerController();
		client = new ClientProxy();
		client.startConnection(IP, PORT);
	}

	@Test
	void sendPlainMessageTest() {
		try {
			System.out.println("Sending...");
			Message msg = new Message();
			
			msg.put("type", "auth");
			MessageDigest dig = MessageDigest.getInstance("SHA-256");
			dig.update("pwd".getBytes());
			Auth auth = new Auth("login", toHex(dig.digest()));
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
		} catch (IOException | ClassNotFoundException | NoSuchAlgorithmException e) {
			fail("Exception shoudn't has been thrown");
			e.printStackTrace();
		}
	}
	
	private String toHex(byte[] byteData) {
		StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	return hexString.toString();
	}
	
	@AfterEach
	public void closeServerClient() {
		server.closeServer();
		client.close();
	}

}
