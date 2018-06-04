package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import server.model.persistance.Database;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseTest {

    private static Database database;
    private static int lastRandomNo;
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
    static void setUp() throws ClassNotFoundException {
        database = new Database("org.postgresql.Driver", "jdbc:postgresql://207.154.237.196:5432/ente?currentSchema=demo", "ente", "ente");
        lastRandomNo = 0;
    }

    @Test
    void testQuerySelectFromStudent() throws SQLException {
        ArrayList<Object[]> list = database.query("SELECT e.id, e.email, e.pwd, e.name, e.changePassword, s.familyid, s.class FROM enteuser e, student s WHERE e.id=s.studentid ORDER BY e.id");
        assertEquals(4, list.size());
        for (Object[] e : list) {
            assertEquals(7, e.length);
        }
    }

    @Test
    void testQuerySelectFromParent() throws SQLException {
        ArrayList<Object[]> list = database.query("SELECT e.id, e.email, e.pwd, e.name, e.changePassword, p.familyid FROM enteuser e, parent p WHERE e.id=p.parentid ORDER BY e.id");
        assertEquals(4, list.size());
        for (Object[] e : list) {
            assertEquals(6, e.length);
        }
    }

    @Test
    void testQuerySelectFromHomeWork() throws SQLException {
        ArrayList<Object[]> list = database.query("SELECT p.postid, p.title, p.content, p.authorname, p.pubDate, h.noOfStudentsToDeliver, h.deadline, h.classes, h.closed FROM Post p, Homework h WHERE p.postid=h.homeworkid ORDER BY p.postid");
        assertEquals(2, list.size());
        for (Object[] e : list) {
            assertEquals(9, e.length);
        }
    }

    @Test
    void testQuerySelectFromHomeworkReply() throws SQLException {
        ArrayList<Object[]> list = database.query("SELECT * FROM homeworkreply ORDER BY (homeworkid,studentid)");
        assertEquals(3, list.size());
        for (Object[] e : list) {
            assertEquals(5, e.length);
        }
    }

    @Test
    void testQuerySelectFromFamily() throws SQLException {
        ArrayList<Object[]> list = database.query("SELECT * FROM family ORDER BY familyid");
        assertEquals(3, list.size());
        for (Object[] e : list) {
            assertEquals(1, e.length);
        }
    }

    @Test
    void testInsertAndDeleteStudent() throws SQLException {
        ArrayList<String> sqlList = new ArrayList<>();
        sqlList.add("INSERT INTO family VALUES ('FamilyIDTEST')");
        sqlList.add("INSERT INTO enteuser VALUES ('StudentIDTEST', 'Student', 'StudentEmailTEST', 'StudentPwdTEST', 'StudentNameTEST', false)");
        sqlList.add("INSERT INTO student VALUES ('StudentIDTEST', 'FamilyIDTEST','First')");
        int[] updates = database.updateAll(sqlList);
        for (int e : updates) {
            assertEquals(1, e);
        }
        sqlList.clear();
        sqlList.add("DELETE FROM enteuser WHERE id='StudentIDTEST'");
        sqlList.add("DELETE FROM family WHERE familyid='FamilyIDTEST'");
        updates = database.updateAll(sqlList);
        for (int e : updates) {
            assertEquals(1, e);
        }
    }

    @Test
    void testInsertAndDeleteParent() throws SQLException {
        ArrayList<String> sqlList = new ArrayList<>();
        sqlList.add("INSERT INTO family VALUES ('FamilyIDTEST')");
        sqlList.add("INSERT INTO enteuser VALUES ('ParentIDTEST', 'Parent', 'ParentEmailTEST', 'ParentPwdTEST', 'ParentNameTEST', false)");
        sqlList.add("INSERT INTO parent VALUES ('ParentIDTEST', 'FamilyIDTEST')");
        int[] updates = database.updateAll(sqlList);
        for (int e : updates) {
            assertEquals(1, e);
        }
        sqlList.clear();
        sqlList.add("DELETE FROM enteuser WHERE id='ParentIDTEST'");
        sqlList.add("DELETE FROM family WHERE familyid='FamilyIDTEST'");
        updates = database.updateAll(sqlList);
        for (int e : updates) {
            assertEquals(1, e);
        }
    }

    @Test
    void testInsertAndDeleteFamily() throws SQLException {
        int update = database.update("INSERT INTO family VALUES ('FamilyIDTEST')");
        assertEquals(1, update);
        update = database.update("DELETE FROM family WHERE familyid='FamilyIDTEST'");
        assertEquals(1, update);
    }

    @Test
    void testInsertAndDeleteHomework() throws SQLException {
        ArrayList<String> sqlList = new ArrayList<>();
        sqlList.add("INSERT INTO post VALUES ('HomeworkIDTEST', 'Homework', 'TitleTEST', 'ContentTEST', 'AuthorNameTEST', '2018-01-01 00:00:00.557000')");
        sqlList.add("INSERT INTO homework VALUES ('HomeworkIDTEST', 10, '2018-10-01 14:59:00.000000', '{Second,Fourth,Seventh}', true)");
        int[] updates = database.updateAll(sqlList);
        for (int e : updates) {
            assertEquals(1, e);
        }
        int update = database.update("DELETE FROM post WHERE postid='HomeworkIDTEST'");
        assertEquals(1, update);
    }

    @Test
    void testInsertAndDeleteHomeworkReply() throws SQLException {
        ArrayList<String> sqlList = new ArrayList<>();
        sqlList.add("INSERT INTO family VALUES ('FamilyIDTEST')");
        sqlList.add("INSERT INTO enteuser VALUES ('StudentIDTEST', 'Student', 'StudentEmailTEST', 'StudentPwdTEST', 'StudentNameTEST', false)");
        sqlList.add("INSERT INTO student VALUES ('StudentIDTEST', 'FamilyIDTEST','First')");
        sqlList.add("INSERT INTO post VALUES ('HomeworkIDTEST', 'Homework', 'TitleTEST', 'ContentTEST', 'AuthorNameTEST', '2018-01-01 00:00:00.557000')");
        sqlList.add("INSERT INTO homework VALUES ('HomeworkIDTEST', 10, '2018-10-01 14:59:00.000000', '{Second,Fourth,Seventh}', true)");
        sqlList.add("INSERT INTO homeworkreply VALUES ('HomeworkIDTEST', 'StudentIDTEST', '2018-06-26 05:55:58.231000', 'SolutionTEST', false)");
        int[] updates = database.updateAll(sqlList);
        for (int e : updates) {
            assertEquals(1, e);
        }
        sqlList.clear();
        sqlList.add("DELETE FROM post WHERE postid='HomeworkIDTEST'");
        sqlList.add("DELETE FROM enteuser WHERE id='StudentIDTEST'");
        sqlList.add("DELETE FROM family WHERE familyid='FamilyIDTEST'");
        updates = database.updateAll(sqlList);
        for (int e : updates) {
            assertEquals(1, e);
        }
    }

    @Test
    void testUpdateStudent() throws SQLException {
        ArrayList<String> sqlList = new ArrayList<>();
        int randomNo;
        do {
            randomNo = (int) (Math.random() * 4 + 5);
        } while (lastRandomNo == randomNo);
        lastRandomNo = randomNo;
        sqlList.add("UPDATE enteuser SET email='StudentEmailNEW" + lastRandomNo + "', pwd='StudentPwdNEW" + lastRandomNo + "',name='StudentNameNEW" + lastRandomNo + "',changePassword=false WHERE id='StudentID1'");
        sqlList.add("UPDATE student SET class='Third',familyID='FamilyID1' WHERE studentid='StudentID1'");
        int[] updates = database.updateAll(sqlList);
        for (int e : updates) {
            assertEquals(1, e);
        }
    }

    @Test
    void testUpdateParent() throws SQLException {
        ArrayList<String> sqlList = new ArrayList<>();
        int randomNo;
        do {
            randomNo = (int) (Math.random() * 4 + 5);
        } while (lastRandomNo == randomNo);
        lastRandomNo = randomNo;
        sqlList.add("UPDATE enteuser SET email='ParentEmailNEW" + lastRandomNo + "', pwd='ParentPwdNEW" + lastRandomNo + "',name='ParentNameNEW" + lastRandomNo + "',changePassword=false WHERE id='ParentID1'");
        sqlList.add("UPDATE parent SET familyID='FamilyID1' WHERE parentid='ParentID1'");
        int[] updates = database.updateAll(sqlList);
        for (int e : updates) {
            assertEquals(1, e);
        }
    }

    @Test
    void testUpdateHomework() throws SQLException {
        ArrayList<String> sqlList = new ArrayList<>();
        int randomNo;
        do {
            randomNo = (int) (Math.random() * 4 + 5);
        } while (lastRandomNo == randomNo);
        lastRandomNo = randomNo;
        sqlList.add("UPDATE post SET title='TitleNEW" + lastRandomNo + "', content='ContentNEW" + lastRandomNo + "',pubdate='2018-0" + lastRandomNo + "-0" + lastRandomNo + " 00:00' WHERE postid='HomeworkID1'");
        sqlList.add("UPDATE homework SET deadline='2018-0" + lastRandomNo + "-0" + lastRandomNo + " 20:00', classes='{First,Third,Seventh}',closed=false WHERE homeworkid='HomeworkID1'");
        int[] updates = database.updateAll(sqlList);
        for (int e : updates) {
            assertEquals(1, e);
        }
    }

    @Test
    void testUpdateHomeworkReply() throws SQLException {
        int randomNo;
        do {
            randomNo = (int) (Math.random() * 4 + 5);
        } while (lastRandomNo == randomNo);
        lastRandomNo = randomNo;
        int updates = database.update("UPDATE homeworkreply SET content='SolutionNEW" + lastRandomNo + "', handindate='2020-0" + lastRandomNo + "-0" + lastRandomNo + " 00:00' WHERE homeworkid='HomeworkID1' AND studentid='StudentID1'");
        assertEquals(1, updates);
    }
}