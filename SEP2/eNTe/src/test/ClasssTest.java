package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import model.Classs;
import org.junit.jupiter.api.Test;

class ClasssTest {

	@Test
	void testGetClasses() {
		List<String> list = new ArrayList<>();
		list.add("First");
		list.add("Second");
		list.add("Third");
		list.add("Fourth");
		list.add("Fifth");
		list.add("Sixth");
		list.add("Seventh");
		list.add("Eigth");
		
		assertEquals(list, Classs.getClassesInStrings());
	}

}
