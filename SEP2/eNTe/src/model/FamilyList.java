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

    public void update(Family family) {
		for (int i = 0, familiesSize = families.size(); i < familiesSize; i++) {
			Family u = families.get(i);
			if (u.getId().equals(family.getId())) {
				families.set(i, family);
			}
		}
	}

    public void addAll(LinkedList<Family> families) {
		this.families.addAll(families);
    }

    public void clear() {
		families.clear();
    }
}
