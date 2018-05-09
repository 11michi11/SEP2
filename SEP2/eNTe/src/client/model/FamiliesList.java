package client.model;

import model.Family;

import java.util.ArrayList;

public class FamiliesList {

    private ArrayList<Family> families = new ArrayList<>();

    public void addFamily(Family family){
        families.add(family);
    }
    public ArrayList<Family> getAll(){
        return families;
    }

}
