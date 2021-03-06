//package test;
//
//import model.*;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import server.model.persistance.DBAdapter;
//import server.model.persistance.DBInterface;
//import server.model.persistance.DBPersistence;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static junit.framework.Assert.fail;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class DBAdapterTestNEW {
//
//    private static DBPersistence adapter;
//    private UsersList users;
//    private FamilyList families;
//    private PostsList posts;
//
//    /*
//    Demo schema contains:
//        1 Administrator
//        2 Teachers
//        4 Students
//        4 Parents
//        3 Families (1st family=1 student,1 parent;
//                    2nd family=2 students,1 parent;
//                    3rd family=1 student,2 parents)
//        2 Posts of type Homework
//        3 HomeworkReplies
//     */
//
//    private void loadFamilies() {
//        families = new FamilyList();
//        for (int i = 0; i < 3; i++) {
//            families.addFamily(new Family("FamilyID" + (i + 1)));
//        }
//        sortFamilies();
//    }
//
//    private void sortFamilies() {
//        List<Family> sorted = families.getAllFamilies()
//                .stream().sorted(Comparator.comparing(Family::getId)).collect(Collectors.toList());
//
//        families.clear();
//        families.addAll(sorted);
//    }
//
//    private void loadUsers() {
//        loadFamilies();
//
//        users = new UsersList();
//        users.add(Administrator.builder().name("AdminName").email("AdminEmail").pwd("AdminPwd").id("AdminID").build());
//        for (int i = 0; i < 2; i++)
//            users.add(Teacher.builder().name("TeacherName" + (i + 1)).email("TeacherEmail" + (i + 1)).pwd("TeacherPwd" + (i + 1)).id("TeacherID" + (i + 1)).build());
//        for (int i = 0; i < 4; i++) {
//            Family family;
//            if (i >= 2) {
//                family = families.getFamilyById("FamilyID" + i);
//            } else {
//                family = families.getFamilyById("FamilyID" + (i + 1));
//            }
//            Student student = Student.builder().name("StudentName" + (i + 1)).email("StudentEmail" + (i + 1)).classNo(ClassNo.values()[i]).pwd("StudentPwd" + (i + 1)).id("StudentID" + (i + 1)).family(family).build();
//            users.add(student);
//            family.addChild(student);
//        }
//        for (int i = 0; i < 4; i++) {
//            Family family;
//            if (i >= 3) {
//                family = families.getFamilyById("FamilyID" + i);
//            } else {
//                family = families.getFamilyById("FamilyID" + (i + 1));
//            }
//            Parent parent = Parent.builder().name("ParentName" + (i + 1)).email("ParentEmail" + (i + 1)).pwd("ParentPwd" + (i + 1)).id("ParentID" + (i + 1)).family(family).build();
//            users.add(parent);
//            family.addParent(parent);
//        }
////        sortUsers();
//    }
//
//    private void loadPosts() {
//        posts = new PostsList();
//        List<ClassNo> classes = new LinkedList<>();
//        classes.add(ClassNo.First);
//        posts.add(new Homework("HomeworkID1", "Title1", "Content1", "Author1", new MyDate(2018, 6, 6, 12, 0), new MyDate(2018, 7, 7, 12, 0), classes, 1, new LinkedList<HomeworkReply>(), false));
//        classes = new LinkedList<>(classes);
//        classes.add(ClassNo.Seventh);
//        classes.add(ClassNo.Eighth);
//        LinkedList<HomeworkReply> replies = new LinkedList<>();
//        replies.add(new HomeworkReply("Solution1", (Student) users.getUserById("StudentID1"), false, new MyDate(2018, 7, 15, 12, 0)));
//        replies.add(new HomeworkReply("Solution2", (Student) users.getUserById("StudentID2"), false, new MyDate(2018, 8, 15, 12, 0)));
//        posts.add(new Homework("HomeworkID2", "Title2", "Content2", "Author2", new MyDate(2018, 7, 7, 12, 0), new MyDate(2018, 8, 8, 12, 0), classes, 1, replies, false));
//    }
//
//    @BeforeAll
//    static void setUpAdapter() {
//        adapter = new DBAdapter(new MockDatabaseTest());
//    }
//
//    @BeforeEach
//    void setUp() {
//        loadUsers();
//    }
//
//    @Test
//    void testGetPosts() {
//        loadPosts();
//        assertEquals(new LinkedList<>(posts.getAll()), adapter.getPosts(users));
//    }
//
//    @Test
//    void testAddPost() {
////        loadPosts();
//        List<ClassNo> classes = new LinkedList<>();
//        classes.add(ClassNo.First);
//        Homework post = new Homework("HomeworkID", "Title", "Content", "Author", new MyDate(2018, 6, 6, 12, 0), new MyDate(2018, 7, 7, 12, 0), classes, 1, new LinkedList<HomeworkReply>(), false);
//        adapter.addPost(post);
//
////        assertThrows(AssertionFailedError.class, () -> adapter.addPost(post));
//    }
//
//    @Test
//    void testAddHomeworkReply() {
//        loadPosts();
//        HomeworkReply reply1 = new HomeworkReply("Solution", (Student) users.getUserById("StudentID1"), false, new MyDate(2018, 6, 6, 15, 0));
//        adapter.addHomeworkReply(posts.getPostById("HomeworkID1").getPostId(), reply1);
//    }
//
//    @Test
//    void testUpdatePost() {
//        loadPosts();
//        List<ClassNo> classes = new LinkedList<>();
//        classes.add(ClassNo.First);
//        classes.add(ClassNo.Third);
//        classes.add(ClassNo.Seventh);
//        Homework post = new Homework("HomeworkID1", "TitleNEW", "ContentNEW", posts.getPostById("HomeworkID1").getAuthor(), posts.getPostById("HomeworkID1").getPubDate(), new MyDate(2018, 9, 9, 0, 0), classes, ((Homework) posts.getPostById("HomeworkID1")).getNumberOfStudentsToDeliver(), ((Homework) posts.getPostById("HomeworkID1")).getReplies(), ((Homework) posts.getPostById("HomeworkID1")).isClosed());
//        posts.editPost(post);
//    }
//
//    @Test
//    void testDeletePost() {
//        loadPosts();
//        adapter.deletePost(posts.getPostById("HomeworkID1").getPostId());
//    }
//
//    @Test
//    void testGetUsers() {
//        assertEquals(users.getAll(), adapter.getUsers(families));
//    }
//
//    @Test
//    void testAddAdmin() {
//        User admin = Administrator.builder().name("AdminName").email("AdminEmail").id("AdminID").pwd("AdminPwd").build();
//        adapter.addUser(admin);
//    }
//
//    @Test
//    void testAddTeacher() {
//        User teacher = Teacher.builder().name("TeacherName").email("TeacherEmail").id("TeacherID").pwd("TeacherPwd").build();
//        adapter.addUser(teacher);
//    }
//
//    @Test
//    void testAddStudent() {
//        Family f1 = new Family("FamilyID");
//        User student1 = Student.builder().name("StudentName").email("StudentEmail").classNo(ClassNo.First).pwd("StudentPwd").id("StudentID").family(f1).build();
//        adapter.addUser(student1);
//    }
//
//    @Test
//    void testAddParent() {
//        Family f1 = new Family("FamilyID");
//        User parent1 = Parent.builder().name("ParentName").email("ParentEmail").pwd("ParentPwd").id("ParentID").family(f1).build();
//        adapter.addUser(parent1);
//    }
//
//    @Test
//    void testUpdateAdmin() {
//        Administrator admin = (Administrator) users.getUserById("AdminID");
//        admin.setPwd("AdminPwdNEW");
//        admin.setChangePassword(true);
//        adapter.updateUser(admin);
//    }
//
//    @Test
//    void testUpdateTeacher() {
//        Teacher teacher = (Teacher) users.getUserById("TeacherID1");
//        teacher.setPwd("TeacherPwdNEW");
//        teacher.setChangePassword(true);
//        adapter.updateUser(teacher);
//    }
//
//    @Test
//    void testUpdateStudent() {
//        Student student = (Student) users.getUserById("StudentID1");
//        student.setFamily(new Family("FamilyIDNEW"));
//        student.setClassNo(ClassNo.Second);
//        student.setPwd("StudentPwdNEW");
//        student.setChangePassword(true);
//        adapter.updateUser(student);
//    }
//
//    @Test
//    void testUpdateParent() {
//        Parent parent = (Parent) users.getUserById("ParentID1");
//        parent.setFamily(new Family("FamilyIDNEW"));
//        parent.setPwd("ParentPwdNEW");
//        parent.setChangePassword(true);
//        adapter.updateUser(parent);
//    }
//
//    @Test
//    void testDeleteAdmin() {
//        adapter.deleteUser(users.getUserById("AdminID").getId());
//    }
//
//    @Test
//    void testDeleteTeacher() {
//        adapter.deleteUser(users.getUserById("TeacherID1").getId());
//    }
//
//    @Test
//    void testDeleteStudent() {
//        adapter.deleteUser(users.getUserById("StudentID1").getId());
//    }
//
//    @Test
//    void testDeleteParent() {
//        adapter.deleteUser(users.getUserById("ParentID1").getId());
//    }
//
//    @Test
//    void testGetFamilies() {
//        loadFamilies();
//        assertEquals(families.getAllFamilies(), adapter.getFamilies());
//    }
//
//    @Test
//    void testAddFamily() {
//        Family family = new Family("FamilyID1");
//        adapter.addFamily(family);
//    }
//
//    @Test
//    void testDeleteFamily() {
//        Family family = new Family("FamilyID1");
//        adapter.deleteFamily(family);
//    }
//
//    @Test
//    void testExecuteSQL() {
//    }
//
//    static class MockDatabaseTest implements DBInterface {
//
//        private ArrayList<Object[]> admins = new ArrayList<>();
//        private ArrayList<Object[]> teachers = new ArrayList<>();
//        private ArrayList<Object[]> students = new ArrayList<>();
//        private ArrayList<Object[]> parents = new ArrayList<>();
//        private ArrayList<Object[]> families = new ArrayList<>();
//        private ArrayList<Object[]> posts = new ArrayList<>();
//        private ArrayList<Object[]> replies = new ArrayList<>();
//        private ArrayList<String> updateList;
//
//
//        public MockDatabaseTest() {
//            initializeFamilies();
//            initializeUsers();
//            initializePosts();
//            loadUpdateList();
//            initializeReplies();
//        }
//
//        private void loadUpdateList() {
//            updateList = new ArrayList<>();
//            updateList.add("INSERT INTO post VALUES ('HomeworkID','Homework','Title','Content','Author','2018-06-06 12:00:00.0')");
//            updateList.add("INSERT INTO homework VALUES ('HomeworkID',1,'2018-07-07 12:00:00.0','{First}',false)");
//            updateList.add("INSERT INTO family VALUES ('FamilyID1')");
//            updateList.add("INSERT INTO enteuser VALUES ('AdminID','Administrator','AdminEmail','AdminPwd','AdminName',false)");
//            updateList.add("INSERT INTO enteuser VALUES ('TeacherID','Teacher','TeacherEmail','TeacherPwd','TeacherName',false)");
//            updateList.add("INSERT INTO enteuser VALUES ('StudentID','Student','StudentEmail','StudentPwd','StudentName',false)");
//            updateList.add("INSERT INTO student VALUES ('StudentID','FamilyID','First')");
//            updateList.add("INSERT INTO enteuser VALUES ('ParentID','Parent','ParentEmail','ParentPwd','ParentName',false)");
//            updateList.add("INSERT INTO parent VALUES ('ParentID','FamilyID')");
//            updateList.add("INSERT INTO homeworkreply VALUES ('HomeworkID1','StudentID1','2018-06-06 15:00:00.0','Solution',false)");
//
//            updateList.add("UPDATE post SET title='TitleNEW', content='ContentNEW',pubdate='2018-06-06 12:00.0' WHERE postid='HomeworkID'");
//            updateList.add("UPDATE homework SET deadline='2018-09-09 00:00', classes='{First,Third,Seventh}',closed=false WHERE homeworkid='HomeworkID1'");
//            updateList.add("UPDATE enteuser SET usertype='Administrator',email='AdminEmail',pwd='AdminPwdNEW',name='AdminName',changepassword=true WHERE id='AdminID'");
//            updateList.add("UPDATE enteuser SET usertype='Teacher',email='TeacherEmail1',pwd='TeacherPwdNEW',name='TeacherName1',changepassword=true WHERE id='TeacherID1'");
//            updateList.add("UPDATE enteuser SET usertype='Student',email='StudentEmail1',pwd='StudentPwdNEW',name='StudentName1',changepassword=true WHERE id='StudentID1'");
//            updateList.add("UPDATE student SET class='Second',familyid='FamilyIDNEW' WHERE studentid='StudentID1'");
//            updateList.add("UPDATE enteuser SET usertype='Parent',email='ParentEmail1',pwd='ParentPwdNEW',name='ParentName1',changepassword=true WHERE id='ParentID1'");
//            updateList.add("UPDATE parent SET familyid='FamilyIDNEW' WHERE parentid='ParentID1'");
//
//            updateList.add("DELETE FROM family WHERE familyid='FamilyID1'");
//            updateList.add("DELETE FROM post WHERE postid='HomeworkID1'");
//            updateList.add("DELETE FROM enteuser WHERE id='AdminID'");
//            updateList.add("DELETE FROM enteuser WHERE id='TeacherID1'");
//            updateList.add("DELETE FROM enteuser WHERE id='StudentID1'");
//            updateList.add("DELETE FROM enteuser WHERE id='ParentID1'");
//
//        }
//
//        private void initializeUsers() {
//            initializeAdmin();
//            initializeTeachers();
//            initializeStudents();
//            initializeParents();
//        }
//
//        private void initializeAdmin() {
//            Object[] row = new Object[6];
//            row[0] = "AdminID";
//            row[1] = "Administrator";
//            row[2] = "AdminEmail";
//            row[3] = "AdminPwd";
//            row[4] = "AdminName";
//            row[5] = false;
//            admins.add(row);
//        }
//
//        private void initializeTeachers() {
//            for (int i = 0; i < 2; i++) {
//                Object[] row = new Object[6];
//                row[0] = "TeacherID" + (i + 1);
//                row[1] = "Teacher";
//                row[2] = "TeacherEmail" + (i + 1);
//                row[3] = "TeacherPwd" + (i + 1);
//                row[4] = "TeacherName" + (i + 1);
//                row[5] = false;
//                teachers.add(row);
//            }
//        }
//
//        private void initializeStudents() {
//            for (int i = 0; i < 4; i++) {
//                Object[] row = new Object[7];
//                row[0] = "StudentID" + (i + 1);
//                row[1] = "StudentEmail" + (i + 1);
//                row[2] = "StudentPwd" + (i + 1);
//                row[3] = "StudentName" + (i + 1);
//                row[4] = false;
//                row[5] = "FamilyID" + (i + 1);
//                if (i >= 2)
//                    row[5] = "FamilyID" + i;
//                row[6] = ClassNo.values()[i].toString();
//                students.add(row);
//            }
//        }
//
//        private void initializeParents() {
//            for (int i = 0; i < 4; i++) {
//                Object[] row = new Object[6];
//                row[0] = "ParentID" + (i + 1);
//                row[1] = "ParentEmail" + (i + 1);
//                row[2] = "ParentPwd" + (i + 1);
//                row[3] = "ParentName" + (i + 1);
//                row[4] = false;
//                row[5] = "FamilyID" + (i + 1);
//                if (i >= 3)
//                    row[5] = "FamilyID" + i;
//                parents.add(row);
//            }
//        }
//
//        private void initializeFamilies() {
//            for (int i = 0; i < 3; i++) {
//                Object[] row = new Object[1];
//                row[0] = "FamilyID" + (i + 1);
//                families.add(row);
//            }
//        }
//
//        private void initializePosts() {
//            for (int i = 0; i < 2; i++) {
//                Object[] row = new Object[9];
//                row[0] = "HomeworkID" + (i + 1);
//                row[1] = "Title" + (i + 1);
//                row[2] = "Content" + (i + 1);
//                row[3] = "Author" + (i + 1);
//                row[4] = MyDate.convertFromMyDateToTimestamp(new MyDate(2018, i + 6, i + 6, 12, 0));
//                row[5] = 1;
//                row[6] = MyDate.convertFromMyDateToTimestamp(new MyDate(2018, i + 7, i + 7, 12, 0));
//                switch (i) {
//                    case 0:
//                        String[] classes = new String[1];
//                        classes[0] = "First";
//                        row[7] = classes;
//                        break;
//                    case 1:
//                        classes = new String[3];
//                        classes[0] = "First";
//                        classes[1] = "Seventh";
//                        classes[2] = "Eighth";
//                        row[7] = classes;
//                        break;
//                    default:
//                        break;
//                }
//                row[8] = false;
//                posts.add(row);
//            }
//        }
//
//        private void initializeReplies() {
//            for (int i = 0; i < 2; i++) {
//                Object[] row = new Object[9];
//                row[0] = "HomeworkID2";
//                row[1] = "StudentID" + (i + 1);
//                row[2] = MyDate.convertFromMyDateToTimestamp(new MyDate(2018, (i + 7), 15, 12, 0));
//                row[3] = "Solution" + (i + 1);
//                row[4] = false;
//                replies.add(row);
//            }
//        }
//
//        @Override
//        public ArrayList<Object[]> query(String sql, Object... statementElements) {
//            switch (sql) {
//                case "SELECT * FROM family ORDER BY familyid":
//                    return families;
//                case "SELECT p.postid, p.title, p.content, p.authorname, p.pubDate, h.noOfStudentsToDeliver, h.deadline, h.classes, h.closed FROM Post p, Homework h WHERE p.postid=h.homeworkid ORDER BY p.postid":
//                    return posts;
//                case "SELECT * FROM homeworkreply ORDER BY (homeworkid,studentid)":
//                    return replies;
//                case "SELECT * FROM enteUser WHERE usertype ='Administrator' ORDER BY id":
//                    return admins;
//                case "SELECT * FROM enteUser WHERE usertype ='Teacher' ORDER BY id":
//                    return teachers;
//                case "SELECT e.id, e.email, e.pwd, e.name, e.changePassword, s.familyid, s.class FROM enteuser e, student s WHERE e.id=s.studentid ORDER BY e.id":
//                    return students;
//                case "SELECT e.id, e.email, e.pwd, e.name, e.changePassword, p.familyid FROM enteuser e, parent p WHERE e.id=p.parentid ORDER BY e.id":
//                    return parents;
//                default:
//                    return null;
//            }
//        }
//
//        @Override
//        public int update(String sql, Object... statementElements) {
//            if (!updateList.contains(sql))
//                fail();
//            return 0;
//        }
//
//        @Override
//        public int[] updateAll(ArrayList<String> sqlList) {
//            for (String e : sqlList) {
//                update(e);
//            }
//            return new int[0];
//        }
//    }
//}