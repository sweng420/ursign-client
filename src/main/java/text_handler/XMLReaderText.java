/*
----------------------------------------------------------------------------------
-- Company: Ortus Innovations
-- Engineer: Alex McDonnell
-- 
-- Create Date: 6.3.2018
-- Module Name: Text Handler
-- Tool versions: Built in Eclipse IDE - Version 2018-12 (4.10.0)
-- Description: This class allows the reading of an XML file with 
-- multiple multimedia elements, only the text elements are read,
-- and each text element is read into a TextHandler class. The
-- class outputs am ArrayList of the read text elements.
-- 
-- Dependencies: JavaFX
--
-- Revision: 3
-- Revision 1 - File Created
-- Revision 2 - Ability to read basic attributes from XML.
-- Revision 3 - Ability to read extended attributes from XML.
 
-- Additional Comments:
-- 
----------------------------------------------------------------------------------
*/

package text_handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * XMLReaderText Class
 * This class reads an XML file full of text and text attributes and returns an
 * ArrayList of type TextHandler. Will read 8 text attributes from the element meta
 * and the filelocation element. Within the filelocation element either plain text 
 * can be input or a local .txt filepath, containing valid plain text, can be read and 
 * parsed into the TextHandler class.
 */
public class XMLReaderText 
{
	//Additional attributes console printout flag
	static final boolean additionalAttributesConsoleFlag_ = true;	
	
	//Create fields for fileLocation and default attributes.
	String fileLocation;

	/**
	* Constructor
	* <br><br/>
	* The master constructor for the XMLReaderText class.
	*
	* @param fileLocation String. The file location at which the XML file is located.
	*/
	public XMLReaderText(String fileLocation)
	{
		this.fileLocation = fileLocation;
	}
	
	/**
	* Read XML
	* <br><br/>
	* Method that reads the XML at the file location of fileLocation (as defined in the constructor)
	* and returns an ArrayList of type TextHandler. This method will only read multimedia elements with
	* type text and the size of the ArrayList is equal to the amount of text elements.
	*
	* @return textHandlers ArrayList (TextHandler)
	*/
	//Method to read the XML
	public ArrayList <TextHandler> readXML()
	{
		//Setup XML builder and reader
		DocumentBuilderFactory xmlBuilderFactory = DocumentBuilderFactory.newInstance();
		xmlBuilderFactory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder xmlBuilder = null;
		
		//Attempt to setup builder with standard configuration
		try 
		{
			xmlBuilder = xmlBuilderFactory.newDocumentBuilder();
		} 
		catch (ParserConfigurationException e) {
			e.printStackTrace();
			System.out.println("Error in setting up XML reader");
			return null;
		}
						
		//Set location of XML file to read
		Document xmlDatabase = null;
		
		//Attempt to parse XML file
		try 
		{
			xmlDatabase = xmlBuilder.parse(fileLocation);
		} 
		catch (SAXException e) 
		{
			e.printStackTrace();
			System.out.println("Error XML Schema: Unable to read file");
			return null;
		} 
		catch (IOException e) 
		{
			System.out.println("File not found: Invalid file location");
			return null;
		}
		
		//Normalise the XML Structure
		xmlDatabase.getDocumentElement().normalize();
				
		//Multimedia elements
		NodeList multiNodest = xmlDatabase.getElementsByTagName("multimedia");
		
		//Check that some nodes where detected
		if (multiNodest.getLength() == 0)
		{
			return null;
		}
		
		//Create array list of TextHandlers
		ArrayList <TextHandler> textHandlers = new ArrayList <TextHandler>();
		
		//Loop through all multumedia nodes
		for (int i = 0; i < multiNodest.getLength(); i++)
		{
			//Get node in list
			Node currentNode = multiNodest.item(i);
		 
			//Check if valid node
			if (currentNode.getNodeType() == Node.ELEMENT_NODE)
			 {
			    ///Cast Node as element to read data
			    Element currentElement = (Element) currentNode;
			    
			    //Define boolean to check if media is of type text
			    Boolean isTextNode = false;
			    
			    //Element may be corrupt, so check if valid
			    try
			    {	
			    	isTextNode = currentElement.getElementsByTagName("type").item(0).getTextContent().trim().toLowerCase().equals("text");
			    }
			    catch (NullPointerException e) 
			    {
			    	System.out.println("type element not found");
			    	break;
			    }
			    
			    //As element has been read correctly, check if it is of type text
			    if (isTextNode)
			    {
			    	//Define string to hold filelocation element
			    	String textBody = "";
			    	
			    	//Get main body text, trim it (to remove whitepsaces) and remove tabs
			    	try
			    	{
			    		textBody = currentElement.getElementsByTagName("filelocation").item(0).getTextContent().trim().replaceAll("\t", "");
			    	}
		    		catch (NullPointerException e) 
		    		{
		    			System.out.println("filelocation element not found");
		    		}	
		    	
			    	//Create new TextHandler class
			    	TextHandler newTextHandler = new TextHandler(textBody);
			    	
			    	//Add default attributes
			    	addDefaultAttributes(currentElement, newTextHandler);
			    	
			    	//Add additional attributes
			    	addAdditionalAttributes(currentElement, newTextHandler);		    	
			    			
			    	//Add newTextHandler to arrayList
			    	textHandlers.add(newTextHandler);
			    }			    
			 }
		}
		
		//Return list of text handlers
		return textHandlers;
	}

