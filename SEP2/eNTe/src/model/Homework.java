package model;


import java.util.ArrayList;

public class Homework extends Post {

    private MyDate deadline;
    private boolean submited;
    //private ArrayList<Student> homeworkDone;

    public Homework(String title, String content, String author, MyDate pubDate, MyDate deadline) {
        super(title, content, author, pubDate);
        this.deadline = deadline;
        this.submited = false;
    }

    public Homework(String postId, String title, String content, String author, MyDate pubDate) {
        super(postId, title, content, author, pubDate);
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

        if (submited != homework.submited) return false;
        return deadline != null ? deadline.equals(homework.deadline) : homework.deadline == null;
    }
}
