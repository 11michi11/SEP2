package model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "post", schema = "test")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Post implements Serializable {

    @Id
    @Column(name = "postid")
    private String postId;

    @Column(name = "title")
    protected String title;

    @Column(name = "content")
    protected String content;

    @Column(name = "authorname")
    protected String author;

    @Column(name = "pubdate")
    @Type(type = "MyDateMapper")
    protected MyDate pubDate;

    @Enumerated(EnumType.STRING)
    private SpecialType specialType;

    @Column(name = "classes")
    @Type(type = "ClassListMapper")
    private List<ClassNo> classes;

    protected Post() {
    }

    public Post(String title, String content, String author, MyDate pubDate) {
        this.postId = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.author = author;
        this.pubDate = pubDate;
        this.specialType = SpecialType.NORMAL;
    }

    public Post(String title, String content, String author, MyDate pubDate, SpecialType specialType) {
        this.postId = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.author = author;
        this.pubDate = pubDate;
        this.specialType = specialType;
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

    public Post(String postId, String title, String content, String author, MyDate pubDate,  List<ClassNo> classes) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.pubDate = pubDate;
        this.classes = classes;
        specialType = SpecialType.NORMAL;
    }

    public String getTitle() {
        return title;
    }

    public SpecialType getSpecialType() {
        return specialType;
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

    public List<ClassNo> getClasses() {return classes;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getClassesAsString() {
        StringBuilder string = new StringBuilder("{");
        for (ClassNo e:classes) {
            string.append(e.toString()).append(",");
        }
        string = new StringBuilder(string.substring(0, string.length() - 1));
        string.append("}");
        return string.toString();
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", title='" + title + '\'' +
                ", classes=" + classes +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", pubDate=" + pubDate +
                ", specialType=" + specialType +
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
        if (classes != null ? !classes.equals(post.classes) : post.classes != null) return false;
        if (pubDate != null ? !pubDate.equals(post.pubDate) : post.pubDate != null) return false;
        return postId != null ? postId.equals(post.postId) : post.postId == null;
    }

}
