package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import model.Classs;
import model.Family;
import org.junit.jupiter.api.Test;

import model.Parent;
import model.Student;

class ParentTest {

	@Test
	void testConstrustors() {
		ArrayList<Student> children = new ArrayList<>();
		Student child1 = new Student("child1", "login", "pwd", Classs.First, new ArrayList<>());
		Student child2 = new Student("child2", "login", "pwd", Classs.First, new ArrayList<>());
		Student child3 = new Student("child3", "login", "pwd", Classs.First, new ArrayList<>());
		children.add(child1);
		children.add(child2);
		children.add(child3);
		Parent parent = Parent.builder().name("name").login("email").pwd("pwd").build();
		assertEquals(children, parent.getChildren());
	}

	@Test
	void testGetChildrenNames() {
		ArrayList<Student> children = new ArrayList<>();
		Student child1 = new Student("child1", "login", "pwd", Classs.First, new ArrayList<>());
		Student child2 = new Student("child2", "login", "pwd", Classs.First, new ArrayList<>());
		Student child3 = new Student("child3", "login", "pwd", Classs.First, new ArrayList<>());
		children.add(child1);
		children.add(child2);
		children.add(child3);
		Family family = new Family("id");
		family.addChild(child1);
		family.addChild(child2);
		family.addChild(child3);
		Parent parent = Parent.builder().name("name").login("email").pwd("pwd").family(family).build();
		assertEquals("child1, child2, child3, ", parent.getChildrenNames());
	}
}
