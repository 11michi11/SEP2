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
import java.util.Collections;
import java.util.Comparator;
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
        new Thread(()->this.view.startView()).start();
        model.startServer();
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
                WelcomingData data = login.getData();
                model.saveData(data);
                if (login.changeLogin()) {
                    view.changePasswordDialog();
                }
                view.showPosts(login.getUserType());
                Thread updater = new Thread(new ModelUpdater());
                updater.setDaemon(true);
                updater.start();
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

    public void addTeacher(String name, String email, String responsibility, String id, String pwd) {
        Teacher user = Teacher.builder().name(name).email(email).responsibility(responsibility).build();
        if (id != null)
            user.setId(id);
        if (pwd != null)
            user.setPwd(pwd);
        model.addOrUpdateUser(user);
    }

    public void addStudent(String name, String email, ClassNo classs, Family family, String id, String pwd) {
        Student student = Student.builder().name(name).email(email).classNo(classs).family(family).build();
        if (id != null)
            student.setId(id);
        if (pwd != null)
            student.setPwd(pwd);
        model.addOrUpdateUser(student);
    }

    public void addParent(String name, String email, Family family, String id, String pwd) {
        Parent parent = Parent.builder().name(name).email(email).family(family).build();
        if (id != null)
            parent.setId(id);
        if (pwd != null)
            parent.setPwd(pwd);
        model.addOrUpdateUser(parent);
    }

    public void deleteUser(String id) {
        model.deleteUser(id);
    }

    public void deleteUser(User user) {
        model.deleteUser(user);
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

    public ArrayList<Post> getAllPosts() {
        ArrayList<Post> allPosts = model.getAllPosts();
        allPosts.sort(Collections.reverseOrder(Comparator.comparing(Post::getPubDate)));
        return allPosts;
    }

    public void deletePost(Post post) {
        model.deletePost(post);
    }

    public void addHomework(String title, String content, MyDate deadline, List<ClassNo> classes, int numberOfStudentsToDeliver) {
        SpecialType specialType = SpecialType.NORMAL;
        specialType.doAction();
        model.addPost(new Homework(title, content, currentUser.getName(), MyDate.now(), deadline, classes, numberOfStudentsToDeliver, specialType));
    }

    public void editHomework(String homeworkId, String title, String content, MyDate deadline, List<ClassNo> classes, List<HomeworkReply> replies, int numberOfStudentsToDeliver) {
        model.editPost(new Homework(homeworkId, title, content, currentUser.getName(), MyDate.now(), deadline, classes, numberOfStudentsToDeliver, replies, !deadline.isBefore(MyDate.now())));
    }

    public void submitHomework(Homework homework, String text) {
        int index = homework.indexOfStudentReply(currentUser.getId());
        if (index != -1) {
            homework.getReplies().get(index).setContent(text);
            homework.getReplies().get(index).setHandInDate(MyDate.now());
        }
        else {
            homework.addHomeworkReply(new HomeworkReply(text, (Student) currentUser, homework.isClosed(), MyDate.now(), homework.getPostId()));
        }
        model.editPost(homework);
    }

    public boolean checkHomeworkClass(Homework homework) {
        if (currentUser instanceof Student)
            return homework.getClasses().contains(((Student) currentUser).getClassNo());
        else if (currentUser instanceof Parent) {
            for (ClassNo e : ((Parent) currentUser).getFamily().getClasses()) {
                if (homework.getClasses().contains(e))
                    return true;
            }
        }
        return false;
    }

    public void addDiscussion(String title, String content, String selectedValue, List<ClassNo> classes) {
        SpecialType specialType = SpecialType.valueOf(selectedValue.toUpperCase());
        specialType.doAction();
        model.addPost(new Discussion(title, content, currentUser.getName(), MyDate.now(), specialType, classes));
    }

    public void addDiscussionComment(String text, String discussionid) {
        model.addCommentToDiscussion(new DiscussionComment(text, currentUser.getId(), MyDate.now(), discussionid));
    }


    public void addAnnouncement(String title, String content, String selectedValue, List<ClassNo> classes, MyDate expirationDate) {
        SpecialType specialType = SpecialType.valueOf(selectedValue.toUpperCase());
        specialType.doAction();
        model.addPost(new Announcement(title, content, currentUser.getName(), MyDate.now(), specialType, classes, expirationDate));
    }

    public void editAnnouncement(String postId, String title, String content, String selectedValue, List<ClassNo> classes, MyDate expiration) {
        SpecialType specialType = SpecialType.valueOf(selectedValue.toUpperCase());
        specialType.doAction();
        model.editPost(new Announcement(postId, title, content, getCurrentUserName(), MyDate.now(), specialType, classes, expiration));
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


    private class ModelUpdater implements Runnable {

        @Override
        public void run() {
            System.out.println("waiting to preform update request");
            try {
                while (true) {
                    Thread.sleep(1000 * 60);
                    System.out.println("Performing model update");
                    model.requestUpdate();
                    System.out.println("Done");
                }
            } catch (InterruptedException e) {
                System.out.println("Updating interrupted");
            }
        }
    }

}
