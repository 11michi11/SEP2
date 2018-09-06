package model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "announcement", schema = "test")
@PrimaryKeyJoinColumn(name = "announcementid")
public class Announcement extends Post{

    @Transient
    public static final String noAnnouncementId = "ThereIsNoAnnouncement*******************";

    @Column(name = "expirationdate")
    @Type(type = "MyDateMapper")
    private MyDate expirationDate;

    public Announcement(){}

    public Announcement(String title, String content, String author, MyDate pubDate, SpecialType specialType, List<ClassNo> classes, MyDate expirationDate) {
        super(title, content, author, pubDate, specialType, classes);
        this.expirationDate = expirationDate;
    }

    public Announcement(String postId, String title, String content, String author, MyDate pubDate,List<ClassNo>  classes) {
        super(postId, title, content, author, pubDate, classes);
    }

    public MyDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(MyDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "expirationDate=" + expirationDate +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", pubDate=" + pubDate +
                "} "+super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Announcement)) return false;
        if (!super.equals(o)) return false;

        Announcement that = (Announcement) o;

        return expirationDate != null ? expirationDate.equals(that.expirationDate) : that.expirationDate == null;
    }

    @Override
    public int hashCode() {
        return expirationDate != null ? expirationDate.hashCode() : 0;
    }
}
