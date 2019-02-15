package ursign.ursign_client;

import java.util.HashMap;
import java.util.Map;

public class UserException extends Exception {
	private static final Map<String,String> messageMap = new HashMap<String,String>() {{
		put("incomplete-params", "Incomplete parameters");
		put("bad-username", "Bad username");
		put("bad-login", "Bad login details");
		put("username-taken", "Username already in use");
	}};
	
	public UserException() {}
	public String toString(String errorcode) {
		String msg = messageMap.get(errorcode);
		if(msg==null) {
			return "Error: Unknown error. Plese report this issue.";
		}
		return "Error: "+msg;
	}
	public UserException(String errorcode) {
		super(errorcode);
	}
}
