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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXSplash.fxml"));
    
        Scene scene = new Scene(root, 800, 600);
        
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

	public static void main(String[] args) {
    	try {
    		launch(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
