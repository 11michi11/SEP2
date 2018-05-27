package test;

import model.Family;
import model.FamilyList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FamilyListTest {

	private LinkedList<Family> families;

	@BeforeEach
	void setUp() {
		families = new LinkedList<>();
	}

	@Test
	void addFamilyTest() {
		Family f = new Family();
		families.add(f);
		assertEquals(1, families.size());
	}

	@Test
	void deleteFamilyTest() {
		Family f = new Family();
		families.add(f);
		families.remove(f);
		assertEquals(0, families.size());
	}

	@Test
	void getAllFamiliesTest() {
		FamilyList familyList = new FamilyList();
		Family f = new Family();
		familyList.addFamily(f);
		assertNotNull(familyList.getAllFamilies());

	}

	@Test
	void getSizeTest() {
		Family f1 = new Family();
		Family f2 = new Family();
		Family f3 = new Family();
		families.add(f1);
		families.add(f2);
		families.add(f3);
		assertEquals(3, families.size());
	}

	@Test
	void getFamilyByIdTest() {
		FamilyList familyList = new FamilyList();
		Family f = new Family("id");
		familyList.addFamily(f);
		assertEquals(f, familyList.getFamilyById("id"));

	}

	@Test
	void updateFamilyTest() {
		//TO DO
	}

}
