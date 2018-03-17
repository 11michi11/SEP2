import java.sql.SQLException;
import java.util.ArrayList;

public interface AdapterInterface {
	public StudentList load() throws SQLException;
	public void save(Student student) throws SQLException;
	public void save(ArrayList<Student> list) throws SQLException;
	public void remove(Student student) throws SQLException;
	public void remove(ArrayList<Student> list) throws SQLException;
	public void clear() throws SQLException;
}
