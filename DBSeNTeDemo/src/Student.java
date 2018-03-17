
public class Student {

	private int id;
	private String fName;
	private String lName;
	private int studentClass;
	private int parentId;
	private boolean isActive;
	
	public Student(int id, String fName, String lName, int studentClass, int parentId, boolean isActive) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.studentClass = studentClass;
		this.parentId = parentId;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(int studentClass) {
		this.studentClass = studentClass;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public String toString() 
	{
		return "Student ID: "+id+"\nName: "+fName+" "+lName+"\nClass: "+studentClass+"\nParent ID: "+parentId+"\nIs active? "+isActive;
	}
}
