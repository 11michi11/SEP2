package model;

import model.communication.Auth;
import model.communication.LoginStatus;

import java.util.List;

public interface ServerModel {

	Post getPost();
	LoginStatus authenticate(Auth auth);
	void addUser(User user);
	void editUser(User user);
	void deleteUser(User user);
	List<Family> getAllFamilies();
	void deleteFamily(Family family);
	void addFamily(Family family);
    boolean checkIfEmailExist(String email);
    void changePwdWithEmail(String email, String newPwd);
    void editFamily(Family family);
    void addPost(Post post);
	void deletePost(Post post);
	void editPost(Post post);
	List<Post> getAllPost();
	void clear();
}
