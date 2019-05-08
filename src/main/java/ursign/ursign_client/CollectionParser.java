package ursign.ursign_client;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class CollectionParser {

	private String source;
	private Presentation presentation;
	CollectionParser(String src) {
		this.source = src;
	}

	public Presentation getPresentation() {
		return presentation;
	}
	
   public void parse() {
	  presentation = new Presentation(0);
      try {
         File inputFile = new File(source);
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         
         int id;	//pass id to "id" 
         try {
        	 	id = Integer.parseInt(doc.getDocumentElement().getAttribute("value"));
         }
         catch(NumberFormatException e) {
        	 	id = 0;
         }
         presentation.setID(id);
         
         NodeList nList = doc.getElementsByTagName("page");
         //System.out.println("----------------------------");
         Page p = new Page();
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            //System.out.println("\nCurrent Element :" + nNode.getNodeName());
            //System.out.println("new page" + nList.getLength());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;

               NodeList mList = eElement.getElementsByTagName("multimedia");
               //System.out.println("----------------------------");
               p = null;
               p = new Page();
               
               //MULTIMEDIA
               //System.out.println("mlist length::"+mList.getLength());
               for (int m_temp = 0; m_temp < mList.getLength(); m_temp++) {
                  Node mNode = mList.item(m_temp);
                  if(mNode != null) {
	                  if (mNode.getNodeType() == Node.ELEMENT_NODE) {
	                      Element mElement = (Element) mNode;
	                      Node styleNode = mElement.getElementsByTagName("style").item(0);
	                      String styleString;
	                      if(styleNode == null) {
	                    	  styleString = "";
	                      } else {
	                    	  styleString = styleNode.getTextContent();
	                      }
		                  p.addMultimedia(new Multimedia(
		                		  mElement.getElementsByTagName("type").item(0).getTextContent(),
		                		  mElement.getElementsByTagName("filelocation").item(0).getTextContent(),
		                		  styleString
		                		  ));
	                  }
                  }
               }

               //System.out.println(p);
               presentation.addPage(p);
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
//https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm