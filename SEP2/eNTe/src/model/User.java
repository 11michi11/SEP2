package model;

import utility.Password;
import utility.SendEmail;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "enteuser", schema = "test")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements Serializable {

    @Column(name = "email")
    private String email;
    @Column(name = "pwd")
    private String pwd;
    @Column(name = "name")
    private String name;
    @Column(name = "changepassword")
    private boolean changePassword;
    @Id @Column(name = "id")
    protected String id;

    public User(String name, String email, String pwd) {
        this.name = name;
        this.email = email;
        this.pwd = Password.encryptPwd(pwd);
        id = UUID.randomUUID().toString();
        changePassword = false;
    }

    public User(){}
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

    public void setPadAndEncrypt(String pwd) {
        this.pwd = Password.encryptPwd(pwd);
        changePassword = false;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
        changePassword = false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
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

    void updateUserFields(User newUser) {
        email = newUser.email;
        pwd = newUser.pwd;
        name = newUser.name;
        changePassword = newUser.changePassword;
    }

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
    public String toString() {
        return "User [email=" + email + ", pwd=" + pwd + ", name=" + name + ", id=" + id + "]";
    }

}
