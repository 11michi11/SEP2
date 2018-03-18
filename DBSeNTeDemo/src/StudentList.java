import java.util.ArrayList;

public class StudentList {
	private ArrayList<Student> students;
	
	public StudentList ()
	{
		students = new ArrayList<>();
	}
	
	public void addStudent(Student student)
	{
		students.add(student);
	}
	
	public void removeStudent(Student student)
	{
		for (Student e: students)
		{
			if (e.equals(student))
			{
				students.remove(e);
			}
		}
	}
	
	public Student getStudentById(int id)
	{
		for (Student e: students)
		{
			if (e.getId()==id)
			{
				return e;
			}
		}
		throw new NullPointerException("Student not found");
	}
	
	public ArrayList<Student> getAllActiveStudents ()
	{
		ArrayList<Student> list = new ArrayList<>();
		for (Student e: students)
		{
			if (e.isActive())
			{
				list.add(e);
			}
		}
		return list;
	}
	
	public ArrayList<Student> getStudentsByClass (int studentClass)
	{
		ArrayList<Student> list = new ArrayList<>();
		for (Student e: students)
		{
			if (e.getStudentClass()==studentClass)
			{
				list.add(e);
			}
		}
		return list;
	}
	
	public String toString ()
	{
		String text = "";
		for (Student e: students)
		{
			text+=e.toString()+"\n";
		}
		return text;
	}
}
