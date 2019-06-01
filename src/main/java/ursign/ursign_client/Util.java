package ursign.ursign_client;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class Util {
	public static Iterable<Node> iterable(final NodeList n) {
		  return new Iterable<Node>() {

		    @Override
		    public Iterator<Node> iterator() {

		      return new Iterator<Node>() {

		        int index = 0;

		        @Override
		        public boolean hasNext() {
		          return index < n.getLength();
		        }

		        @Override
		        public Node next() {
		          if (hasNext()) {
		            return n.item(index++);
		          } else {
		            throw new NoSuchElementException();
		          }  
		        }

		        @Override
		        public void remove() {
		          throw new UnsupportedOperationException();
		        }
		      };
		    }
		  };
	}
	
	public static List<Multimedia> parseMultimedias(NodeList mList) {
		   List<Multimedia> ret = new ArrayList<Multimedia>();
		   for (int m_temp = 0; m_temp < mList.getLength(); m_temp++) {
	           Node mNode = mList.item(m_temp);
	           if(mNode != null) {
	               if (mNode.getNodeType() == Node.ELEMENT_NODE) {
	                   Element mElement = (Element) mNode;
	                   String rowInfo = mElement.getAttribute("row");
	                   Node styleNode = mElement.getElementsByTagName("style").item(0);
	                   String styleString;
	                   if(styleNode == null) {
	                 	  styleString = "";
	                   } else {
	                 	  styleString = styleNode.getTextContent();
	                   }
	                   String loc = mElement.getElementsByTagName("filelocation").item(0).getTextContent();
	                   String multiText = loc;
	                   if(mElement.getElementsByTagName("type").item(0).getTextContent().equals("text")){
	                 	  if(loc.endsWith(".txt")){
	                 		  try {
	                 			  byte[] encoded = Files.readAllBytes(Paths.get(loc));
	                 			  multiText = new String(encoded, StandardCharsets.UTF_8);
	                 		  } catch (IOException ioe) {
	                 			  System.out.println("Warning: Couldn't read file "+loc+", using as literal.");
	                 		  }
	                 	  }
	                   }
		               ret.add(new Multimedia(
		                		  mElement.getElementsByTagName("type").item(0).getTextContent(),
		                		  multiText,
		                		  styleString,
		                		  rowInfo
		                ));
	               }
	           }
		   }
		   return ret;
	   }
}
