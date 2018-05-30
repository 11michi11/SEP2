package test;

import client.model.ClientProxy;
import model.*;
import model.communication.ManagePost;
import org.junit.jupiter.api.*;
import server.controller.ServerController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class HomeworkCommunicationTest {

    static ServerModel serverModel;
    static ClientProxy clientProxy;
    static ServerController serverController;
    private Homework homework;
    private static CountDownLatch latch;

    @BeforeAll
    static void setUpConnection() {
        latch = new CountDownLatch(1);
        new Thread(new TestServer(latch)).start();
        new Thread(new TestClient(latch)).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp(){
        MyDate pubDate = MyDate.now(), deadLine = MyDate.now();
        List<ClassNo> classes = new LinkedList<>();
        classes.add(ClassNo.First);
        classes.add(ClassNo.Eigth);
        int numberOfStudentsToDeliver = 0;
        homework = new Homework("Title", "Content", "Author", pubDate, deadLine, classes, numberOfStudentsToDeliver);
    }

    @AfterEach
    void tearDown(){
        serverModel.clear();
    }

    @AfterAll
    static void shutDown(){
        serverController.closeServer();
        clientProxy.close();
    }

    @Test
    void addHomeworkTest() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clientProxy.managePost(ManagePost.ADD, homework);

        List<Post> list = serverModel.getAllPost();
        assertTrue(list.contains(homework));
    }

    @Test
    void deleteHomeworkTest(){
        serverModel.addPost(homework);

        clientProxy.managePost(ManagePost.DELETE, homework);

        assertFalse(serverModel.getAllPost().contains(homework));
    }

    @Test
    void editHomeworkTest(){
            serverModel.addPost(homework);

            homework = new Homework(homework.getPostId(), "Title1", "Content1", "Author1", homework.getPubDate(), homework.getDeadline(), homework.getClasses(), homework.getNumberOfStudentsToDeliver(), new ArrayList<>(), false);
            clientProxy.managePost(ManagePost.EDIT, homework);

            assertTrue(serverModel.getAllPost().contains(homework));
            assertEquals(1,serverModel.getAllPost().size());
    }

}