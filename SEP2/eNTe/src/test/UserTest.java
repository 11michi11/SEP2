package test;


import model.Administrator;
import utility.Password;
import model.Teacher;
import model.User;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
		User user1 = Teacher.builder().name("name").email("email").id("id").pwd("pwd").build();
		User user2 = Administrator.builder().name("name").email("email").id("id").pwd("pwd").build();
		User user3 = Administrator.builder().name("anotherName").email("email").id("id").pwd("pwd").build();
		User user4 = Administrator.builder().name("name").email("anotherEmail").id("id").pwd("pwd").build();
		User user5 = Administrator.builder().name("name").email("email").id("anotherId").pwd("pwd").build();
		User user6 = Administrator.builder().name("name").email("email").id("id").pwd("anotherPwd").build();
		User user7 = Administrator.builder().name("name").email("email").id("id").pwd("pwd").build();
		user7.setChangePassword(true);

		assertFalse(user1.equals(user2));
		assertFalse(user1.equals(user3));
		assertFalse(user1.equals(user4));
		assertFalse(user1.equals(user5));
		assertFalse(user1.equals(user6));
		assertFalse(user1.equals(user7));
	}

	@Test
	void gettersTest() {
		User user = Teacher.builder().name("name").email("email").pwdEncrypt("pwd").id("id").build();
		assertEquals("name", user.getName());
		assertEquals("email", user.getEmail());
		assertEquals(Password.encryptPwd("pwd"), user.getPwd());
		assertEquals("id", user.getId());
	}

	
	@Test
	void testToString() {
		User user1 = Teacher.builder().name("name").email("email").pwdEncrypt("pwd").id("id").build();
		assertEquals("User [email=email, pwd=" + Password.encryptPwd("pwd") + ", name=name, id=id]", user1.toString());
	}

}
