package ursign.ursign_client;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import ursign.ursign_client.Multimedia.MultimediaType;

public class TestFeaturePage {
	
	private Page page;
	
	@Test
	public void testAddAndGet() {;
		ArrayList<Multimedia> multimedias = new ArrayList<Multimedia>();
	
		// Define two multimedia objects
		MultimediaType image = MultimediaType.image;
		MultimediaType audio = MultimediaType.audio;
		
		Multimedia newMultimediaImage = new Multimedia(image, "location", "style", 2);
		Multimedia newMultimediaAudio = new Multimedia(audio, "location2", "style2", 3);
		
		// Add to test list
		multimedias.add(0, newMultimediaImage);
		multimedias.add(1, newMultimediaAudio);
		
		// Add to page
		page.addMultimedia(newMultimediaImage);
		page.addMultimedia(newMultimediaAudio);
	
		// Compare test list to list generated by page variable
		assertEquals(page.getMultimedias(), multimedias);
		assertEquals(page.toString(), multimedias.toString());
		
	}
	
	@Test
	public void testSetMultimedia(){
		ArrayList<Multimedia> multimedias = new ArrayList<Multimedia>();
		
		// Define two multimedia objects
		MultimediaType image = MultimediaType.image;
		MultimediaType audio = MultimediaType.audio;
		
		Multimedia newMultimediaImage = new Multimedia(image, "location", "style", 2);
		Multimedia newMultimediaAudio = new Multimedia(audio, "location2", "style2", 3);
		
		// Add to test list
		multimedias.add(0, newMultimediaImage);
		multimedias.add(1, newMultimediaAudio);
		
		// Add to page
		page.setMultimedias(multimedias);
	
		// Compare test list to list generated by page variable
		assertEquals(page.getMultimedias(), multimedias);
		assertEquals(page.toString(), multimedias.toString());
		
	}
}
	
	