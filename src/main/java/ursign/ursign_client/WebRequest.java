package ursign.ursign_client;

public class WebRequest {
	private String errorMessage;
	private String jsonContent;
	
	WebRequest(String em, String jc) {
		this.errorMessage = em;
		this.jsonContent = jc;
	}
}
