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
		Student child1 = Student.builder().name("child1").email("email").classs(Classs.First).pwdEncrypt("pwd").build();
		Student child2 = Student.builder().name("child1").email("email").classs(Classs.First).pwdEncrypt("pwd").build();
		Student child3 = Student.builder().name("child1").email("email").classs(Classs.First).pwdEncrypt("pwd").build();
		children.add(child1);
		children.add(child2);
		children.add(child3);
		Parent parent = Parent.builder().name("name").email("email").pwdEncrypt("pwd").build();
		assertEquals(children, parent.getChildren());
	}

	@Test
	void testGetChildrenNames() {
		ArrayList<Student> children = new ArrayList<>();
		Student child1 = Student.builder().name("child1").email("email").classs(Classs.First).pwdEncrypt("pwd").build();
		Student child2 = Student.builder().name("child1").email("email").classs(Classs.First).pwdEncrypt("pwd").build();
		Student child3 = Student.builder().name("child1").email("email").classs(Classs.First).pwdEncrypt("pwd").build();
		children.add(child1);
		children.add(child2);
		children.add(child3);
		Family family = new Family();
		family.addChild(child1);
		family.addChild(child2);
		family.addChild(child3);
		Parent parent = Parent.builder().name("name").email("email").pwdEncrypt("pwd").family(family).build();
		assertEquals("child1, child2, child3, ", parent.getChildrenNames());
	}
}
