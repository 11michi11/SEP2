package client.model;

import java.util.ArrayList;
import java.util.Arrays;
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
        server.startConnection("localhost", 7777);
    }

    public void closeServer() {
        server.close();
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }

    @Override
    public void addPost(String title, String content, String author, MyDate publicationDate) {
        posts.add(new Post(title, content, author, publicationDate));
    }

    @Override
    public ArrayList<Family> getAllFamilies() {
        return families.getAll();
    }

    @Override
    public Post getPost() {
        return posts.getFirstPost();
    }

    @Override
    public Homework getHomework() {
        return null;
        //to do
    }

    @Override
    public Discussion getDiscussion() {
        return null;
        //to do
    }

    @Override
    public void storePost(Post post) {
        posts.add(post);
    }

    @Override
    public void saveData(WelcomingData data) {
        posts.addAll(Arrays.asList(data.getPosts()));
    }

    @Override
    public void addOrUpdateUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
            server.manageUser(ManageUser.ADD, user);
        } else {
            users.updateUser(user);
        }
    }

    @Override
    public void deleteUser(String id) {
        User user = users.getUserById(id);
        server.manageUser(ManageUser.DELETE, user);
        if(user instanceof IFamily)
            ((IFamily) user).getFamily().deleteMember(user);
        users.delete(id);
    }
    @Override
    public void deleteUser(User user) {
        server.manageUser(ManageUser.DELETE, users.getUserById(user.getId()));
        if(user instanceof IFamily)
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

    public User getUserById(String id) {
        return users.getUserById(id);
    }

    private User getUserByEmail(String email){
        return users.getUserByEmail(email);
    }

    @Override
    public void deleteFamily(Family family) {
        family.clear();
        families.deleteFamily(family);
        server.manageFamily(ManageFamily.ADD, family);
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
        user.setPwd(newPwd);
        server.changePwdWithEmail(email, newPwd);
    }

}
