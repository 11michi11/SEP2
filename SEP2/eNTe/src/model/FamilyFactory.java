package model;

import java.util.HashMap;

public class FamilyFactory {
	private static HashMap<String, Family> families = new HashMap<String, Family>();
	
	public static Family getFamily(String id) {
		return createFamily(id);
	}
	public static Family createFamily(String id) {
		Family item=families.get(id);
		if(item==null) {
			item=new Family(id);
			families.put(id, item);
		}
		return item;	
	}
}
