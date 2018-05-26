package model.communication;

import model.User;

import java.io.Serializable;

public class Login implements Serializable{

	private LoginStatus status;
	private WelcomingData data;
	private User currentUser;

	public Login(LoginStatus status, WelcomingData data, User currentUser) {
		this.status = status;
		this.data = data;
		this.currentUser = currentUser;
	}

	public LoginStatus getLoginStatus() {
		return status;
	}

	public WelcomingData getData() {
		return data;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Login [status=" + status + ", data=" + data + "]";
	}

	public String getUserType() {
		return currentUser.getClass().getSimpleName();
	}

	public boolean changeLogin() {
		return currentUser.isPasswordChangeNeeded();
	}

	public User getCurrentUser() {
		return currentUser;
	}
}
