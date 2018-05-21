package test;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class StudentTest {


	@Test
	void initializeStudentTest() {
		Family f = new Family("id");
		Student student = Student.builder().name("child1").email("email").classs(Classs.First).pwdEncrypt("pwd").family(f).build();
		assertEquals(Classs.First, student.getClasss());
		assertEquals("id", student.getFamilyID());
	}

	@Test
	void testClsses() {
		Student student = Student.builder().name("child1").email("email").classs(Classs.First).pwdEncrypt("pwd").build();
		assertEquals(Classs.First, student.getClasss());

		student.setClasss(Classs.Second);
		assertEquals(Classs.Second, student.getClasss());
	}

	@Test
	void testAddHistoryOfActivity() {
		Student student = Student.builder().name("child1").email("email").classs(Classs.First).pwdEncrypt("pwd").historyOfActivity("").build();
		student.addHistoryOfActivity("someText");
		assertEquals("someText\n", student.getHistoryOfActivity());
	}

	@Test
	void getHistoryOfActivityTest() {
		Student student = Student.builder().name("child1").email("email").classs(Classs.First).pwdEncrypt("pwd").historyOfActivity("").build();
		student.addHistoryOfActivity("someText");
		assertEquals("someText\n", student.getHistoryOfActivity());
	}

	@Test
	void setClasssTest() {
		Student student = Student.builder().name("child1").email("email").classs(Classs.First).pwdEncrypt("pwd").historyOfActivity("").build();
		student.setClasss(Classs.First);
		assertEquals(Classs.First, student.getClasss());
		student.setClasss(Classs.Eigth);
		assertEquals(Classs.Eigth, student.getClasss());
	}

	@Test
	void getClasssTest() {
		Student student = Student.builder().name("child1").email("email").classs(Classs.First).pwdEncrypt("pwd").historyOfActivity("").build();
		assertEquals(Classs.First, student.getClasss());
	}

	@Test
	void getFamilyIdTest() {
		Family f = new Family("id");
		Student student = Student.builder().name("child1").email("email").classs(Classs.First).pwdEncrypt("pwd").family(f).build();
		assertEquals("id", student.getFamilyID());

	}

	@Test
	void buildTest() {
		Family f = new Family("id");
		Student student = Student.builder().name("child").email("email").classs(Classs.Eigth).family(f).historyOfActivity("").pwdEncrypt("pwd").id("id").build();

		assertEquals("child",student.getName());
		assertEquals("email",student.getEmail());
		assertEquals(Classs.Eigth,student.getClasss());
		assertEquals(f.getId(), student.getFamilyID());
		assertEquals("",student.getHistoryOfActivity());
		assertEquals("id", student.getId());
		assertEquals(Password.encryptPwd("pwd"),student.getPwd());
	}

	@Test
	void buildNotFinishedTest() {
		Student studentNotFinished = Student.builder().name("child").email("email").classs(Classs.Eigth).build();
		try {
			UUID uuid = UUID.fromString(studentNotFinished.getId());
		} catch (IllegalArgumentException exception) {
			fail();
		}
		assertNotNull(studentNotFinished.getName());
		assertNotNull(studentNotFinished.getEmail());
		assertNotNull(studentNotFinished.getClasss());
		assertEquals("there is no family", studentNotFinished.getFamilyID());
		assertNull(studentNotFinished.getHistoryOfActivity());
	}

}


	

