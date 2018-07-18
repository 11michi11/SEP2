package server.model.persistance;

import model.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class DBAdapter implements DBPersistence {

    private DBInterface db;

    public DBAdapter(DBInterface database) {
        db = database;
    }

    @Override
    public LinkedList<Family> getFamilies() {
        LinkedList<Family> list = new LinkedList<>();
        try {
            String sql = "SELECT * FROM family ORDER BY familyid";
            ArrayList<Object[]> resultSet = db.query(sql);
            for (Object[] e : resultSet) {
                String familyID = (String) e[0];
                list.add(new Family(familyID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public LinkedList<User> getUsers(FamilyList families) {
        LinkedList<User> users = new LinkedList<>();

        LinkedList<Administrator> admins = getAdmins();
        LinkedList<Teacher> teachers = getTeachers();

        LinkedList<Student> students = getStudents(families);
        LinkedList<Parent> parents = getParents(families);

        users.addAll(admins);
        users.addAll(teachers);
        users.addAll(students);
        users.addAll(parents);
        return users;
    }

    @Override
    public LinkedList<Post> getPosts(UsersList users) {

        LinkedList<Post> posts = new LinkedList<>();
        LinkedList<Homework> homeworks = getHomeworks(getHomeworkReplies(users));

        posts.addAll(homeworks);

        return posts;
    }

    @Override
    public void addFamily(Family family) {
        try {
            String sql = "INSERT INTO family VALUES ('";
            sql += family.getId() + "')";

            db.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        try {
            ArrayList<String> sqlList = new ArrayList<>();

            String sql = "INSERT INTO enteuser VALUES ('";
            sql += user.getId() + "','";
            sql += user.getClass().getSimpleName() + "','";
            sql += user.getEmail() + "','";
            sql += user.getPwd() + "','";
            sql += user.getName() + "',";
            sql += user.isPasswordChangeNeeded() + ")";
            sqlList.add(sql);

            sql = "INSERT INTO ";

            switch (user.getClass().getSimpleName()) {
                case "Student":
                    Student student = (Student) user;
                    sql += "student VALUES ('";
                    sql += student.getId() + "','";
                    sql += student.getFamilyId() + "','";
                    sql += student.getClassNo() + "')";
                    sqlList.add(sql);
                    break;

                case "Parent":
                    Parent parent = (Parent) user;
                    sql += "parent VALUES ('";
                    sql += parent.getId() + "','";
                    sql += parent.getFamilyId() + "')";
                    sqlList.add(sql);
                    break;

                default:
                    break;
            }
            db.updateAll(sqlList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPost(Post post) {
        try {
            ArrayList<String> sqlList = new ArrayList<>();

            String sql = "INSERT INTO post VALUES ('";
            sql += post.getPostId() + "','";
            sql += post.getClass().getSimpleName() + "','";
            sql += post.getTitle() + "','";
            sql += post.getContent() + "','";
            sql += post.getAuthor() + "','";
            sql += MyDate.convertFromMyDateToTimestamp(post.getPubDate()) + "')";
            sqlList.add(sql);

            sql = "INSERT INTO ";

            switch (post.getClass().getSimpleName()) {
                case "Homework":
                    Homework homework = (Homework) post;
                    sql += "homework VALUES ('";
                    sql += homework.getPostId() + "',";
                    sql += homework.getNumberOfStudentsToDeliver() + ",'";
                    sql += MyDate.convertFromMyDateToTimestamp(homework.getDeadline()) + "','";
                    sql += homework.getClassesAsString() + "',";
                    sql += homework.isClosed() + ")";
                    sqlList.add(sql);

//                     NOT NECCESARY BC ALWAYS WHEN POST IS ADDED, it doesn't contain any reply .. or ?
                    if (homework.getReplies().size() > 0) {
                        LinkedList<HomeworkReply> replies = new LinkedList<>();
                        replies.addAll(homework.getReplies());
                        for (HomeworkReply e : replies) {
                            sql = "INSERT INTO homeworkreply VALUES ('";
                            sql += homework.getPostId() + "','";
                            sql += e.getStudentId() + "','";
                            sql += MyDate.convertFromMyDateToTimestamp(e.getHandInDateObj()) + "','";
                            sql += e.getContent() + "',";
                            sql += e.isLate() + ")";
                            sqlList.add(sql);
                        }
                    }
                    break;

                default:
                    break;
            }
            db.updateAll(sqlList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addHomeworkReply(String homeworkId, HomeworkReply reply) {
        String sql = "INSERT INTO homeworkreply VALUES ('";
        sql += homeworkId + "','";
        sql += reply.getStudentId() + "','";
        sql += MyDate.convertFromMyDateToTimestamp(reply.getHandInDateObj()) + "','";
        sql += reply.getContent() + "',";
        sql += reply.isLate() + ")";
        try {
            db.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            ArrayList<String> sqlList = new ArrayList<>();
            String sql = "";
            String usertype = user.getClass().getSimpleName();
            sql += "UPDATE enteuser SET ";
            sql += "usertype='" + usertype + "',";
            sql += "email='" + user.getEmail() + "',";
            sql += "pwd='" + user.getPwd() + "',";
            sql += "name='" + user.getName() + "',";
            sql += "changepassword=" + user.isPasswordChangeNeeded() + " ";
            sql += "WHERE id='" + user.getId() + "'";
            sqlList.add(sql);
            switch (usertype) {
                case "Student":
                    Student student = (Student) user;
                    sql = "UPDATE student SET class='" + student.getClassNo() + "',";
                    sql += "familyid='" + student.getFamilyId() + "' ";
                    sql += "WHERE studentid='" + student.getId() + "'";
                    sqlList.add(sql);
                    break;
                case "Parent":
                    Parent parent = (Parent) user;
                    sql = "UPDATE parent SET ";
                    sql += "familyid='" + parent.getFamilyId() + "' ";
                    sql += "WHERE parentid='" + parent.getId() + "'";
                    sqlList.add(sql);
                    break;
                default:
                    break;
            }
            db.updateAll(sqlList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePost(Post post) {
        try {
            ArrayList<String> sqlList = new ArrayList<>();
            String sql = "";
            String posttype = post.getClass().getSimpleName();
            sql += "UPDATE post SET ";
            sql += "type='" + posttype + "',";
            sql += "title='" + post.getTitle() + "',";
            sql += "content='" + post.getContent() + "',";
            sql += "authorname='" + post.getAuthor() + "',";
            sql += "pubdate='" + MyDate.convertFromMyDateToTimestamp(post.getPubDate()) + "' ";
            sql += "WHERE postid='" + post.getPostId() + "'";
            sqlList.add(sql);
            switch (posttype) {
                case "Homework":
                    Homework homework = (Homework) post;
                    sql = "UPDATE homework SET noOfStudentsToDeliver='" + homework.getNumberOfStudentsToDeliver() + "',";
                    sql += "deadline='" + MyDate.convertFromMyDateToTimestamp(homework.getDeadline()) + "',";
                    sql += "classes='" + homework.getClassesAsString() + "',";
                    sql += "closed='" + homework.isClosed() + "' ";
                    sql += "WHERE homeworkid='" + homework.getPostId() + "'";
                    sqlList.add(sql);

                    sql = "DELETE FROM homeworkreply WHERE homeworkid='" + homework.getPostId() + "'";
                    sqlList.add(sql);
                    if (homework.getReplies() != null) {
                        LinkedList<HomeworkReply> replies = new LinkedList<>(homework.getReplies());
                        for (HomeworkReply e : replies) {
                            sql = "INSERT INTO homeworkreply VALUES ('";
                            sql += homework.getPostId() + "','";
                            sql += e.getStudentId() + "','";
                            sql += MyDate.convertFromMyDateToTimestamp(e.getHandInDateObj()) + "','";
                            sql += e.getContent() + "',";
                            sql += e.isLate() + ")";
                            sqlList.add(sql);
                        }
                    }
                    break;

                default:
                    break;
            }
            db.updateAll(sqlList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateHomeworkReply(HomeworkReply reply) {
//      for (HomeworkReply e : replies2) {
//        sql = "UPDATE homeworkreply SET handindate='" + MyDate.convertFromMyDateToTimestamp(e.getHandInDate()) + "',";
//        sql += "content='" + e.getContent() + "',";
//        sql += "late=" + e.isLate();
//        sql += " WHERE homeworkid='" + homework.getPostId() + "' AND studentid='" + e.getStudent().getId() + "'";
//        sqlList.add(sql);
//      }
    }

    @Override
    public void deleteFamily(Family family) {
        try {
            String sql = "";
            if (family.getChildren() != null && family.getParents() != null) {
                sql = "DELETE FROM family WHERE familyid='" + family.getId() + "'";
            }
            db.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String id) {
        String sql = "DELETE FROM enteuser WHERE id='" + id + "'";
        try {
            db.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePost(String postID) {
        String sql = "DELETE FROM post WHERE postid='" + postID + "'";
        try {
            db.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private LinkedList<Administrator> getAdmins() {
        LinkedList<Administrator> list = new LinkedList<>();
        try {
            String sql = "SELECT * FROM enteUser WHERE usertype ='Administrator' ORDER BY id";
            ArrayList<Object[]> resultSet = db.query(sql);
            for (Object[] e : resultSet) {
                String id = (String) e[0];
                String email = (String) e[2];
                String pwd = (String) e[3];
                String name = (String) e[4];
                boolean changePwdNeeded = (boolean) e[5];
                Administrator administrator = Administrator.builder().name(name).email(email).id(id).pwd(pwd).build();
                administrator.setChangePassword(changePwdNeeded);
                list.add(administrator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private LinkedList<Teacher> getTeachers() {
        LinkedList<Teacher> list = new LinkedList<>();
        try {
            String sql = "SELECT * FROM enteUser WHERE usertype ='Teacher' ORDER BY id";
            ArrayList<Object[]> resultSet = db.query(sql);
            for (Object[] e : resultSet) {
                String id = (String) e[0];
                String email = (String) e[2];
                String pwd = (String) e[3];
                String name = (String) e[4];
                boolean changePwdNeeded = (boolean) e[5];
                Teacher teacher = Teacher.builder().name(name).email(email).pwd(pwd).id(id).build();
                teacher.setChangePassword(changePwdNeeded);
                list.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private LinkedList<Student> getStudents(FamilyList families) {
        LinkedList<Student> list = new LinkedList<>();

        try {
            String sql = "SELECT e.id, e.email, e.pwd, e.name, e.changePassword, s.familyid, s.class FROM enteuser e, student s WHERE e.id=s.studentid ORDER BY e.id";
            ArrayList<Object[]> resultSet = db.query(sql);
            for (Object[] e : resultSet) {
                String id = (String) e[0];
                String email = (String) e[1];
                String pwd = (String) e[2];
                String name = (String) e[3];
                boolean changePwdNeeded = (boolean) e[4];
                String familyID = (String) e[5];
                // ClassNo classs = (ClassNo) e[6];
                ClassNo classs = ClassNo.valueOf((String) e[6]);
                Student student = Student.builder().name(name).email(email).classNo(classs).id(id).pwd(pwd).family(families.getFamilyById(familyID)).build();
                student.setChangePassword(changePwdNeeded);
                families.getFamilyById(familyID).addChild(student);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private LinkedList<Parent> getParents(FamilyList families) {
        LinkedList<Parent> list = new LinkedList<>();
        try {
            String sql = "SELECT e.id, e.email, e.pwd, e.name, e.changePassword, p.familyid FROM enteuser e, parent p WHERE e.id=p.parentid ORDER BY e.id";
            ArrayList<Object[]> resultSet = db.query(sql);
            for (Object[] e : resultSet) {
                String id = (String) e[0];
                String email = (String) e[1];
                String pwd = (String) e[2];
                String name = (String) e[3];
                boolean changePwdNeeded = (boolean) e[4];
                String familyID = (String) e[5];
                Parent parent = Parent.builder().name(name).email(email).id(id).pwd(pwd).family(families.getFamilyById(familyID)).build();
                parent.setChangePassword(changePwdNeeded);
                families.getFamilyById(familyID).addParent(parent);
                list.add(parent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private LinkedList<Homework> getHomeworks(HashMap<String, LinkedList<HomeworkReply>> replies) {
        LinkedList<Homework> list = new LinkedList<>();
        try {
            String sql = "SELECT p.postid, p.title, p.content, p.authorname, p.pubDate, h.noOfStudentsToDeliver, h.deadline, h.classes, h.closed FROM Post p, Homework h WHERE p.postid=h.homeworkid ORDER BY p.postid";
            ArrayList<Object[]> resultSet = db.query(sql);
            for (Object[] e : resultSet) {
                String postID = (String) e[0];
                String title = (String) e[1];
                String content = (String) e[2];
                String authorName = (String) e[3];
                Timestamp pubDateStamp = (Timestamp) e[4];
                int noOfStudentsToDeliver = (int) e[5];
                Timestamp deadlineStamp = (Timestamp) e[6];
                String[] classesString = (String[]) e[7];
                List<ClassNo> classes = new ArrayList<>();
                for (String a : classesString) {
                    classes.add(ClassNo.valueOf(a));
                }
                boolean closed = (boolean) e[8];
                list.add(new Homework(postID, title, content, authorName, MyDate.convertFromTimestampToMyDate(pubDateStamp), MyDate.convertFromTimestampToMyDate(deadlineStamp), classes, noOfStudentsToDeliver, replies.getOrDefault(postID, new LinkedList<>()), closed));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private HashMap<String, LinkedList<HomeworkReply>> getHomeworkReplies(UsersList students) {
        HashMap<String, LinkedList<HomeworkReply>> map = new HashMap<>();
        try {
            String sql = "SELECT * FROM homeworkreply ORDER BY (homeworkid,studentid)";
            ArrayList<Object[]> resultSet = db.query(sql);
            LinkedList<HomeworkReply> replies = new LinkedList<>();
            if (resultSet != null && resultSet.size() > 0) {
                String previousHomeworkId = (String) resultSet.get(0)[0];
                for (Object[] e : resultSet) {
                    String homeworkID = (String) e[0];
                    String studentid = (String) e[1];
                    Student student = (Student) students.getUserById(studentid);
                    Timestamp timestamp = (Timestamp) e[2];
                    String content = (String) e[3];
                    boolean late = (boolean) e[4];
                    if (!previousHomeworkId.equals(homeworkID)) {
                        map.put(previousHomeworkId, replies);
                        replies = new LinkedList<>();
                        previousHomeworkId = homeworkID;
                    }
                    replies.add(new HomeworkReply(content, student, late, MyDate.convertFromTimestampToMyDate(timestamp)));
                }
                map.put(previousHomeworkId, replies);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public void executeSQL(String sql) {
        try {
            db.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}