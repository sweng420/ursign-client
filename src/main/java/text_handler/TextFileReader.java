/*
----------------------------------------------------------------------------------
-- Company: Ortus Innovations
-- Engineer: Alex McDonnell
-- 
-- Create Date: 6.3.2018
-- Module Name: Text Handler
-- Tool versions: Built in Eclipse IDE - Version 2018-12 (4.10.0)
-- Description: This class allows the reading of a text file from  
-- a local location and outputs a string.
-- 
-- Dependencies: JavaFX
--
-- Revision: 2
-- Revision 1 - File Created
-- Revision 2 - Ability to read with all errors checked.
 
-- Additional Comments:
-- 
----------------------------------------------------------------------------------
*/

package text_handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * TextFileReader Class
 * This class reads the contents of a .txt file and returns its contents as a string. 
 * Each new line within the text file is represented as a new line within the output.
 */
public class TextFileReader 
{
	//Define field for file location
	private File fileLocation;
	
	public TextFileReader(String fileLocation)
	{
		//Set input fileLocation to class field
		this.fileLocation = new File(fileLocation);
	}
	
	//Exception handled by external method
	public String readFile() throws IOException
	{
		//Declare objects for reading text file
		FileReader fileReader = new FileReader(fileLocation);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		//Create string that will hold output
		String textFile = new String();
		
		//Create lineCounter so
		int lineCount = 0;
			
		//Read first line
		String newLine = bufferedReader.readLine();
		
		//Read file while file still has lines
		while(newLine != null) 
		{	
			//For first count, don't need to add new line tag
			if(lineCount == 0)
			{
				textFile = textFile + newLine;
			}
			//Every other line requires new line tag to indicate new line
			else
			{
				textFile = textFile + "\n" + newLine;
			}
			
			//Read next line in file
			newLine = bufferedReader.readLine();
			
			//Increment lineCounter
			lineCount++;
		}
		
		//Close buffered reader
		bufferedReader.close();
				
		//Return string from file
		return textFile;
	}
}
