package test;

import client.model.ClientProxy;

import java.util.concurrent.CountDownLatch;

public class TestClient implements Runnable {

    private CountDownLatch latch;

    public TestClient(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Client");
        HomeworkCommunicationTest.clientProxy = new ClientProxy();
        HomeworkCommunicationTest.clientProxy.startConnection("localhost", 7777);
    }

}
