package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Classs;
import model.Family;
import org.junit.jupiter.api.Test;

import model.Parent;
import model.Student;

class ParentTest {

	@Test
	void testConstructors() {
		Family f = new Family();
		Student child1 = Student.builder().name("child1").email("email").classs(Classs.First).pwd("pwd").family(f).build();
		Parent parent = Parent.builder().name("name").email("email").pwd("pwd").family(f).build();
		f.addChild(child1);
		f.addParent(parent);
		List<Student> list = new ArrayList<>();
		list.add(child1);
		assertEquals(list,parent.getChildren());
	}

	@Test
	void testGetChildrenNames() {
		ArrayList<Student> children = new ArrayList<>();
		Student child1 = Student.builder().name("child1").email("email").classs(Classs.First).pwd("pwd").build();
		Student child2 = Student.builder().name("child2").email("email").classs(Classs.First).pwd("pwd").build();
		Student child3 = Student.builder().name("child3").email("email").classs(Classs.First).pwd("pwd").build();
		children.add(child1);
		children.add(child2);
		children.add(child3);
		Family family = new Family();
		family.addChild(child1);
		family.addChild(child2);
		family.addChild(child3);
		Parent parent = Parent.builder().name("name").email("email").pwd("pwd").family(family).build();
		assertEquals("child1, child2, child3, ", parent.getChildrenNames());
	}
}
