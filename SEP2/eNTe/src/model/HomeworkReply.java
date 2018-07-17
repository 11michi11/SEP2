package model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "homeworkreply", schema = "test")
public class HomeworkReply implements Serializable {

    @Id @Column(name = "replyid")
    private String id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentid")
    private Student student;

    @Column(name = "late")
    private boolean late;

    @Column(name = "pubdate")
    @Type(type = "MyDateMapper")
    private MyDate handInDate;

    public HomeworkReply(){}

    public HomeworkReply(String content, Student student, boolean late, MyDate handInDate) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.student = student;
        this.late = late;
        this.handInDate = handInDate;
    }

    public String getContent() {
        return content;
    }

    public Student getStudent() {
        return student;
    }

    public boolean isLate() {
        return late;
    }

    public MyDate getHandInDateObj() {
        return handInDate;
    }

    public String getHandInDate() {
        return handInDate.toString();
    }

    @Override
    public String toString() {
        return "HomeworkReply{" +
                "content='" + content + '\'' +
                ", student=" + student +
                ", late=" + late +
                ", handInDate=" + handInDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HomeworkReply)) return false;

        HomeworkReply that = (HomeworkReply) o;

        if (late != that.late) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (student != null ? !student.equals(that.student) : that.student != null) return false;
        return handInDate != null ? handInDate.equals(that.handInDate) : that.handInDate == null;
    }
}
