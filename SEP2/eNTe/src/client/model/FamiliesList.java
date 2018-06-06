package client.model;

import model.Family;

import java.util.ArrayList;
import java.util.List;

public class FamiliesList {

    private ArrayList<Family> families = new ArrayList<>();

    public FamiliesList() {
    }

    public void addFamily(Family family) {
        families.add(0, family);
    }

    public ArrayList<Family> getAllFamilies() {
        return families;
    }

    public void deleteFamily(Family family) {
        families.remove(family);
    }

    public void addAll(List<Family> families) {
        this.families.addAll(families);
    }
}
