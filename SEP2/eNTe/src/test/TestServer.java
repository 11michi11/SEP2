package test;

import server.controller.ServerController;
import server.model.ServerModelManager;

import java.util.concurrent.CountDownLatch;

public class TestServer implements Runnable {

    private CountDownLatch latch;

    public TestServer(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        HomeworkCommunicationTest.serverModel = new ServerModelManager();
        HomeworkCommunicationTest.serverController = new ServerController(HomeworkCommunicationTest.serverModel);
        System.out.println("Server");
        latch.countDown();
    }
}

