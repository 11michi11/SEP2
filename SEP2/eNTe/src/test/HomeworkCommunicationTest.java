package test;

import client.model.ClientProxy;
import model.*;
import model.communication.ManagePost;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.controller.ServerController;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class HomeworkCommunicationTest {

    static ServerModel serverModel;
    static ClientProxy clientProxy;
    static ServerController serverController;
    private Homework homework;

    @BeforeAll
    static void setUpConnection() {
        CountDownLatch latch = new CountDownLatch(1);
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

    @Test
    void addHomeworkTest() {
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

        homework = new Homework(homework.getId(), "Title1", "Content1", "Author1", homework.getPubDate(), homework.getDeadline(), homework.getClasses(), homework.getNumberOfStudentsToDeliver());
        clientProxy.managePost(ManagePost.EDIT, homework);

        assertTrue(serverModel.getAllPost().contains(homework));
        assertEquals(1,serverModel.getAllPost().size());
    }

}