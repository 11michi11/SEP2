package model;

import java.io.Serializable;
import java.util.stream.Collectors;

public class Parent extends User implements Serializable {
	private ArrayList<Student> children;
	private String childrenNames;
	private ArrayList<Class> classes;
	private String familyID;

	public Parent(String name, String login, String pwd, ArrayList<Student> children, String familyID) {
		super(name, login, pwd);
		this.familyID=familyID;
		initializeChildren(children);
	}
	
	public Parent(String name, String login, String pwd, ArrayList<Student> children, String id, String familyID) {
		super(name, login, pwd, id);
		this.familyID=familyID;
		initializeChildren(children);
	}
    private Family family;

    public Parent(String name, String login, String pwd, Family family) {
        super(name, login, pwd);
        this.family = family;
    }

    public Parent(String name, String login, String pwd, Family family, String id) {
        super(name, login, pwd, id);
        this.family = family;
    }

    public Parent(String name, String login, String pwd) {
        super(name, login, pwd);
    }

    public String getChildrenNames() {
        if (family != null)
            return family.getChildren().stream().map(s -> s.getName() + ", ").collect(Collectors.joining());
        return "";
    }

    public Object getChildren() {
        return null;
    }

	public ArrayList<Student> getChildren() {
		return children;
	}
	
	public String getFamilyID() {
		return familyID;
	}
}
    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public static NeedName builder() {
        return new Builder();
    }

    public static final class Builder implements NeedName, NeedLogin, NeedPwd, CanBeBuild {

        private String name;
        private String login;
        private String pwd;
        private String id;
        private Family family;

        @Override
        public NeedLogin name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public NeedPwd login(String login) {
            this.login = login;
            return this;
        }

        @Override
        public CanBeBuild pwd(String pwd) {
            this.pwd = pwd;
            return this;
        }

        @Override
        public CanBeBuild family(Family family) {
            this.family = family;
            return this;
        }

        @Override
        public CanBeBuild id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public Parent build() {
            Parent parent = new Parent(this.name, this.login, this.pwd);
            parent.family = this.family;
            parent.id = this.id;

            return parent;
        }
    }

    public interface NeedName {
        public NeedLogin name(String name);
    }

    public interface NeedLogin {
        public NeedPwd login(String login);
    }

    public interface NeedPwd {
        public CanBeBuild pwd(String pwd);
    }

    public interface CanBeBuild {
        public Parent build();

        public CanBeBuild family(Family family);

        public CanBeBuild id(String id);
    }

}