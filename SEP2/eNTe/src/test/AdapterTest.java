package test;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.persistance.DBAdapter;
import server.model.persistance.DBPersistence;

import java.util.LinkedList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class AdapterTest {

    private DBPersistence adapter;
    private LinkedList<User> users;
    private FamilyList families;

    @BeforeEach
    void setUp() throws ClassNotFoundException {
        adapter = new DBAdapter();
        User admin = new Administrator("AdminName","AdminEmail","AdminPwd","AdminId");
        User teacher1 = new Teacher("TeacherName1","TeacherEmail1","TeacherPwd1","TeacherId1");

        Family f1 = new Family("FamilyId1");
        User student1 = Student.builder().name("StudentName1").email("StudentEmail1").classs(Classs.First).pwd("StudentPwd1").id("StudentId1").family(f1).build();
        User parent1 = Parent.builder().name("ParentName1").email("ParentEmail1").pwd("ParentPwd1").id("ParentId1").family(f1).build();
        User parent2 = Parent.builder().name("ParentName2").email("ParentEmail2").pwd("ParentPwd2").id("ParentId2").family(f1).build();
        f1.addChild((Student) student1);
        f1.addParent((Parent) parent1);
        f1.addParent((Parent) parent2);

        families.addFamily(f1);
        users.add(admin);
        users.add(teacher1);
        users.add(parent1);
        users.add(parent2);
        users.add(student1);

        adapter.addUser(admin);
        adapter.addUser(teacher1);
        adapter.addFamily(f1);
        adapter.addUser(student1);
        adapter.addUser(parent1);
        adapter.addUser(parent2);
    }

    @Test
    void testGetUsers () {
        LinkedList<User> list = adapter.getUsers(families);
        assertTrue(users.containsAll(list));
    }


//    @Test
//    void testFirstPost() {
//
//        LinkedList<Post> list = adapter.getPosts();
//
//        System.out.println(list.getFirst());
//        assertEquals("Lessons cancelled", list.getFirst().getTitle());
//    }

}
