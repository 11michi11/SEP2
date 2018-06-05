package server.model;

import model.*;
import model.communication.Auth;
import model.communication.LoginStatus;
import server.model.persistance.DBAdapter;
import server.model.persistance.DBPersistence;
import server.model.persistance.Database;

import java.util.ArrayList;
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
        try {
            db = new DBAdapter(new Database("org.postgresql.Driver", "jdbc:postgresql://207.154.237.196:5432/ente", "ente", "ente"));
            restoreState();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database connection has not been established");
        }
        Thread dbSynchronize = new Thread(new SynchronizeDatabase());
        dbSynchronize.setDaemon(true);
        dbSynchronize.start();
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

    @Override
    public void addPost(Post post) {
        posts.add(post);
    }

    @Override
    public void deletePost(Post post) {
        posts.deletePost(post);
    }

    @Override
    public void editPost(Post post) {
        posts.editPost(post);
    }

    @Override
    public List<Post> getAllPost() {
        return posts.getAll();
    }

    @Override
    public void clear() {
        posts.clear();
        families.clear();
        users.clear();
    }

    @Override
    public List<User> getAllUsers() {
        return users.getAll();
    }

    private LinkedList<User> getUsers() {
        User user1 = Student.builder().name("student").email("student").classNo(ClassNo.First).pwdEncrypt("pwd").build();
        User administrator = Administrator.builder().name("name").email("email").pwdEncrypt("pwd").build();

        LinkedList<User> list = new LinkedList<>();
        Collections.addAll(list, administrator, user1);
        list.add(administrator);
        return list;
    }

    private LinkedList<Post> getPosts() {
        Post post = new Post("Lorem ipsum dolor",
                "This is a post about Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin mattis at dolor sed."
                , "Author", MyDate.now());

        LinkedList<Post> list = new LinkedList<>();
        list.add(post);
        list.addAll(getHomework());
        return list;
    }

    private LinkedList<Homework> getHomework() {
        MyDate submitDate = new MyDate(15, 12, 2018);
        List<ClassNo> classes = new ArrayList<>();
        classes.add(ClassNo.First);
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
                        + " Cras facilisis neque at scelerisque placerat.", "Author", MyDate.now(), submitDate, classes, 1);

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

    private void updateDb() {
        List<Family> fList = db.getFamilies();
        FamilyList familyList = new FamilyList();
        familyList.addAll(fList);
        List<List<Family>> famDiff = getFamilyDiff(fList);
        List<Family> familiesToAdd = famDiff.get(0);
        List<Family> familiesToUpdate = famDiff.get(1);

        List<User> uList = db.getUsers(familyList);
        UsersList usersList = new UsersList();
        usersList.addAll(uList);
        List<List<User>> usersDiff = getUserDiff(uList);
        List<User> usersToAdd = usersDiff.get(0);
        List<User> usersToUpdate = usersDiff.get(1);

        List<List<Post>> postsDiff = getPostDiff(db.getPosts(usersList));
        List<Post> postsToAdd = postsDiff.get(0);
        List<Post> postsToUpdate = postsDiff.get(1);

        updateFamilies(familiesToAdd, familiesToUpdate);
        updateUsers(usersToAdd, usersToUpdate);
        postsUsers(postsToAdd, postsToUpdate);
    }

    private List<List<Post>> getPostDiff(List<Post> dbPosts) {
        List<Post> toAdd = new LinkedList<>();
        List<Post> toUpdate = new LinkedList<>();

        for (Post post : posts.getAll()) {
            if (!dbPosts.contains(post)) {
                toAdd.add(post);
            } else {
                for (Post dbPost : dbPosts)
                    if (dbPost.getPostId().equals(post.getPostId())) {
                        toUpdate.add(post);
                        break;
                    }
            }
        }

        List<List<Post>> result = new LinkedList<>();
        result.add(toAdd);
        result.add(toUpdate);
        return result;
    }

    private List<List<User>> getUserDiff(List<User> dbUsers) {
        List<User> toAdd = new LinkedList<>();
        List<User> toUpdate = new LinkedList<>();

        for (User user : users.getAll()) {
            if (!dbUsers.contains(user)) {
                toAdd.add(user);
            } else {
                for (User dbUser : dbUsers)
                    if (dbUser.getId().equals(user.getId())) {
                        toUpdate.add(user);
                        break;
                    }
            }
        }

        List<List<User>> result = new LinkedList<>();
        result.add(toAdd);
        result.add(toUpdate);
        return result;
    }

    private List<List<Family>> getFamilyDiff(List<Family> dbFamilies) {
        List<Family> toAdd = new LinkedList<>();
        List<Family> toUpdate = new LinkedList<>();

        for (Family family : families.getAllFamilies()) {
            if (!dbFamilies.contains(family)) {
                toAdd.add(family);
            } else {
                for (Family dbFamily : dbFamilies)
                    if (dbFamily.getId().equals(family.getId())) {
                        toUpdate.add(family);
                        break;
                    }
            }
        }

        List<List<Family>> result = new LinkedList<>();
        result.add(toAdd);
        result.add(toUpdate);
        return result;
    }

    private void postsUsers(List<Post> postsToAdd, List<Post> postsToUpdate) {
        postsToAdd.forEach(post -> db.addPost(post));
        postsToUpdate.forEach(post -> db.updatePost(post));
    }

    private void updateUsers(List<User> usersToAdd, List<User> usersToUpdate) {
        usersToAdd.forEach(user -> db.addUser(user));
        usersToUpdate.forEach(user -> db.updateUser(user));
    }

    private void updateFamilies(List<Family> familiesToAdd, List<Family> familiesToUpdate) {
        familiesToAdd.forEach(family -> db.addFamily(family));
        // familiesToUpdate.forEach(family -> db.updateFamilies(family));
    }

    class SynchronizeDatabase implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000 * 60 * 60 * 12);
                } catch (InterruptedException e) {
                    return;
                }
                updateDb();
            }
        }
    }


}
