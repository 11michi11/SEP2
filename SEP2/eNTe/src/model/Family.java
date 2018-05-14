package model;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

public class Family {
	private String id;
	private ArrayList<Student> children = new ArrayList<>();
	private ArrayList<Parent> parents = new ArrayList<>();

	public Family() {
		id = UUID.randomUUID().toString();
	}

	public Family(String id) {
		this.id = id;
	}

	public void addChild(Student child) {
		children.add(child);
	}

	public void deleteChild(Student child) {
		children.remove(child);
	}

	public ArrayList<Student> getChildren() {
		return children;
	}

	public Student getChild(String name) throws NoSuchElementException {
		for (Student e : children)
			if (e.getName().equals(name))
				return e;
		throw new NoSuchElementException(name + "was not found.");
	}

	public Parent getParent(String name) throws NoSuchElementException {
		for (Parent e : parents)
			if (e.getName().equals(name))
				return e;
		throw new NoSuchElementException(name + "was not found.");
	}

	public String getMembersNames() {
		String parentsNames = parents.stream().map(s -> s.getName() + ", ").collect(Collectors.joining());
		String childrenNames = children.stream().map(s -> s.getName() + ", ").collect(Collectors.joining());
		if (parentsNames.length() + childrenNames.length() > 0)
			return parentsNames + childrenNames.substring(0, childrenNames.length() - 2);
		return "";
	}

	public void addParent(Parent parent) {
		parents.add(parent);
	}

	public void deleteParent(Parent parent) {
		parents.remove(parent);
	}

	public ArrayList<Parent> getParents() {
		return parents;
	}

	public ArrayList<Classs> getClasses() {
		ArrayList<Classs> classes = new ArrayList<>();
		for (Student e : children) {
			if (classes.contains(e.getClasss()))
				classes.add(e.getClasss());
		}
		return classes;
	}

	public String getId() {
		return id;
	}
}
