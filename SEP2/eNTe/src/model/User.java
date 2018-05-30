package model;

import utility.Password;
import utility.SendEmail;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class User implements Serializable {

    private String email;
    private String pwd;
    private String name;
    private boolean changePassword;
    protected String id;

    public User(String name, String email, String pwd) {
        this.name = name;
        this.email = email;
        this.pwd = Password.encryptPwd(pwd);
        id = UUID.randomUUID().toString();
        changePassword = false;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.pwd = Password.generateEntePassword();
        id = UUID.randomUUID().toString();
        changePassword = true;
        //Uncomment in real system - i don't want spam right now
        //SendEmail.sendPasswordEmail(email, pwd);
    }

    public User(String name, String email, String pwd, String id) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.id = id;
        changePassword = false;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public String getId() {
        return id;
    }

    public void setPwd(String pwd) {
        this.pwd = Password.encryptPwd(pwd);
        changePassword = false;
    }

    public void setPwdNoEncrypt(String pwd) {
        this.pwd = pwd;
        changePassword = false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void changePassword() {
        changePassword = true;
        SendEmail.sendPasswordEmail(email, pwd);
    }

    public void setChangePassword(boolean changePassword) {
        this.changePassword = changePassword;
    }

    public boolean isPasswordChangeNeeded() {
        return changePassword;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof User) {
//            User other = (User) obj;
//            return id.equals(other.id) && email.equals(other.email) && name.equals(other.name) && pwd.equals(other.pwd);
//        }
//        return false;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (changePassword != user.changePassword) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (pwd != null ? !pwd.equals(user.pwd) : user.pwd != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (changePassword ? 1 : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", pwd=" + pwd + ", name=" + name + ", id=" + id + "]";
    }


}
