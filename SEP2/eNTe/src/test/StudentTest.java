package test;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import model.*;
import org.junit.jupiter.api.Test;
import utility.Password;

import java.util.UUID;

class StudentTest {


	@Test
	void initializeStudentTest() {
		Family f = new Family("id");
		Student student = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").family(f).build();
		assertEquals(ClassNo.First, student.getClassNo());
		assertEquals("id", student.getFamilyId());
	}

	@Test
	void testEquals() {
		Family family = new Family("fID");
		Family family2 = new Family("fID2");
		User student1 = Student.builder().name("name").email("email").classs(ClassNo.First).pwdEncrypt("pwd").family(family).build();
		User student2 = Student.builder().name("name").email("email").classs(ClassNo.First).pwdEncrypt("pwd").family(family).build();
		User student3 = Student.builder().name("anotherName").email("email").classs(ClassNo.First).pwdEncrypt("pwd").family(family).build();
		User student4 = Student.builder().name("name").email("anotherEmail").classs(ClassNo.First).pwdEncrypt("pwd").family(family).build();
		User student5 = Student.builder().name("name").email("Email").classs(ClassNo.Second).pwdEncrypt("pwd").family(family).build();
		User student6 = Student.builder().name("name").email("email").classs(ClassNo.First).pwdEncrypt("anotherPwd").family(family).build();
		User student7 = Student.builder().name("name").email("email").classs(ClassNo.First).pwdEncrypt("pwd").family(family2).build();
		User student8 = Student.builder().name("name").email("email").classs(ClassNo.First).pwdEncrypt("pwd").family(family).build();
		student8.setChangePassword(true);
		assertFalse(student1.equals(student2)); //different IDs
		assertFalse(student1.equals(student3));
		assertFalse(student1.equals(student4));
		assertFalse(student1.equals(student5));
		assertFalse(student1.equals(student6));
		assertFalse(student1.equals(student7));
		assertFalse(student1.equals(student8));
	}
	
	@Test
	void testClasses() {
		Student student = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").build();
		assertEquals(ClassNo.First, student.getClassNo());

		student.setClassNo(ClassNo.Second);
		assertEquals(ClassNo.Second, student.getClassNo());
	}

	@Test
	void testAddHistoryOfActivity() {
		Student student = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").historyOfActivity("").build();
		student.addHistoryOfActivity("someText");
		assertEquals("someText\n", student.getHistoryOfActivity());
	}

	@Test
	void getHistoryOfActivityTest() {
		Student student = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").historyOfActivity("").build();
		student.addHistoryOfActivity("someText");
		assertEquals("someText\n", student.getHistoryOfActivity());
	}

	@Test
	void setClasssTest() {
		Student student = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").historyOfActivity("").build();
		student.setClassNo(ClassNo.First);
		assertEquals(ClassNo.First, student.getClassNo());
		student.setClassNo(ClassNo.Eighth);
		assertEquals(ClassNo.Eighth, student.getClassNo());
	}

	@Test
	void getClasssTest() {
		Student student = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").historyOfActivity("").build();
		assertEquals(ClassNo.First, student.getClassNo());
	}

	@Test
	void getFamilyIdTest() {
		Family f = new Family("id");
		Student student = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").family(f).build();
		assertEquals("id", student.getFamilyId());

	}

	@Test
	void buildTest() {
		Family f = new Family("id");
		Student student = Student.builder().name("child").email("email").classs(ClassNo.Eighth).family(f).historyOfActivity("").pwdEncrypt("pwd").id("id").build();

		assertEquals("child",student.getName());
		assertEquals("email",student.getEmail());
		assertEquals(ClassNo.Eighth,student.getClassNo());
		assertEquals(f.getId(), student.getFamilyId());
		assertEquals("",student.getHistoryOfActivity());
		assertEquals("id", student.getId());
		assertEquals(Password.encryptPwd("pwd"),student.getPwd());
	}

	@Test
	void buildNotFinishedTest() {
		Student studentNotFinished = Student.builder().name("child").email("email").classs(ClassNo.Eighth).build();
		try {
			UUID uuid = UUID.fromString(studentNotFinished.getId());
		} catch (IllegalArgumentException exception) {
			fail();
		}
		assertEquals("child", studentNotFinished.getName());
		assertEquals("email",studentNotFinished.getEmail());
		assertEquals(ClassNo.Eighth,studentNotFinished.getClassNo());
		assertEquals("there is no family", studentNotFinished.getFamilyId());
		assertNull(studentNotFinished.getHistoryOfActivity());
	}

}


	

