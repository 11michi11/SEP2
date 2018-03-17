import java.sql.SQLException;

public class Controller {

	private StudentList list;
	private Adapter adapter;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		StudentList list = new StudentList();
		Adapter adapter = new Adapter();
		Student s1 = new Student(2, "Chuck", "Norris", 2, 22, false);
		//list.addStudent(s1);
		adapter.save(s1);
		//adapter.remove(s1);
		list = adapter.load();
		System.out.println(list);
	}
}
