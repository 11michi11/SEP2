package model.communication;

import model.Post;

import java.io.Serializable;

public class ManagePost extends Manage implements Serializable {

    private Post post;

    public ManagePost(String action, Post post) {
        super(action);
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
}
