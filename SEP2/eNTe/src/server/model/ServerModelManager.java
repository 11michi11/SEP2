package server.model;

import model.*;
import model.communication.Auth;
import model.communication.LoginStatus;
import server.model.persistance.DBAdapter;
import server.model.persistance.DBPersistence;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ServerModelManager implements ServerModel {

    private PostsList posts;
    private UsersList users;
    private FamilyList families;
    private DBPersistence db;

    public ServerModelManager() {
        posts = new PostsList();
        users = new UsersList();
        families = new FamilyList();
        db = new DBAdapter("org.postgresql.Driver", "jdbc:postgresql://207.154.237.196:5432/ente", "ente", "ente");

        restoreState();
    }

    @Override
    public Post getPost() {
        return posts.getFirstPost();
    }

    @Override
    public LoginStatus authenticate(Auth auth) {
        return users.authenticate(auth);
    }

    private void restoreState() {
        families.addAll(db.getFamilies());
        users.addAll(db.getUsers(families));
        posts.addAll(db.getPosts(users));
        users.addAll(getUsers());
        posts.addAll(getPosts());
    }

    @Override
    public void addUser(User user) {
        users.add(user);
        //  db.addUser(user);
    }

    @Override
    public void editUser(User user) {
        users.updateUser(user);
        db.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        users.delete(user.getId());
        db.deleteUser(user.getId());
    }

    @Override
    public List<Family> getAllFamilies() {
        return families.getAllFamilies();
    }

    @Override
    public void deleteFamily(Family family) {
        families.deleteFamily(family);
    }

    @Override
    public void addFamily(Family family) {
        families.addFamily(family);
    }

    @Override
    public boolean checkIfEmailExist(String email) {
        return users.checkIfEmailExist(email);
    }

    @Override
    public void changePwdWithEmail(String email, String newPwd) {
        User user = users.getUserByEmail(email);
        user.setPwd(newPwd);
        user.changePassword();
    }

    @Override
    public void editFamily(Family family) {
        families.update(family);
    }

    private LinkedList<User> getUsers() {
        User user = new Administrator("name", "login", "pwd");
        User user1 = Student.builder().name("student").email("student").classs(ClassNo.First).pwdEncrypt("pwd").build();
        User administrator = Administrator.builder().name("name").email("email").pwdEncrypt("pwd").build();

        LinkedList<User> list = new LinkedList<>();
        Collections.addAll(list, user, user1);
        list.add(administrator);
        return list;
    }

    private LinkedList<Post> getPosts() {
        Post post = new Post("Lorem ipsum dolor",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                        + "Proin mattis at dolor sed aliquam. Nulla facilisi. "
                        + "Maecenas sodales urna quis risus sollicitudin, "
                        + "eget posuere neque aliquet. Nulla lacinia maximus "
                        + "risus non elementum. Donec egestas sit amet lacus vitae"
                        + " efficitur. Nulla ac mauris in turpis condimentum tincidunt "
                        + "sed id metus. Cras vel lectus rutrum, interdum tellus non,"
                        + " venenatis eros. Etiam posuere tempus est non maximus."
                        + " Pellentesque diam tortor, fringilla eget cursus pretium,"
                        + " dictum posuere dolor. Donec non eros commodo," + " ultrices risus sed, fermentum dolor."
                        + " Cras facilisis neque at scelerisque placerat.", "Author", MyDate.now());

        LinkedList<Post> list = new LinkedList<>();
        list.add(post);
        return list;
    }
    private LinkedList<Homework> getHomework() {
    	MyDate submitDate = new MyDate(15, 12, 2018);
        Homework homework = new Homework("HOMEWORK Lorem ipsum dolor",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                        + "Proin mattis at dolor sed aliquam. Nulla facilisi. "
                        + "Maecenas sodales urna quis risus sollicitudin, "
                        + "eget posuere neque aliquet. Nulla lacinia maximus "
                        + "risus non elementum. Donec egestas sit amet lacus vitae"
                        + " efficitur. Nulla ac mauris in turpis condimentum tincidunt "
                        + "sed id metus. Cras vel lectus rutrum, interdum tellus non,"
                        + " venenatis eros. Etiam posuere tempus est non maximus."
                        + " Pellentesque diam tortor, fringilla eget cursus pretium,"
                        + " dictum posuere dolor. Donec non eros commodo," + " ultrices risus sed, fermentum dolor."
                        + " Cras facilisis neque at scelerisque placerat.", "Author", MyDate.now(), submitDate);

        LinkedList<Homework> list = new LinkedList<>();
        list.add(homework);
        return list;
    }
    
    private LinkedList<Discussion> getDiscussion() {
        Discussion discussion = new Discussion("HOMEWORK Lorem ipsum dolor",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                        + "Proin mattis at dolor sed aliquam. Nulla facilisi. "
                        + "Maecenas sodales urna quis risus sollicitudin, "
                        + "eget posuere neque aliquet. Nulla lacinia maximus "
                        + "risus non elementum. Donec egestas sit amet lacus vitae"
                        + " efficitur. Nulla ac mauris in turpis condimentum tincidunt "
                        + "sed id metus. Cras vel lectus rutrum, interdum tellus non,"
                        + " venenatis eros. Etiam posuere tempus est non maximus."
                        + " Pellentesque diam tortor, fringilla eget cursus pretium,"
                        + " dictum posuere dolor. Donec non eros commodo," + " ultrices risus sed, fermentum dolor."
                        + " Cras facilisis neque at scelerisque placerat.", "Author", MyDate.now());

        LinkedList<Discussion> list = new LinkedList<>();
        list.add(discussion);
        return list;
    }

}
