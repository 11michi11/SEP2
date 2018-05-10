package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.LinkedList;


import model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.persistance.DBPersistance;
import server.model.persistance.DBAdapter;

public class DBGetFirstPost {

	private DBPersistance adapter;
	
	@BeforeEach
	public void before() throws ClassNotFoundException, SQLException
	{
		adapter = new DBAdapter();
	}
	@Test
	public void testFirstPost() throws SQLException {
		LinkedList<Post> list = adapter.getPosts();
		System.out.println(list.getFirst());
		assertEquals("Lessons cancelled", list.getFirst().getTitle());
	}

}
