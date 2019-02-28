package ursign.ursign_client;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class CollectionParser {

	private Presentation presentation = new Presentation(0);


	public Presentation getPresentation() {
		return presentation;
	}
	
   public void parse(String source) {
	  
      try {
         File inputFile = new File(source);
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         
         int id;	//pass id to "id" 
         try {
        	 	id = Integer.parseInt(doc.getDocumentElement().getAttribute("value"));
         }
         catch(NumberFormatException e) {
        	 	id = 0;
         }
         presentation.setID(id);
         
         NodeList nList = doc.getElementsByTagName("page");
         System.out.println("----------------------------");
         
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;

               NodeList mList = doc.getElementsByTagName("multimedia");
               System.out.println("----------------------------");
               Page p = new Page();
         //MULTIMEDIA
     
               for (temp = 0; temp < mList.getLength(); temp++) {
                  Node mNode = mList.item(temp);
                  System.out.println("\nCurrent Element :" + mNode.getNodeName());
                  if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                      Element mElement = (Element) mNode;
	                  p.addMultimedia(new Multimedia(
	                		  mElement.getElementsByTagName("type").item(0).getTextContent(),
	                		  mElement.getElementsByTagName("filelocation").item(0).getTextContent()));  
	                  
	        
                  }
               }
               System.out.println(p);
               presentation.addPage(p);
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
//https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm