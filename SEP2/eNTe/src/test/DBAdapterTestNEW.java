package test;

import model.FamilyList;
import model.PostsList;
import model.UsersList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.persistance.DBAdapter;
import server.model.persistance.DBPersistence;

import java.util.ArrayList;

class DBAdapterTestNEW {

    private static DBPersistence adapter;
    private ArrayList<Object[]> list;
    private UsersList users;
    private FamilyList families;
    private PostsList posts;

    /*
    Demo schema contains:
        1 Administrator
        2 Teachers
        4 Students
        4 Parents
        3 Families (1st family=1 student,1 parent;
                    2nd family=2 students,1 parent;
                    3rd family=1 student,2 parents)
        2 Posts of type Homework
        3 HomeworkReplies
     */

    @BeforeAll
    static void setUpAdapter() {
        adapter = new DBAdapter("org.postgresql.Driver", "jdbc:postgresql://207.154.237.196:5432/ente?currentSchema=demo", "ente", "ente");
    }

    @BeforeEach
    void setUp() {
        users = new UsersList();
        posts = new PostsList();
        families = new FamilyList();
        list = new ArrayList<>();
    }

    @Test
    void testGetPosts() {

    }

    @Test
    void testAddPost() {
    }

    @Test
    void testUpdatePost() {
    }

    @Test
    void testDeletePost() {
    }

    @Test
    void testGetUsers() {

    }

    @Test
    void testAddUser() {
    }

    @Test
    void testUpdateUser() {
    }

    @Test
    void testDeleteUser() {
    }

    @Test
    void testGetFamilies() {
        for (int i = 0;i<3;i++) {
            Object[] row = new Object[1];
            row[0] = "FamilyID"+(i+1);
            list.add(row);
        }

    }

    @Test
    void testAddFamily() {
    }

    @Test
    void testDeleteFamily() {
    }

    @Test
    void testExecuteSQL() {
    }
}