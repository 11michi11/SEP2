package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import utility.Password;
import org.junit.jupiter.api.Test;

import model.Administrator;
import model.User;

class AdministratorTets {

	@Test
	void testConstructors() {
		Administrator admin = new Administrator("name", "email", "pwd");
		assertEquals("name", admin.getName());
		assertEquals("email", admin.getEmail());
		assertEquals(Password.encryptPwd("pwd"), admin.getPwd());


		admin = new Administrator("name", "email", "pwd", "id");
		assertEquals("name", admin.getName());
		assertEquals("email", admin.getEmail());
		assertEquals(Password.encryptPwd("pwd"), admin.getPwd());
		assertEquals("id", admin.getId());
	}

	@Test
	void testToString() {
		User user1 = new Administrator("name", "email", "pwd", "id");

		assertEquals("User [email=email, pwd=" + Password.encryptPwd("pwd") + ", name=name, id=id]", user1.toString());
	}

}
