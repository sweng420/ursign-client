/*
----------------------------------------------------------------------------------
-- Company: Ortus Innovations
-- Engineer: Alex McDonnell
-- 
-- Create Date: 6.3.2018
-- Module Name: Text Handler
-- Tool versions: Built in Eclipse IDE - Version 2018-12 (4.10.0)
-- Description: This class holds the attributes for the text handler
-- with the ability to get/set any attribute and the ability to create
-- a JavaFX text class. 
-- 
-- Dependencies: JavaFX
--
-- Revision: 3
-- Revision 1 - File Created
-- Revision 2 - Added master getters and setters
-- Revision 3 - Added through checks for all inputs to ensure all inputs arguments
--				are checked and valid. 
-- Additional Comments:
-- 
----------------------------------------------------------------------------------
*/

package text_handler;

import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * TextHandler Class
 * This class generates a JavaFX Text Node for a given set of attributes.
 */
public class TextHandler 
{
	//Give values to error and default values 
	final static String errorBodyText_ = "Error: No valid text has been loaded!";
	final static String defaultTextFont_ = "verdana";
	final static FontWeight defaultFontWeight_ = FontWeight.NORMAL;
	final static FontPosture defaultFontPosture_ = FontPosture.REGULAR;
	final static boolean defaultIsFontUnderlined_ = false;
	final static Paint defaultTextColour_ = Color.web("000000");
	final static float defaultTextSize_ = 16;
	final static int defaultTextXLocation_ = 0;
	final static int defaultTextYLocation_ = 20;
	final static int defaultTextWrappingWidth_ = 0;
	
	
	//Private fields
	private String textFont;
	private FontWeight fontWeight;
	private FontPosture fontPosture;
	private boolean isFontUnderlined;
	private Paint textColour;
	private float textSize;
	private int textXLocation;
	private int textYLocation;
	private int textWrappingWidth;
	private String textBody;
	
	/**
    * Constructor
    * <br><br/>
    * The master constructor for the TextHandler class 
    *
    * @param textBody String main body of text to be displayed
    */
	public TextHandler(String textBody)
	{
		//Set defaults
		this.textFont = defaultTextFont_;
		this.fontWeight = FontWeight.NORMAL;
		this.fontPosture = FontPosture.REGULAR;
		this.isFontUnderlined = defaultIsFontUnderlined_;
		this.textColour = defaultTextColour_;
		this.textSize = defaultTextSize_;
		this.textXLocation = defaultTextXLocation_;
		this.textYLocation = defaultTextYLocation_;
		this.textWrappingWidth = defaultTextWrappingWidth_;
				
		//Check if input textBody is valid
		if (textBody.equals("") || textBody == null)
		{
			this.textBody = errorBodyText_;
			System.out.println("No valid text loaded");
		}
		else
		{
			//If so check if its a filepath
			this.textBody = isTextFileLocation(textBody);
			
			//Once complete, check if private field textBody is valid
			//This checks the read txt file for errors
			if (this.textBody.equals("") || this.textBody == null)
			{
				this.textBody = errorBodyText_;
				System.out.println("No valid text loaded");
			}
		}
	}
	
	/**
	* Create Frame
	* <br><br/>
	* Method that generates a JavaFX Text class for the current set of text attributes 
	*
	* @return JavaFX Text instance
	*/
	//Method to create a text instance
	public Text createFrame()
	{
		//Create new Text object 
	    Text text = new Text(); 
	            
	    //Set font to the text 
	    text.setFont(Font.font(this.textFont,this.fontWeight,this.fontPosture,this.textSize)); 
	       
	    //Set position of the text
	    text.setX(this.textXLocation); 
	    text.setY(this.textYLocation);       
	    
	    //Apply wrapping if WrappingWidth is above 0
	    if (this.textWrappingWidth > 0)
	    {
	    	text.setWrappingWidth(this.textWrappingWidth);
	    }
	    
	    text.setUnderline(this.isFontUnderlined);
	    	    
	    text.setFill(this.textColour); 
	      
	    //Setting the text to be added. 
	    text.setText(this.textBody); 
	    
	    return text;
	}
		
