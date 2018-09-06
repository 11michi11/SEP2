package test;

import client.model.ClientModelManager;
import model.*;
import model.communication.WelcomingData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.controller.ServerController;
import server.model.ServerModelManager;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientModelManagerTest {

	private ClientModelManager model;
	private static ServerController serverController;
	
	@BeforeAll
	static void startServer() {
		CountDownLatch latch = new CountDownLatch(1);
		Thread t = new Thread(new ServerStarted(latch));
		t.start();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Server");
	}

	@BeforeEach
	void setup() {
		
		model = new ClientModelManager();
	}

	@AfterAll
	static void shutDown(){
		serverController.closeServer();
	}

//	@Test
//	void getAndStorePostTest() {
//		MyDate pubDate = MyDate.now();
//		Post post = new Post("Title", "Content", "Author", pubDate);
//		model.addPost(post);
//
//		assertEquals(new Post(post.getPostId(), "Title", "Content", "Author", pubDate), model.getPost());
//	}
//
//	@Test
//	void editPostTest(){
//		MyDate pubDate = MyDate.now();
//		Post post = new Post("Title", "Content", "Author", pubDate);
//		model.addPost(post);
//
//		post = new Post(post.getPostId(), "Changed", "Change", "Changed", pubDate);
//		model.editPost(post);
//		assertEquals(post, model.getPost());
//	}
//
//	@Test
//	void deletePostTest(){
//		MyDate pubDate = MyDate.now();
//		Post post = new Post("Title", "Content", "Author", pubDate);
//		model.addPost(post);
//
//		model.deletePost(post);
//		assertThrows(NoSuchElementException.class, () -> model.getPost());
//	}
//
//	@Test
//	void saveDataFromWelcomingData() {
//		MyDate pubDate = MyDate.now();
//		Post post1 = new Post("Title1", "Content", "Author", pubDate);
//		Post post2 = new Post("Title2", "Content", "Author", pubDate);
//		Post post3 = new Post("Title3", "Content", "Author", pubDate);
//		WelcomingData data = new WelcomingData();
//		LinkedList<Post> list1 = new LinkedList<>();
//		LinkedList<Post> list2 = new LinkedList<>();
//		LinkedList<Post> list3;
//		list1.add(post1);
//		list1.add(post2);
//		list1.add(post3);
//		list3 = (LinkedList<Post>) list1.clone();
//		data.insertPosts(list1);
//		model.saveData(data);
//
//		list2.addAll(model.getAllPosts());
//
//		assertEquals(list3, list2);
//	}

	@Test
	void getParentsTest() {
		fail();
//		Parent parent1 = Parent.builder().name("name1").email("email").pwdEncrypt("pwd").build();
//		Parent parent2 = Parent.builder().name("name2").email("email").pwdEncrypt("pwd").build();
//		ArrayList<Parent> list = new ArrayList<>();
//		list.addAll(parent1);
//		list.addAll(parent2);
//
//		model.addOrUpdateUser(parent1);
//		model.addOrUpdateUser(parent2);
//		assertEquals(list, model.getParents());
	}
	
	@Test
	void deleteUserAndGetUserByIdTest() {
		Parent parent = Parent.builder().name("name").email("email").pwdEncrypt("pwd").build();
		Student student = Student.builder().name("name").email("email").classNo(ClassNo.First).pwdEncrypt("pwd").build();
		Teacher teacher = Teacher.builder().name("name").email("email").responsibility("").pwd("pwd").build();
		Administrator admin = Administrator.builder().name("name").email("email").pwd("pwd").build();
		
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
		Parent parent = Parent.builder().name("name").email("email").pwdEncrypt("pwd").build();
		model.addOrUpdateUser(parent);

		parent = Parent.builder().name("changed").email("email").pwdEncrypt("pwd").build();
		model.addOrUpdateUser(parent);

		assertEquals(parent, model.getUserById(parent.getId()));
	}


	static class ServerStarted implements Runnable{

		private CountDownLatch latch;

		ServerStarted(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {
			serverController = new ServerController(new ServerModelManager());
			latch.countDown();
		}
	}

}
