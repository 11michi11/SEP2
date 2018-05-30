package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import utility.Password;
import org.junit.jupiter.api.Test;

import model.Administrator;
import model.User;

class AdministratorTest {

	@Test
	void testConstructors() {
		Administrator admin = Administrator.builder().name("name").email("email").pwdEncrypt("pwd").build();
		assertEquals("name", admin.getName());
		assertEquals("email", admin.getEmail());
		assertEquals(Password.encryptPwd("pwd"), admin.getPwd());


		admin = Administrator.builder().name("name").email("email").pwdEncrypt("pwd").id("id").build();
		assertEquals("name", admin.getName());
		assertEquals("email", admin.getEmail());
		assertEquals(Password.encryptPwd("pwd"), admin.getPwd());
		assertEquals("id", admin.getId());
	}

	@Test
	void testEquals() {
		User user1 = Administrator.builder().name("name").email("email").pwdEncrypt("pwd").id("id").build();
		User user2 = Administrator.builder().name("name").email("email").pwdEncrypt("pwd").build();
		User user3 = Administrator.builder().name("anotherName").email("email").pwdEncrypt("pwd").id("id").build();
		User user4 = Administrator.builder().name("name").email("anotherEmail").pwdEncrypt("pwd").id("id").build();
		User user5 = Administrator.builder().name("name").email("email").pwdEncrypt("anotherPwd").id("id").build();
		User user6 = Administrator.builder().name("name").email("email").pwdEncrypt("pwd").id("anotherId").build();
		User user7 = Administrator.builder().name("name").email("email").pwdEncrypt("pwd").id("id").build();
		user7.setChangePassword(true);
		assertFalse(user1.equals(user2));
		assertFalse(user1.equals(user3));
		assertFalse(user1.equals(user4));
		assertFalse(user1.equals(user5));
		assertFalse(user1.equals(user6));
		assertFalse(user1.equals(user7));
	}

	@Test
	void testToString() {
		User user1 = Administrator.builder().name("name").email("email").pwdEncrypt("pwd").id("id").build();
		assertEquals("User [email=email, pwd=" + Password.encryptPwd("pwd") + ", name=name, id=id]", user1.toString());
	}

	@Test()
	void builderTest() {
		Administrator adm = Administrator.builder().name("name").email("email").pwdEncrypt("pwd").id("id").build();
		assertEquals("name", adm.getName());
		assertEquals("email", adm.getEmail());
		assertEquals("id", adm.getId());
		assertEquals(Password.encryptPwd("pwd"), adm.getPwd());
	}

	@Test()
	void builderTestPwd() {
		Administrator adm = Administrator.builder().name("name").email("email").pwd("pwd").id("id").build();
		assertEquals("name", adm.getName());
		assertEquals("email", adm.getEmail());
		assertEquals("id", adm.getId());
		assertEquals("pwd", adm.getPwd());
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
