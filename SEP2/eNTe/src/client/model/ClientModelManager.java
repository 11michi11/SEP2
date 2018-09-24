package client.model;

import java.util.ArrayList;
import java.util.List;

import client.controller.ClientController;
import model.*;
import model.communication.*;

public class ClientModelManager implements ClientModel {

    private ClientProxy server;
    private PostsList posts;
    private UsersList users;
    private FamiliesList families;
    private ClientController controller;


    public ClientModelManager() {
        posts = new PostsList();
        users = new UsersList();
        server = new ClientProxy();
        families = new FamiliesList();
    }

    public void startServer(){
        server.startConnection("localhost", 7777);
    }

    public void closeServer() {
        server.close();
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }

    @Override
    public ArrayList<Family> getAllFamilies() {
        return families.getAllFamilies();
    }

    @Override
    public void addPost(Post post) {
        posts.add(post);
        server.managePost(ManagePost.ADD, post);
    }

    @Override
    public void saveData(WelcomingData data) {
        posts.addAll(data.getPosts());
        users.addAll(data.getUsers());
        families.addAll(data.getFamilies());
    }

    @Override
    public void addOrUpdateUser(User user) {
        if (!users.checkIfIdExist(user.getId())) {
            users.add(user);
            if(user instanceof  Student)
                ((Student) user).getFamily().addChild((Student) user);
            if(user instanceof  Parent)
                ((Parent) user).getFamily().addParent((Parent) user);
            server.manageUser(ManageUser.ADD, user);
        } else {
            users.updateUser(user);
            server.manageUser(ManageUser.EDIT, user);
        }
    }

    @Override
    public void deleteUser(String id) {
        User user = users.getUserById(id);
        server.manageUser(ManageUser.DELETE, user);
        if (user instanceof IFamily) {
            IFamily familyUser = (IFamily) user;
            if (familyUser.getFamily() != null)
                familyUser.getFamily().deleteMember(user);
        }
        users.delete(id);
    }

    @Override
    public void deleteUser(User user) {
        server.manageUser(ManageUser.DELETE, users.getUserById(user.getId()));
        if (user instanceof IFamily)
            ((IFamily) user).getFamily().deleteMember(user);
        users.delete(user.getId());
    }

    @Override
    public void login(String email, String pwd) {
        Auth auth = new Auth(email, pwd);
        Message response = server.login(auth);
        controller.handleMessage(response);
    }

    @Override
    public ArrayList<Parent> getParents() {
        return users.getParents();
    }

    @Override
    public User getUserById(String id) {
        return users.getUserById(id);
    }

    @Override
    public void addCommentToDiscussion(DiscussionComment comment) {
        Discussion discussion = posts.addComment(comment);
        if (discussion != null)
            server.managePost(ManagePost.EDIT, discussion);
    }



    private User getUserByEmail(String email) {
        return users.getUserByEmail(email);
    }

    @Override
    public void deleteFamily(Family family) {
        family.clear();
        families.deleteFamily(family);
        server.manageFamily(ManageFamily.DELETE, family);
    }

    @Override
    public List<Teacher> getTeachers() {
        return users.getAllTeachers();
    }

    @Override
    public void addFamily(Family family) {
        families.addFamily(family);
        server.manageFamily(ManageFamily.ADD, family);
    }

    @Override
    public boolean checkEmailForPwdReset(String email) {
        return server.checkEmailForPwdReset(email);
    }

    @Override
    public void changePwdWithEmail(String email, String newPwd) {
        User user = getUserByEmail(email);
        user.setPwdAndEncrypt(newPwd);
        server.changePwdWithEmail(email, newPwd);
    }

    @Override
    public ArrayList<Post> getAllPosts() {
        return posts.getAll();
    }

    @Override
    public void deletePost(Post post) {
        posts.deletePost(post);
        server.managePost(ManagePost.DELETE, post);
    }

    @Override
    public void editPost(Post post) {
        posts.editPost(post);
        server.managePost(ManagePost.EDIT, post);
    }

    @Override
    public void requestUpdate() {
        WelcomingData data = server.requestUpdate();
        clearLists();
        saveData(data);
    }

    private void clearLists(){
        posts.clear();
        families.clear();
        users.clear();
    }

}
