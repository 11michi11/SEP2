package server.controller;

import server.model.ServerModelManager;

public class ServerMain {
	
	public static void main(String[] args) {
		ServerModelManager model = new ServerModelManager();
		ServerController controller = new ServerController(model);
	}

}
