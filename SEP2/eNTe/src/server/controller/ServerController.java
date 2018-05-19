package server.controller;

import java.sql.SQLException;
import java.util.LinkedList;

import model.Post;
import model.ServerModel;
import model.User;
import model.communication.*;
import server.model.ServerModelManager;

public class ServerController {

    private ServerModel model;
    private ServerProxy server;

    public ServerController(ServerModel model) {
        this.model = model;
        server = new ServerProxy(this);
        server.start();
    }

    public void closeServer() {
        server.close();
    }

    Message handleMessage(Message msg) {
        Message response;

        switch (msg.getType()) {
            case Auth:
                response = handleAuthentication(msg);
                break;
            case ManageUser:
                ManageUser manageUser = msg.getManageUser();
                response = handleManageUser(manageUser);
                break;
            case CheckEmail:
                String email = msg.getEmail();
                response = checkEmail(email);
				break;
            case ChangePwd:
                ChangePwd change = msg.getChangePwd();
                model.changePwdWithEmail(change.email, change.newPwd);
                response = Message.createSuccessfulResponse();
                break;
            default:
                response = Message.createFail();
                break;
        }
        return response;
    }

    private Message checkEmail(String email) {
        return model.checkIfEmailExist(email) ? Message.createEmailExist() : Message.createEmailDoesNotExist();
    }

    private Message handleManageUser(ManageUser manageUser) {
        Message response;
        switch (manageUser.getAction()) {
            case ManageUser.ADD:
                model.addUser(manageUser.getUser());
                response = Message.createSuccessfulResponse();
                break;
            case ManageUser.EDIT:
                model.editUser(manageUser.getUser());
                response = Message.createSuccessfulResponse();
                break;
            case ManageUser.DELETE:
                model.deleteUser(manageUser.getUser());
                response = Message.createSuccessfulResponse();
                break;
            default:
                response = Message.createFail();
                break;
        }

        return response;
    }

    private Message handleAuthentication(Message msg) {
        WelcomingData data;
        Login login = null;

        LoginStatus status = model.authenticate(msg.getAuth());
        switch (status) {
            case SUCCESS:
                data = new WelcomingData();
                Post post = model.getPost();
                LinkedList<Post> list = new LinkedList<>();
                list.add(post);
                data.insertPosts(list);
                //status.currentUser.changePassword(); - for testing changing password
                login = new Login(LoginStatus.SUCCESS, data, status.currentUser);
                break;
            case FAILURE_LOGIN:
                data = new WelcomingData();
                login = new Login(LoginStatus.FAILURE_LOGIN, data, null);
                break;
            case FAILURE_PWD:
                data = new WelcomingData();
                login = new Login(LoginStatus.FAILURE_PWD, data, null);
                break;
        }
        return Message.createLogin(login);
    }
}
