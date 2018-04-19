package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.RandomPassword;

class RandomPasswordTest {

	@Test
	void testLength() {
		assertEquals(10, RandomPassword.generatePassword(10).length());
	}

}
