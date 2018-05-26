package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.ClassNo;
import model.Family;
import org.junit.jupiter.api.Test;

import model.Parent;
import model.Student;
import utility.Password;

import static org.junit.jupiter.api.Assertions.*;

class ParentTest {

	@Test
	void testConstructors() {
		Family f = new Family();
		Parent p = Parent.builder().name("name").email("email").build();
		assertEquals("name", p.getName());
		assertEquals("email", p.getEmail());
		Parent p1 = Parent.builder().name("name").email("email").pwdEncrypt("pwd").build();
		assertEquals("name", p1.getName());
		assertEquals("email", p1.getEmail());
		assertEquals(Password.encryptPwd("pwd"), p1.getPwd());
		Parent p2 = Parent.builder().name("name").email("email").pwdEncrypt("pwd").family(f).id("id").build();
		assertEquals("name", p2.getName());
		assertEquals("email", p2.getEmail());
		assertEquals(Password.encryptPwd("pwd"), p2.getPwd());
		assertEquals(f,p2.getFamily());
		assertEquals("id", p2.getId());

	}

	@Test
	void testGetChildrenNames() {
		Parent p = Parent.builder().name("name").email("email").build();
		assertEquals("", p.getChildrenNames());
		ArrayList<Student> children = new ArrayList<>();
		Student child1 = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").build();
		Student child2 = Student.builder().name("child2").email("email").classs(ClassNo.First).pwdEncrypt("pwd").build();
		Student child3 = Student.builder().name("child3").email("email").classs(ClassNo.First).pwdEncrypt("pwd").build();
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

	@Test
	void getChildrenTest() {
		Student child1 = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").build();
		Family family = new Family();
		family.addChild(child1);
		Parent parent = Parent.builder().name("name").email("email").pwdEncrypt("pwd").family(family).build();
		assertEquals(family.getChildren(), parent.getChildren());
	}

	@Test
	void getFamilyTest() {
		Family family = new Family();
		assertNotNull(family);
	}

	@Test
	void getFamilyIdTest() {
		Family family = new Family("id");
		Parent parent = Parent.builder().name("name").email("email").pwdEncrypt("pwd").family(family).build();
		assertEquals("id", parent.getFamilyId());
	}

	@Test
	void setFamilyTest() {
		Family family = new Family();
		Parent parent = Parent.builder().name("name").email("email").pwdEncrypt("pwd").family(family).build();
		assertEquals(family, parent.getFamily());
	}

	@Test
	void buildParentTest() {
		Family family = new Family("id");
		Parent parent = Parent.builder().name("name").email("email").pwdEncrypt("pwd").family(family).id("id").build();
		assertEquals("name", parent.getName());
		assertEquals("email", parent.getEmail());
		assertEquals(Password.encryptPwd("pwd"), parent.getPwd());
		assertEquals(family, parent.getFamily());
		assertEquals("id", parent.getId());

	}

	@Test
	void buildParentNotFinished() {
		Parent parent = Parent.builder().name("name").email("email").pwdEncrypt("pwd").build();
		assertEquals("name", parent.getName());
		assertEquals("email", parent.getEmail());
		assertEquals(Password.encryptPwd("pwd"), parent.getPwd());
		assertNull(parent.getFamily());


	}

}
