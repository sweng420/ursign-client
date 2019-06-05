package ursign.ursign_client;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import ursign.ursign_client.Presentation;

public class TestFeaturePresentation {
	
	private Presentation presentation;
	
	@Test
	public void testConstructor() {;
		ArrayList<Page> pages = new ArrayList<Page>();
		
		Page page1 = new Page();
		Page page2 = new Page();
		Page page3 = new Page();
		
		pages.add(page1);
		pages.add(page2);
		
		presentation.setPages(pages);
		presentation.addPage(page3);
		presentation.setID(69);
		
		assertEquals(presentation.getPages(), pages.add(page3));
		
	}
}
	
	