	//Public setters
	/**
	* Set Font
	* <br><br/>
	* Sets the font of the text. Input font is checked and validated. 
	* If an invalid font is entered, the default font will be selected.
	* 
	* @param textFont String
	*/
	public void setFont(String textFont)
	{
		//Set to default font incase font is invalid
		this.textFont = defaultTextFont_;
		
		//Check if font is valid
		GraphicsEnvironment graphicsEnvio = null;
		graphicsEnvio = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		//Get list of fonts
        String[] fontsAvailable = graphicsEnvio.getAvailableFontFamilyNames();
        
        //Check if valid font
        for (int i = 0; i < fontsAvailable.length; i++) 
        {    
        	//If font found break loop and set font
		    if(fontsAvailable[i].equals(textFont))
		    {
		    	this.textFont = textFont;
		    	break;
		    }
        }
	}
	
	/**
	* Set Text Weight
	* <br><br/>
	* Sets the text weight of the text. Input is checked and validated.
	* If an invalid text weight is entered, the default text weight will be selected.
	* <br><br/>
	* See javafx.scene.text.FontWeight for possible inputs
	* 
	* @see javafx.scene.text.FontWeight
	* 
	* @param fontWeight FontWeight
	*/
	public void setWeight(FontWeight fontWeight)
	{
		//Check if input is null, if so load default weight
		if (fontWeight == null)
		{
			this.fontWeight = defaultFontWeight_;
		}
		else
		{
			this.fontWeight = fontWeight;
		}	
	}
	
	/**
	* Set Text Posture
	* <br><br/>
	* Sets the text posture of the text. Input is checked and validated.
	* If an invalid text posture is entered, the default text posture will be selected.
	* <br><br/><br><br/>
	* Possible inputs<br><br/>
	* FontPosture.REGULAR<br><br/>
	* FontPosture.ITALIC
	* @see javafx.scene.text.FontPosture
	* 
	* @param fontPosture FontPosture
	*/
	public void setPosture(FontPosture fontPosture)
	{
		//Check if input is null, if so load default Posture
		if (fontPosture == null)
		{
			this.fontPosture = defaultFontPosture_;
		}
		else
		{
			this.fontPosture = fontPosture;
		}
	}
	
	/**
	* Set Text Underlined
	* <br><br/>
	* Sets if the text is underlined (true) or not (false).
	* 
	* @param isFontUnderlined boolean
	*/
	public void setUnderlined(boolean isFontUnderlined)
	{
		this.isFontUnderlined = isFontUnderlined;
	}
	
	/**
	* Set Text Colour
	* <br><br/>
	* Sets the colour of the text. Input is checked and validated.
	* If an invalid colour is entered, the default colour will be selected.
	* 
	* @param hexColour String. Must be 6 characters (standard hex notation)
	*/
	public void setColour(String hexColour)
	{
		//Check if input is null, if so load default colour
		if (hexColour.length() != 6)
		{
			this.textColour = defaultTextColour_;
		}
		else
		{
			this.textColour = Color.web(hexColour);
		}
	}
	
	/**
	* Set Text Size
	* <br><br/>
	* Sets the size of the text. Input is checked and validated.
	* If an invalid size is entered, the default size will be selected.
	* 
	* @param textSize float. Can not be negative.
	*/
	public void setTextSize(float textSize)
	{
		//Check textSize has valid range, in this case must be greater than zero
		if (textSize > 0)
		{
			this.textSize = textSize;
		}
		else
		{
			this.textSize = defaultTextSize_;
			System.out.println("Invalid text size");
		}
		
	}
	
	/**
	* Set X Position
	* <br><br/>
	* Sets the X position of the text. Input is checked and validated.
	* If an invalid X position is entered, the default X position will be selected.
	* 
	* @param textXLocation Integer. Can not be negative.
	*/
	public void setXLocation(int textXLocation)
	{
		//Check textXLocation has valid range, in this case must not be negative
		if (textXLocation < 0)
		{
			this.textXLocation = defaultTextXLocation_;
			System.out.println("Invalid text X location");
		}
		else
		{
			this.textXLocation = textXLocation;
		}
	}
	
