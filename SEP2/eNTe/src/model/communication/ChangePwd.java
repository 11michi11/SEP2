package model.communication;

public class ChangePwd {


    public String email;
    public String newPwd;

    public ChangePwd(String email, String newPwd) {
        this.email = email;
        this.newPwd = newPwd;
    }
}
