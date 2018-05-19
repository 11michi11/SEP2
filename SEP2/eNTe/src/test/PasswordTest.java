package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Password;

class PasswordTest {

	@Test
	void testLength() {
		assertEquals(8, Password.generateEntePassword().length());
	}

}
