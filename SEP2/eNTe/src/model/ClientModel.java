
package model;

import java.util.ArrayList;
import java.util.List;

import client.controller.ClientController;
import model.communication.WelcomingData;

public interface ClientModel {

    void setController(ClientController controller);

    void addPost(Post post);

    void editPost(Post post);

    void deletePost(Post post);

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

	void addCommentToDiscussion(DiscussionComment comment);

    void requestUpdate();
}
