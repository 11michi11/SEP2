package test;

import model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import server.model.persistance.DBAdapter;
import server.model.persistance.DBPersistence;

import java.util.LinkedList;

import static junit.framework.Assert.*;


class AdapterTest {


    private static DBPersistence adapter;
    private static LinkedList<User> users;
    private static FamilyList families;

    @BeforeAll
    static void setUp() {
        adapter = new DBAdapter("org.postgresql.Driver","jdbc:postgresql://207.154.237.196:5432/ente?currentSchema=test","ente","ente");
        users = new LinkedList<>();
        families = new FamilyList();

        User admin = new Administrator("AdminName","AdminEmail","AdminPwd","b2c74531-49ea-4efe-9308-59d01f4792cb");
        User teacher1 = new Teacher("TeacherName1","TeacherEmail1","TeacherPwd1","190edc14-b80c-484b-aa39-97d1012c1597");

        Family f1 = new Family("cee12240-3e76-406e-bf12-0d40488ed3b9");
        User student1 = Student.builder().name("StudentName1").email("StudentEmail1").classs(Classs.First).pwd("StudentPwd1").id("64e691e3-204f-45ee-8c5a-aefdffa1b3a5").family(f1).build();
        User parent1 = Parent.builder().name("ParentName1").email("ParentEmail1").pwd("ParentPwd1").id("adc8ba24-7250-425e-a0c9-00e144bbf75c").family(f1).build();
        User parent2 = Parent.builder().name("ParentName2").email("ParentEmail2").pwd("ParentPwd2").id("167c1002-d823-4569-a848-f39c8f536130").family(f1).build();
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
        for (User e:list) {
            System.out.println(e.getName());
        }
        assertTrue(list.containsAll(users));
//        for (User e:users) {
//            assertTrue(list.contains(e));
//        }
    }

    private void loadUsers(){

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
