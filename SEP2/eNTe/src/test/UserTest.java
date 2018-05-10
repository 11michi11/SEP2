package test;


import model.Teacher;
import model.User;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

	@Test
	void userUUIDTest() {
		User user = new Teacher("name", "email", "pwd");
		try{
		    UUID uuid = UUID.fromString(user.getId());
		} catch (IllegalArgumentException exception){
			fail();
		}
	}
	
	@Test
	void equalsTest() {
		User user1 = new Teacher("name", "email", "pwd");
		User user2 = new Teacher("name", "email", "pwd", user1.getId());
		assertEquals(true, user1.equals(user2));
	}
	
	@Test
	void gettersTest() {
		User user = new Teacher("name", "email", "pwd", "id");
		assertEquals("name", user.getName());
		assertEquals("email", user.getEmail());
		assertEquals("pwd", user.getPwd());
		assertEquals("id", user.getId());
	}
	
	@Test
	void testEquals() {
		User user1 = new Teacher("name", "email", "pwd");
		User user2 = new Teacher("name", "email", "pwd", user1.getId());
		User user1b = new Teacher("name", "email", "pwd");
		User user3 = new Teacher("name1", "email", "pwd");
		User user4 = new Teacher("name", "login1", "pwd");
		User user5 = new Teacher("name", "email", "pwd1");
		
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
		User user1 = new Teacher("name", "email", "pwd", "id");
		assertEquals("User [email=email, pwd=pwd, name=name, id=id]", user1.toString());
	}

}
