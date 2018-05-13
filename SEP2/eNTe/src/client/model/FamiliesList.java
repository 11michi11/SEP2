package client.model;

import model.Classs;
import model.Family;
import model.Parent;
import model.Student;

import java.util.ArrayList;

public class FamiliesList {

    private ArrayList<Family> families = new ArrayList<>();

    public FamiliesList(){
        Family family = new Family();
        family.addParent(Parent.builder().name("Pname1").email("email").pwd("pwd").family(family).build());
        family.addParent(Parent.builder().name("Pname2").email("email").pwd("pwd").family(family).build());
        family.addParent(Parent.builder().name("Pname3").email("email").pwd("pwd").family(family).build());

        family.addChild(Student.builder().name("Sname1").email("email").classs(Classs.First).family(family).pwd("pwd").build());
        family.addChild(Student.builder().name("Sname2").email("email").classs(Classs.First).family(family).pwd("pwd").build());
        family.addChild(Student.builder().name("Sname3").email("email").classs(Classs.First).family(family).pwd("pwd").build());
        families.add(family);
    }

    public void addFamily(Family family){
        families.add(family);
    }
    public ArrayList<Family> getAll(){
        return families;
    }
    public void deleteFamily(Family family) {
        families.remove(family);
    }

}
