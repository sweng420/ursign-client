package ursign.ursign_client;

import java.util.Calendar;
import java.util.List;

import org.apache.http.cookie.Cookie;

public class User {
	private String username;
	private Integer uid;
	private String email;
	private String realname;
	private Integer born;
	private Integer age;
	private List<Collection> collections;
	private List<User> children;
	private Boolean login_complete;
	private List<Cookie> cookies;
	
	public List<Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(List<Cookie> cookies) {
		this.cookies = cookies;
	}
	
	public User(String username, String email, Integer born, String realname, Integer id) {
		this.username = username;
		this.uid = id;
		this.email = email;
		this.realname = realname;
		this.born = born;
		this.age = Calendar.getInstance().get(Calendar.YEAR) - born;
		this.collections = null;
		this.children = null;
		this.login_complete = false;
		this.cookies = null;
	}

	public User() {
		this.username = "";
		this.uid = -1;
		this.email = "";
		this.realname = "";
		this.born = 0;
		this.age = 0;
		this.collections = null;
		this.children = null;
		this.login_complete = false;
		this.cookies = null;
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
	
	public void setAge(Integer age) {
		this.age = age;
		this.born = Calendar.getInstance().get(Calendar.YEAR) - age;
	}
	
	public Integer getAge() {
		return age;
	}

}
