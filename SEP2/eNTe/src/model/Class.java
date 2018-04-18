package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.io.Serializable;

//TO DO, class not finished
public enum Class implements Serializable{
	 First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eigth;
	
	public static List<String> getClasses() {
		return Stream.of(Class.values()).map(Enum::name).collect(Collectors.toList());
	}
}


