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
		Teacher teacher = Teacher.builder().name("name").email( "email").responsibility("").pwdEncrypt( "pwd").build();
		users.add(teacher);
		
		assertEquals(LoginStatus.SUCCESS, users.authenticate(auth));
		
		auth = new Auth("email", "pass");
		assertEquals(LoginStatus.FAILURE_PWD, users.authenticate(auth));

		auth = new Auth("badLogin", "pass");
		assertEquals(LoginStatus.FAILURE_LOGIN, users.authenticate(auth));
		
	}
	
	@Test
	void testGetUserByLogin() {
		Teacher teacher = Teacher.builder().name("name").email( "email").responsibility("").pwdEncrypt( "pwd").build();
		users.add(teacher);
		
		assertEquals(teacher, users.getUserByEmail("email"));
	}
	
	@Test
	void testGetUserById() {
		Teacher teacher = Teacher.builder().name("name").email( "email").responsibility("").pwdEncrypt( "pwd").id("id").build();
		users.add(teacher);
		
		assertEquals(teacher, users.getUserById("id"));
	}
	
	@Test
	void testAddDeleteContains() {
		Teacher teacher = Teacher.builder().name("name").email( "email").responsibility("").pwdEncrypt( "pwd").id("id").build();
		users.add(teacher);
		
		assertEquals(true, users.contains(teacher));
		
		users.delete("id");
		assertEquals(false, users.contains(teacher));
	}
	
	@Test
	void getAll() {
		Teacher teacher = Teacher.builder().name("name").email( "email").responsibility("").pwdEncrypt( "pwd").build();
		Administrator admin = Administrator.builder().name("name").email( "email").pwdEncrypt( "pwd").build();
		Parent parent = Parent.builder().name("name").email( "email").pwdEncrypt( "pwd").build();
		Student student =  Student.builder().name("name").email("email").classNo(ClassNo.First).build();
		ArrayList<User> list = new ArrayList<>();
		list.add(teacher);
		list.add(admin);
		list.add(parent);
		list.add(student);
		
		users.addAll(list);
		
		assertEquals(list, users.getAll());
	}
	
	
}
