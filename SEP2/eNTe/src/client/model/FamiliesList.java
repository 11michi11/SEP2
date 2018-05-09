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
        family.addParent(Parent.builder().name("Pname1").login("login").pwd("pwd").family(family).build());
        family.addParent(Parent.builder().name("Pname2").login("login").pwd("pwd").family(family).build());
        family.addParent(Parent.builder().name("Pname3").login("login").pwd("pwd").family(family).build());

        family.addChild(Student.builder().name("Sname1").login("login").pwd("pwd").classs(Classs.First).family(family).build());
        family.addChild(Student.builder().name("Sname2").login("login").pwd("pwd").classs(Classs.First).family(family).build());
        family.addChild(Student.builder().name("Sname3").login("login").pwd("pwd").classs(Classs.First).family(family).build());
        families.add(family);
    }

    public void addFamily(Family family){
        families.add(family);
    }
    public ArrayList<Family> getAll(){
        return families;
    }

}
