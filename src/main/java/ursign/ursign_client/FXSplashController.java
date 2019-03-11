package ursign.ursign_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXSplashController {
	@FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Text actiontarget;
    private String cookies;
    public User u;
    private String err;
    private static final Map<String,String> messageMap = new HashMap<String,String>() {{
		put("incomplete-params", "Incomplete parameters");
		put("bad-username", "Bad username");
		put("bad-login", "Bad login details");
		put("username-taken", "Username already in use");
	}};
    
	@FXML protected void handleRegisterTransition(ActionEvent event) {
		Stage stage = (Stage)usernameField.getScene().getWindow();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXRegister.fxml"));     

		Parent root;
			try {
				root = (Parent)fxmlLoader.load();
				FXRegisterController controller = fxmlLoader.<FXRegisterController>getController();
				//controller.setUser(u);
				Scene scene = new Scene(root, 1000, 500); 

				stage.setScene(scene);    

				stage.show(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
	}
	
	
    @FXML protected void handleLogin(ActionEvent event) {
        //actiontarget.setText("Sign in button pressed");
        
        String uname = usernameField.getText();
        String password = passwordField.getText();
        
        System.out.println(uname+" "+password);
        
    	String url = "http://erostratus.net:5000/login";

    	CookieHandler.setDefault(new CookieManager());
    	HttpClient client = HttpClientBuilder.create().build();
    	HttpPost post = new HttpPost(url);

    	// add header
    	post.setHeader("User-Agent", "test");

    	List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    	urlParameters.add(new BasicNameValuePair("username", uname));
    	urlParameters.add(new BasicNameValuePair("password", password));
    	/*urlParameters.add(new BasicNameValuePair("locale", ""));
    	urlParameters.add(new BasicNameValuePair("caller", ""));
    	urlParameters.add(new BasicNameValuePair("num", "12345"));*/

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
				
				if(err.equals("")){
					setCookies(response.getFirstHeader("Set-Cookie") == null ? "" : 
	                    response.getFirstHeader("Set-Cookie").toString());
					
					System.out.println(cookies);

					u = new User();
					u.setCookie(getCookies());
					u.setUsername(uname);
					u.setUid(1);
					
					Stage stage = (Stage)usernameField.getScene().getWindow();
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXHomePage.fxml"));     

					Parent root;
						root = (Parent)fxmlLoader.load();
						FXHomePageController controller = fxmlLoader.<FXHomePageController>getController();
						controller.setUser(u);
						Scene scene = new Scene(root, 1000, 500); 

						stage.setScene(scene);    

						stage.show();   
				} else {
					actiontarget.setText(messageMap.get(err));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    public String getCookies() {
    	return cookies;
      }

    public void setCookies(String cookies) {
    	this.cookies = cookies;
    }
	
}
