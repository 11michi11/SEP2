package server.model.persistance;

import client.model.FamiliesList;
import model.*;
import utility.persistence.MyDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class DBAdapter implements DBPersistence {

    private MyDatabase db;
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://207.154.237.196:5432/ente";
    private static final String USER = "ente";
    private static final String PASSWORD = "ente";

    public DBAdapter() throws ClassNotFoundException {
        db = new MyDatabase(DRIVER, URL, USER, PASSWORD);
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
                //Post post = new Post(title, content);
                //author.makePost(post);
                // String date = (String) e[4];
                //list.add(new Post(title, content)); // maybe it's needed to add fields(author,date) to Post class
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public LinkedList<User> getUsers(FamiliesList families) {
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
            String sql = "INSERT INTO ";

            switch (user.getClass().getSimpleName()) {
                case "Student":
                    Student student = (Student) user;
                    sql += "student ('";
                    sql += student.getId() + "','";
                    sql += student.getFamilyID() + "','";
                    sql += student.getClasss() + "')";
                    sqlList.add(sql);
                    break;

                case "Parent":
                    Parent parent = (Parent) user;
                    sql += "parent ('";
                    sql += parent.getId() + "','";
                    sql += parent.getFamily().getId() + "')";
                    sqlList.add(sql);
                    break;

                default:
                    break;
            }

            sql = "INSERT INTO enteuser ('";
            sql += user.getId() + "','";
            sql += user.getClass().getSimpleName() + "','";
            sql += user.getEmail() + "','";
            sql += user.getPwd() + "','";
            sql += user.getName() + "',";
            sql += user.isPasswordChangeNeeded() + ")";
            sqlList.add(sql);
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
            sql += "UPDATE enteuser SET";
            sql += "type='" + usertype + "',";
            sql += "email='" + user.getEmail() + "',";
            sql += "pwd='" + user.getPwd() + "',";
            sql += "changePwdNeeded=" + user.isPasswordChangeNeeded() + ",";
            sql += "name='" + user.getName() + "' ";
            sql += "WHERE id='" + user.getId() + "'";
            sqlList.add(sql);
            switch (usertype) {
                case "student":
                    Student student = (Student) user;
                    sql = "UPDATE student SET class='" + student.getClasss() + "',";
                    sql += "familyid='" + student.getFamilyID() + "'";
                    sql += "WHERE studentid='" + student.getId() + "'";
                    sqlList.add(sql);
                    break;
                case "parent":
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
                list.add(new Administrator(name, email, pwd, id));
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
                list.add(new Teacher(name, email, pwd, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private LinkedList<Student> getStudents(FamiliesList families) {
        LinkedList<Student> list = new LinkedList<>();

        //parameter is of type FamiliesList from client model package, needs to be changed
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
                Classs classs = (Classs) e[6];
//              Student student = Student.builder().name(name).email(email).classs(classs).id(id).pwd(pwd).family(families.getFamilyById(familyID)).build();
//			    families.getFamilyById(familyID).addChild(student);
//			    list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private LinkedList<Parent> getParents(FamiliesList families) {
        LinkedList<Parent> list = new LinkedList<>();
        try {
            String sql = "SELECT e.id, e.email, e.pwd, e.name, e.changePassword, p.familyid FROM enteuser e, parent p WHERE e.id=p.parentid";
            ArrayList<Object[]> resultSet = db.query(sql);
            for (Object[] e : resultSet) {
                String id = (String) e[0];
                String email = (String) e[2];
                String pwd = (String) e[3];
                String name = (String) e[4];
                boolean changePwdNeeded = (boolean) e[5];
                String familyID = (String) e[5];
//			Parent parent = Parent.builder().name(name).email(email).id(id).pwd(pwd).family(families.getFamilyById(familyID));
//			families.getFamilyById(familyID).addParent(parent);
//			list.add(parent);
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
            String sql = "INSERT INTO family ('";
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

}