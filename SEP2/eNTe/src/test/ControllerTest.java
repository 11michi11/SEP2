package test;

import client.controller.ClientController;
import client.view.ClientView;
import model.*;
import model.communication.WelcomingData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ControllerTest {

    private ClientModel model;
    private ClientController controller;
    private MockClientViewManagerTest view;
    private MyDate homeworkDeadline;
    private List<ClassNo> classes;
    private Administrator currentUser = Administrator.builder().name("name").email("email").build();
    private String postId;

    @BeforeEach
    void setUp() {
        model = new MockClientModelManagerTest();
        view = new MockClientViewManagerTest();
        controller = ClientController.getInstance(model, view);
        controller.setCurrentUser(currentUser);
    }

    @Test
    void addStudent() {
       // Student student = Student.builder().name("child").email("email").classs(ClassNo.Eigth).pwdEncrypt("pwd").build();
        fail();
    }

    @Test
    void addHomeworkTest() {
        addHomework();
    }

    @Test
    void editHomeworkTest() {
        addHomework();
        controller.editHomework(postId, "Changed", "Changed", homeworkDeadline, classes, new ArrayList<>(), 0);
    }


    private void addHomework() {
        homeworkDeadline = MyDate.now();
        classes = new ArrayList<>();
        classes.add(ClassNo.First);
        classes.add(ClassNo.Second);
        int numberOfStudentToDeliver = 1;
        controller.addHomework("Title", "Content", homeworkDeadline, classes, numberOfStudentToDeliver);
    }

    class MockClientModelManagerTest implements ClientModel {

        @Override
        public void setController(ClientController controller) {

        }

        @Override
        public void addPost(Post post) {
            postId = post.getPostId();
            switch (post.getClass().getSimpleName()) {
                case "Homework":
                    try {
                        Homework h = (Homework) post;
                        assertEquals("Title", h.getTitle());
                        assertEquals("Content", h.getContent());
                        assertEquals(currentUser.getName(), h.getAuthor());
                        assertEquals(homeworkDeadline, h.getDeadline());
                        assertEquals(MyDate.now(), h.getPubDate());
                        assertEquals(classes, h.getClasses());
                        assertEquals(1, h.getNumberOfStudentsToDeliver());
                    } catch (Exception e) {
                        fail();
                    }
                    break;
                default:
                    fail();
                    break;
            }
        }

        @Override
        public Post getPost() {
            return null;
        }

        @Override
        public Homework getHomework() {
            return null;
        }

        @Override
        public void saveData(WelcomingData data) {

        }

        @Override
        public void addOrUpdateUser(User user) {

        }

        @Override
        public void deleteUser(String id) {

        }

        @Override
        public void deleteUser(User user) {

        }

        @Override
        public void login(String email, String pwd) {

        }

        @Override
        public ArrayList<Parent> getParents() {
            return null;
        }

        @Override
        public ArrayList<Family> getAllFamilies() {
            return null;
        }

        @Override
        public void deleteFamily(Family family) {

        }

        @Override
        public List<Teacher> getTeachers() {
            return null;
        }

        @Override
        public void addFamily(Family family) {

        }

        @Override
        public boolean checkEmailForPwdReset(String email) {
            return false;
        }

        @Override
        public void changePwdWithEmail(String email, String newPwd) {

        }

        @Override
        public void submitHomework(String text) {

        }

        @Override
        public ArrayList<Post> getAllPosts() {
            return null;
        }

        @Override
        public void deletePost(Post post) {

        }

        @Override
        public void editPost(Post post) {
            postId = post.getPostId();
            switch (post.getClass().getSimpleName()) {
                case "Homework":
                    Homework original = new Homework(postId, "Title", "Content", currentUser.getName(), MyDate.now(), homeworkDeadline, classes, 0, new ArrayList<>(), !homeworkDeadline.isBefore(MyDate.now()));
                    try {
                        assertNotEquals(original, post);
                    } catch (Exception e) {
                        fail();
                    }
                    break;
                default:
                    fail();
                    break;
            }
        }
    }

    class MockClientViewManagerTest implements ClientView {

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
        public boolean showDeleteMessage(String message) {
            return false;
        }

        @Override
        public void changePasswordDialog() {

        }

        @Override
        public void setController(ClientController controller) {

        }
    }

}