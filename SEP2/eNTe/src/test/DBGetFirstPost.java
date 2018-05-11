package test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.persistance.DBPersistence;
import server.model.persistance.DBAdapter;

public class DBGetFirstPost {

    private DBPersistence adapter;

    @BeforeEach
    public void before() throws ClassNotFoundException, SQLException {
        adapter = new DBAdapter();
    }

    @Test
    public void testFirstPost() throws SQLException {
        fail();
       // LinkedList<Post> list = adapter.getPosts();
//        System.out.println(list.getFirst());
//        assertEquals("Lessons cancelled", list.getFirst().getTitle());
    }

}
