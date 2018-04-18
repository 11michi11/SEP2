package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import client.model.ClientModelManager;
import model.Administrator;
import model.Class;
import model.Parent;
import model.Post;
import model.Student;
import model.Teacher;
import model.communication.WelcomingData;
import server.controller.ServerMain;

class ClientModelManagerTest {

	public ClientModelManager model;
	
	@BeforeAll
	
	static void startServer() {
		Thread t = new Thread(() -> {
			ServerMain.main(new String[0]);
		});
		t.start();
		System.out.println("Server");
	}

	@BeforeEach
	void setup() {
		
		model = new ClientModelManager();
	}

	@Test
	void getAndStorePostTest() {
		Post post = new Post("Title", "Content");
		model.storePost(post);

		assertEquals(new Post("Title", "Content"), model.getPost());
	}

	@Test
	void saveDataFromWelcomingData() {
		Post post1 = new Post("Title1", "Content");
		Post post2 = new Post("Title2", "Content");
		Post post3 = new Post("Title3", "Content");
		WelcomingData data = new WelcomingData();
		LinkedList<Post> list1 = new LinkedList<Post>();
		LinkedList<Post> list2 = new LinkedList<Post>();
		LinkedList<Post> list3 = new LinkedList<Post>();
		list1.add(post1);
		list1.add(post2);
		list1.add(post3);
		list3 = (LinkedList<Post>) list1.clone();
		data.insertPosts(list1);
		model.saveData(data);

		for (int i = 0; i < 3; i++)
			list2.add(model.getPost());

		assertEquals(list3, list2);
	}

	@Test
	void getParentsTest() {
		Parent parent1 = new Parent("name1", "login", "pwd", new ArrayList<Student>());
		Parent parent2 = new Parent("name2", "login", "pwd", new ArrayList<Student>());
		ArrayList<Parent> list = new ArrayList<Parent>();
		list.add(parent1);
		list.add(parent2);

		model.addOrUpdateUser(parent1);
		model.addOrUpdateUser(parent2);
		assertEquals(list, model.getParents());
	}
	
	@Test
	void deleteUserandGetUserByIdTest() {
		Parent parent = new Parent("name1", "login", "pwd", new ArrayList<Student>());
		Student student = new Student("name", "login", "pwd", Class.First, new ArrayList<String>());
		Teacher teacher = new Teacher("name", "login", "pwd");
		Administrator admin = new Administrator("name", "login", "pwd");
		
		model.addOrUpdateUser(admin);
		model.addOrUpdateUser(parent);
		model.addOrUpdateUser(student);
		model.addOrUpdateUser(teacher);
		
		model.deleteUser(parent.getId());
		model.deleteUser(student.getId());
		model.deleteUser(teacher.getId());
		model.deleteUser(admin.getId());
		
		assertThrows(NoSuchElementException.class, () -> model.getUserById(parent.getId()));
		assertThrows(NoSuchElementException.class, () -> model.getUserById(admin.getId()));
		assertThrows(NoSuchElementException.class, () -> model.getUserById(student.getId()));
		assertThrows(NoSuchElementException.class, () -> model.getUserById(teacher.getId()));
	}
	
	@Test
	void updateUserTest() {
		Parent parent = new Parent("name1", "login", "pwd", new ArrayList<Student>());
		model.addOrUpdateUser(parent);
	
		parent = new Parent("changed", "login", "pwd", new ArrayList<Student>(), parent.getId());
		model.addOrUpdateUser(parent);
		
		assertEquals(parent, model.getUserById(parent.getId()));
	}

}