	//Method to read the Default Attributes (Font, colour and size)
	private TextHandler addDefaultAttributes(Element currentElement, TextHandler newTextHandler)
	{
		//Declare string that will hold the style text
		String styleBody = null;
		
    	//Try read Style from metadata tag
    	try
    	{
    		styleBody = currentElement.getElementsByTagName("style").item(0).getTextContent().trim();
    	}
    	catch (NullPointerException e) 
    	{
    		System.out.println("textlocationy element not found");
    		return newTextHandler;
    	}	
    	
    	//Separate attributes by ;
    	String[] syleMeta = styleBody.split(";");
    	
    	if (syleMeta.length != 3)
    	{
    		System.out.println("Formatting of Style is incorrect");
    		return newTextHandler;
    	}
    	
   		//Extract font size from syleMeta
	   	String fontSizeString = syleMeta[0].replace("-fx-font-size: ", "");
	   	fontSizeString = fontSizeString.replace("pt", "");
	    
	   	//Attempt to parse string as float
	   	try
	   	{
	   		//Parse string to float and add to newTextHandler
	   		Float fontSize = Float.parseFloat(fontSizeString);
	   		newTextHandler.setTextSize(fontSize);
	   	}
	   	catch (NumberFormatException e) 
	   	{
	   		System.out.println("Invalid FontSize");
	    }				   	
	   			    				    	
    	//Extract font from syleMeta
    	String fontString = syleMeta[1].replace(" -fx-font-family: ", "");
    	fontString = fontString.replace("\"", "");
    	
    	//Add font to newTextHandler
    	newTextHandler.setFont(fontString);
    	
    	//Obtain colour from syleMeta
    	String fontColour = syleMeta[2].replace(" -fx-color: #", "");
    	
    	//Add colour to newTextHandler
    	newTextHandler.setColour(fontColour);
		
    	//Return handler
		return newTextHandler;
	}
	
	//Method to read the additional attributes (weight, posture, underlined and text location)
	private TextHandler addAdditionalAttributes(Element currentElement, TextHandler newTextHandler)
	{
		//Try extract weight from XML
    	try
    	{
    		String textWeight = currentElement.getElementsByTagName("weight").item(0).getTextContent().trim().toUpperCase();
    		//Add to newTextHandler
    		newTextHandler.setWeight(FontWeight.findByName(textWeight));
    	}
    	catch (NullPointerException e) 
    	{
    		if (additionalAttributesConsoleFlag_)
    		{
    			System.out.println("weight element not found");
    		}			    		
    	}	    	
    	
    	//Try extract posture from XML
    	try
    	{
    		String textPosture = currentElement.getElementsByTagName("posture").item(0).getTextContent().trim().toUpperCase();
    		//Add to newTextHandler
    		newTextHandler.setPosture(FontPosture.findByName(textPosture));
    	}
    	catch (NullPointerException e) 
    	{
    		if (additionalAttributesConsoleFlag_)
    		{
    			System.out.println("posture element not found");
    		}			    		
    	}	
    	
    	//Try extract underlined from XML
    	try
    	{
    		String textUnderlined = currentElement.getElementsByTagName("underlined").item(0).getTextContent().trim().toLowerCase();
    		//Parse string to boolean
    		Boolean isUnderlined = Boolean.parseBoolean(textUnderlined);
    		//Add to newTextHandler
    		newTextHandler.setUnderlined(isUnderlined);
    	}
    	catch (NullPointerException e) 
    	{
    		if (additionalAttributesConsoleFlag_)
    		{
    			System.out.println("underlined element not found");
    		}			    		
    	}	
 	    	
    	//Try extract text position X from XML
    	try
    	{
    		String textXPositionString = currentElement.getElementsByTagName("textlocationx").item(0).getTextContent().trim();
    		//Parse string to integer
    		int textXPosition = Integer.parseInt(textXPositionString);
    		//Add to newTextHandler
    		newTextHandler.setXLocation(textXPosition);
    	}
    	catch (NullPointerException e) 
    	{
    		if (additionalAttributesConsoleFlag_)
    		{
    			System.out.println("textlocationx element not found");
    		}	
    	}	
    	catch (NumberFormatException e)
    	{
    		System.out.println("Text Location X element has invalid formatting");
    	}
    	
    	
    	//Try extract text position Y from XML
    	try
    	{
    		String textYPositionString = currentElement.getElementsByTagName("textlocationy").item(0).getTextContent().trim();
    		//Parse string to integer
    		int textYPosition = Integer.parseInt(textYPositionString);
    		//Add to newTextHandler
    		newTextHandler.setYLocation(textYPosition);
    	}
    	catch (NullPointerException e) 
    	{
    		if (additionalAttributesConsoleFlag_)
    		{
    			System.out.println("textlocationy element not found");
    		}	
    	}	
    	catch (NumberFormatException e)
    	{
    		System.out.println("Text Location Y element has invalid formatting");
    	}
		
		//Return handler
		return newTextHandler;
	}	
}
