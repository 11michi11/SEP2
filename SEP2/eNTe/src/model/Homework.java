package model;


import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.List;

public class Homework extends Post {

    @Transient
    public static final String noHomeworkId = "ThereIsNoHomework*******************";

    private MyDate deadline;
    private List<ClassNo> classes;
    private int numberOfStudentsToDeliver;
    private List<HomeworkReply> replies;
    private boolean closed;

    public Homework(String title, String content, String author, MyDate pubDate, MyDate deadline, List<ClassNo> classes, int numberOfStudentsToDeliver) {
        super(title, content, author, pubDate);
        this.deadline = deadline;
        this.classes = classes;
        this.numberOfStudentsToDeliver = numberOfStudentsToDeliver;
        replies = new LinkedList<>();
        closed = false;
    }

    public Homework(String postId, String title, String content, String author, MyDate pubDate, MyDate deadline, List<ClassNo> classes, int numberOfStudentsToDeliver, List<HomeworkReply> replies, boolean closed) {
        super(postId, title, content, author, pubDate);
        this.deadline = deadline;
        this.classes = classes;
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

    public List<ClassNo> getClasses() {
        return classes;
    }

    public String getClassesAsString() {
        String string = "{";
        for (ClassNo e:classes) {
          string += e.toString()+",";
        }
        string = string.substring(0,string.length()-1);
        string += "}";
        return string;
    }

    public int getNumberOfStudentsToDeliver() {
        return numberOfStudentsToDeliver;
    }

    public boolean isClosed() {
        return closed;
    }

    public HomeworkReply getStudentReply(String id) {
    	return replies.stream().filter(r -> r.getStudent().getId().equals(id)).findFirst().orElse(null);
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
        if (classes != null ? !classes.equals(homework.classes) : homework.classes != null) return false;
        return replies != null ? replies.equals(homework.replies) : homework.replies == null;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "deadline=" + deadline +
                ", classes=" + classes +
                ", numberOfStudentsToDeliver=" + numberOfStudentsToDeliver +
                ", replies=" + replies +
                ", closed=" + closed +
                "} " + super.toString();
    }
}
