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
	private String cookie;
	
	public User() {
		this.username = "";
		this.uid = -1;
		this.email = "";
		this.realname = "";
		this.born = 0;
		this.collections = null;
		this.children = null;
		this.login_complete = false;
		this.cookie = "";
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getBorn() {
		return born;
	}

	public void setBorn(Integer born) {
		this.born = born;
	}

	public List<Collection> getCollections() {
		return collections;
	}

	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}

	public List<User> getChildren() {
		return children;
	}

	public void setChildren(List<User> children) {
		this.children = children;
	}

	public Boolean getLogin_complete() {
		return login_complete;
	}

	public void setLogin_complete(Boolean login_complete) {
		this.login_complete = login_complete;
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
	
	public void setCookie(String s) {
		this.cookie = s;
	}
	
	public String getCookie() {
		return this.cookie;
	}
}
