package ursign.ursign_client;

import static org.junit.Assert.*;

import org.junit.Test;
import ursign.ursign_client.CollectionParser;

public class TestFeatureXML {
	private CollectionParser cp;
	
	@Test
	public void testValidContents() {
		//fail("Not yet implemented");
		cp = new CollectionParser("colours2.xml");
		cp.parse();
		System.out.println(cp.getPresentation());
		System.out.println(cp.getPresentation().getPages().size());
		assertEquals(cp.getPresentation().getPages().size(), 3);
		//assertEquals(cp.getPresentation().getPages().get(0), )
	}
	
	@Test
	public void testNoFile() {
		//fail("Not yet implemented");
		cp = new CollectionParser("colours3.xml");
		cp.parse();
		System.out.println(cp.getPresentation());
		
		assertEquals(cp.getPresentation().getPages().size(), 4);
		//assertEquals(cp.getPresentation().getPages().get(0), )
	}

}
