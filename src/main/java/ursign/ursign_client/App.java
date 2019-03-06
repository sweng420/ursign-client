package ursign.ursign_client;

import java.awt.EventQueue;
import java.io.IOException;

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
    		launch(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	if(!u.loggedIn()){
    		System.out.println( "Sorry!" );
    		//return;
    	}
    	System.out.print("Welcome!");
    	
    	
    	
    	 
    	 System.out.println(javafx.scene.text.Font.getFamilies());
	}
	
    /*public static void main( String[] args )
    {
    	
    
    	FXLoginDialog ld = new FXLoginDialog();
       
    }*/
}
