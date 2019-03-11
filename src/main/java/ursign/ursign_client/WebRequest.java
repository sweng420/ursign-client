package ursign.ursign_client;

import com.google.gson.JsonObject;

public class WebRequest {
	private String errorMessage;
	private JsonObject jsonContent;
	
	WebRequest(String em, JsonObject jc) {
		this.errorMessage = em;
		this.jsonContent = jc;
	}
}
