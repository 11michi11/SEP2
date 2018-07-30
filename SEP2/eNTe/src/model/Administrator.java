package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "admin", schema = "test")
@PrimaryKeyJoinColumn(name = "adminid")
public class Administrator extends User implements Serializable {

    protected Administrator(){}
    private Administrator(String name, String email) {
        super(name, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Administrator)
            return super.equals(obj);
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static AdminNeedName builder() {
        return new Builder();
    }

    public void updateAdminFields(Administrator newUser) {

    }

    public static final class Builder implements AdminNeedName, AdminNeedEmail, AdminCanBeBuild {

        protected String id;
        private String name;
        private String email;
        private String pwd;
        private boolean encryptPwd;


        @Override
        public AdminNeedEmail name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public AdminCanBeBuild email(String email) {
            this.email = email;
            return this;
        }

        @Override
        public AdminCanBeBuild pwd(String pwd) {
            this.pwd = pwd;
            return this;
        }

        @Override
        public AdminCanBeBuild pwdEncrypt(String pwd) {
            this.pwd = pwd;
            encryptPwd = true;
            return this;
        }

        @Override
        public AdminCanBeBuild id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public Administrator build() {
            Administrator administrator = new Administrator(this.name, this.email);
            if (this.pwd != null)
                if (encryptPwd)
                    administrator.setPwdAndEncrypt(pwd);
                else
                    administrator.setPwd(pwd);
                if(this.id != null)
                    administrator.id = this.id;
            return administrator;
        }
    }

    public interface AdminNeedName {
        AdminNeedEmail name(String name);
    }

    public interface AdminNeedEmail {
        AdminCanBeBuild email(String name);
    }

    public interface AdminCanBeBuild {
        Administrator build();

        AdminCanBeBuild pwd(String pwd);

        AdminCanBeBuild pwdEncrypt(String pwd);

        AdminCanBeBuild id(String id);
    }

}
