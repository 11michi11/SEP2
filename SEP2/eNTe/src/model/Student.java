package model;

import java.io.Serializable;

public class Student extends User implements Serializable {

    private String historyOfActivity;
    private Classs classs;
    private Family family;

    //Student should be initialized with builder!
    //Student.builder()...

    private Student(String name, String email) {
        super(name, email);
    }

    public Student(String name, String email, String pwd, Classs classs, Family family) {
        super(name, email, pwd);
        initializeStudent(classs, family);
    }

    public Student(String name, String email, String pwd, String id, Classs classs, Family family) {
        super(name, email, pwd, id);
        initializeStudent(classs, family);
    }

    private void initializeStudent(Classs classs, Family family) {
        this.classs = classs;
        historyOfActivity = "";
        this.family = family;
    }

    public void addHistoryOfActivity(String text) {
        historyOfActivity += text + "\n";
    }

    public String getHistoryOfActivity() {
        return historyOfActivity;
    }

    public void setClasss(Classs classs) {
        this.classs = classs;
    }

    public Classs getClasss() {
        return classs;
    }

    public String getFamilyID() {
        return family.getId();
    }

    @Override
    public String toString() {
        return "Student [historyOfActivity=" + historyOfActivity + ", classs=" + classs + ", family=" + family + "]";
    }


    public static StudentNeedName builder() {
        return new Builder();
    }


    public static final class Builder implements StudentNeedName, StudentNeedEmail, StudentNeedClasss, StudentCanBeBuild {
        protected String id;
        private String historyOfActivity;
        private String email;
        private Classs classs;
        private String pwd;
        private Family family;
        private String name;

        public StudentNeedEmail name(String name) {
            this.name = name;
            return this;
        }

        public StudentNeedClasss email(String email) {
            this.email = email;
            return this;
        }

        public StudentCanBeBuild classs(Classs classs) {
            this.classs = classs;
            return this;
        }

        public StudentCanBeBuild pwd(String pwd) {
            this.pwd = pwd;
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
            if(this.pwd != null)
                student.setPwd(pwd);
            if (this.classs == null)
                throw new IllegalStateException("Classs must be specified");
            if (this.id != null)
                student.id = this.id;
            student.classs = this.classs;
            student.family = family;
            student.historyOfActivity = this.historyOfActivity;
            return student;
        }
    }

    public interface StudentNeedName {
        StudentNeedEmail name(String name);
    }

    public interface StudentNeedEmail {
        StudentNeedClasss email(String email);
    }

    public interface StudentNeedClasss {
        StudentCanBeBuild classs(Classs classs);
    }

    public interface StudentCanBeBuild {
        Student build();

        StudentCanBeBuild pwd(String pwd);

        StudentCanBeBuild family(Family family);

        StudentCanBeBuild historyOfActivity(String history);

        StudentCanBeBuild id(String id);
    }
}