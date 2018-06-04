package test;

import model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.persistance.DBAdapter;
import server.model.persistance.DBInterface;
import server.model.persistance.DBPersistence;
import server.model.persistance.Database;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DBAdapterTestNEW {

    private DBInterface database;
    private static DBPersistence adapter;
    private static ArrayList<String> selectList;
    private static ArrayList<String> insertList;
    private static ArrayList<String> updateList;
    private static ArrayList<String> deleteList;
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

    private void sortFamilies() {
        List<Family> sorted = families.getAllFamilies()
                .stream().sorted(Comparator.comparing(Family::getId)).collect(Collectors.toList());

        families.clear();
        families.addAll(sorted);
    }

    private void sortUsers() {
        List<User> sorted = users.getAll()
                .stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());

        users.clear();
        users.addAll(sorted);
    }

    private void loadModelData() {
        families = new FamilyList();
        for (int i = 0; i < 3; i++) {
            families.addFamily(new Family("FamilyID" + (i + 1)));
        }
        users = new UsersList();
        users.add(Administrator.builder().name("AdminName").email("AdminEmail").pwd("AdminPwd").id("AdminID").build());
        for (int i = 0; i < 2; i++)
            users.add(Teacher.builder().name("TeacherName" + (i + 1)).email("TeacherEmail" + (i + 1)).pwd("TeacherPwd" + (i + 1)).id("TeacherID" + (i + 1)).build());
        for (int i = 0; i < 4; i++) {
            Family family;
            if (i >= 2) {
                family = families.getFamilyById("FamilyID"+i);
            } else {
                family = families.getFamilyById("FamilyID"+(i+1));
            }
            Student student = Student.builder().name("StudentName" + (i + 1)).email("StudentEmail" + (i + 1)).classs(ClassNo.values()[i]).pwd("StudentPwd" + (i + 1)).id("StudentID" + (i + 1)).family(family).build();
            users.add(student);
            family.addChild(student);
        }
        for (int i = 0; i < 4; i++) {
            Family family;
            if (i >= 3) {
                family = families.getFamilyById("FamilyID"+i);
            } else {
                family = families.getFamilyById("FamilyID"+(i+1));
            }
            Parent parent = Parent.builder().name("ParentName1").email("ParentEmail1").pwd("ParentPwd1").id("ParentID"+(i+1)).family(family).build();
            users.add(parent);
            family.addParent(parent);
        }
        posts = new PostsList();

    }

    @BeforeAll
    static void setUpAdapter() {
        adapter = new DBAdapter(new MockDatabaseTest());
        selectList = new ArrayList<>();
        selectList.add("SELECT p.postid, p.title, p.content, p.authorname, p.pubDate, h.noOfStudentsToDeliver, h.deadline, h.classes, h.closed FROM Post p, Homework h WHERE p.postid=h.homeworkid ORDER BY p.postid");
        selectList.add("SELECT * FROM homeworkreply ORDER BY (homeworkid,studentid)");
        selectList.add("SELECT * FROM enteUser WHERE usertype ='Administrator' ORDER BY id");
        selectList.add("SELECT * FROM enteUser WHERE usertype ='Teacher' ORDER BY id");
        selectList.add("SELECT e.id, e.email, e.pwd, e.name, e.changePassword, s.familyid, s.class FROM enteuser e, student s WHERE e.id=s.studentid ORDER BY e.id");
        selectList.add("SELECT e.id, e.email, e.pwd, e.name, e.changePassword, p.familyid FROM enteuser e, parent p WHERE e.id=p.parentid ORDER BY e.id");
        selectList.add("SELECT * FROM family ORDER BY familyid");


    }

    @BeforeEach
    void setUp() {
        loadModelData();
        sortFamilies();
        sortUsers();
    }

    @Test
    void testGetPosts() {
    }

    @Test
    void testAddPost() {
        MyDate deadline = MyDate.now();
        deadline.stepForwardOneDay();
        List<ClassNo> classes = new LinkedList<>();
        classes.add(ClassNo.First);
        Homework post = new Homework("id", "title", "content", "author", MyDate.now(), deadline, classes, 1, new LinkedList<HomeworkReply>(), false);
        adapter.addPost(post);
    }

    @Test
    void testUpdatePost() {
    }

    @Test
    void testDeletePost() {
    }

    @Test
    void testGetUsers() {
        assertEquals(users.getAll(),adapter.getUsers(families));
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
        assertEquals(families.getAllFamilies(), adapter.getFamilies());
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

    static class MockDatabaseTest implements DBInterface {

        private ArrayList<Object[]> admins = new ArrayList<>();
        private ArrayList<Object[]> teachers = new ArrayList<>();
        private ArrayList<Object[]> students = new ArrayList<>();
        private ArrayList<Object[]> parents = new ArrayList<>();
        private ArrayList<Object[]> families = new ArrayList<>();
        private ArrayList<Object[]> posts = new ArrayList<>();
        private ArrayList<Object[]> replies = new ArrayList<>();

        public MockDatabaseTest() {
            initializeFamilies();
            initializeUsers();
        }

        private void initializeUsers() {
            initializeAdmin();
            initializeTeachers();
            initializeStudents();
            initializeParents();
        }

        private void initializeAdmin() {
            Object[] row = new Object[6];
            row[0] = "AdminID";
            row[1] = "Administrator";
            row[2] = "AdminEmail";
            row[3] = "AdminPwd";
            row[4] = "AdminName";
            row[5] = false;
            admins.add(row);
        }

        private void initializeTeachers() {
            for (int i = 0; i < 2; i++) {
                Object[] row = new Object[6];
                row[0] = "TeacherID" + (i + 1);
                row[1] = "Teacher";
                row[2] = "TeacherEmail" + (i + 1);
                row[3] = "TeacherPwd" + (i + 1);
                row[4] = "TeacherName" + (i + 1);
                row[5] = false;
                teachers.add(row);
            }
        }

        private void initializeStudents() {
            for (int i = 0; i < 4; i++) {
                Object[] row = new Object[7];
                row[0] = "StudentID" + (i + 1);
                row[1] = "StudentEmail" + (i + 1);
                row[2] = "StudentPwd" + (i + 1);
                row[3] = "StudentName" + (i + 1);
                row[4] = false;
                row[5] = "FamilyID" + (i + 1);
                if (i >= 2)
                    row[5] = "FamilyID" + i;
                row[6] = ClassNo.values()[i];
                students.add(row);
            }
        }

        private void initializeParents() {
            for (int i = 0; i < 4; i++) {
                Object[] row = new Object[6];
                row[0] = "StudentID" + (i + 1);
                row[1] = "StudentEmail" + (i + 1);
                row[2] = "StudentPwd" + (i + 1);
                row[3] = "StudentName" + (i + 1);
                row[4] = false;
                row[5] = "FamilyID" + (i + 1);
                if (i >= 3)
                    row[5] = "FamilyID" + i;
                parents.add(row);
            }
        }

        private void initializeFamilies() {
            for (int i = 0; i < 3; i++) {
                Object[] row = new Object[1];
                row[0] = "FamilyID" + (i + 1);
                families.add(row);
            }
        }

        @Override
        public ArrayList<Object[]> query(String sql, Object... statementElements) {
            switch (sql) {
                case "SELECT * FROM family ORDER BY familyid":
                    return families;
                case "SELECT p.postid, p.title, p.content, p.authorname, p.pubDate, h.noOfStudentsToDeliver, h.deadline, h.classes, h.closed FROM Post p, Homework h WHERE p.postid=h.homeworkid ORDER BY p.postid":
                    return posts;
                case "SELECT * FROM homeworkreply ORDER BY (homeworkid,studentid)":
                    return replies;
                case "SELECT * FROM enteUser WHERE usertype ='Administrator' ORDER BY id":
                    return admins;
                case "SELECT * FROM enteUser WHERE usertype ='Teacher' ORDER BY id":
                    return teachers;
                case "SELECT e.id, e.email, e.pwd, e.name, e.changePassword, s.familyid, s.class FROM enteuser e, student s WHERE e.id=s.studentid ORDER BY e.id":
                    return students;
                case "SELECT e.id, e.email, e.pwd, e.name, e.changePassword, p.familyid FROM enteuser e, parent p WHERE e.id=p.parentid ORDER BY e.id":
                    return parents;
                default:
                    return null;
            }
        }

        @Override
        public int update(String sql, Object... statementElements) {
            return 0;
        }

        @Override
        public int[] updateAll(ArrayList<String> sqlList) {
//            for (String sql : sqlList)
//                if (!list.contains(sql))
//                    fail()

            return new int[0];
        }
    }
}