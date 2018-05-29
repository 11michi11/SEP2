package test;

import client.controller.ClientController;
import client.model.ClientModelManager;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.controller.ServerController;
import server.model.ServerModelManager;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeworkCommunicationTest {


    private ServerModel serverModel;
    private ServerController serverController;
    private ClientController clientController;
    private ClientModelManager clientModel;

    @BeforeEach
    void setUp(){
        new Thread(() -> {
            serverModel = new ServerModelManager();
            serverController = new ServerController(serverModel);
        }).start();
        clientModel = new ClientModelManager();
        clientController = ClientController.getInstance(clientModel, new EmptyViewForTests());
    }

    @Test
    void addHomeworkTest(){
        MyDate pubDate = MyDate.now(), deadLine = MyDate.now();
        List<ClassNo> classes = new LinkedList<>();
        classes.add(ClassNo.First);
        classes.add(ClassNo.Eigth);
        int numberOfStudentsToDeliver = 0;
        Homework h = new Homework("id","Title", "Content", "Author", pubDate, deadLine, classes, numberOfStudentsToDeliver);

        clientController.addHomework("Title", "Content", "Author", pubDate,deadLine ,classes ,numberOfStudentsToDeliver);
        h = (Homework) clientModel.getPost();

        List<Post> list = serverModel.getAllPost();
        assertTrue(list.contains(h));
    }




}
