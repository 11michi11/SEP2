package model;


import java.util.ArrayList;
import java.util.List;

public class Homework extends Post {

    private MyDate deadline;
    private List <ClassNo> classes;
    private int numberOfStudentsToDeliver;
    private String extraInfo;
    //private List <HomeworkReply> replies;
    //private boolean submited;
    //private ArrayList<Student> homeworkDone;

    public Homework(String title, String content, String author,MyDate pubDate,  MyDate deadline, List <ClassNo> classes, int numberOfStudentsToDeliver) {
        super(title, content, author, pubDate);
        this.deadline = deadline;
        this.classes=classes;
        this.numberOfStudentsToDeliver=numberOfStudentsToDeliver;
        extraInfo="";
        //this.submited = false;
    }

    public Homework(String postId, String title, String content, String author,MyDate pubDate, MyDate deadline, List <ClassNo> classes, int numberOfStudentsToDeliver) {
        super(postId, title, content, author, pubDate);
        this.deadline = deadline;
        this.classes=classes;
        this.numberOfStudentsToDeliver=numberOfStudentsToDeliver;
        extraInfo="";
        //this.submited = false;
    }

    public MyDate getDeadline() {
        return deadline;
    }

    public String toString() {
        return "Homework{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", pubDate=" + pubDate +
                ", deadline=" + deadline +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Homework homework = (Homework) o;

        //if (submited != homework.submited) return false;
        return deadline != null ? deadline.equals(homework.deadline) : homework.deadline == null;
    }
}
