package client.controller;

import client.view.ClientView;
import client.view.ParentDT;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import model.Classs;
import model.communication.Login;
import model.communication.Message;
import model.communication.WelcomingData;

import java.util.ArrayList;

public class ClientController {

    private ClientModel model;
    private ClientView view;
    private static ClientController instance;

    private ClientController(ClientModel model, ClientView view) {
        instance = this;
        this.model = model;
        model.setController(this);
        this.view = view;
        this.view.startView();
    }

    public static ClientController getInstance(ClientModel model, ClientView view) {
        if (instance == null)
            instance = new ClientController(model, view);
        return instance;
    }

    public static ClientController getInstance() {
        if (instance == null)
            throw new IllegalStateException("There is no instance");
        return instance;
    }

    public Post[] getPosts() {
        Post[] posts = new Post[1];
        posts[0] = model.getPost();
        return posts;
    }

    public void login(String login, String pwd) {
        model.login(login, pwd);
    }

    public void handleMessage(Message msg) {
        switch (msg.getType()) {
            case Login:
                handleLogin(msg);
                break;
            case Fail:
            default:
                System.out.println("Error!!!");
                break;
        }
    }

    private void handleLogin(Message msg) {
        Login login = msg.getLogin();
        switch (login.getLoginStatus()) {
            case SUCCESS:
                WelcomingData data = login.getData();
                model.saveData(data);
                view.showPosts(login.getUserType());
                break;
            case FAILURE_LOGIN:
                view.showMessage("Wrong user name, try again.");
                break;
            case FAILURE_PWD:
                view.showMessage("Wrong user password, try again.");
                break;
        }
    }

    public void addTeacher(String name, String email, String password, Boolean admin) {
        User user;
        if (admin)
            user = new Administrator(name, email, password);
        else
            user = new Teacher(name, email, password);

        model.addOrUpdateUser(user);
    }

    public void addStudent(String name, String email, String password, Classs classs) {
        Student student = Student.builder().name(name).login(email).pwd(password).classs(classs).build();
        model.addOrUpdateUser(student);
    }

    public void addStudentWithFamily(String name, String email, String password, Classs classs, Family family) {
        Student student = Student.builder().name(name).login(email).pwd(password).classs(classs).family(family).build();
        model.addOrUpdateUser(student);
    }

    private ArrayList<String> extractParentsIDs(ArrayList<Parent> parents) {
        ArrayList<String> ids = new ArrayList<>();
        for (Parent p : parents)
            ids.add(p.getId());
        return ids;
    }

    public void addParent(String name, String email, String password) {
        Parent parent = Parent.builder().name(name).login(email).pwd(password).build();
        model.addOrUpdateUser(parent);
    }

    public void deleteUser(String id) {
        model.deleteUser(id);
    }

    public ObservableList<ParentDT> getParentsForView() {
        ObservableList<ParentDT> parents = FXCollections.observableArrayList();
        Parent p1 = Parent.builder().name("name").login("email").pwd("pwd").build();
        Parent p2 = Parent.builder().name("name").login("email").pwd("pwd").build();
        Parent p3 = Parent.builder().name("name").login("email").pwd("pwd").build();
        Parent p4 = Parent.builder().name("name").login("email").pwd("pwd").build();
        parents.addAll(new ParentDT(p1), new ParentDT(p2), new ParentDT(p3), new ParentDT(p4));
        return parents;
    }

    public void addPost(String title, String content) {
        model.addPost(title, content);
    }
}
