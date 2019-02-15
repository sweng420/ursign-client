package ursign.ursign_client;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JDialog;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class App 
{
    public static void main( String[] args )
    {
    	User u = new User();

    	try {
			LoginDialog dialog = new LoginDialog(u);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	if(!u.loggedIn()){
    		System.out.println( "Sorry!" );
    		return;
    	}
    	System.out.print("Welcome!");
    	/* put here what you want to do when the login worked */
        
        
    }
}
