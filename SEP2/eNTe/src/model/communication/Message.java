package model.communication;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {

    private Map<Type, Object> data;

    public enum Type {
        Type, Login, Auth, ManageUser, NoType, CheckEmail, EmailStatus, ChangePwd, ManageFamily, Fail, ManagePost, Success, RequestUpdate, Update
    }

    private Message() {
        data = new HashMap<>();
    }

    public void put(Type key, Object value) {
        data.put(key, value);
    }

    public Object get(String key) {
        return data.get(key);
    }

    public EmailStatus getEmailStatus() {
        if (!data.get(Type.Type).equals(Type.EmailStatus))
            throw new IllegalStateException("Message does not contain email information");
        return (EmailStatus) data.get(Type.EmailStatus);
    }

    public Type getType() {
        return (Type) data.getOrDefault(Type.Type, Type.NoType);
    }

    public Login getLogin() throws NullPointerException, IllegalStateException {
        if (!data.get(Type.Type).equals(Type.Login))
            throw new IllegalStateException("Message does not contain login information");
        return (Login) data.get(Type.Login);
    }

    public Auth getAuth() {
        if (!data.get(Type.Type).equals(Type.Auth))
            throw new IllegalStateException("Message does not contain authentication information");
        return (Auth) data.get(Type.Auth);
    }

    public ManageUser getManageUser() {
        if (!data.get(Type.Type).equals(Type.ManageUser))
            throw new IllegalStateException("Message does not contain managing user information");
        return (ManageUser) data.get(Type.ManageUser);
    }

    public ManageFamily getManageFamily() {
        if (!data.get(Type.Type).equals(Type.ManageFamily))
            throw new IllegalStateException("Message does not contain managing family information");
        return (ManageFamily) data.get(Type.ManageFamily);
    }

    public ManagePost getManagePost() {
        if (!data.get(Type.Type).equals(Type.ManagePost))
            throw new IllegalStateException("Message does not contain managing post information");
        return (ManagePost) data.get(Type.ManagePost);
    }

    public String getEmail() {
        if (!data.get(Type.Type).equals(Type.CheckEmail))
            throw new IllegalStateException("Message does not contain email information");
        return (String) data.get(Type.CheckEmail);
    }

    public ChangePwd getChangePwd() {
        if (!data.get(Type.Type).equals(Type.ChangePwd))
            throw new IllegalStateException("Message does not contain email information");
        return (ChangePwd) data.get(Type.ChangePwd);
    }

    public Update getUpdate() {
        if (!data.get(Type.Type).equals(Type.Update))
            throw new IllegalStateException("Message does not contain update information");
        return (Update) data.get(Type.Update);
    }

    public static Message createChangePwdWithEmail(String email, String newPwd) {
        Message msg = new Message();
        msg.data.put(Type.Type, Type.ChangePwd);
        msg.data.put(Type.ChangePwd, new ChangePwd(email, newPwd));
        return msg;
    }

    public static Message createAuth(Auth auth) {
        Message msg = new Message();
        msg.data.put(Type.Type, Type.Auth);
        msg.data.put(Type.Auth, auth);
        return msg;
    }

    public static Message createLogin(Login login) {
        Message msg = new Message();
        msg.data.put(Type.Type, Type.Login);
        msg.data.put(Type.Login, login);
        return msg;
    }

    public static Message createMangeUser(ManageUser manageUser) {
        Message msg = new Message();
        msg.data.put(Type.Type, Type.ManageUser);
        msg.data.put(Type.ManageUser, manageUser);
        return msg;
    }

    public static Message createMangeFamily(ManageFamily manageFamily) {
        Message msg = new Message();
        msg.data.put(Type.Type, Type.ManageFamily);
        msg.data.put(Type.ManageFamily, manageFamily);
        return msg;
    }

    public static Message createMangePost(ManagePost managePost) {
        Message msg = new Message();
        msg.data.put(Type.Type, Type.ManagePost);
        msg.data.put(Type.ManagePost, managePost);
        return msg;
    }

    public static Message createFail() {
        Message msg = new Message();
        msg.data.put(Type.Type, Type.Fail);
        return msg;
    }

    public static Message createSuccessfulResponse() {
        Message msg = new Message();
        msg.data.put(Type.Type, Type.Success);
        return msg;
    }

    public static Message createCheckEmail(String email) {
        Message msg = new Message();
        msg.data.put(Type.Type, Type.CheckEmail);
        msg.data.put(Type.CheckEmail, email);
        return msg;
    }

    public static Message createEmailDoesNotExist() {
        Message msg = new Message();
        msg.data.put(Type.Type, Type.EmailStatus);
        msg.data.put(Type.EmailStatus, EmailStatus.NOT_EXIST);
        return msg;
    }

    public static Message createEmailExist() {
        Message msg = new Message();
        msg.data.put(Type.Type, Type.EmailStatus);
        msg.data.put(Type.EmailStatus, EmailStatus.EXIST);
        return msg;
    }

    public static Message createRequestUpdate() {
        Message msg = new Message();
        msg.data.put(Type.Type, Type.RequestUpdate);
        return msg;
    }

    public static Message createUpdate(WelcomingData data) {
        Message msg = new Message();
        msg.data.put(Type.Type, Type.Update);
        msg.data.put(Type.Update, new Update(data));
        return msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return data != null ? data.equals(message.data) : message.data == null;
    }

    @Override
    public String toString() {
        return "Message [data=" + data + "]";
    }

}
