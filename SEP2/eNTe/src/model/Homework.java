package model;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "homework", schema = "test")
@PrimaryKeyJoinColumn(name = "homeworkid")
public class Homework extends Post {

    @Transient
    public static final String noHomeworkId = "ThereIsNoHomework*******************";

    @Column(name = "deadline")
    @Type(type = "MyDateMapper")
    private MyDate deadline;

    @Column(name = "noofstudentstodeliver")
    private int numberOfStudentsToDeliver;

    @OneToMany( mappedBy = "homeworkid",  cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<HomeworkReply> replies;

    @Column(name = "closed")
    private boolean closed;

    public Homework(){}

    public Homework(String title, String content, String author, MyDate pubDate, MyDate deadline, List<ClassNo> classes, int numberOfStudentsToDeliver, SpecialType specialType) {
        super(title, content, author, pubDate, specialType, classes);
        this.deadline = deadline;
        this.numberOfStudentsToDeliver = numberOfStudentsToDeliver;
        replies = new LinkedList<>();
        closed = false;
    }

    public Homework(String postId, String title, String content, String author, MyDate pubDate, MyDate deadline, List<ClassNo> classes, int numberOfStudentsToDeliver, List<HomeworkReply> replies, boolean closed) {
        super(postId, title, content, author, pubDate, classes);
        this.deadline = deadline;
        this.numberOfStudentsToDeliver = numberOfStudentsToDeliver;
        this.closed = closed;
        this.replies = replies;
    }

    public void addHomeworkReply(HomeworkReply reply) {
        replies.add(reply);
    }

    public List<HomeworkReply> getReplies() {
        return new LinkedList<>(replies);
    }

    public MyDate getDeadline() {
        return deadline;
    }

    public int getNumberOfStudentsToDeliver() {
        return numberOfStudentsToDeliver;
    }

    public boolean isClosed() {
        return closed;
    }

    public HomeworkReply getStudentReply(String id) {

    	return replies.stream().filter(r -> r.getStudentId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Homework homework = (Homework) o;

        if (numberOfStudentsToDeliver != homework.numberOfStudentsToDeliver) return false;
        if (closed != homework.closed) return false;
        if (deadline != null ? !deadline.equals(homework.deadline) : homework.deadline != null) return false;
        return replies != null ? replies.equals(homework.replies) : homework.replies == null;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "deadline=" + deadline +
                ", numberOfStudentsToDeliver=" + numberOfStudentsToDeliver +
                ", replies=" + replies +
                ", closed=" + closed +
                "} " + super.toString();
    }
}
