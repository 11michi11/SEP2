package server.controller;

import java.util.LinkedList;

import model.Post;
import model.ServerModel;
import model.communication.*;

public class ServerController {

    private ServerModel model;
    private ServerProxy server;

    public ServerController(ServerModel model) {
        this.model = model;
        server = new ServerProxy(this);
        new Thread(() -> server.start()).start();
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
            case ManageFamily:
                ManageFamily manageFamily = msg.getManageFamily();
                response = handleManageFamily(manageFamily);
                break;
            case ManagePost:
                ManagePost managePost = msg.getManagePost();
                response = handleManagePost(managePost);
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

    private Message handleManagePost(ManagePost managePost) {
        Message response;
        switch (managePost.getAction()) {
            case ManagePost.ADD:
                model.addPost(managePost.getPost());
                response = Message.createSuccessfulResponse();
                break;
            case ManagePost.DELETE:
                model.deletePost(managePost.getPost());
                response = Message.createSuccessfulResponse();
                break;
            case ManagePost.EDIT:
                model.editPost(managePost.getPost());
                response = Message.createSuccessfulResponse();
                break;
            default:
                response = Message.createFail();
        }
        return response;
    }

    private Message handleManageFamily(ManageFamily manageFamily) {
        Message response;
        switch (manageFamily.getAction()) {
            case ManageUser.ADD:
                model.addFamily(manageFamily.getFamily());
                response = Message.createSuccessfulResponse();
                break;
            case ManageUser.EDIT:
                model.editFamily(manageFamily.getFamily());
                response = Message.createSuccessfulResponse();
                break;
            case ManageUser.DELETE:
                model.deleteFamily(manageFamily.getFamily());
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
                data.insertPosts(model.getAllPost());
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
