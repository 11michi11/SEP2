package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "post", schema = "test")
@Inheritance(strategy = InheritanceType.JOINED)
public class Post implements Serializable {

    @Column(name = "title")
    protected String title;

    @Column(name = "content")
    protected String content;

    @Column(name = "authorname")
    protected String author;

    @Transient
    protected MyDate pubDate;

    private Date pubDateDB;

    public SpecialType getSpecialType() {
        return specialType;
    }

    //@Enumerated(value = EnumType.STRING)
    @Transient
    private SpecialType specialType;

    @Id
    @Column(name = "postid")
    private String postId;
    private List<ClassNo> classes;

    protected Post() {
    }

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


    @Temporal(TemporalType.TIMESTAMP)
    @Access(AccessType.PROPERTY)
    @Column(name = "pubdate")
    public Date getPubDateDB() {
        return pubDateDB;
    }

    public MyDate getPubDate() {
        return pubDate;
    }

    public String getPostId() {
        return postId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPubDateDB(Date pubDate) {
        this.pubDateDB = pubDate;
        this.pubDate = new MyDate(pubDate);
    }

    public void setPubDate(MyDate pubDate) {
        this.pubDate = pubDate;
    }

    public void setSpecialType(SpecialType specialType) {
        this.specialType = specialType;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

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
