package ursign.ursign_client;

import java.util.List;

import org.apache.http.cookie.Cookie;

import com.google.gson.JsonObject;

public class WebRequest {
	private String errorMessage;
	private JsonObject jsonContent;
	private String jsonString;
	private List<Cookie> cookies;
	
	WebRequest(String em, JsonObject jc, List<Cookie> cs) {
		this.errorMessage = em;
		this.jsonContent = jc;
		this.cookies = cs;
	}
	
	public Boolean hasError() {
		return this.errorMessage.length() > 0;
	}
	
	public String getError() {
		return this.errorMessage;
	}
	
	public JsonObject getJSON() {
		return this.jsonContent;
	}
	
	public String getJSONString() {
		return jsonString;
	}
	
	public void setJSONString(String jsonString) {
		this.jsonString = jsonString;
	}
	
	public List<Cookie> getCookies() {
		return cookies;
	}
}
