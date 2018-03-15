package client.proxy;

public enum LoginStatus {

	SUCCESS("Success"),
	FAILURE_PWD("Wrong password"),
	FAILURE_LOGIN("Wrong login, user not found");
	
	private String msg;

	LoginStatus(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
	
}
