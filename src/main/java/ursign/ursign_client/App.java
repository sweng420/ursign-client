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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
	
	@Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXLoginDialog.fxml"));
    
        Scene scene = new Scene(root, 300, 275);
    
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }

	public static void main(String[] args) {
		//launch(args);
		
		User u = new User();

    	try {
			/*LoginDialog dialog = new LoginDialog(u);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);*/
    		launch(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	if(!u.loggedIn()){
    		System.out.println( "Sorry!" );
    		return;
    	}
    	System.out.print("Welcome!");
    	
    	
    	CollectionParser parser = new CollectionParser();
    	parser.parse("colours2.xml");
    	 System.out.println(parser.getPresentation());
	}
	
    /*public static void main( String[] args )
    {
    	
    
    	FXLoginDialog ld = new FXLoginDialog();
       
    }*/
}
