package ursign.ursign_client;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class QuizParser {

	private Quiz quiz = new Quiz(0);


	public Quiz getQuiz() {
		return quiz;
	}
	
   public Boolean parse(String source) {
	  
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
         quiz.setID(id);
         
         //loop questions		type correct index
         	//content
         	//multimedia			
         	//loop answers		id	multimedia  
         
   //Question      		
       //Question
         NodeList qList = doc.getElementsByTagName("question");
         System.out.println("----------------------------");
         for (int temp = 0; temp < qList.getLength(); temp++) {
            Question q = new Question();
            Node nNode = qList.item(temp);				//adding questions
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            System.out.println("new question" + qList.getLength());
            
            //Answer
            NodeList aList = ((Element) nNode).getElementsByTagName("answer");
            System.out.println("----------------------------");

            for (int tempA = 0; tempA < qList.getLength(); tempA++) {
                Node aNode = aList.item(tempA);				//adding answers
                System.out.println("\nCurrent Element :" + aNode.getNodeName());
                System.out.println("new answer" + aList.getLength());
                
                String answerid = ((Element) aNode).getAttribute("value");
                Answer answer = new Answer(answerid);
            }
                
            //Content
            System.out.println("----------------------------");
            Content content = new Content();
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               NodeList cList = eElement.getElementsByTagName("content");
               Node contentNode = cList.item(0);
               if(contentNode == null) {
            	   	return false;
               }
               NodeList mList = ((Element) contentNode).getElementsByTagName("multimedia");
               System.out.println("----------------------------");
        //       q = null;
        //      q = new Question();
               
	             //Multimedia
	               System.out.println("mlist length::"+mList.getLength());
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
			                  content.addMultimedia(new Multimedia(
			                		  mElement.getElementsByTagName("type").item(0).getTextContent(),
			                		  mElement.getElementsByTagName("filelocation").item(0).getTextContent(),
			                		  styleString
			                		  ));
		                  }
	                  }
	               }
              // System.out.println(q);
            }
            quiz.addQuestion(q);
            	q = null;
         }
       
      } catch (Exception e) {
         e.printStackTrace();
      }
	return null;
   }
}
//https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm