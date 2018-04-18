package test;


import static org.junit.Assert.fail;
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

}
