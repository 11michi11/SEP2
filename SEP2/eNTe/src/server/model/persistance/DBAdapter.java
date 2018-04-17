package server.model.persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Post;
import model.User;

public class DBAdapter implements DBPersistance{
	
	private MyDatabase db;
	private static final String DRIVER = "org.postgresql.Driver";
	//private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
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
		for (Object[] e: resultSet)
		{
			int id = (int) e[0];
			String title = (String) e[1];
			String content = (String) e[2];
			String author = (String) e[3];
			//String date = (String) e[4];
			list.add(new Post(title, content));		//maybe it's needed to add fields(author,date) to Post class
		}		
		return list;
	}

	@Override
	public LinkedList<User> getUsers() throws SQLException {
		LinkedList<User> list = new LinkedList<>();
		
		String sql = "SELECT id,username,password FROM Parent p, Teacher t, Student s";
		ArrayList<Object[]> resultSet = db.query(sql);
		for (Object[] e: resultSet)
		{
			int id = (int) e[0]; //maybe ID is not neccesary
			String username = (String) e[1];
			String password = (String) e[2];
			String name = (String) e[3]; //name added coz user need it and there was comile error
 			list.add(new User(name, username, password));
		}
		
//		User user = new User("login", "a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8");
		
		return list;
	}

	
	
}
