package quiz;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import quiz.Question.Type;
import ursign.ursign_client.Multimedia;
import ursign.ursign_client.Util;
import ursign.ursign_client.CollectionParser;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class QuizParser {
	
	private Quiz quiz = new Quiz(0);
	private String quizFile;
	public Quiz getQuiz() {
		return quiz;
	}
	
	public QuizParser(String source) {
		this.quizFile = source;
	}
	
   public void parse() {
	  
      try {
         File inputFile = new File(quizFile);
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         
         int id;
         try {
        	 	id = Integer.parseInt(doc.getDocumentElement().getAttribute("value"));
         }
         catch(NumberFormatException e) {
        	 	id = 0;
         }
         quiz.setID(id);
         
            		
         /* qlist - nodelist of questions
          */
         NodeList qList = doc.getElementsByTagName("question");
         
         /* qnode - a question node */
         for (Node qNode : Util.iterable(qList)) {
        	Question q;
        	 
        	String qTypeAttrib = ((Element) qNode).getAttribute("type");
        	switch(qTypeAttrib) {
        	case "multiple":
        		q = new Question(Type.multiple, 5);
        		break;
        	case "anki":
        		q = new Question(Type.anki, 5);
        		break;
        	case "fill":
        		q = new Question(Type.fill, 5);
        		break;
        	default:
        		throw new IllegalArgumentException("Unrecognised question type: "+qTypeAttrib);
        	}
        	
        	
            /* alist - nodelist of answers */
            NodeList aList = ((Element) qNode).getElementsByTagName("answer");

            for (Node aNode : Util.iterable(aList)) {          
                String answerid = ((Element) aNode).getAttribute("id");
                Answer answer = new Answer(answerid);
                
                /* get a list of the multimedia in this answer */
                NodeList answerMediaList = ((Element) aNode).getElementsByTagName("multimedia");
                
                List<Multimedia> answerMedias = Util.parseMultimedias(answerMediaList);
                
                for(Multimedia am : answerMedias){
                	answer.addMultimedia(am);
                }
                
                /* add answer to question */
                q.addAnswer(answer);
            }
            
            /* clist - nodelist of content */
            NodeList cList = ((Element) qNode).getElementsByTagName("content");
            
            /* cnode - first clist item */
            Node cNode = cList.item(0);
            
            /* mlist - list of multimedia */
            NodeList mList = ((Element) cNode).getElementsByTagName("multimedia");
            
            String qIndexAttrib = ((Element) qNode).getAttribute("index");
            if(qIndexAttrib != null) {
            	System.out.println(qIndexAttrib);
            }
            q.setIndex(Integer.parseInt(qIndexAttrib));
            
            String qCorrectAttrib = ((Element) qNode).getAttribute("correct");
            if(qIndexAttrib != null) {
            	System.out.println(qCorrectAttrib);
            }
            q.setCorrect(qCorrectAttrib);
            
            q.setContent(Util.parseMultimedias(mList));
            
            quiz.addQuestion(q);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
}
//https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm