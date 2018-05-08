package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.communication.Auth;
import model.communication.LoginStatus;

class UsersListTest {

	private UsersList users;
	
	@BeforeEach
	void setUp() {
		users = new UsersList();
	}
	
	@Test
	void testAuthenticate() {		
		Auth auth = new Auth("login", "pwd");
		Teacher teacher = new Teacher("name", "login", auth.pwd);
		users.add(teacher);
		
		assertEquals(LoginStatus.SUCCESS, users.authenticate(auth));
		
		auth = new Auth("login", "pass");
		assertEquals(LoginStatus.FAILURE_PWD, users.authenticate(auth));

		auth = new Auth("email", "pass");
		assertEquals(LoginStatus.FAILURE_LOGIN, users.authenticate(auth));
		
	}
	
	@Test
	void testGetUserByLogin() {
		Teacher teacher = new Teacher("name", "login", "pwd");
		users.add(teacher);
		
		assertEquals(teacher, users.getUserByLogin("login"));		
	}
	
	@Test
	void testGetUserById() {
		Teacher teacher = new Teacher("name", "login", "pwd", "id");
		users.add(teacher);
		
		assertEquals(teacher, users.getUserById("id"));
	}
	
	@Test
	void testAddDeleteContains() {
		Teacher teacher = new Teacher("name", "login", "pwd", "id");
		users.add(teacher);
		
		assertEquals(true, users.contains(teacher));
		
		users.delete("id");
		assertEquals(false, users.contains(teacher));
	}
	
	@Test
	void getAll() {
		Teacher teacher = new Teacher("name", "login", "pwd");
		Administrator admin = new Administrator("name", "login", "pwd");
		Parent parent = new Parent("name", "login", "pwd");
		Student student = new Student("name", "login", "pwd", Classs.First, null);
		ArrayList<User> list = new ArrayList<>();
		list.add(teacher);
		list.add(admin);
		list.add(parent);
		list.add(student);
		
		users.add(list);
		
		assertEquals(list, users.getAll());
	}
	
	
}
