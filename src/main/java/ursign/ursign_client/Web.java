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
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Web {
	private HttpResponse response;
	public WebRequest makeRequest(String url, List<NameValuePair> parameters, List<Cookie> cookies) throws Exception {
		String err;
		CookieStore httpCookieStore = new BasicCookieStore();
		for(Cookie c : cookies) {
			httpCookieStore.addCookie(c);
		}
		HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore).build();

		HttpPost post = new HttpPost(url);
		
		post.setHeader("User-Agent", "test");
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		for(NameValuePair p : parameters){
			urlParameters.add(p);
		}
	
		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
		try {
			System.out.println(post.toString());
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
					
					WebRequest wr = new WebRequest(err, jsonObject, httpCookieStore.getCookies());
					wr.setJSONString(result.toString());
					return wr;
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
	
	public HttpResponse getResponse() {
		return response;
	}
}
