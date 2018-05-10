package server.model.persistance;

import model.*;
import utility.persistence.MyDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import client.model.FamiliesList;

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
	public LinkedList<Post> getPosts(UsersList users) throws SQLException {
		LinkedList<Post> list = new LinkedList<>();

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
		return list;
	}

	@Override
	public LinkedList<User> getUsers(FamiliesList families) throws SQLException {
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
//
// NOT FINISHED
//
//
	@Override
	public void addUser(User user) throws SQLException {
		ArrayList<String> sqlList = new ArrayList<>();
		String sql = "INSERT INTO ";
		
		switch (user.getClass().getSimpleName())
		{
			case "Student":
				Student student = (Student) user;
				sql += "student ('";
				sql += student.getId()+"','";
				sql += student.getFamilyID()+"','";
				sql += student.getClasss()+"')";
				sqlList.add(sql);
				break;
				
			case "Parent":
				Parent parent = (Parent) user;
				sql += "parent ('";
				sql += parent.getId()+"','";
				sql += parent.getFamily().getId()+"')";
				sqlList.add(sql);
				break;
				
			default: break;
		}
		
		sql = "INSERT INTO enteuser ('";
		sql += user.getId() + "','";
		sql += user.getClass().getSimpleName()+"','";
		sql += user.getEmail() + "','";
		sql += user.getPwd() + "','";
		sql += user.getName() + "',";
		sql += user.isPasswordChangeNeeded() + ")";
		sqlList.add(sql);
		db.updateAll(sqlList);
	}

	@Override
	public void updateUser(User user) throws SQLException {
		ArrayList<String> sqlList = new ArrayList<>();
		String sql = "";
		String usertype = user.getClass().getSimpleName();
		sql += "UPDATE enteuser SET"; 
		sql += "type='"+usertype+"',";
		sql += "email='"+user.getEmail()+"',";
		sql += "pwd='"+user.getPwd()+"',";
		sql += "changepassword="+user.isPasswordChangeNeeded()+",";
		sql += "name='"+user.getName()+"' ";
		sql += "WHERE id='"+user.getId()+"'";
		sqlList.add(sql);
		switch (usertype) {
			case "student":
				Student student = (Student) user;
				sql = "UPDATE student SET class='"+student.getClasss()+"',";
				sql += "familyid='"+student.getFamilyID()+"'";
				sql += "WHERE studentid='"+student.getId()+"'";
				sqlList.add(sql);
				break;
			case "parent":
				Parent parent = (Parent) user;
				sql = "UPDATE parent SET ";
				sql += "familyid='"+parent.getFamily().getId()+"' ";
				sql += "WHERE parentid='"+parent.getId()+"'";
				sqlList.add(sql);
				break;
			default:break;
		}
		db.updateAll(sqlList);
	}

	@Override
	public void deleteUser(String id) throws SQLException {
		String sql = "DELETE FROM enteuser WHERE id='"+id+"'";
		db.update(sql);
	}

	private LinkedList<Administrator> getAdmins() throws SQLException {

		LinkedList<Administrator> list = new LinkedList<>();

		String sql = "SELECT * FROM enteUser WHERE usertype ='Administrator'";
		ArrayList<Object[]> resultSet = db.query(sql);
		for (Object[] e : resultSet) {
			String id = (String) e[0];
			String email = (String) e[2];
			String pwd = (String) e[3];
			String name = (String) e[4];
			boolean changePassword = (boolean) e[5];
			list.add(new Administrator(name, email, pwd, id));
		}
		return list;
	}

	private LinkedList<Teacher> getTeachers() throws SQLException {
		LinkedList<Teacher> list = new LinkedList<>();

		String sql = "SELECT * FROM enteUser WHERE usertype ='Teacher'";
		ArrayList<Object[]> resultSet = db.query(sql);
		for (Object[] e : resultSet) {
			String id = (String) e[0];
			String email = (String) e[2];
			String pwd = (String) e[3];
			String name = (String) e[4];
			boolean changePassword = (boolean) e[5];
			list.add(new Teacher(name, email, pwd, id));
		}
		return list;
	}

	private LinkedList<Student> getStudents(FamiliesList families) throws SQLException {
		LinkedList<Student> list = new LinkedList<>();

		//parameter is of type FamiliesList from client model package, needs to be changed
		
		String sql = "SELECT e.id, e.login, e.pwd, e.name, e.changePassword, s.familyid, s.class FROM enteuser e, student s WHERE e.id=s.studentid";
		ArrayList<Object[]> resultSet = db.query(sql);
		for (Object[] e : resultSet) {
			String id = (String) e[0];
			String email = (String) e[1];
			String pwd = (String) e[2];
			String name = (String) e[3];
			boolean changePassword = (boolean) e[4];
			String familyID = (String) e[5];
			Classs classs = (Classs) e[6];
//			Student student = Student.builder().name(name).login(email).classs(classs).family(families.getFamilyById(familyID));
//			families.getFamilyById(familyID).addChild(student);
//			list.add(student);
		}
		return list;
	}

	private LinkedList<Parent> getParents(FamiliesList families) throws SQLException {
		LinkedList<Parent> list = new LinkedList<>();

		String sql = "SELECT e.id, e.login, e.pwd, e.name, e.changePassword, p.familyid FROM enteuser e, parent p WHERE e.id=p.parentid";
		ArrayList<Object[]> resultSet = db.query(sql);
		for (Object[] e : resultSet) {
			String id = (String) e[0];
			String email = (String) e[2];
			String pwd = (String) e[3];
			String name = (String) e[4];
			boolean changePassword = (boolean) e[5];
			String familyID = (String) e[5];
//			Parent parent = Parent.builder().name(name).email(email).id(id).pwd(pwd).family(families.getFamilyById(familyID));
//			families.getFamilyById(familyID).addParent(parent);
//			list.add(parent);
		}
		return list;
	}
	@Override
	public LinkedList<Family> getFamilies() throws SQLException {
		LinkedList<Family> list = new LinkedList<>();
		
		String sql = "SELECT * FROM family ORDER BY familyid";
		ArrayList<Object[]> resultSet = db.query(sql);
		for (Object[] e : resultSet) {
			String familyID = (String) e[0];
			list.add(new Family(familyID));
		}

		return list;
	}

	@Override
	public void addFamily(Family family) throws SQLException {
		String sql = "INSERT INTO family ('";
		sql += family.getId() + "')";

		db.update(sql);
	}

	@Override
	public void deleteFamily(Family family) throws SQLException {
		String sql = "";
		if(family.getChildren()!=null && family.getParents()!=null) {
			sql = "DELETE FROM family WHERE familyid='"+family.getId()+"'";			
		}
		db.update(sql);
	}

}