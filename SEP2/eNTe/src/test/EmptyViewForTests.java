package test;

import client.controller.ClientController;
import client.view.ClientView;

public class EmptyViewForTests implements ClientView {
    @Override
    public void showPosts(String user) {

    }

    @Override
    public void startView() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void changePasswordDialog() {

    }

    @Override
    public void setController(ClientController controller) {

    }
}
