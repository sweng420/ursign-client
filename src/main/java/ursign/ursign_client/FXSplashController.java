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
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
		put("unauthorized", "You cannot access without correct credentials.");
	}};
    
	@FXML protected void handleRegisterTransition(ActionEvent event) {

			try {

				Stage stage = (Stage)usernameField.getScene().getWindow();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXRegister.fxml"));     
				FXRegisterController controller = new FXRegisterController(0); /* no parent here, main registration screen */
				fxmlLoader.setController(controller);
				Node root = fxmlLoader.load();			
				Scene scene = new Scene((Parent)root, 800, 600); 
				stage.setScene(scene);    
				stage.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
	}
	
	
    @FXML protected void handleLogin(ActionEvent event) {
    			String uname = usernameField.getText();
    			String password = passwordField.getText();
    			
    			Web webObject = new Web();
    			
    			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    	    	urlParameters.add(new BasicNameValuePair("username", uname));
    	    	urlParameters.add(new BasicNameValuePair("password", password));
    	    	
    			WebRequest wr;
				try {
					wr = webObject.makeRequest("/login", urlParameters, new ArrayList<Cookie>());
					
					if(!wr.hasError()){
						System.out.println(Integer.decode(wr.getJSON().get("userid").toString()));
						u = new User();
						u.setCookies(wr.getCookies());
						u.setUsername(uname);
						u.setUid(Integer.decode(wr.getJSON().get("userid").toString()));
						
						Stage stage = (Stage)usernameField.getScene().getWindow();
						
						/* kill the video audio (if any) when the stage is closed
						 * this code was taken from player.Player.
						 */
						stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				            @Override
				            public void handle(WindowEvent event) {
				                Platform.exit();
				                System.exit(0);
				            }
				        });
						
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXHomePage.fxml"));     
						FXHomePageController controller = new FXHomePageController(u, stage); /* pass the user to the HomePage controller */
						fxmlLoader.setController(controller);
						Node root = fxmlLoader.load();			
						Scene scene = new Scene((Parent)root, stage.getWidth(), stage.getHeight()); 
						stage.setScene(scene);    
						stage.show();
						
					} else {
						actiontarget.setText(wr.getError());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
	}

    public String getCookies() {
    	return cookies;
      }

    public void setCookies(String cookies) {
    	this.cookies = cookies;
    }
	
}
