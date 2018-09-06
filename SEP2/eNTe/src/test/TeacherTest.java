package test;

import model.User;
import model.Teacher;
import org.junit.jupiter.api.Test;
import utility.Password;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertFalse;

public class TeacherTest {

	@Test
	void contructorTest() {
		Teacher t = Teacher.builder().name("name").email("email").responsibility("").pwdEncrypt("pwd").id("id").build();
		assertEquals("name", t.getName());
		assertEquals("email", t.getEmail());
		assertEquals("", t.getResponsibility());
		assertEquals(Password.encryptPwd("pwd"), t.getPwd());
		assertEquals("id", t.getId());
	}

	@Test
	void testEquals() {
		User user1 = Teacher.builder().name("name").email("email").responsibility("").pwdEncrypt("pwd").id("id").build();
		User user2 = Teacher.builder().name("name").email("email").responsibility("").pwdEncrypt("pwd").build();
		User user3 = Teacher.builder().name("anotherName").email("email").responsibility("").pwdEncrypt("pwd").id("id").build();
		User user4 = Teacher.builder().name("name").email("anotherEmail").responsibility("").pwdEncrypt("pwd").id("id").build();
		User user5 = Teacher.builder().name("name").email("email").responsibility("").pwdEncrypt("anotherPwd").id("id").build();
		User user6 = Teacher.builder().name("name").email("email").responsibility("").pwdEncrypt("pwd").id("anotherId").build();
		User user7 = Teacher.builder().name("name").email("email").responsibility("").pwdEncrypt("pwd").id("id").build();
		user7.setChangePassword(true);
		assertFalse(user1.equals(user2));
		assertFalse(user1.equals(user3));
		assertFalse(user1.equals(user4));
		assertFalse(user1.equals(user5));
		assertFalse(user1.equals(user6));
		assertFalse(user1.equals(user7));
	}

	@Test
	void buildTeacherTest() {
		Teacher teacher = Teacher.builder().name("name").email("email").responsibility("").pwdEncrypt("pwd").id("id").build();
		assertEquals("name", teacher.getName());
		assertEquals("email", teacher.getEmail());
		assertEquals("", teacher.getResponsibility());
		assertEquals(Password.encryptPwd("pwd"), teacher.getPwd());
		assertEquals("id", teacher.getId());
	}

	@Test
	void buildNotFinishedTest() {
		Teacher teacher = Teacher.builder().name("name").email("email").responsibility("").build();
		assertEquals("name", teacher.getName());
		assertEquals("email", teacher.getEmail());
		assertEquals("", teacher.getResponsibility());
		assertNotNull(teacher.getId());
		assertNotNull(teacher.getPwd());
	}

}
