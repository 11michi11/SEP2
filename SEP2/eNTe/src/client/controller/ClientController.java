package client.controller;

import client.view.ClientView;
import client.view.TeacherDT;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import model.communication.Login;
import model.communication.Message;
import model.communication.WelcomingData;
import utility.Password;
import utility.SendEmail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientController {

    private ClientModel model;
    private ClientView view;
    private static ClientController instance;
    private User currentUser;

    private ClientController(ClientModel model, ClientView view) {
        instance = this;
        this.model = model;
        model.setController(this);
        initializeModelForTests();
        this.view = view;
        view.setController(this);
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

    public void login(String email, String pwd) {
        model.login(email, pwd);
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
                currentUser = login.getCurrentUser();
                if (login.changeLogin()) {
                    view.changePasswordDialog();
                }
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

    public void changePassword(String pwd) {
        currentUser.setPwd(pwd);
        model.addOrUpdateUser(currentUser);
    }

    public void addTeacher(String name, String email, String id) {
        User user = Teacher.builder().name(name).email(email).build();
        if (id != null)
            user.setId(id);
        model.addOrUpdateUser(user);
    }

    public void addStudent(String name, String email, ClassNo classs, Family family, String id) {
        Student student = Student.builder().name(name).email(email).classs(classs).family(family).build();
        if (id != null)
            student.setId(id);
        model.addOrUpdateUser(student);
        family.addChild(student);
    }

    public void addParent(String name, String email, Family family, String id) {
        Parent parent = Parent.builder().name(name).email(email).family(family).build();
        if (id != null)
            parent.setId(id);
        model.addOrUpdateUser(parent);
        family.addParent(parent);
    }

    public void deleteUser(String id) {
        model.deleteUser(id);
    }

    public void deleteUser(User user) {
        model.deleteUser(user);
    }

    public Post[] getPosts() {
        Post[] posts = new Post[1];
        posts[0] = model.getPost();
        return posts;
    }

    public Homework[] getHomework() {
        Homework[] homework = new Homework[1];
        homework[0] = model.getHomework();
        return homework;
    }
    
    public void addPost(String title, String content, String author, MyDate publicationDate) {
        model.addPost(new Post(title, content, author, publicationDate));
    }

    public void addHomework(String title, String content, MyDate deadline, List<ClassNo> classes, int numberOfStudentsToDeliver){
        model.addPost(new Homework(title, content, currentUser.getName(), MyDate.now(), deadline, classes, numberOfStudentsToDeliver));
    }

    public void editHomework(String homeworkId, String title, String content, MyDate deadline, List<ClassNo> classes,List<HomeworkReply> replies, int numberOfStudentsToDeliver){
        model.editPost(new Homework(homeworkId, title, content, currentUser.getName(), MyDate.now(), deadline, classes, numberOfStudentsToDeliver,replies, !deadline.isBefore(MyDate.now())));
    }


    public ArrayList<Family> getFamilies() {
        return model.getAllFamilies();
    }

    public void deleteFamily(Family family) {
        model.deleteFamily(family);

    }

    public ObservableList<TeacherDT> getTeachersForView() {
        ObservableList<TeacherDT> teachers = FXCollections.observableArrayList();
        teachers.addAll(model.getTeachers().stream()
                .map(TeacherDT::new).collect(Collectors.toList()));
        return teachers;
    }

    private void initializeModelForTests() {
        Teacher t1 = Teacher.builder().name("Pato").email("email").build();
        Teacher t2 = Teacher.builder().name("Juraj").email("sdfdsf").build();
        Teacher t3 = Teacher.builder().name("Michał Pompa").email("emailIzidro").build();
        model.addOrUpdateUser(t1);
        model.addOrUpdateUser(t2);
        model.addOrUpdateUser(t3);
        Parent p1 = Parent.builder().name("name").email("email").pwdEncrypt("pwd").build();
        Parent p2 = Parent.builder().name("name").email("email").pwdEncrypt("pwd").build();
        Parent p3 = Parent.builder().name("name").email("email").pwdEncrypt("pwd").build();
        Parent p4 = Parent.builder().name("name").email("email").pwdEncrypt("pwd").build();
        model.addOrUpdateUser(p1);
        model.addOrUpdateUser(p2);
        model.addOrUpdateUser(p3);
        model.addOrUpdateUser(p4);

    }
    public boolean showDeleteMessage(String message) {
        return view.showDeleteMessage(message);
    }

    public void createFamily() {
        Family family = new Family();
        model.addFamily(family);
    }

    public String getCurrentUserName() {
        return currentUser.getName();
    }

    public void resetPwd(String email) {
        if (model.checkEmailForPwdReset(email)) {
            String newPwd = Password.generateEntePassword();
            SendEmail.sendPasswordEmail(email, newPwd);
            model.changePwdWithEmail(email, newPwd);
        }else{
            view.showMessage("Entered email does not exist in the system.\nTry again or contact administrator: enteEmailService@gmail.com");
        }
    }

	public void submitHomework(String text) {
		model.submitHomework(text);
		
	}

	public ArrayList<Post> getAllPosts() {
		return model.getAllPosts();
	}


    public void deletePost(Post post) {
        model.deletePost(post);
    }

    public void setCurrentUser(Administrator currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentUserId() {
        return currentUser.getId();
    }
}
