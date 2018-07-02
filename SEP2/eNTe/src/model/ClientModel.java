
package model;

import java.util.ArrayList;
import java.util.List;

import client.controller.ClientController;
import model.communication.WelcomingData;

public interface ClientModel {

    void setController(ClientController controller);

    void addPost(Post post);

    Post getPost();

    void editPost(Post post);

    void deletePost(Post post);

    Homework getHomework();
   	Discussion getDiscussion();

    void saveData(WelcomingData data);

    void addOrUpdateUser(User user);

    void deleteUser(String id);

    void deleteUser(User user);

    void login(String email, String pwd);

    ArrayList<Parent> getParents();

    void addFamily(Family family);

    ArrayList<Family> getAllFamilies();

    void deleteFamily(Family family);

    List<Teacher> getTeachers();

    boolean checkEmailForPwdReset(String email);

    void changePwdWithEmail(String email, String newPwd);

    ArrayList<Post> getAllPosts();

    User getUserById(String id);
}
