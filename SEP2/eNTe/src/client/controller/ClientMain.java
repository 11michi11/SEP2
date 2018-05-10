package client.controller;

import client.model.ClientModelManager;
import client.view.ClientView;
import client.view.ClientViewManager;
import model.ClientModel;

import java.lang.reflect.Member;
import java.util.function.Function;

public class ClientMain {


	public static void main(String[] args) {
		ClientModel model = new ClientModelManager();
		ClientView view = new ClientViewManager();
		ClientController controller = ClientController.getInstance(model, view);
	}

}
