package model;

import java.io.Serializable;

public class Student extends User implements Serializable{
	private String historyOfActivity;
	private Classs classs;
	private Family family;

	//Student should be initialized with builder!
	//Student.builder()...

	private Student(String name, String login, String pwd) {
		super(name, login, pwd);
	}

	public Student(String name, String login, String pwd, Classs classs, Family family) {
		super(name, login, pwd);
		initializeStudent(classs, family);
	}
	
	public Student(String name, String login, String pwd, String id, Classs classs, Family family) {
		super(name, login, pwd, id);
		initializeStudent(classs, family);
	}
	
	private void initializeStudent(Classs classs, Family family) {
		this.classs = classs;
		historyOfActivity = "";
		this.family = family;
	}
		
	public void addHistoryOfActivity(String text) {
		historyOfActivity += text + "\n";
	}

	public String getHistoryOfActivity() {
		return historyOfActivity;
	}

	public void setClasss(Classs classs) {
		this.classs = classs;
	}

	public Classs getClasss() {
		return classs;
	}
	
	public String getFamilyID() {
		return family.getId();
	}

	public static StudentNeedName builder(){
		return new Builder();
	}

	public static final class Builder implements StudentNeedName, StudentNeedPwd, StudentNeedLogin, StudentNeedClasss, StudentCanBeBuild {
		protected String id;
		private String historyOfActivity;
		private String login;
		private Classs classs;
		private String pwd;
		private Family family;
		private String name;

		public StudentNeedLogin name(String name) {
			this.name = name;
			return this;
		}

		public StudentNeedPwd login(String login) {
			this.login = login;
			return this;
		}

		public StudentNeedClasss pwd(String pwd) {
			this.pwd = pwd;
			return this;
		}

		public StudentCanBeBuild classs(Classs classs) {
			this.classs = classs;
			return this;
		}

		public StudentCanBeBuild historyOfActivity(String historyOfActivity) {
			this.historyOfActivity = historyOfActivity;
			return this;
		}

		public StudentCanBeBuild family(Family family) {
			this.family = family;
			return this;
		}

		public StudentCanBeBuild id(String id) {
			this.id = id;
			return this;
		}

		public Student build() {
			Student student = new Student(name, login, pwd);
			if(this.classs == null)
				throw new IllegalStateException("Classs must be specified");
			if(this.id != null)
				student.id = this.id;
			student.classs = this.classs;
			student.family = family;
			student.historyOfActivity = this.historyOfActivity;
			return student;
		}
	}

	public interface StudentNeedName {
		public StudentNeedLogin name(String name);
	}

	public interface StudentNeedLogin {
		public StudentNeedPwd login(String login);
	}

	public interface StudentNeedPwd {
		public StudentNeedClasss pwd(String pwd);
	}

	public interface StudentNeedClasss{
		public StudentCanBeBuild classs(Classs classs);
	}

	public interface StudentCanBeBuild {
		public Student build();

		public StudentCanBeBuild family(Family family);

		public StudentCanBeBuild historyOfActivity(String history);

		public StudentCanBeBuild id(String id);
	}
}