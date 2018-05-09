package model;

import java.io.Serializable;

public class Teacher extends User implements Serializable {

	public Teacher(String name, String login, String pwd) {
		super(name, login, pwd);
	}
	
	public Teacher(String name, String login, String pwd, String id) {
		super(name, login, pwd, id);
	}


	public static final class Builder {
		protected String id;
		private String login;
		private String pwd;
		private String name;

		private Builder() {
		}

		public static Builder aTeacher() {
			return new Builder();
		}

		public Builder withLogin(String login) {
			this.login = login;
			return this;
		}

		public Builder withPwd(String pwd) {
			this.pwd = pwd;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		public Teacher build() {
			return new Teacher(name, login, pwd, id);
		}
	}
}
