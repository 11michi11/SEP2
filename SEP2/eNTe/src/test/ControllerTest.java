package test;

import model.ClassNo;
import model.ClientModel;
import model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ControllerTest {

	private ClientModel model;

	@Test
	void clientControllerTest() {

	}

	@Test
	void addStudent() {
		Student student = Student.builder().name("child").email("email").classs(ClassNo.Eigth).pwdEncrypt("pwd").build();


	}

}
