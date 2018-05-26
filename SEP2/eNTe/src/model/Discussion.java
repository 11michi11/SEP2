package model;

import model.MyDate;
import model.Post;

public class Discussion extends Post {

    public Discussion(String title, String content, String author, MyDate pubDate) {
        super(title, content, author, pubDate);
    }

    public Discussion(String postId, String title, String content, String author, MyDate pubDate) {
        super(postId, title, content, author, pubDate);
    }
}
