package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ClassTest {

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
		
		assertEquals(list, model.Class.getClasses());
	}

}
