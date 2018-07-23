package edu.sjsu.seekers.PaymentAPI.Request;

import java.io.Serializable;

public class PaymentRequest implements Serializable {

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PaymentRequest{" +
				"userName='" + userName + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;


	public PaymentRequest() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
