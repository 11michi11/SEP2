package test;

import client.model.ClientModelManager;
import model.*;
import model.communication.WelcomingData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.controller.ServerMain;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientModelManagerTest {

	public ClientModelManager model;
	
	@BeforeAll
	
	static void startServer() {
		Thread t = new Thread(() -> ServerMain.main(new String[0]));
		t.start();
		System.out.println("Server");
	}

	@BeforeEach
	void setup() {
		
		model = new ClientModelManager();
	}

	@Test
	void getAndStorePostTest() {
		MyDate pubDate = MyDate.now();
		Post post = new Post("Title", "Content", "Author", pubDate);
		model.storePost(post);

		assertEquals(new Post("Title", "Content", "Author", pubDate), model.getPost());
	}

	@Test
	void saveDataFromWelcomingData() {
		MyDate pubDate = MyDate.now();
		Post post1 = new Post("Title1", "Content", "Author", pubDate);
		Post post2 = new Post("Title2", "Content", "Author", pubDate);
		Post post3 = new Post("Title3", "Content", "Author", pubDate);
		WelcomingData data = new WelcomingData();
		LinkedList<Post> list1 = new LinkedList<>();
		LinkedList<Post> list2 = new LinkedList<>();
		LinkedList<Post> list3;
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
		fail();
//		Parent parent1 = Parent.builder().name("name1").email("email").pwd("pwd").build();
//		Parent parent2 = Parent.builder().name("name2").email("email").pwd("pwd").build();
//		ArrayList<Parent> list = new ArrayList<>();
//		list.add(parent1);
//		list.add(parent2);
//
//		model.addOrUpdateUser(parent1);
//		model.addOrUpdateUser(parent2);
//		assertEquals(list, model.getParents());
	}
	
	@Test
	void deleteUserAndGetUserByIdTest() {
		Parent parent = Parent.builder().name("name").email("email").pwd("pwd").build();
		Student student = Student.builder().name("name").email("email").classs(Classs.First).pwdEncrypt("pwd").build();
		Teacher teacher = new Teacher("name", "email", "pwd");
		Administrator admin = new Administrator("name", "email", "pwd");
		
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
		Parent parent = Parent.builder().name("name").email("email").pwd("pwd").build();
		model.addOrUpdateUser(parent);
	
		parent = Parent.builder().name("name").email("email").pwd("pwd").build();
		model.addOrUpdateUser(parent);
		
		assertEquals(parent, model.getUserById(parent.getId()));
	}

}
