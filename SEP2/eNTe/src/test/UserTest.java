package test;


import utility.Password;
import model.Teacher;
import model.User;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

	@Test
	void userUUIDTest() {
		User user = Teacher.builder().name("name").email("email").pwdEncrypt("pwd").build();
		try{
		    UUID uuid = UUID.fromString(user.getId());
		} catch (IllegalArgumentException exception){
			fail();
		}
	}
	
	@Test
	void equalsTest() {
		//UNFINISHED, CREATE ANOTHER TRUE CASE
		User user1 = Teacher.builder().name("name").email("email").id("id").build();
		User user2 = Teacher.builder().name("name").email("email").id("id").build();
		assertEquals(false, user1.equals(user2));
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
