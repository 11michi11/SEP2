package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Parent extends User implements Serializable {

    private Family family;

    public Parent(String name, String email, String pwd, Family family) {
        super(name, email, pwd);
        this.family = family;
    }

    public Parent(String name, String email, String pwd, Family family, String id) {
        super(name, email, pwd, id);
        this.family = family;
    }

    public Parent(String name, String email, String pwd) {
        super(name, email, pwd);
    }

    public String getChildrenNames() {
        if (family != null)
            return family.getChildren().stream().map(s -> s.getName() + ", ").collect(Collectors.joining());
        return "";
    }

    public ArrayList<Student> getChildren() {
        return family.getChildren();
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

    public static final class Builder implements NeedName, NeedEmail, CanBeBuild {

        private String name;
        private String email;
        private String pwd;
        private String id;
        private Family family;

        @Override
        public NeedEmail name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public CanBeBuild email(String email) {
            this.email = email;
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
            Parent parent = new Parent(this.name, this.email, this.pwd);
            parent.family = this.family;
            parent.id = this.id;

            return parent;
        }
    }

    public interface NeedName {
        NeedEmail name(String name);
    }

    public interface NeedEmail {
        CanBeBuild email(String email);
    }

    public interface CanBeBuild {
        Parent build();

        CanBeBuild pwd(String pwd);

        CanBeBuild family(Family family);

        CanBeBuild id(String id);
    }

}