package test;

import model.ClassNo;
import model.Homework;
import model.HomeworkReply;
import model.MyDate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class HomeworkTest {

	@Test
	void constructorsTest() {
		ArrayList<ClassNo> classes = new ArrayList<>();
		Homework h1 = new Homework("title", "content", "author", MyDate.now(), MyDate.now(), classes, 2);
		assertEquals("title", h1.getTitle());
		assertEquals("content", h1.getContent());
		assertEquals("author", h1.getAuthor());
		assertEquals(classes, h1.getClasses());
		assertEquals(MyDate.now(), h1.getPubDate());
		assertEquals(MyDate.now(), h1.getDeadline());
		assertEquals(2, h1.getNumberOfStudentsToDeliver());

		ArrayList<HomeworkReply> replies = new ArrayList<>();
		Homework h2 = new Homework("id","title", "content", "author", MyDate.now(), MyDate.now(), classes, 2, replies, true);
		assertEquals("title", h2.getTitle());
		assertEquals("content", h2.getContent());
		assertEquals("author", h2.getAuthor());
		assertEquals(MyDate.now(), h2.getPubDate());
		assertEquals(MyDate.now(), h2.getDeadline());
		assertEquals(2, h2.getNumberOfStudentsToDeliver());
		assertEquals(replies, h2.getReplies());
		assertEquals(true, h2.getClosed());

	}
	@Test
	void gettersTest() {
		ArrayList<HomeworkReply> replies = new ArrayList<>();
		ArrayList<ClassNo> classes = new ArrayList<>();
		Homework h2 = new Homework("id","title", "content", "author", MyDate.now(), MyDate.now(), classes, 2, replies, true);
		assertEquals("title", h2.getTitle());
		assertEquals("content", h2.getContent());
		assertEquals("author", h2.getAuthor());
		assertEquals(MyDate.now(), h2.getPubDate());
		assertEquals(MyDate.now(), h2.getDeadline());
		assertEquals(2, h2.getNumberOfStudentsToDeliver());
		assertEquals(replies, h2.getReplies());
		assertEquals(true, h2.getClosed());
	}


}
