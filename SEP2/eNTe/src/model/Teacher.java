package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "teacher", schema = "test")
@PrimaryKeyJoinColumn(name = "teacherid")
public class Teacher extends User implements Serializable {

    protected Teacher(){}
    private Teacher(String name, String email) {
        super(name, email);
    }

    public static TeacherNeedName builder() {
        return new Builder();
    }

    void updateTeacherFields(Teacher newUser) {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Teacher)
            return super.equals(obj);
        return false;
    }

    public static final class Builder implements TeacherNeedName, TeacherNeedEmail, TeacherCanBeBuild {


        protected String id;
        private String name;
        private String email;
        private String pwd;

        private boolean encryptPwd;

        @Override
        public TeacherNeedEmail name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public TeacherCanBeBuild email(String email) {
            this.email = email;
            return this;
        }

        @Override
        public TeacherCanBeBuild pwd(String pwd) {
            this.pwd = pwd;
            return this;
        }

        @Override
        public TeacherCanBeBuild pwdEncrypt(String pwd) {
            this.pwd = pwd;
            encryptPwd = true;
            return this;
        }

        @Override
        public TeacherCanBeBuild id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public Teacher build() {
            Teacher teacher = new Teacher(this.name, this.email);
            if (this.pwd != null)
                if (encryptPwd)
                    teacher.setPadAndEncrypt(pwd);
                else
                    teacher.setPwd(pwd);
            if (this.id != null)
                teacher.id = this.id;
            return teacher;
        }
    }

    public interface TeacherNeedName {
        TeacherNeedEmail name(String name);
    }

    public interface TeacherNeedEmail {
        TeacherCanBeBuild email(String name);
    }

    public interface TeacherCanBeBuild {
        Teacher build();

        TeacherCanBeBuild pwd(String pwd);

        TeacherCanBeBuild pwdEncrypt(String pwd);

        TeacherCanBeBuild id(String id);
    }
}
