package utility.collection;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import model.Post;
import server.model.persistance.DBPersistance;
import server.model.persistance.DBAdapter;

public class DBGetFirstPost {

	private DBPersistance adapter;
	
	@Before
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
