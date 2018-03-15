package client.proxy;

import java.io.Serializable;

public class Login implements Serializable{

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

}
