package model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Family extends AbstractFamily {
	

	private String familyName;
	
	public Family(String id, String familyName) {
		super(id);
	}
	
	public String getFamilyName() {
		return familyName;
	}

	

}
