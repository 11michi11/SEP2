package model;

import java.util.LinkedList;
import java.util.List;

public class FamilyList {
	private LinkedList<Family> families;
	
	public FamilyList() {
		families=new LinkedList<>();
	}
	public void addFamily(Family family) {
		families.add(family);
	}
	public void deleteFamily(Family family) {
		families.remove(family);
	}
	public List<Family> getAllFamilies(){
		return families;
	}
	public int getSize() {
		return families.size();
	}
	public Family getFamilyById (String familyId) {
//		return families.get(families.indexOf(familyId));
		Family family = null;
		for (Family e:families) {
			if (e.getId().equals(familyId)) {
				family = e;
				break;
			}
		}
		return family;
	}
}
