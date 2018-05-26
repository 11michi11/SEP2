package test;

import model.Teacher;
import org.junit.jupiter.api.Test;
import utility.Password;

import static junit.framework.Assert.assertEquals;

public class TeacherTest {

	@Test
	void contructorTest() {
		Teacher t = Teacher.builder().name("name").email("email").pwdEncrypt("pwd").id("id").build();
		assertEquals("name", t.getName());
		assertEquals("email", t.getEmail());
		assertEquals(Password.encryptPwd("pwd"), t.getPwd());
		assertEquals("id", t.getId());

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
	}

}
