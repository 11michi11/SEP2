package test;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.UUID;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FamilyTest {

	private Family f;


	@BeforeEach
	void setUp() {
		f = new Family();
	}

	@Test
	void idConstructorTest() {
		Family f = new Family("id");

		assertEquals("id", f.getId());
	}

	@Test
	void UUIDConstructorTest() {
		Family f = new Family();

		try {
			UUID uuid = UUID.fromString(f.getId());
		} catch (IllegalArgumentException exception) {
			fail();
		}
	}

	@Test
	void addChildTest() {
		Student child = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").build();
		f.addChild(child);
		assertEquals(child, f.getChild("child1"));
	}

	@Test
	void deleteChildTest() {
		Student child = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").build();
		f.addChild(child);
		f.deleteChild(child);
		assertEquals(Collections.EMPTY_LIST, f.getChildren());
		assertThrows(NoSuchElementException.class, () -> f.getChild("child1"));
	}

	@Test
	void getChildTest() {
		Student child = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").build();
		f.addChild(child);
		assertEquals(child, f.getChild("child1"));
		assertThrows(NoSuchElementException.class, () -> f.getChild("child2"));

	}

	@Test
	void getParentTest() {
		Parent p = Parent.builder().name("parent1").email("email").build();
		f.addParent(p);
		assertEquals(p, f.getParent("parent1"));
		assertThrows(NoSuchElementException.class, () -> f.getParent("p2"));
	}

	@Test
	void getMembersNamesTest() {
		assertEquals("", f.getMembersNames());
		Student child = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").build();
		f.addChild(child);
		assertEquals("child1", f.getMembersNames());

	}
	@Test
	void addParentTest() {
		Parent p = Parent.builder().name("parent1").email("email").build();
		f.addParent(p);
		assertEquals(p, f.getParent("parent1"));
	}

	@Test
	void deleteParentTest() {
		Parent p = Parent.builder().name("parent1").email("email").build();
		f.addParent(p);
		f.deleteParent(p);
		assertEquals(Collections.EMPTY_LIST, f.getParents());
		assertThrows(NoSuchElementException.class, () -> f.getChild("parent1"));
	}

	@Test
	void getParentsTest() {
		Parent p = Parent.builder().name("parent1").email("email").build();
		f.addParent(p);
		assertNotNull(f.getParents());
	}

	@Test
	void getClassesTest() {
		Student child1 = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").build();
		Student child2 = Student.builder().name("child2").email("email").classs(ClassNo.Second).pwdEncrypt("pwd").build();
		Student child3 = Student.builder().name("child3").email("email").classs(ClassNo.Third).pwdEncrypt("pwd").build();
		assertNotNull(f.getClasses());
	}

	@Test
	void getIdTest() {
		Family family = new Family("id");
		assertEquals("id", family.getId());
	}

//	@Test
//	void deleteMemberTest() {
//		Student student1 = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").build();
//		Student student2 = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").build();
//		f.addChild(student1);
//		f.deleteMember(student1);
//		assertEquals(student2, f.getChildren());
//
//	}

	@Test
	void clearTest() {
		Student s = Student.builder().name("child").email("email").classs(ClassNo.First).family(f).pwdEncrypt("pwd").build();
		Parent p = Parent.builder().name("parent").email("email").family(f).build();
		f.addChild(s);
		f.addParent(p);
		assertNotNull(s.getFamily());
		assertNotNull(p.getFamily());
		f.clear();
		assertNull(s.getFamily());
		assertNull(p.getFamily());
		assertEquals("removed", f.getId());


	}

	@Test
	void testEquals() {
		Family f1 = new Family("ID1");
		Family f2 = new Family("ID1");
		Student child1 = Student.builder().name("child1").email("email").classs(ClassNo.First).pwdEncrypt("pwd").build();
		Student child2 = Student.builder().name("child2").email("email").classs(ClassNo.Second).pwdEncrypt("pwd").build();
		Parent parent1 = Parent.builder().name("parent1").email("email").build();
		Parent parent2 = Parent.builder().name("parent2").email("email").build();

		assertEquals(f1,f2);
		f1.addChild(child1);
		assertFalse(f1.equals(f2));
		f1.addParent(parent1);
		assertFalse(f1.equals(f2));
		f2.addParent(parent1);
		assertFalse(f1.equals(f2));
		f2.addChild(child1);
		f2.addParent(parent2);
		assertFalse(f1.equals(f2));
		f1.addChild(child2);
		f2.addChild(child2);
		f1.addParent(parent2);
		assertEquals(f1,f2);
	}



}
