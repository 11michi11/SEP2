package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import utility.Password;
import org.junit.jupiter.api.Test;

import model.Administrator;
import model.User;

class AdministratorTest {

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

	@Test()
	void builderTest() {
		Administrator adm = Administrator.builder().name("name").email("email").pwd("pwd").id("id").build();
		assertEquals("name", adm.getName());
		assertEquals("email", adm.getEmail());
		assertEquals("pwd", adm.getPwd());
		assertEquals("id", adm.getId());
	}

	@Test
	void builderNotFinished() {
		Administrator adm = Administrator.builder().name("name").email("email").build();
		assertEquals("name", adm.getName());
		assertEquals("email", adm.getEmail());
		assertNotNull(adm.getId());
		assertNotNull(adm.getPwd());
	}

}
