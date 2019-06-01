package ursign.ursign_client;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import ursign.ursign_client.Util;

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
               
               p.setMultimedias(Util.parseMultimedias(mList));

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