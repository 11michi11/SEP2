package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Classs;
import org.junit.jupiter.api.Test;

import model.Student;

class StudentTest {
	
	@Test
	void testClsses() {
		Student student = Student.builder().name("child1").email("email").classs(Classs.First).pwd("pwd").build();
		assertEquals(Classs.First, student.getClasss());

		student.setClasss(Classs.Second);
		assertEquals(Classs.Second, student.getClasss());
	}
	
	@Test
	void testHistoryOfActivity() {
		Student student = Student.builder().name("child1").email("email").classs(Classs.First).pwd("pwd").build();
		student.addHistoryOfActivity("history");
		assertEquals("history\n", student.getHistoryOfActivity());
	}
	
}
