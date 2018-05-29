package test;

import client.model.ClientProxy;
import model.*;
import model.communication.ManagePost;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.controller.ServerController;
import server.model.ServerModelManager;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeworkCommunicationTest {

    private static ServerModel serverModel;
    private static ClientProxy clientProxy;
    private static ServerController serverController;
    private Homework homework;

    @BeforeAll
    static void setUpConnection() {
        Thread t = new Thread(() -> {
            serverModel = new ServerModelManager();
            serverController = new ServerController(serverModel);
            //while(true){}
        });
        t.start();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clientProxy = new ClientProxy();
        clientProxy.startConnection("localhost", 7777);
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

        List<Post> list = serverModel.getAllPost();
        assertFalse(list.contains(homework));
    }


}
