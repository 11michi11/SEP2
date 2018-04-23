package model;

public class NoUserWithNameException extends Exception {
	public NoUserWithNameException(String msg) {
		super(msg);
	}
}
