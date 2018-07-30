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

    @Column(name = "homeworkid")
    private String homeworkid;

    @Column(name = "studentid")
    private String studentId;

    @Column(name = "content")
    private String content;

    @Column(name = "studentname")
    private String studentName;

    @Column(name = "studentclass")
    @Enumerated(EnumType.STRING)
    private ClassNo studentClass;

    @Column(name = "late")
    private boolean late;

    @Column(name = "handindate")
    @Type(type = "MyDateMapper")
    private MyDate handInDate;


    public HomeworkReply(){}

    public HomeworkReply(String content, Student student, boolean late, MyDate handInDate) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.late = late;
        this.handInDate = handInDate;
        studentId = student.getId();
        studentName = student.getName();
        studentClass = student.getClassNo();
    }

    public String getContent() {
        return content;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public ClassNo getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(ClassNo studentClass) {
        this.studentClass = studentClass;
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
                "id='" + id + '\'' +
                ", homeworkid='" + homeworkid + '\'' +
                ", studentId='" + studentId + '\'' +
                ", content='" + content + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentClass=" + studentClass +
                ", late=" + late +
                ", handInDate=" + handInDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HomeworkReply that = (HomeworkReply) o;

        if (late != that.late) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (homeworkid != null ? !homeworkid.equals(that.homeworkid) : that.homeworkid != null) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (studentName != null ? !studentName.equals(that.studentName) : that.studentName != null) return false;
        if (studentClass != that.studentClass) return false;
        return handInDate != null ? handInDate.equals(that.handInDate) : that.handInDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (homeworkid != null ? homeworkid.hashCode() : 0);
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (studentName != null ? studentName.hashCode() : 0);
        result = 31 * result + (studentClass != null ? studentClass.hashCode() : 0);
        result = 31 * result + (late ? 1 : 0);
        result = 31 * result + (handInDate != null ? handInDate.hashCode() : 0);
        return result;
    }
}
