package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Student;
import model.Class;

class StudentTest {

	@Test
	void addParentITest() {
		Student student = new Student("name", "login", "pwd", Class.First, new ArrayList<String>());
		ArrayList<String> list = new ArrayList<>();
		list.add("parentID");
		student.addParentId("parentID");
		
		assertEquals(list, student.getParentsIDs());
	}

	@Test
	void constructorsTest() {
		ArrayList<String> parentsIDs = new ArrayList<>();
		parentsIDs.add("parentID");
		Student student = new Student("name", "login", "pwd", "id", Class.First, parentsIDs);
	
		assertEquals(Class.First, student.getClasss());
		assertEquals(parentsIDs, student.getParentsIDs());
	
	}
	
}
