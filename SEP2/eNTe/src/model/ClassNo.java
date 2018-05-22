package model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TO DO, class not finished
public enum ClassNo implements Serializable{
	 First("First"),
	 Second("Second"),
	 Third("Third"),
	 Fourth("Fourth"),
	 Fifth("Fifth"),
	 Sixth("Sixth"),
	 Seventh("Seventh"),
	 Eigth("Eigth");
	 
	private String name;
	
	ClassNo(String name) {
		this.name = name;
	}
	
	
	public static List<String> getClassesInStrings() {
		return Stream.of(ClassNo.values()).map(Enum::name).collect(Collectors.toList());
	}
	
	public static List<ClassNo> getClasses() {
		return Stream.of(ClassNo.values()).collect(Collectors.toList());
	}
	
	public String toString() {
		return name;
	}
}


