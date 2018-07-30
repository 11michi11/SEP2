package client.controller;

import client.view.ClientView;
import client.view.managingUsers.TeacherDT;
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

    public static ClientController getInstance() {
        if (instance == null)
            instance = new ClientController();
        return instance;
    }

    public void init(ClientModel model, ClientView view) {
        this.model = model;
        model.setController(this);
        this.view = view;
        view.setController(this);
        this.view.startView();
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
        currentUser.setPwdAndEncrypt(pwd);
        model.addOrUpdateUser(currentUser);
    }

    public void addTeacher(String name, String email, String id, String pwd) {
        User user = Teacher.builder().name(name).email(email).build();
        if (id != null)
            user.setId(id);
        if(pwd != null)
            user.setPwd(pwd);
        model.addOrUpdateUser(user);
    }

    public void addStudent(String name, String email, ClassNo classs, Family family, String id) {
        Student student = Student.builder().name(name).email(email).classNo(classs).family(family).build();
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

    public void addPost(String title, String content, String selectedValue, List<ClassNo> classes) {
        SpecialType specialType = SpecialType.valueOf(selectedValue.toUpperCase());
        specialType.doAction();
        model.addPost(new Post(title, content, currentUser.getName(), MyDate.now(), specialType, classes));
    }

    public ArrayList<Post> getAllPosts() {
        return model.getAllPosts();
    }

    public void deletePost(Post post) {
        model.deletePost(post);
    }

    public void addHomework(String title, String content, MyDate deadline, List<ClassNo> classes, int numberOfStudentsToDeliver, String selectedValue) {
        SpecialType specialType = SpecialType.valueOf(selectedValue.toUpperCase());
        specialType.doAction();
        model.addPost(new Homework(title, content, currentUser.getName(), MyDate.now(), deadline, classes, numberOfStudentsToDeliver, specialType));
    }

    public void editHomework(String homeworkId, String title, String content, MyDate deadline, List<ClassNo> classes, List<HomeworkReply> replies, int numberOfStudentsToDeliver) {
        model.editPost(new Homework(homeworkId, title, content, currentUser.getName(), MyDate.now(), deadline, classes, numberOfStudentsToDeliver, replies, !deadline.isBefore(MyDate.now())));
    }

    public void submitHomework(Homework homework, String text) {
        homework.addHomeworkReply(new HomeworkReply(text, (Student) currentUser, homework.isClosed(), MyDate.now()));
        model.editPost(homework);
    }

    public boolean checkHomeworkClass(Homework homework) {
        if (currentUser instanceof Student)
            return homework.getClasses().contains(((Student) currentUser).getClassNo());
        else
            return false;
    }

    public void addDiscussion(String title, String content, String selectedValue, List<ClassNo> classes) {
        SpecialType specialType = SpecialType.valueOf(selectedValue.toUpperCase());
        specialType.doAction();
        model.addPost(new Discussion(title, content, currentUser.getName(), MyDate.now(), specialType, classes));
    }

    public void addAnnouncement(String title, String content, String selectedValue, List<ClassNo> classes, MyDate expirationDate) {
        SpecialType specialType = SpecialType.valueOf(selectedValue.toUpperCase());
        specialType.doAction();
        model.addPost(new Announcement(title,content,currentUser.getName(),MyDate.now(),specialType,classes,expirationDate));
    }



    public ArrayList<Family> getFamilies() {
        return model.getAllFamilies();
    }

    public void createFamily() {
        Family family = new Family();
        model.addFamily(family);
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

    public String getCurrentUserName() {
        return currentUser.getName();
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentUserId() {
        return currentUser.getId();
    }

    public String getCurrentUserType() {
        return currentUser.getClass().getSimpleName();
    }

    public void resetPwd(String email) {
        if (model.checkEmailForPwdReset(email)) {
            String newPwd = Password.generateEntePassword();
            SendEmail.sendPasswordEmail(email, newPwd);
            model.changePwdWithEmail(email, newPwd);
        } else {
            view.showMessage("Entered email does not exist in the system.\nTry again or contact administrator: enteEmailService@gmail.com");
        }
    }

    public boolean showDeleteMessage(String message) {
        return view.showDeleteMessage(message);
    }

    public void sendImportantPostEmail() {
        System.out.println("IMPLEMENT SENDING IMPORTANT EMAILS");
    }

    public void sendParentalPostEmail() {
        System.out.println("IMPLEMENT SENDING PARENTAL EMAILS");
    }


    public void addDiscussionComment(String text) {
        model.addCommentToDiscussion(new DiscussionComment(text, currentUser.getName(), MyDate.now()));
    }

    public List<Post> getHomeworkForParent() {
        if (!(currentUser instanceof Parent))
            throw new IllegalStateException("Current user is not a parent");
        Parent parent = (Parent) currentUser;
        List<Post> posts = getAllPosts();
        List<ClassNo> classes = parent.getFamily().getClasses();
        List<Post> parentHomework = new ArrayList<>();
        for (Post p : posts)
            if (p instanceof Homework && ((Homework) p).getClasses().contains(classes))
                parentHomework.add(p);
        return parentHomework;
    }

    public String getUserNameById(String userId) {
        return model.getUserById(userId).getName();
    }

//    public List<Post> getImportantSpecialPostsForParent() {
//        List<Post> posts = getAllPosts();
//        List<Post> parentPosts = new ArrayList<>();
//        for(Post p : posts)
//            if (p.getSpecialType().equals(SpecialType.PARENTAL) || p.getSpecialType().equals(SpecialType.IMPORTANT))
//                parentPosts.add(p);
//        return parentPosts;
//    }


}
