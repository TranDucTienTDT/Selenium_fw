package com.logigear.test.ta_dashboard.data_object;

public class User {

	private String username = "";
	private String password = "";
	private String repository = "";
	

	public User (String username, String password, String repository )
	{
		this.username = username;
		this.password = password;
		this.repository = repository;
	}
	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepository() {
		return repository;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}
}