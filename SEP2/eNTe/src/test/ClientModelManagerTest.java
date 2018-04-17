package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import client.model.ClientModelManager;
import model.Post;
import model.communication.WelcomingData;



class ClientModelManagerTest {
	
	public ClientModelManager model;
	
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
		list3 = (LinkedList<Post>)list1.clone();
		data.insertPosts(list1);
		model.saveData(data);
		
		for(int i=0;i<3;i++)
			list2.add(model.getPost());
			
		assertEquals(list3, list2);
	}

}
