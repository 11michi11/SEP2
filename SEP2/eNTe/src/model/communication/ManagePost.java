package model.communication;

import model.Post;

import java.io.Serializable;

public class ManagePost implements Serializable {


    private String action;
    private Post post;
    public static final String ADD = "ADD";
    public static final String DELETE = "DELETE";
    public static final String EDIT = "EDIT";

    public ManagePost(String action, Post user) {
        this.post = user;
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public Post getPost() {
        return post;
    }
}
