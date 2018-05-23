package model;

import java.io.Serializable;

public class Student extends User implements Serializable, IFamily {

    private String historyOfActivity;
    private ClassNo classNo;
    private Family family;

    //Student should be initialized with builder!
    //Student.builder()...

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

    public String getFamilyId() {
        return (family != null) ? family.getId() : "there is no family";
    }

    @Override
    public Family getFamily(){
        return family;
    }

    @Override
    public void setFamily(Family family){
        this.family = family;
    }

    @Override
    public String toString() {
        return super.toString()+"\nStudent [historyOfActivity=" + historyOfActivity + ", classNo=" + classNo + ", family=" + family + "]";
    }

    public static StudentNeedName builder() {
        return new Builder();
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

        public StudentCanBeBuild classs(ClassNo classs) {
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
                    student.setPwd(pwd);
                else
                    student.setPwdNoEncrypt(pwd);
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
        StudentCanBeBuild classs(ClassNo classs);
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