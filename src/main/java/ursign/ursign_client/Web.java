package ursign.ursign_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Web {
	public WebRequest makeRequest(List<BasicNameValuePair> parameters) throws Exception {
		String url = "http://erostratus.net:5000/login";
		String err;
		CookieHandler.setDefault(new CookieManager());
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
	
		// add header
		post.setHeader("User-Agent", "test");
	
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		for(BasicNameValuePair p : parameters){
			urlParameters.add(p);
		}
	
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		HttpResponse response;
		try {
				response = client.execute(post);
				
				System.out.println("Response Code : " 
		                + response.getStatusLine().getStatusCode());
		
			BufferedReader rd;
			try {
				rd = new BufferedReader(
				        new InputStreamReader(response.getEntity().getContent()));
				StringBuffer result = new StringBuffer();
		    	String line = "";
		    	try {
					while ((line = rd.readLine()) != null) {
						result.append(line);
					}
					System.out.println(result);
					
					JsonObject jsonObject = new JsonParser().parse(result.toString()).getAsJsonObject();
		
					err = jsonObject.get("error").getAsString();
					
					return new WebRequest(err, result.toString());
		    	} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
		    		throw e;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw e;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw e;
		}
	}
}
