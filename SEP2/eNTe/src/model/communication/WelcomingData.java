package model.communication;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Family;
import model.FamilyList;
import model.Post;
import model.User;

public class WelcomingData implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Post> posts;
    private List<Family> families;
    private List<User> users;
   // private List<ChatMsg> unreadMessages;


    public WelcomingData() {
        posts = new LinkedList<>();
       // unreadMessages = new LinkedList<>();
        families = new LinkedList<>();
        users = new LinkedList<>();
    }

    public List<Post> getPosts() {
        return posts;
    }

//    public List<ChatMsg> getUnreadMessages() {
//        return unreadMessages;
//    }

    public List<Family> getFamilies() {
        return families;
    }

    public List<User> getUsers() {
        return users;
    }

    public void insertPosts(List<Post> list) {
        posts.addAll(list);
    }

    public void insertUsers(List<User> list) {
        users.addAll(list);
    }

    public void insertFamilies(List<Family> list) {
        families.addAll(list);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WelcomingData that = (WelcomingData) o;

        if (posts != null ? !posts.equals(that.posts) : that.posts != null) return false;
        if (families != null ? !families.equals(that.families) : that.families != null) return false;
        return users != null ? users.equals(that.users) : that.users == null;
    }

    @Override
    public String toString() {
        return "WelcomingData{" +
                "posts=" + posts +
                ", families=" + families +
                ", users=" + users +
                '}';
    }
}
