package model;

import java.util.HashMap;

public class FamilyFactory {
	private static HashMap<String, Family> families = new HashMap<String, Family>();
	
	public static Family getFamily(String id, String familyName) {
		return createFamily(id, familyName);
	}
	public static Family createFamily(String id, String familyName) {
		Family item=families.get(id);
		if(item==null) {
			item=new Family(id, familyName);
			families.put(id, item);
		}
		return item;	
	}
}
