package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import model.Classs;
import org.junit.jupiter.api.Test;

import model.Student;

class StudentTest {

	@Test
	void addParentITest() {
		Student student = new Student("name", "login", "pwd", Classs.First, new ArrayList<String>());
		ArrayList<String> list = new ArrayList<>();
		list.add("parentID");
		student.addParentId("parentID");
		
		assertEquals(list, student.getParentsIDs());
	}

	@Test
	void constructorsTest() {
		ArrayList<String> parentsIDs = new ArrayList<>();
		parentsIDs.add("parentID");
		Student student = new Student("name", "login", "pwd", "id", Classs.First, parentsIDs);
	
		assertEquals(Classs.First, student.getClasss());
		assertEquals(parentsIDs, student.getParentsIDs());
	}
	
	@Test
	void testClsses() {
		Student student = new Student("name", "login", "pwd", Classs.First, new ArrayList<String>());
		assertEquals(Classs.First, student.getClasss());

		student.setClasss(Classs.Second);
		assertEquals(Classs.Second, student.getClasss());
	}
	
	@Test
	void testHistoryOfActivity() {
		Student student = new Student("name", "login", "pwd", Classs.First, new ArrayList<String>());
		student.addHistoryOfActivity("history");
		assertEquals("history\n", student.getHistoryOfActivity());
	}
	
}
