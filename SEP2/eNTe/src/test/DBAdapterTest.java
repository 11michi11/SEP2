package test;

import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import server.model.persistance.DBAdapter;
import server.model.persistance.DBPersistence;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DBAdapterTest {

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

    private void loadPosts() {
        LinkedList<Post> list = adapter.getPosts(users);
        PostsList postsTemp = new PostsList();
        for (Post e:list) {
            postsTemp.add(e);
        }
        posts = postsTemp;
    }

    //-------------POST ISSSUES NEEED TO BE REPAIRED-----------------------
    // foreign key from post to enteuser temporarily deleted
    @BeforeAll
    static void setUp() {
        adapter = new DBAdapter("org.postgresql.Driver","jdbc:postgresql://207.154.237.196:5432/ente?currentSchema=test","ente","ente");
        families = new FamilyList();
        users = new UsersList();
        posts = new PostsList();
    }

    @Test
    void testGetPosts() {
//        assertTrue(false);
//        User teacher1 = Teacher.builder().name("TeacherName1").email("TeacherEmail1").pwd("TeacherPwd1").id("21bfea93-0c98-4490-a7bf-ad7878991bbc").build();
//        User teacher2 = Teacher.builder().name("TeacherName2").email("TeacherEmail2").pwd("TeacherPwd2").id("9d5ff8a7-77ba-49ff-ac0d-7b48978987f1").build();
//        teacher1.setChangePassword(false);
//        teacher2.setChangePassword(true);
//        adapter.addUser(teacher1);
//        adapter.addUser(teacher2);
//        loadUsers(families);
//        loadPosts(users);
//        assertEquals(2,posts.getAll().size());
//        assertEquals("af7e04fa-4b5e-424a-8637-0b2be1250cc9",posts.getAll().get(0).getPostId());
//        assertEquals("Lessons cancelled",posts.getAll().get(0).getTitle());
//        assertEquals("All lessons are cancelled tommorow (20.3.2018) due to school reconstruction.",posts.getAll().get(0).getContent());
//        assertEquals("TeacherName2",posts.getAll().get(0).getAuthor());
//        MyDate date1 = new MyDate(2018,3,18);
//        assertEquals(date1,posts.getAll().get(0).getPubDate());
//
//        assertEquals("c0ccc398-29ae-4f7e-ac61-e294fa8d0583",posts.getAll().get(1).getPostId());
//        assertEquals("End of school year",posts.getAll().get(1).getTitle());
//        assertEquals("Dear pupils and parents, Friday 30.06.2018 is the last day, so don't forget to bring some flowers or dark chocolates for your lovely teachers.",posts.getAll().get(1).getContent());
//        assertEquals("TeacherName1",posts.getAll().get(1).getAuthor());
//        MyDate date2 = new MyDate(2018,3,10);
//        assertEquals(date2,posts.getAll().get(1).getPubDate());
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
        loadPosts();
        assertEquals(0,posts.getAll().size());
    }

    //----------O-----------
        //adding and loading
    @Test
    void testOneFamilyAddedAndLoaded() {
        Family f1 = new Family("cee12240-3e76-406e-bf12-0d40488ed3b9");
        adapter.addFamily(f1);
        loadFamilies();
        assertEquals(1,families.getSize());
        assertEquals(f1,families.getAllFamilies().get(0));
    }

    @Test
    void testOneAdminAddedAndLoaded() {
        User admin = Administrator.builder().name("AdminName").email("AdminEmail").pwd("AdminPwd").id("b2c74531-49ea-4efe-9308-59d01f4792cb").build();
        admin.setChangePassword(true);
        adapter.addUser(admin);
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        assertEquals(admin,users.getAll().get(0));
//        assertEquals("b2c74531-49ea-4efe-9308-59d01f4792cb",users.getAll().get(0).getId());
//        assertEquals("AdminName",users.getAll().get(0).getName());
//        assertEquals("AdminEmail",users.getAll().get(0).getEmail());
//        assertEquals("AdminPwd",users.getAll().get(0).getPwd());
//        assertTrue(users.getAll().get(0).isPasswordChangeNeeded());
//        assertEquals("Administrator",users.getAll().get(0).getClass().getSimpleName());
    }

    @Test
    void testOneTeacherAddedAndLoaded() {
        User teacher1 = Teacher.builder().name("TeacherName1").email("TeacherEmail1").pwd("TeacherPwd1").id("190edc14-b80c-484b-aa39-97d1012c1597").build();
        teacher1.setChangePassword(false);
        adapter.addUser(teacher1);
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        assertEquals(teacher1,users.getAll().get(0));
    }

    @Test
    void testOneStudentAddedAndLoaded() {
        Family f1 = new Family("cee12240-3e76-406e-bf12-0d40488ed3b9");
        User student1 = Student.builder().name("StudentName1").email("StudentEmail1").classNo(ClassNo.First).pwd("StudentPwd1").id("64e691e3-204f-45ee-8c5a-aefdffa1b3a5").family(f1).build();
        student1.setChangePassword(true);
        f1.addChild((Student) student1);
        adapter.addFamily(f1);
        loadFamilies();
        adapter.addUser(student1);
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        assertEquals(student1,users.getAll().get(0));

//        assertEquals("64e691e3-204f-45ee-8c5a-aefdffa1b3a5",users.getAll().get(0).getId());
//        assertEquals("StudentName1",users.getAll().get(0).getName());
//        assertEquals("StudentEmail1",users.getAll().get(0).getEmail());
//        assertEquals("StudentPwd1",users.getAll().get(0).getPwd());
//        assertTrue(users.getAll().get(0).isPasswordChangeNeeded());
//        assertEquals(ClassNo.First,((Student) users.getAll().get(0)).getClassNo());
//        assertEquals("Student",users.getAll().get(0).getClass().getSimpleName());
//        assertEquals("cee12240-3e76-406e-bf12-0d40488ed3b9",((Student) users.getUserById("64e691e3-204f-45ee-8c5a-aefdffa1b3a5")).getFamilyId());
//        assertEquals("64e691e3-204f-45ee-8c5a-aefdffa1b3a5",families.getFamilyById("cee12240-3e76-406e-bf12-0d40488ed3b9").getChild("StudentName1").getId());
    }

    @Test
    void testOneParentAddedAndLoaded() {
        Family f1 = new Family("cee12240-3e76-406e-bf12-0d40488ed3b9");
        User parent1 = Parent.builder().name("ParentName1").email("ParentEmail1").pwd("ParentPwd1").id("adc8ba24-7250-425e-a0c9-00e144bbf75c").family(f1).build();
        parent1.setChangePassword(true);
        f1.addParent((Parent) parent1);
        adapter.addFamily(f1);
        adapter.addUser(parent1);
        loadFamilies();
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        assertEquals(parent1,users.getAll().get(0));
    }

    @Test
    void testOneHomeworkAddedAndLoaded() {
        ArrayList<ClassNo> classes = new ArrayList<>();
        classes.add(ClassNo.First);
        classes.add(ClassNo.Second);
        classes.add(ClassNo.Fourth);
        classes.add(ClassNo.Eighth);
        Post post = new Homework("cee12240-3e76-406e-bf12-0d40488ed3b9","Title","Content","Phill",new MyDate(2018,5,5,0,0),new MyDate(2018,10,10,10,0),classes,5,null,false);
        adapter.addPost(post);
        loadPosts();

        assertEquals(1,posts.getAll().size());
        assertEquals(post,posts.getAll().get(0));
    }

        //updating
    @Test
    void testUpdateAdmin() {
        User admin = Administrator.builder().name("AdminName").email("AdminEmail").pwd("AdminPwd").id("b2c74531-49ea-4efe-9308-59d01f4792cb").build();
        admin.setChangePassword(true);
        adapter.addUser(admin);
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        assertEquals(admin,users.getAll().get(0));

        users.getAll().get(0).setPwdNoEncrypt("AdminPwdNEW");
        admin.setPwdNoEncrypt("AdminPwdNEW");
        users.getAll().get(0).setChangePassword(false);
        admin.setChangePassword(false);
        adapter.updateUser(users.getAll().get(0));
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        assertEquals(admin,users.getAll().get(0));
    }

    @Test
    void testUpdateTeacher() {
        User teacher1 = Teacher.builder().name("TeacherName1").email("TeacherEmail1").pwd("TeacherPwd1").id("190edc14-b80c-484b-aa39-97d1012c1597").build();
        teacher1.setChangePassword(false);
        adapter.addUser(teacher1);
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        assertEquals(teacher1,users.getAll().get(0));

        users.getAll().get(0).setPwdNoEncrypt("TeacherPwd1NEW");
        users.getAll().get(0).setChangePassword(true);
        teacher1.setPwdNoEncrypt("TeacherPwd1NEW");
        teacher1.setChangePassword(true);
        adapter.updateUser(users.getAll().get(0));
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        assertEquals(teacher1,users.getAll().get(0));
    }

    @Test
    void testUpdateStudent() {
        Family f1 = new Family("cee12240-3e76-406e-bf12-0d40488ed3b9");
        User student1 = Student.builder().name("StudentName1").email("StudentEmail1").classNo(ClassNo.First).pwd("StudentPwd1").id("64e691e3-204f-45ee-8c5a-aefdffa1b3a5").family(f1).build();
        student1.setChangePassword(true);
        f1.addChild((Student) student1);
        adapter.addFamily(f1);
        loadFamilies();
        adapter.addUser(student1);
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        assertEquals(f1,families.getAllFamilies().get(0));
        assertEquals(student1,users.getAll().get(0));

        Family f2 = new Family("bee12240-3e76-406e-bf12-0d40488ed3b9");
        f2.addChild((Student) users.getAll().get(0));
        adapter.addFamily(f2);
        loadFamilies();
        assertEquals(2,families.getSize());

        ((Student) users.getAll().get(0)).setClassNo(ClassNo.Second);
        users.getAll().get(0).setChangePassword(false);
        users.getAll().get(0).setPwdNoEncrypt("StudentPwd1NEW");
        ((Student) users.getAll().get(0)).setFamily(f2);
        ((Student) student1).setClassNo(ClassNo.Second);
        student1.setChangePassword(false);
        student1.setPwdNoEncrypt("StudentPwd1NEW");
        ((Student) student1).setFamily(f2);
        f1.deleteChild((Student) student1);

        adapter.updateUser(users.getAll().get(0));
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        assertEquals(student1,users.getAll().get(0));
        assertEquals(f1,families.getFamilyById(f1.getId()));
        assertEquals(f2,families.getFamilyById(f2.getId()));
    }

    @Test
    void testUpdateParent() {
        Family f1 = new Family("cee12240-3e76-406e-bf12-0d40488ed3b9");
        User parent1 = Parent.builder().name("ParentName1").email("ParentEmail1").pwd("ParentPwd1").id("adc8ba24-7250-425e-a0c9-00e144bbf75c").family(f1).build();
        parent1.setChangePassword(true);
        f1.addParent((Parent) parent1);
        adapter.addFamily(f1);
        adapter.addUser(parent1);
        loadFamilies();
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        assertEquals(f1,families.getAllFamilies().get(0));
        assertEquals(parent1,users.getAll().get(0));

        Family f2 = new Family("bee12240-3e76-406e-bf12-0d40488ed3b9");
        f2.addParent((Parent) users.getAll().get(0));
        adapter.addFamily(f2);
        loadFamilies();
        assertEquals(2,families.getSize());

        users.getAll().get(0).setChangePassword(false);
        users.getAll().get(0).setPwdNoEncrypt("ParentPwd1NEW");
        ((Parent) users.getAll().get(0)).setFamily(f2);
        parent1.setChangePassword(false);
        parent1.setPwdNoEncrypt("ParentPwd1NEW");
        ((Parent) parent1).setFamily(f2);
        f1.deleteParent((Parent) parent1);

        adapter.updateUser(users.getAll().get(0));
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        assertEquals(parent1,users.getAll().get(0));
        assertEquals(f1,families.getFamilyById(f1.getId()));
        assertEquals(f2,families.getFamilyById(f2.getId()));
    }

    @Test
    void testUpdateHomework() {
        ArrayList<ClassNo> classes = new ArrayList<>();
        classes.add(ClassNo.First);
        classes.add(ClassNo.Fourth);
        classes.add(ClassNo.Eighth);
        Post post = new Homework("cee12240-3e76-406e-bf12-0d40488ed3b9","Title","Content","Phill",new MyDate(2018,5,5,0,0),new MyDate(2018,10,10,10,0),classes,5,null,false);
        adapter.addPost(post);
        loadPosts();

        assertEquals(100000,posts.getAll().size());
        assertEquals(post,posts.getAll().get(0));
// cannot be tested yet .. homework setters missing
    }

        //deleting
    @Test
    void testDeleteAdmin() {
        User admin = Administrator.builder().name("AdminName").email("AdminEmail").pwd("AdminPwd").id("b2c74531-49ea-4efe-9308-59d01f4792cb").build();
        admin.setChangePassword(true);
        adapter.addUser(admin);
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        adapter.deleteUser(users.getUserById(admin.getId()).getId());
        loadUsers(families);
        assertThrows(NoSuchElementException.class, () -> users.getUserById(admin.getId()));
        assertEquals(0,users.getAll().size());
    }

    @Test
    void testDeleteTeacher() {
        User teacher1 = Teacher.builder().name("TeacherName1").email("TeacherEmail1").pwd("TeacherPwd1").id("190edc14-b80c-484b-aa39-97d1012c1597").build();
        teacher1.setChangePassword(false);
        adapter.addUser(teacher1);
        loadUsers(families);
        assertEquals(1,users.getAll().size());
        adapter.deleteUser(users.getUserById(teacher1.getId()).getId());
        loadUsers(families);
        assertThrows(NoSuchElementException.class, () -> users.getUserById(teacher1.getId()));
        assertEquals(0,users.getAll().size());
    }

    @Test
    void testDeleteStudent() {
        Family f1 = new Family("cee12240-3e76-406e-bf12-0d40488ed3b9");
        User student1 = Student.builder().name("StudentName1").email("StudentEmail1").classNo(ClassNo.First).pwd("StudentPwd1").id("64e691e3-204f-45ee-8c5a-aefdffa1b3a5").family(f1).build();
        student1.setChangePassword(true);
        f1.addChild((Student) student1);
        adapter.addFamily(f1);
        loadFamilies();
        adapter.addUser(student1);
        loadUsers(families);
        assertEquals(f1,families.getAllFamilies().get(0));
        assertEquals(1,users.getAll().size());
        adapter.deleteUser(users.getUserById(student1.getId()).getId());
        loadUsers(families);
        assertThrows(NoSuchElementException.class, () -> users.getUserById(student1.getId()));
        assertEquals(0,users.getAll().size());
    }

    @Test
    void testDeleteParent() {
        Family f1 = new Family("cee12240-3e76-406e-bf12-0d40488ed3b9");
        User parent1 = Parent.builder().name("ParentName1").email("ParentEmail1").pwd("ParentPwd1").id("adc8ba24-7250-425e-a0c9-00e144bbf75c").family(f1).build();
        parent1.setChangePassword(true);
        f1.addParent((Parent) parent1);
        adapter.addFamily(f1);
        adapter.addUser(parent1);
        loadFamilies();
        loadUsers(families);
        assertEquals(f1,families.getAllFamilies().get(0));
        assertEquals(1,users.getAll().size());
        adapter.deleteUser(users.getUserById(parent1.getId()).getId());
        loadUsers(families);
        assertThrows(NoSuchElementException.class, () -> users.getUserById(parent1.getId()));
        assertEquals(0,users.getAll().size());
    }

    @Test
    void testDeleteFamily() {
        Family f1 = new Family("cee12240-3e76-406e-bf12-0d40488ed3b9");
        adapter.addFamily(f1);
        loadFamilies();
        assertEquals(1,families.getSize());
        assertEquals(f1,families.getAllFamilies().get(0));

        adapter.deleteFamily(families.getFamilyById(f1.getId()));
        loadFamilies();
        assertEquals(0,families.getSize());
    }

    @Test
    void testDeleteHomework() {
        ArrayList<ClassNo> classes = new ArrayList<>();
        classes.add(ClassNo.First);
        classes.add(ClassNo.Fourth);
        classes.add(ClassNo.Eighth);
        Post post = new Homework("cee12240-3e76-406e-bf12-0d40488ed3b9","Title","Content","Phill",new MyDate(2018,5,5,0,0),new MyDate(2018,10,10,10,0),classes,5,null,false);
        adapter.addPost(post);
        loadPosts();

        assertEquals(1,posts.getAll().size());
        assertEquals(post,posts.getAll().get(0));
        adapter.deletePost("cee12240-3e76-406e-bf12-0d40488ed3b9");
        loadPosts();
        assertEquals(0,posts.getAll().size());

    }

    //----------M-----------
    @Test
    void testAdminAndTwoTeachersAddedAndLoaded() {
        User admin = Administrator.builder().name("AdminName").email("AdminEmail").pwd("AdminPwd").id("b2c74531-49ea-4efe-9308-59d01f4792cb").build();
        admin.setChangePassword(true);
        adapter.addUser(admin);
        User teacher1 = Teacher.builder().name("TeacherName1").email("TeacherEmail1").pwd("TeacherPwd1").id("190edc14-b80c-484b-aa39-97d1012c1597").build();
        User teacher2 = Teacher.builder().name("TeacherName2").email("TeacherEmail2").pwd("TeacherPwd2").id("190edc14-b80c-484b-aa39-97d1012c2000").build();
        teacher1.setChangePassword(false);
        teacher2.setChangePassword(true);
        adapter.addUser(teacher1);
        adapter.addUser(teacher2);
        loadUsers(families);

        assertEquals(3,users.getAll().size());
        //assertions for admin
        assertEquals(admin,users.getAll().get(0));
        //assertions for teachers
        assertEquals(teacher1,users.getAll().get(1));
        assertEquals(teacher2,users.getAll().get(2));
    }
    
    @Test
    void testStudentAndTwoParentsAddedAndLoaded() {
        Family f1 = new Family("cee12240-3e76-406e-bf12-0d40488ed3b9");
        User student1 = Student.builder().name("StudentName1").email("StudentEmail1").classNo(ClassNo.First).pwd("StudentPwd1").id("64e691e3-204f-45ee-8c5a-aefdffa1b3a5").family(f1).build();
        student1.setChangePassword(true);
        f1.addChild((Student) student1);
        User parent1 = Parent.builder().name("ParentName1").email("ParentEmail1").pwd("ParentPwd1").id("adc8ba24-7250-425e-a0c9-00e144bbf75c").family(f1).build();
        parent1.setChangePassword(true);
        f1.addParent((Parent) parent1);
        User parent2 = Parent.builder().name("ParentName2").email("ParentEmail2").pwd("ParentPwd2").id("adc8ba24-7250-425e-a0c9-00e144bbf75g").family(f1).build();
        parent2.setChangePassword(true);
        f1.addParent((Parent) parent2);
        adapter.addFamily(f1);
        loadFamilies();
        
        adapter.addUser(student1);
        adapter.addUser(parent1);
        adapter.addUser(parent2);
        loadUsers(families);

        assertEquals(3,users.getAll().size());
        //assertions for student
        assertEquals(student1,users.getAll().get(0));
        assertEquals(f1.getChildren().get(0),users.getAll().get(0));
        //assertions for parents
        assertEquals(parent1,users.getAll().get(1));
        assertEquals(parent2,users.getAll().get(2));
        assertEquals(f1.getParent("ParentName1"),parent1);
        assertEquals(f1.getParent("ParentName2"),parent2);
    }

    @Test
    void testTwoHomeworksAdded() {
        ArrayList<ClassNo> classes = new ArrayList<>();
        classes.add(ClassNo.First);
        classes.add(ClassNo.Fourth);
        classes.add(ClassNo.Eighth);
        Post post1 = new Homework("cee12240-3e76-406e-bf12-0d40488ed3b9","Title","Content","Phill",new MyDate(2018,5,5,0,0),new MyDate(2018,10,10,10,0),classes,5,null,false);
        Post post2 = new Homework("gee12240-3e76-406e-bf12-0d40488ed3b9","Title2","Content3","Phillips",new MyDate(2019,5,5,0,0),new MyDate(2019,10,10,10,0),classes,4,null,false);
        adapter.addPost(post1);
        adapter.addPost(post2);
        loadPosts();

        assertEquals(2,posts.getAll().size());
        assertEquals(post1,posts.getAll().get(0));
        assertEquals(post2,posts.getAll().get(1));
    }

    @AfterEach
    void tearDown() {
        ((DBAdapter) adapter).executeSQL("DELETE FROM enteuser");
        ((DBAdapter) adapter).executeSQL("DELETE FROM family");
        ((DBAdapter) adapter).executeSQL("DELETE FROM post");
    }
}