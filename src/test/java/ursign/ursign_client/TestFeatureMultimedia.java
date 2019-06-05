package ursign.ursign_client;

import static org.junit.Assert.*;

import org.junit.Test;
import ursign.ursign_client.CollectionParser;
import ursign.ursign_client.Multimedia.MultimediaType;

public class TestFeatureMultimedia {
	
	private Multimedia myMultimedia; 
	
	@Test
	public void testConstructor() {
		MultimediaType type = MultimediaType.image;
		String filelocation = "location";
		String style = "style";
		int row = 4;
		
		 myMultimedia = new Multimedia(type, filelocation, style, row);
		
		// Test inputs
		assertEquals(4, myMultimedia.getRowInfo());
		assertEquals("style", myMultimedia.getStyle());
		assertEquals("location", myMultimedia.getFilelocation());
		assertEquals(MultimediaType.image, myMultimedia.getType());
		
		// Since not text type, this should return a false
		assertEquals(false, myMultimedia.isText());
		
	}
	
	@Test
	public void testToStringAndGetters() {
		MultimediaType newType = MultimediaType.text;
		
		// Update fields to be different using setters
		myMultimedia.setFilelocation("newLocation");
		myMultimedia.setType(newType);
		
		Multimedia myMultimedia = new Multimedia(newType, "newLocation", "style", 4);
		
		assertEquals("Multimedia {Type: audio Location:newLocation Style:style Row:4", myMultimedia.toString());
		
		// Since now text type, this should return a true
		assertNotEquals(false, myMultimedia.isText());
		
	}

}
