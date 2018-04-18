package server.model.persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Administrator;
import model.Parent;
import model.Post;
import model.Student;
import model.Teacher;
import model.User;
import utility.persistence.MyDatabase;

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
		LinkedList<User> list = new LinkedList<>();

		String sql = "SELECT e.id, e.username, e.pwd, e.active, e.name, p.child, s.class FROM enteUser e, parent p, student s where e.id=p.id OR e.id=s.i";
		ArrayList<Object[]> resultSet = db.query(sql);
		for (Object[] e : resultSet) {
			String id = (String) e[0];
			String username = (String) e[1]; // e-mail as well
			String password = (String) e[2];
			boolean active = (boolean) e[3];
			String name = (String) e[4]; // name added coz user need it and there was comile error
			switch (id.charAt(0)) {
			case 'A':
				list.add(new Administrator(username, username, password));
				break;
			case 'T':
				list.add(new Teacher(name, username, password));
			case 'P':
				String child = (String) e[5];
				// list.add(new Parent(name, username, password, children));
			}
			// list.add(new User(name, username, password));
		}

		// User user = new User("login",
		// "a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8");

		return list;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

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

		String sql = "SELECT * FROM enteUser WHERE id LIKE 'A%'";
		ArrayList<Object[]> resultSet = db.query(sql);
		for (Object[] e : resultSet) {
			String id = (String) e[0];
			String username = (String) e[1]; // e-mail as well
			String password = (String) e[2];
			boolean active = (boolean) e[3];
			String name = (String) e[4]; // name added coz user need it and there was comile error

			list.add(new Administrator(name, username, password)); // what with ID's and if it's active
																	// ?????????????????????????????????????/
		}
		return list;
	}

	private LinkedList<Teacher> getTeachers() throws SQLException {
		LinkedList<Teacher> list = new LinkedList<>();

		String sql = "SELECT * FROM enteUser WHERE id LIKE 'T%'";
		ArrayList<Object[]> resultSet = db.query(sql);
		for (Object[] e : resultSet) {
			String id = (String) e[0];
			String username = (String) e[1]; // e-mail as well
			String password = (String) e[2];
			boolean active = (boolean) e[3];
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
			String username = (String) e[1]; // e-mail as well
			String password = (String) e[2];
			boolean active = (boolean) e[3];
			String name = (String) e[4]; // name added coz user need it and there was comile error
			int studentClass = (int) e[5];
			list.add(new Student(name, username, password, null, null)); // what with ID's and if it's active
																	// ?????????????????????????????????????/
		}
		return list;	}

	private LinkedList<Parent> getParents() {
		return null;
	}

}
