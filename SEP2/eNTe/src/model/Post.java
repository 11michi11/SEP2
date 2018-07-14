package model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Post implements Serializable {

    protected String title;
    protected String content;
    protected String author;
    protected MyDate pubDate;
    private SpecialType specialType;
    private String postId;
    private List<ClassNo> classes;

    public Post(String title, String content, String author, MyDate pubDate) {
        this.postId = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.author = author;
        this.pubDate = pubDate;
    }

    public Post(String title, String content, String author, MyDate pubDate, SpecialType specialType, List<ClassNo> classes) {
        this.postId = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.author = author;
        this.pubDate = pubDate;
        this.specialType = specialType;
        this.classes = classes;
    }
    public Post(String postId, String title, String content, String author, MyDate pubDate) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public MyDate getPubDate() {
        return pubDate;
    }

    public String getPostId() {
        return postId;
    }

    public SpecialType getSpecialType() { return specialType; }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", pubDate=" + pubDate +
                ", postId='" + postId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;
        if (author != null ? !author.equals(post.author) : post.author != null) return false;
        if (pubDate != null ? !pubDate.equals(post.pubDate) : post.pubDate != null) return false;
        return postId != null ? postId.equals(post.postId) : post.postId == null;
    }

}
