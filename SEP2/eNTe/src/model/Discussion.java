package model;

public class Discussion extends Post {

    public Discussion(String title, String content, String author, MyDate pubDate) {
        super(title, content, author, pubDate);
    }

    public Discussion(String postId, String title, String content, String author, MyDate pubDate) {
        super(postId, title, content, author, pubDate);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Discussion)
            return super.equals(o);
        return false;
    }
}
