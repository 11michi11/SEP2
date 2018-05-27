package server.model.persistance;

import model.*;
import utility.persistence.MyDatabase;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

public class DBAdapter implements DBPersistence {

    private MyDatabase db;
//    private static final String DRIVER = "org.postgresql.Driver";
//    private static final String URL = "jdbc:postgresql://207.154.237.196:5432/ente";
//    private static final String USER = "ente";
//    private static final String PASSWORD = "ente";

    public DBAdapter(String driver, String url, String user, String password) {
        try {
            db = new MyDatabase(driver,url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LinkedList<Post> getPosts(UsersList users) {
        LinkedList<Post> list = new LinkedList<>();
        try {
            String sql = "SELECT * FROM Post";
            ArrayList<Object[]> resultSet = db.query(sql);
            for (Object[] e : resultSet) {
                String postID = (String) e[0];
                String title = (String) e[1];
                String content = (String) e[2];
                String authorID = (String) e[3];
                User author = users.getUserById(authorID);
                Timestamp timestamp = (Timestamp) e[4];
                Calendar time = Calendar.getInstance();
                time.setTime(timestamp);
                MyDate date = new MyDate(time.get(Calendar.YEAR),time.get(Calendar.MONTH)+1,time.get(Calendar.DAY_OF_MONTH));
                list.add(new Post(postID,title,content,author.getName(),date));
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
                    sql += "familyid='" + student.getFamilyId() + "'";
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
    public void deleteUser(String id) {
        String sql = "DELETE FROM enteuser WHERE id='" + id + "'";
        try {
            db.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private LinkedList<Administrator> getAdmins() {
        LinkedList<Administrator> list = new LinkedList<>();
        try {
            String sql = "SELECT * FROM enteUser WHERE usertype ='Administrator'";
            ArrayList<Object[]> resultSet = db.query(sql);
            for (Object[] e : resultSet) {
                String id = (String) e[0];
                String email = (String) e[2];
                String pwd = (String) e[3];
                String name = (String) e[4];
                boolean changePwdNeeded = (boolean) e[5];
                Administrator administrator = new Administrator(name, email, pwd, id);
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
            String sql = "SELECT * FROM enteUser WHERE usertype ='Teacher'";
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
            String sql = "SELECT e.id, e.email, e.pwd, e.name, e.changePassword, s.familyid, s.class FROM enteuser e, student s WHERE e.id=s.studentid";
            ArrayList<Object[]> resultSet = db.query(sql);
            for (Object[] e : resultSet) {
                String id = (String) e[0];
                String email = (String) e[1];
                String pwd = (String) e[2];
                String name = (String) e[3];
                boolean changePwdNeeded = (boolean) e[4];
                String familyID = (String) e[5];
                ClassNo classs = ClassNo.valueOf((String) e[6]);
                Student student = Student.builder().name(name).email(email).classs(classs).id(id).pwd(pwd).family(families.getFamilyById(familyID)).build();
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
            String sql = "SELECT e.id, e.email, e.pwd, e.name, e.changePassword, p.familyid FROM enteuser e, parent p WHERE e.id=p.parentid";
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

    public void executeSQL(String sql) {
        try {
            db.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}