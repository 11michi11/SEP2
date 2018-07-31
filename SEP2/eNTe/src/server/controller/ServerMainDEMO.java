package server.controller;

import model.Announcement;
import model.Post;
import server.model.ServerModelManager;

import java.util.LinkedList;
import java.util.List;

public class ServerMainDEMO {
    static ServerController controller;

    public static void main(String[] args) {
        ServerModelManager model = new ServerModelManager();
        controller = new ServerController(model);
        List<Post> posts = new LinkedList<>(model.getAllPost());
        System.out.println("----------------------------------");
        for (Post e:posts) {
            if (e instanceof Announcement) {
                System.out.println(e);
            }
        }
    }
}