	/**
	* Set Y Position
	* <br><br/>
	* Sets the Y position of the text. Input is checked and validated.
	* If an invalid Y position is entered, the default Y position will be selected.
	* 
	* @param textYLocation Integer. Can not be negative.
	*/
	public void setYLocation(int textYLocation)
	{
		//Check textXLocation has valid range, in this case must not be negative 
		if (textYLocation < 0)
		{
			this.textYLocation = defaultTextYLocation_;
			System.out.println("Invalid text Y location");
		}
		else
		{
			this.textYLocation = textYLocation;
		}
	}
	
	/**
	* Set Wrapping Width
	* <br><br/>
	* Sets the wrapping width of the text. Input is checked and validated.
	* If an invalid wrapping width is entered, the default wrapping width will be selected.
	* 
	* @param textWrappingWidth Integer. Can not be negative.
	*/
	public void setWrappingWidth(int textWrappingWidth)
	{
		//Check textWrappingWidth has valid range, in this case must not be negative
		if (textWrappingWidth < 0)
		{
			System.out.println("Invalid width");
			this.textWrappingWidth = defaultTextWrappingWidth_;
		}
		else
		{
			this.textWrappingWidth = textWrappingWidth;
		}
	}
	
	//Public getters
	/**
	* Get Text Body
	* <br><br/>
	* Returns the current body of text.
	* 
	* @return textBody String
	*/
	public String getBodyText()
	{
		return this.textBody;
	}
	
	/**
	* Get Font
	* <br><br/>
	* Returns the current font of text.
	* 
	* @return textFont String
	*/
	public String getFont()
	{
		return this.textFont;
	}
	
	/**
	* Get Text Weight
	* <br><br/>
	* Returns the text weight of text.
	* 
	* @return fontWeight FontWeight
	*/
	public FontWeight getWeight()
	{
		return this.fontWeight;
	}
		
	/**
	* Get Text Posture
	* <br><br/>
	* Returns the text posture of text.
	* 
	* @return FontPosture FontPosture
	*/
	public FontPosture getPosture()
	{
		return this.fontPosture;
	}
	
	/**
	* Get Is Font Underlined
	* <br><br/>
	* Returns the the boolean indicating if the text is underlined. 
	* 
	* @return isFontUnderlined boolean
	*/
	public boolean getUnderlined()
	{
		return this.isFontUnderlined;
	}

	/**
	* Get Text Colour
	* <br><br/>
	* Returns the text colour as Paint class. 
	* 
	* @return textColour Paint
	*/
	public Paint getColour()
	{
		return this.textColour;
	}
	
	/**
	* Get Text Size
	* <br><br/>
	* Returns the size of the text in pixels.
	* 
	* @return textSize float
	*/
	public float getTextSize()
	{
		return this.textSize;
	}
	
	/**
	* Get X Location
	* <br><br/>
	* Returns the X location of the text in pixels.
	* 
	* @return textXLocation Integer
	*/
	public int getXLocation()
	{
		return this.textXLocation;
	}
	
	/**
	* Get Y Location
	* <br><br/>
	* Returns the Y location of the text in pixels.
	* 
	* @return textYLocation Integer
	*/
	public int getYLocation()
	{
		return this.textYLocation;
	}
	
	/**
	* Get Wrapping Width
	* <br><br/>
	* Returns the wrapping width in pixels.
	* 
	* @return textWrappingWidth Integer
	*/
	public int getWrappingWidth()
	{
		return this.textWrappingWidth;
	}
		
	//Method to check if the textBody input is text or a .txt 
	private String isTextFileLocation(String textBody)
	{
		//Create new string to hold the body of text
		String outputText = new String();
		
		//Find last 4 characters of textBody
		String textLastFourChars = textBody.substring(textBody.length() - 4);
		
		//If the last characters are .txt, then must be a file location.
		if (textLastFourChars.equals(".txt"))
		{
			//Attempt to read text file
			try 
			{
				//Set output string to contents of the read file
				outputText = new TextFileReader(textBody).readFile();
			} 
			catch (IOException Exception) 
			{
				//If file cannot be read, warn user and print stack trace
				System.out.println("Text file could not be read at " + textBody);
				Exception.printStackTrace();
			}
		}
		else
		{
			//If contents is not a file location, then set output string to input string
			outputText = textBody;
		}
		
		return outputText;		
	}
}
