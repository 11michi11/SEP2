package model.communication;

import model.Post;

import java.io.Serializable;

public class ManagePost extends Manage implements Serializable {

    private Post post;

    public ManagePost(String action, Post user) {
        super(action);
        this.post = user;
    }

    public Post getPost() {
        return post;
    }
}
