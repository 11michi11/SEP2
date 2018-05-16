package model;

import java.io.Serializable;

public class Post implements Serializable {

    private String title;
    private String content;
    private String author;
    private MyDate pubDate;

    public Post(String title, String content, String author, MyDate pubDate) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Post) {
            Post other = (Post) obj;
            return content.equals(other.content) && title.equals(other.title);
        }
        return false;
    }

    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", pubDate=" + pubDate +
                '}';
    }
}
