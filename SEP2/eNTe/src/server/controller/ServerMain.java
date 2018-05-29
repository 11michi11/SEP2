package server.controller;

import server.model.ServerModelManager;

public class ServerMain {

	static ServerController controller;
	
	public static void main(String[] args) {
		ServerModelManager model = new ServerModelManager();
		controller = new ServerController(model);
	}

}
