package test;

import model.ClassNo;
import model.HomeworkReply;
import model.MyDate;
import model.Student;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class HomeworkReplyTest {

	@Test
	void contructorTest() {
		Student student = Student.builder().name("name").email("email").classNo(ClassNo.First).build();
		HomeworkReply reply = new HomeworkReply("content", student, true, MyDate.now());
		assertEquals("content", reply.getContent());
		assertEquals(student, reply.getStudent());
		assertEquals(true, reply.isLate());
		assertEquals(MyDate.now(), reply.getHandInDate());

	}

	@Test
	void gettersTest() {
		Student student = Student.builder().name("name").email("email").classNo(ClassNo.First).build();
		HomeworkReply reply = new HomeworkReply("content", student, true, MyDate.now());
		assertEquals("content", reply.getContent());
		assertEquals(student, reply.getStudent());
		assertEquals(true, reply.isLate());
		assertEquals(MyDate.now(), reply.getHandInDate());

	}
}
