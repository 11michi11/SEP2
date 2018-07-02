package model;

import java.util.LinkedList;
import java.util.List;

public class Discussion extends Post {

    public static final String noDiscussionId = "ThereIsNoDiscussion*****************";
    private List<DiscussionComment> comments;

    public Discussion(String title, String content, String author, MyDate pubDate) {
        super(title, content, author, pubDate);
    }
    public Discussion(String title, String content, String author, MyDate pubDate,List<DiscussionComment> comments) {
        super(title, content, author, pubDate);
        this.comments = comments;
    }

    public Discussion(String postId, String title, String content, String author, MyDate pubDate) {
        super(postId, title, content, author, pubDate);
    }

    public void addComment(DiscussionComment comment) {
        comments.add(comment);
    }

    public List<DiscussionComment> getComments() {
        return new LinkedList<>(comments);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Discussion)
            return super.equals(o);
        return false;
    }


}
