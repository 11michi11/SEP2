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
		Auth auth = new Auth("email", "pwd");
		Teacher teacher = new Teacher("name", "email", auth.pwd);
		users.add(teacher);
		
		assertEquals(LoginStatus.SUCCESS, users.authenticate(auth));
		
		auth = new Auth("email", "pass");
		assertEquals(LoginStatus.FAILURE_PWD, users.authenticate(auth));

		auth = new Auth("email", "pass");
		assertEquals(LoginStatus.FAILURE_LOGIN, users.authenticate(auth));
		
	}
	
	@Test
	void testGetUserByLogin() {
		Teacher teacher = new Teacher("name", "email", "pwd");
		users.add(teacher);
		
		assertEquals(teacher, users.getUserByLogin("email"));
	}
	
	@Test
	void testGetUserById() {
		Teacher teacher = new Teacher("name", "email", "pwd", "id");
		users.add(teacher);
		
		assertEquals(teacher, users.getUserById("id"));
	}
	
	@Test
	void testAddDeleteContains() {
		Teacher teacher = new Teacher("name", "email", "pwd", "id");
		users.add(teacher);
		
		assertEquals(true, users.contains(teacher));
		
		users.delete("id");
		assertEquals(false, users.contains(teacher));
	}
	
	@Test
	void getAll() {
		Teacher teacher = new Teacher("name", "email", "pwd");
		Administrator admin = new Administrator("name", "email", "pwd");
		Parent parent = new Parent("name", "email", "pwd");
		Student student =  Student.builder().name("name").email("email").classs(Classs.First).build();
		ArrayList<User> list = new ArrayList<>();
		list.add(teacher);
		list.add(admin);
		list.add(parent);
		list.add(student);
		
		users.add(list);
		
		assertEquals(list, users.getAll());
	}
	
	
}
