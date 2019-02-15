package ursign.ursign_client;

import java.util.List;

public class User {
	private String username;
	private Integer uid;
	private String email;
	private String realname;
	private Integer born;
	private List<Collection> collections;
	private List<User> children;
	private Boolean login_complete;
	
	public User() {
		this.username = "";
		this.uid = -1;
		this.email = "";
		this.realname = "";
		this.born = 0;
		this.collections = null;
		this.children = null;
		this.login_complete = false;
	}
	
	public void login(String usernameOrEmail, String password) throws UserException {
		Boolean badlogin = false;
		if(badlogin) {
			throw new UserException("bad-login");
		}
		login_complete = true;
	}

	public Boolean loggedIn() {
		return login_complete;
	}
}
