package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student", schema = "test")
@PrimaryKeyJoinColumn(name = "studentid")
public class Student extends User implements Serializable, IFamily {


    @Transient
    private String historyOfActivity;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "class")
    private ClassNo classNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "familyid")
    private Family family;

    protected Student() {
    }

    private Student(String name, String email) {
        super(name, email);
    }

    public void addHistoryOfActivity(String text) {
        historyOfActivity += text + "\n";
    }

    public String getHistoryOfActivity() {
        return historyOfActivity;
    }

    public void setClassNo(ClassNo classNo) {
        this.classNo = classNo;
    }

    public ClassNo getClassNo() {
        return classNo;
    }

    @Override
    public String getFamilyId() {
        return (family != null) ? family.getId() : "there is no family";
    }

    @Override
    public Family getFamily() {
        return family;
    }

    @Override
    public void setFamily(Family family) {
        this.family = family;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Student student = (Student) o;

        if (historyOfActivity != null ? !historyOfActivity.equals(student.historyOfActivity) : student.historyOfActivity != null)
            return false;
        if (classNo != student.classNo) return false;
        return family != null ? family.equals(student.family) : student.family == null;
    }

    @Override
    public String toString() {
        return super.toString() + "\nStudent [historyOfActivity=" + historyOfActivity + ", classNo=" + classNo + ", family=" + family + "]";
    }

    public static StudentNeedName builder() {
        return new Builder();
    }

    public void updateStudentFields(Student newUser) {
        historyOfActivity = newUser.historyOfActivity;
        classNo = newUser.classNo;
        family = newUser.family;
    }

    public static final class Builder implements StudentNeedName, StudentNeedEmail, StudentNeedClassNo, StudentCanBeBuild {
        protected String id;
        private String historyOfActivity;
        private String email;
        private ClassNo classNo;
        private String pwd;
        private Family family;
        private String name;
        private boolean encryptPwd;

        public StudentNeedEmail name(String name) {
            this.name = name;
            return this;
        }

        public StudentNeedClassNo email(String email) {
            this.email = email;
            return this;
        }

        public StudentCanBeBuild classNo(ClassNo classs) {
            this.classNo = classs;
            return this;
        }

        public StudentCanBeBuild pwd(String pwd) {
            this.pwd = pwd;
            return this;
        }

        @Override
        public StudentCanBeBuild pwdEncrypt(String pwd) {
            this.pwd = pwd;
            encryptPwd = true;
            return this;
        }

        public StudentCanBeBuild historyOfActivity(String historyOfActivity) {
            this.historyOfActivity = historyOfActivity;
            return this;
        }

        public StudentCanBeBuild family(Family family) {
            this.family = family;
            return this;
        }

        public StudentCanBeBuild id(String id) {
            this.id = id;
            return this;
        }

        public Student build() {
            Student student = new Student(name, email);
            if (this.pwd != null)
                if (encryptPwd)
                    student.setPwdAndEncrypt(pwd);
                else
                    student.setPwd(pwd);
            if (this.classNo == null)
                throw new IllegalStateException("Classs must be specified");
            if (this.id != null)
                student.id = this.id;
            student.classNo = this.classNo;
            student.family = family;
            student.historyOfActivity = this.historyOfActivity;
            return student;
        }
    }

    public interface StudentNeedName {
        StudentNeedEmail name(String name);
    }

    public interface StudentNeedEmail {
        StudentNeedClassNo email(String email);
    }

    public interface StudentNeedClassNo {
        StudentCanBeBuild classNo(ClassNo classs);
    }

    public interface StudentCanBeBuild {
        Student build();

        StudentCanBeBuild pwd(String pwd);

        StudentCanBeBuild pwdEncrypt(String pwd);

        StudentCanBeBuild family(Family family);

        StudentCanBeBuild historyOfActivity(String history);

        StudentCanBeBuild id(String id);
    }
}