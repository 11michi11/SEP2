package test;

import model.Teacher;
import org.junit.jupiter.api.Test;
import utility.Password;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class TeacherTest {

	@Test
	void contructorTest() {
		Teacher teacher = new Teacher("name", "email", "pwd", "id");
		assertEquals("name", teacher.getName());
		assertEquals("email", teacher.getEmail());
		assertEquals(Password.encryptPwd("pwd"), teacher.getPwd());
		assertEquals("id", teacher.getId());
	}

	@Test
	void buildTeacherTest() {
		Teacher teacher = Teacher.builder().name("name").email("email").pwdEncrypt("pwd").id("id").build();
		assertEquals("name", teacher.getName());
		assertEquals("email", teacher.getEmail());
		assertEquals(Password.encryptPwd("pwd"), teacher.getPwd());
		assertEquals("id", teacher.getId());
	}

	@Test
	void buildNotFinishedTest() {
		Teacher teacher = Teacher.builder().name("name").email("email").build();
		assertEquals("name", teacher.getName());
		assertEquals("email", teacher.getEmail());
		assertNotNull(teacher.getId());
		assertNotNull(teacher.getPwd());
	}

}
