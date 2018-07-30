package model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "discussion", schema = "test")
@PrimaryKeyJoinColumn(name = "discussionid")
public class Discussion extends Post {

    public static final String noDiscussionId = "ThereIsNoDiscussion*****************";

    @OneToMany(mappedBy = "discussionid")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DiscussionComment> comments;

    public Discussion(){}

    public Discussion(String title, String content, String author, MyDate pubDate, SpecialType specialType, List<ClassNo> classes) {
        super(title, content, author, pubDate, specialType, classes);
        comments = new LinkedList<>();
    }
    public Discussion(String title, String content, String author, MyDate pubDate,List<DiscussionComment> comments, SpecialType specialType) {
        super(title, content, author, pubDate, specialType);
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
    public String toString() {
        return "Discussion{" +
                "comments=" + comments +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", pubDate=" + pubDate +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Discussion that = (Discussion) o;

        return comments != null ? comments.equals(that.comments) : that.comments == null;
    }

    @Override
    public int hashCode() {
        return comments != null ? comments.hashCode() : 0;
    }
}
