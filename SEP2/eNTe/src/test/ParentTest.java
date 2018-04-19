package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Parent;
import model.Student;

class ParentTest {

	@Test
	void testConstrustors() {
		ArrayList<Student> children = new ArrayList<>();
		Student child1 = new Student("child1", "login", "pwd", model.Class.First, new ArrayList<String>());
		Student child2 = new Student("child2", "login", "pwd", model.Class.First, new ArrayList<String>());
		Student child3 = new Student("child3", "login", "pwd", model.Class.First, new ArrayList<String>());
		children.add(child1);
		children.add(child2);
		children.add(child3);
		Parent parent = new Parent("name", "login", "pwd", children);
		assertEquals(children, parent.getChildren());
	}

	@Test
	void testGetChildrenNames() {
		ArrayList<Student> children = new ArrayList<>();
		Student child1 = new Student("child1", "login", "pwd", model.Class.First, new ArrayList<String>());
		Student child2 = new Student("child2", "login", "pwd", model.Class.First, new ArrayList<String>());
		Student child3 = new Student("child3", "login", "pwd", model.Class.First, new ArrayList<String>());
		children.add(child1);
		children.add(child2);
		children.add(child3);
		Parent parent = new Parent("name", "login", "pwd", children);
		assertEquals("child1, child2, child3, ", parent.getChildrenNames());
	}

	@Test
	void testAddRemoveChild() {
		Parent parent = new Parent("name", "login", "pwd", new ArrayList<Student>());

		Student child1 = new Student("child1", "login", "pwd", model.Class.First, new ArrayList<String>());
		parent.addChild(child1);
		
		assertEquals(true, parent.getChildren().contains(child1));
		parent.removeChild(child1);
		assertEquals(false, parent.getChildren().contains(child1));
	}
}
