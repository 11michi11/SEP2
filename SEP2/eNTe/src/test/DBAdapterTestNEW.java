package test;

import jdk.nashorn.internal.ir.annotations.Ignore;
import model.*;
import org.junit.jupiter.api.*;
import server.model.persistance.DBAdapter;
import server.model.persistance.DBPersistence;
import utility.Password;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DBAdapterTestNEW {

    private static DBPersistence adapter;
    private static UsersList users;
    private static FamilyList families;
    private static PostsList posts;

    private void loadFamilies() {
        LinkedList<Family> list = adapter.getFamilies();
        FamilyList familiesTemp = new FamilyList();
        for (Family e:list) {
            familiesTemp.addFamily(e);
        }
        families = familiesTemp;
    }

    private void loadUsers(FamilyList families) {
        LinkedList<User> list = adapter.getUsers(families);
        UsersList usersTemp = new UsersList();
        for (User e:list) {
            usersTemp.add(e);
        }
        users = usersTemp;
    }

    private void loadPosts(UsersList users) {
        LinkedList<Post> list = adapter.getPosts(users);
        PostsList postsTemp = new PostsList();
        for (Post e:list) {
            postsTemp.add(e);
        }
        posts = postsTemp;
    }

    @BeforeAll
    static void setUp() {
        adapter = new DBAdapter("org.postgresql.Driver","jdbc:postgresql://207.154.237.196:5432/ente?currentSchema=test","ente","ente");
        families = new FamilyList();
        users = new UsersList();
        posts = new PostsList();
    }

    //----------Z-----------
    @Test
    void testCanBeInstantiated() {
        assertNotNull(adapter);
    }

    @Test
    void testEmptyFamilyListWhenInstantiated() {
        families = new FamilyList();
        assertEquals(0,families.getSize());
    }

    @Test
    void testNoFamilyLoadedBeforeInsert() {
        loadFamilies();
        assertEquals(0,families.getSize());
    }

    @Test
    void testEmptyUsersListWhenInstantiated() {
        users = new UsersList();
        assertEquals(0,users.getAll().size());
    }

    @Test
    void testNoUserLoadedBeforeInsert() {
        loadUsers(families);
        assertEquals(0,users.getAll().size());
    }

    @Test
    void testEmptyPostsListWhenInstantiated() {
        posts = new PostsList();
        assertEquals(0,posts.getAll().size());
    }

    @Test
    void testNoPostLoadedBeforeInsert() {
        loadPosts(users);
        assertEquals(0,posts.getAll().size());
    }

    //----------O-----------
    @Test
    void testOneFamilyAddedAndLoaded() {
        Family f1 = new Family("cee12240-3e76-406e-bf12-0d40488ed3b9");
        adapter.addFamily(f1);
        loadFamilies();
        assertEquals(1,families.getSize());
        assertEquals("cee12240-3e76-406e-bf12-0d40488ed3b9",families.getAllFamilies().get(0).getId());
    }

    @Test
    void testOneAdminAddedAndLoaded() {
        User admin = new Administrator("AdminName","AdminEmail","AdminPwd","b2c74531-49ea-4efe-9308-59d01f4792cb");
        admin.setChangePassword(true);
        adapter.addUser(admin);
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        assertEquals("b2c74531-49ea-4efe-9308-59d01f4792cb",users.getAll().get(0).getId());
        assertEquals("AdminName",users.getAll().get(0).getName());
        assertEquals("AdminEmail",users.getAll().get(0).getEmail());
        assertEquals("AdminPwd",users.getAll().get(0).getPwd());
        assertTrue(users.getAll().get(0).isPasswordChangeNeeded());
        assertEquals("Administrator",users.getAll().get(0).getClass().getSimpleName());
    }

    @Test
    void testOneTeacherAddedAndLoaded() {
        User teacher1 = new Teacher("TeacherName1","TeacherEmail1","TeacherPwd1","190edc14-b80c-484b-aa39-97d1012c1597");
        teacher1.setChangePassword(false);
        adapter.addUser(teacher1);
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        assertEquals("190edc14-b80c-484b-aa39-97d1012c1597",users.getAll().get(0).getId());
        assertEquals("TeacherName1",users.getAll().get(0).getName());
        assertEquals("TeacherEmail1",users.getAll().get(0).getEmail());
        assertEquals("TeacherPwd1",users.getAll().get(0).getPwd());
        assertFalse(users.getAll().get(0).isPasswordChangeNeeded());
        assertEquals("Teacher",users.getAll().get(0).getClass().getSimpleName());
    }

    @Test
    void testOneStudentAddedAndLoaded() {
        Family f1 = new Family("cee12240-3e76-406e-bf12-0d40488ed3b9");
        User student1 = Student.builder().name("StudentName1").email("StudentEmail1").classs(Classs.First).pwd("StudentPwd1").id("64e691e3-204f-45ee-8c5a-aefdffa1b3a5").family(f1).build();
        student1.setChangePassword(true);
        f1.addChild((Student) student1);
        adapter.addFamily(f1);
        loadFamilies();
        adapter.addUser(student1);
        loadUsers(families);
        System.out.println(student1.toString());
        assertEquals(1,users.getAll().size());
        assertEquals("64e691e3-204f-45ee-8c5a-aefdffa1b3a5",users.getAll().get(0).getId());
        assertEquals("StudentName1",users.getAll().get(0).getName());
        assertEquals("StudentEmail1",users.getAll().get(0).getEmail());
        assertEquals("StudentPwd1",users.getAll().get(0).getPwd());
        assertTrue(users.getAll().get(0).isPasswordChangeNeeded());
        assertEquals("Student",users.getAll().get(0).getClass().getSimpleName());
    }

    @AfterEach
    void tearDown() {
        ((DBAdapter) adapter).executeSQL("DELETE FROM enteuser");
        ((DBAdapter) adapter).executeSQL("DELETE FROM family");
        ((DBAdapter) adapter).executeSQL("DELETE FROM post");
    }
}