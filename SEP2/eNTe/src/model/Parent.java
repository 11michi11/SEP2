package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "parent", schema = "test")
@PrimaryKeyJoinColumn(name = "parentid")
public class Parent extends User implements Serializable, IFamily {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "familyid")
    private Family family;

    protected Parent() {
    }

    private Parent(String name, String email) {
        super(name, email);
    }

    public String getChildrenNames() {
        if (family != null)
            return family.getChildren().stream().map(s -> s.getName() + ", ").collect(Collectors.joining());
        return "";
    }

    public List<Student> getChildren() {
        return family.getChildren();
    }

    @Override
    public Family getFamily() {
        return family;
    }

    @Override
    public String getFamilyId() {
        return family.getId();
    }

    @Override
    public void setFamily(Family family) {
        this.family = family;
    }

    void updateParentFields(Parent newUser) {
        family = newUser.family;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Parent parent = (Parent) o;

        return family != null ? family.equals(parent.family) : parent.family == null;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "family=" + family +
                "} " + super.toString();
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
        private boolean encryptPwd;

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
        public CanBeBuild pwdEncrypt(String pwd) {
            this.pwd = pwd;
            encryptPwd = true;
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
            Parent parent = new Parent(this.name, this.email);
            if (this.pwd != null)
                if (encryptPwd)
                    parent.setPwdAndEncrypt(pwd);
                else
                    parent.setPwd(pwd);
            parent.family = this.family;
            if (this.id != null)
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

        CanBeBuild pwdEncrypt(String pwd);

        CanBeBuild family(Family family);

        CanBeBuild id(String id);
    }

}