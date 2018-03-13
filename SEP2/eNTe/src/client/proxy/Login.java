package client.proxy;

public class Login {

	private LoginStatus status;
	private WelcomingData data;

	public Login(LoginStatus status, WelcomingData data) {
		this.status = status;
		this.data = data;
	}

	public LoginStatus getLoginStatus() {
		return status;
	}

	public WelcomingData getData() {
		return data;
	}

}
