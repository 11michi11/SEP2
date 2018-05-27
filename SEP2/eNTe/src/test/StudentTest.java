package test;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
	void testClsses() {
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
		student.setClassNo(ClassNo.Eigth);
		assertEquals(ClassNo.Eigth, student.getClassNo());
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
		Student student = Student.builder().name("child").email("email").classs(ClassNo.Eigth).family(f).historyOfActivity("").pwdEncrypt("pwd").id("id").build();

		assertEquals("child",student.getName());
		assertEquals("email",student.getEmail());
		assertEquals(ClassNo.Eigth,student.getClassNo());
		assertEquals(f.getId(), student.getFamilyId());
		assertEquals("",student.getHistoryOfActivity());
		assertEquals("id", student.getId());
		assertEquals(Password.encryptPwd("pwd"),student.getPwd());
	}

	@Test
	void buildNotFinishedTest() {
		Student studentNotFinished = Student.builder().name("child").email("email").classs(ClassNo.Eigth).build();
		try {
			UUID uuid = UUID.fromString(studentNotFinished.getId());
		} catch (IllegalArgumentException exception) {
			fail();
		}
		assertEquals("child", studentNotFinished.getName());
		assertEquals("email",studentNotFinished.getEmail());
		assertEquals(ClassNo.Eigth,studentNotFinished.getClassNo());
		assertEquals("there is no family", studentNotFinished.getFamilyId());
		assertNull(studentNotFinished.getHistoryOfActivity());
	}

}


	

