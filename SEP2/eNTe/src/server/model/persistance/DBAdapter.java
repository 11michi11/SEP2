package server.model.persistance;

import model.*;
import utility.persistence.MyDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class DBAdapter implements DBPersistance {

    private MyDatabase db;
    private static final String DRIVER = "org.postgresql.Driver";
    // private static final String URL =
    // "jdbc:postgresql://localhost:5432/postgres";
    private static final String URL = "jdbc:postgresql://207.154.237.196:5432/ente";
    private static final String USER = "ente";
    private static final String PASSWORD = "ente";

    public DBAdapter() throws ClassNotFoundException, SQLException {
        db = new MyDatabase(DRIVER, URL, USER, PASSWORD);
    }

    @Override
    public LinkedList<Post> getPosts() throws SQLException {
        LinkedList<Post> list = new LinkedList<>();

        String sql = "SELECT * FROM Post WHERE id=1";
        ArrayList<Object[]> resultSet = db.query(sql);
        for (Object[] e : resultSet) {
            int id = (int) e[0];
            String title = (String) e[1];
            String content = (String) e[2];
            String author = (String) e[3];
            // String date = (String) e[4];
            list.add(new Post(title, content)); // maybe it's needed to add fields(author,date) to Post class
        }
        return list;
    }

    @Override
    public LinkedList<User> getUsers() throws SQLException {
        LinkedList<User> users = new LinkedList<>();

        LinkedList<Administrator> admins = getAdmins();
        LinkedList<Teacher> teachers = getTeachers();
        LinkedList<Student> students = getStudents();
        LinkedList<Parent> parents = getParents();

        users.addAll(admins);
        users.addAll(teachers);
        users.addAll(students);
        users.addAll(parents);
        return users;
    }

    @Override
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO ";
        switch (user.getClass().getName()) {
            case "Student":
                Student student = (Student) user;
                sql += "student ('";
                sql += student.getId() + "','";
                //sql += student.getFamilyID ?????;
                sql += student.getClasss() + "')";
                db.update(sql);
                break;

            case "Parent":

                break;

            default:
                break;
        }

        sql = "INSERT INTO enteuser ('";
        sql += user.getId() + "','";
        sql += user.getEmail() + "','";
        sql += user.getPwd() + "',";
        //sql += user.getChangedPassword() + ",'";
        sql += user.getName() + "',')";
        sql += user.getClass().getName().toLowerCase() + "')";            //column for type of user
        db.update(sql);
    }

    @Override
    public void updateUser(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteUser(String id) {
        // TODO Auto-generated method stub

    }

    private LinkedList<Administrator> getAdmins() throws SQLException {

        LinkedList<Administrator> list = new LinkedList<>();

        String sql = "SELECT * FROM enteUser WHERE type ='admin'";
        ArrayList<Object[]> resultSet = db.query(sql);
        for (Object[] e : resultSet) {
            String id = (String) e[0];
            String username = (String) e[1]; // e-mail as well
            String password = (String) e[2];
            boolean changePassword = (boolean) e[3];
            String name = (String) e[4]; // name added coz user need it and there was comile error

            list.add(new Administrator(name, username, password)); // what with ID's and if it's active
            // ?????????????????????????????????????/
        }
        return list;
    }

    private LinkedList<Teacher> getTeachers() throws SQLException {
        LinkedList<Teacher> list = new LinkedList<>();

        String sql = "SELECT * FROM enteUser WHERE type ='teacher'";
        ArrayList<Object[]> resultSet = db.query(sql);
        for (Object[] e : resultSet) {
            String id = (String) e[0];
            String username = (String) e[1]; // e-mail as well
            String password = (String) e[2];
            boolean changePassword = (boolean) e[3];
            String name = (String) e[4]; // name added coz user need it and there was comile error

            list.add(new Teacher(name, username, password)); // what with ID's and if it's active
            // ?????????????????????????????????????/
        }
        return list;
    }

    private LinkedList<Student> getStudents() throws SQLException {
        LinkedList<Student> list = new LinkedList<>();

        String sql = "SELECT e.id, e.username, e.pwd, e.active, e.name, s.class FROM enteUser e, student s WHERE e.id=s.id";
        ArrayList<Object[]> resultSet = db.query(sql);
        for (Object[] e : resultSet) {
            String id = (String) e[0];
            String login = (String) e[1]; // e-mail as well
            String password = (String) e[2];
            boolean changePassword = (boolean) e[3];
            String name = (String) e[4]; // name added coz user need it and there was comile error
            Classs studentClasss = (Classs) e[5];
            //Matej, since now, you can create Students and Parents with builder. Example below
            list.add(Student.builder().name(name).login(login).classs(studentClasss).id(id).pwd(password).build());
            //After .classs() you can choose: build(), id(), history...
            //list.add(Student.builder().name(name).email(email).pwd(password).classs(studentClasss).build());
        }
        return list;
    }

    private LinkedList<Parent> getParents() throws SQLException {
        LinkedList<Parent> list = new LinkedList<>();

        String sql = "SELECT * FROM enteUser WHERE type ='parent'";
        ArrayList<Object[]> resultSet = db.query(sql);
        for (Object[] e : resultSet) {
            String id = (String) e[0];
            String login = (String) e[1]; // e-mail as well
            String password = (String) e[2];
            boolean changePassword = (boolean) e[3];
            String name = (String) e[4]; // name added coz user need it and there was comile error
            //list.add(new Parent(name, username, password, new Family(), id));
            list.add(Parent.builder().name(name).email(login).pwd(password).id(id).build());
        }
        return list;
    }

    private LinkedList<Family> getFamilies(UsersList familyMembers) throws SQLException {
        LinkedList<Family> list = new LinkedList<>();
        String lastFamilyID = "";

        String sql = "SELECT * FROM family ORDER BY familyid";
        ArrayList<Object[]> resultSet = db.query(sql);
        for (int i = 0; i < resultSet.size(); i++) {
            if (i == 0) {
                lastFamilyID = null;
            } else {
                lastFamilyID = (String) resultSet.get(i - 1)[0];
            }
            String familyID = (String) resultSet.get(i)[0];
            String memberID = (String) resultSet.get(i)[1];
            //Don't forget to check for null, when you set null
            if (lastFamilyID != null && !lastFamilyID.equals(familyID)) {
                Family family = new Family();
                familyMembers.getUserById(memberID);
            }
        }

        return null;
    }

}
