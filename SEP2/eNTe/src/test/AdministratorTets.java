package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.Administrator;
import model.Teacher;
import model.User;

class AdministratorTets {
	
	@Test
	void testConstructors() {
		Administrator admin = new Administrator("name", "login", "pwd");
		assertEquals("name", admin.getName());
		assertEquals("login", admin.getLogin());
		assertEquals("pwd", admin.getPwd());
		
		
		
		admin = new Administrator("name", "login", "pwd", "id");
		assertEquals("name", admin.getName());
		assertEquals("login", admin.getLogin());
		assertEquals("pwd", admin.getPwd());
		assertEquals("id", admin.getId());
	}

	@Test
	void testToString() {
		User user1 = new Administrator("name", "login", "pwd", "id");
		assertEquals("User [login=login, pwd=pwd, name=name, id=id]", user1.toString());
	}

}
