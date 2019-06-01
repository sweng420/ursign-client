/*
----------------------------------------------------------------------------------
-- Company: Ortus Innovations
-- Engineer: Alex McDonnell
-- 
-- Create Date: 6.3.2018
-- Module Name: Text Handler
-- Tool versions: Built in Eclipse IDE - Version 2018-12 (4.10.0)
-- Description: This class shows how to integrate the image handler into a larger
-- javaFX scene with examples on reading and editing text attributes. This assumes
-- The test XML and .txt files are stores at C:/TextModule/
-- 
-- Dependencies: JavaFX
--
-- Revision: 2
-- Revision 1 - File Created
-- Revision 2 - Created example on how to use code
-- Additional Comments:
-- 
----------------------------------------------------------------------------------
*/

package text_handler;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Example class showing how to use the text handler.

public class Main extends Application {

	@Override
    public void start(Stage primaryStage) 
    {		
		//Declare filepath to media XML
        String filePath = "C://TextModule//test_example.xml";
    	
        //Create ArrayList to store output of XML reader
        ArrayList <TextHandler> textHandlers = new ArrayList <TextHandler>();
        
        //Create new instance of the XML file reader class
        XMLReaderText textReader = new XMLReaderText(filePath);
       
        //Read XML file and set output to the array list    
        textHandlers = textReader.readXML();
        
        //Check if there was an error in reading the XML file
        if (textHandlers == null)
        {
        	System.out.println("XML File was not read, no valid text nodes");
        	return;
        }
        
        //As this is a test, we add the text nodes manually to the group to show the functionality
        
        //Set wrapping width of text hander 1
        textHandlers.get(0).setColour("00FF00");
        textHandlers.get(0).setWrappingWidth(560);
        
        //Create text elements from textHandlers
		Text text1 = textHandlers.get(0).createFrame();
		Text text2 = textHandlers.get(1).createFrame();
		//This text has no text in the XML, so will display an error message
		Text text3 = textHandlers.get(2).createFrame();
		
		//Create FX group where text is stored
        Group root = new Group();
        
        //Add text to group
	    root.getChildren().add(text1);
	    root.getChildren().add(text2);
	    root.getChildren().add(text3);
        
	    //Create new scene
        Scene scene = new Scene(root, 800, 600);
        
        //Set scene attributes and show scene
        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        
        //Dynamic text wrapping can be setup by using the binding method, this is shown
        //below by binding text2 to the width of the scene. The text can be bound and wrapped
        //to any parent node within JavaFX that has the widthProperty method.
        //This must be performed outside the TextHandler class as prior knowledge of the
        //JavaFX layout must be known and instantiated.
        text2.wrappingWidthProperty().bind(scene.widthProperty());
        
        primaryStage.show();
    }
	
    public static void main(String[] args)
    {          	
    	//Start Scene
    	launch(args);
    }
}
