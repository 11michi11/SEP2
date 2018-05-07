package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Parent extends User implements Serializable {

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
        return null;
    }

    public Object getChildren() {
        return null;
    }

    public Family getFamily(){
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public static NeedName builder(){
        return new Builder();
    }

    public static final class Builder implements  NeedName, NeedLogin, NeedPwd, CanBeBuild{

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
            return null;
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

    public interface NeedName{
        public NeedLogin name(String name);
    }

    public interface NeedLogin{
        public NeedPwd login(String login);
    }

    public interface NeedPwd{
        public CanBeBuild pwd(String pwd);
    }

    public interface CanBeBuild{
        public Parent build();
        public CanBeBuild family(Family family);
        public CanBeBuild id(String id);
    }

}

/*
import java.io.Serializable;
import java.util.ArrayList;

public class Parent extends User implements Serializable {
	private ArrayList<Student> children;
	private String childrenNames;
	private ArrayList<Class> classes;

	public Parent(String name, String login, String pwd, ArrayList<Student> children) {
		super(name, login, pwd);
		initializeChildren(children);
	}

	public Parent(String name, String login, String pwd, ArrayList<Student> children, String id) {
		super(name, login, pwd, id);
		initializeChildren(children);
	}

	private void initializeChildren(ArrayList<Student> children) {
		this.children = children;
		classes = new ArrayList<Class>();
		for (Student s : children)
			classes.add(s.getClasss());

		childrenNames = "";
		for (Student s : children)
			childrenNames += s.getName() + ", ";
	}

	public String getChildrenNames() {
		return childrenNames;
	}

	public void addChild(Student child) {
		children.add(child);
		classes.add(child.getClasss());
	}

	public void removeChild(Student child) {
		children.remove(child);
	}

	public ArrayList<Student> getChildren() {
		return children;
	}
}
*/
