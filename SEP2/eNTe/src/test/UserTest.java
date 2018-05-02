package test;


import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import model.Teacher;
import model.User;

class UserTest {

	@Test
	void userUUIDTest() {
		User user = new Teacher("name", "login", "pwd");
		try{
		    UUID uuid = UUID.fromString(user.getId());
		} catch (IllegalArgumentException exception){
			fail();
		}
	}
	
	@Test
	void equalsTest() {
		User user1 = new Teacher("name", "login", "pwd");
		User user2 = new Teacher("name", "login", "pwd", user1.getId());
		assertEquals(true, user1.equals(user2));
	}
	
	@Test
	void gettersTest() {
		User user = new Teacher("name", "login", "pwd", "id");
		assertEquals("name", user.getName());
		assertEquals("login", user.getLogin());
		assertEquals("pwd", user.getPwd());
		assertEquals("id", user.getId());
	}
	
	@Test
	void testEquals() {
		User user1 = new Teacher("name", "login", "pwd");
		User user2 = new Teacher("name", "login", "pwd", user1.getId());
		User user1b = new Teacher("name", "login", "pwd");
		User user3 = new Teacher("name1", "login", "pwd");
		User user4 = new Teacher("name", "login1", "pwd");
		User user5 = new Teacher("name", "login", "pwd1");
		
		assertEquals(true, user1.equals(user2));
		assertEquals(true, user2.equals(user1));
		
		assertEquals(false, user1.equals(user1b));
		assertEquals(false, user1.equals(user3));
		assertEquals(false, user1.equals(user4));
		assertEquals(false, user1.equals(user5));
		assertEquals(false, user1.equals(new Object()));
	}
	
	@Test
	void testToString() {
		User user1 = new Teacher("name", "login", "pwd", "id");
		assertEquals("User [login=login, pwd=pwd, name=name, id=id]", user1.toString());
	}

}
