package server.model.persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Administrator;
import model.Family;
import model.Parent;
import model.Post;
import model.Student;
import model.Teacher;
import model.User;
import model.UsersList;
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
	public LinkedList<Post> getPosts(UsersList users) throws SQLException {
		LinkedList<Post> list = new LinkedList<>();

		String sql = "SELECT * FROM Post";
		ArrayList<Object[]> resultSet = db.query(sql);
		for (Object[] e : resultSet) {
			String id = (String) e[0];
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
		
		switch (user.getClass().getSimpleName())
		{
			case "Student":
				Student student = (Student) user;
				sql += "student ('";
				sql += student.getId()+"','";
				//sql += student.getFamilyID ?????;
				sql += student.getClasss()+"')";
				db.update(sql);
				break;
				
			case "Parent":
				
				break;
				
			default: break;
		}
		
		sql = "INSERT INTO enteuser ('";
		sql += user.getId() + "','";
		sql += user.getLogin() + "','";
		sql += user.getPwd() + "',";
		//sql += user.getChangedPassword() + ",'";
		sql += user.getName() + "',')";
		sql += user.getClass().getSimpleName().toLowerCase()+"')";			//column for type of user
		db.update(sql);
	}

	@Override
	public void updateUser(User user) throws SQLException {
		ArrayList<String> sqlList = new ArrayList<>();
		String sql = "";
		String usertype = user.getClass().getSimpleName().toLowerCase();
		sql += "UPDATE enteuser SET"; 
		sql += "username='"+user.getLogin()+"',";
		sql += "pwd='"+user.getPwd()+"',";
		//sql += "changepassword="+user.isChangedPassword()+",";
		sql += "name='"+user.getName()+"',";
		sql += "type='"+usertype+"'";
		sql += "WHERE id='"+user.getId()+"'";
		sqlList.add(sql);
		switch (usertype) {
			case "student":
				Student student = (Student) user;
				sql = "UPDATE student SET class='"+student.getClasss()+"'";
				sql += "WHERE id='"+student.getId()+"'";
				sqlList.add(sql);
				//sql = "UPDATE family SET familyid='"+student.getFamily().getID+"',";
				sql += "type='"+usertype+"'";
				sql += "WHERE familymemberid='"+user.getId()+"'";
				sqlList.add(sql);
				break;
			case "parent":
				Parent parent = (Parent) user;
				//sql = "UPDATE family SET familyid='"+parent.getFamily().getID+"',";
				sql += "type='"+usertype+"'";
				sql += "WHERE familymemberid='"+user.getId()+"'";
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
			String login = (String) e[2];
			String pwd = (String) e[3];
			String name = (String) e[4];
			boolean changePassword = (boolean) e[5];

			//list.add(new Administrator(name, username, password, id)); 
		}
		return list;
	}

	private LinkedList<Teacher> getTeachers() throws SQLException {
		LinkedList<Teacher> list = new LinkedList<>();

		String sql = "SELECT * FROM enteUser WHERE usertype ='Teacher'";
		ArrayList<Object[]> resultSet = db.query(sql);
		for (Object[] e : resultSet) {
			String id = (String) e[0];
			String login = (String) e[2];
			String pwd = (String) e[3];
			String name = (String) e[4];
			boolean changePassword = (boolean) e[5];

			//list.add(new Teacher(name, username, password, id));
		}
		return list;
	}

	private LinkedList<Student> getStudents(LinkedList<Family> families) throws SQLException {
		LinkedList<Student> list = new LinkedList<>();

		String sql = "SELECT e.id, e.login, e.pwd, e.name, e.changePassword, s.familyid, s.class FROM enteuser e, student s WHERE e.id=s.studentid";
		ArrayList<Object[]> resultSet = db.query(sql);
		for (Object[] e : resultSet) {
			String id = (String) e[0];
			String login = (String) e[1];
			String pwd = (String) e[2];
			String name = (String) e[3];
			boolean changePassword = (boolean) e[4];
			String familyID = (String) e[5];
			model.Class studentClass = (model.Class) e[6];
//			Student student = new Student(blablabla);
//			student.setFamily(families.getFamilyById());
//			families.getFamilyById().addChild(student);
//			list.add(student);
		}
		return list;
	}

	private LinkedList<Parent> getParents(LinkedList<Family> families) throws SQLException {
		LinkedList<Parent> list = new LinkedList<>();

		String sql = "SELECT * FROM enteUser WHERE usertype ='Parent'";
		ArrayList<Object[]> resultSet = db.query(sql);
		for (Object[] e : resultSet) {
			String id = (String) e[0];
			String login = (String) e[2];
			String pwd = (String) e[3];
			String name = (String) e[4];
			boolean changePassword = (boolean) e[5];
//			Parent parent = new Parent(blablabla);
//			parent.setFamily(families.getFamilyById());
			families.getFamilyById().addParent(parent);
			list.add(new Parent(name, username, password, null, id));
		}
		return list;
	}
	
	private LinkedList<Family> getFamilies() throws SQLException {
		LinkedList<Family> list = new LinkedList<>();
		String lastFamilyID = "";
		Family lastFamily = new Family();
		String sql = "SELECT * FROM family ORDER BY familyid";
		ArrayList<Object[]> resultSet = db.query(sql);
		for (Object[] e : resultSet) {
			String familyID = (String) e[0];
//			list.add(new Family(familyID));
		}

		return list;
	}

	/*private LinkedList<Family> getFamilies(UsersList familyMembers) throws SQLException {
		LinkedList<Family> list = new LinkedList<>();
		String lastFamilyID = "";
		Family lastFamily = new Family();
		String sql = "SELECT * FROM family ORDER BY familyid";
		ArrayList<Object[]> resultSet = db.query(sql);
		for (int i = 0; i < resultSet.size(); i++) {
			if (i>0)
			{
				lastFamilyID = (String) resultSet.get(i-1)[0];
			}
			String familyID = (String) resultSet.get(i)[0];
			String memberID = (String) resultSet.get(i)[1];
			String usertype = (String) resultSet.get(i)[2];
			if (!lastFamilyID.equals(familyID))
			{
				//lastFamily = new Family(id);
				//shouldnt be in family class stored also ID ???, I would need constructor with given parameter ID
				
			}
			switch (usertype) {
			case "student":
				lastFamily.addChild((Student) familyMembers.getUserById(memberID));
				break;

			case "parent":
				lastFamily.addParent((Parent) familyMembers.getUserById(memberID));
				break;
			default:
				break;
			}
			list.add(lastFamily);
		}

		return list;
	}*/

	@Override
	public void addFamily(Family family) throws SQLException {
		String sql = "";
		ArrayList<String> sqlList = new ArrayList<>();
		ArrayList<User> list = new ArrayList<>();
		list.addAll(family.getChildren());
		list.addAll(family.getParents());
		for (User e:list)
		{
			sql = "INSERT INTO family ('";
			//sql += family.getID()+"','";
			sql += e.getId()+"')";
			sqlList.add(sql);
		}
		db.updateAll(sqlList);
	}

	@Override
	public void deleteFamily(Family family) throws SQLException {
		String sql = "";
		if(family.getChildren()!=null && family.getParents()!=null) {
//			sql = "DELETE FROM family WHERE familyid='"+family.getID()+"'";			
		}
		db.update(sql);
	}

}
