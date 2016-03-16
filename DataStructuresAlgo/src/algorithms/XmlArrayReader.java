package algorithms;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlArrayReader {
	
	public static void main(String args[])
	{
		String filePath = "InputFiles\\Input_2_20.xml";
	    File xmlFile = new File(filePath);
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder;
	    try {
	        dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(xmlFile);
	        doc.getDocumentElement().normalize();	        
	        NodeList nodeList = doc.getElementsByTagName("Value");
	        
	        int[] array = new int[nodeList.getLength()];
	        for (int i = 0; i < nodeList.getLength(); i++) {
                array[i]= Integer.parseInt(nodeList.item(i).getTextContent());
            }
	        
	        for(int i = 0; i<array.length;i++)
	        {
	        	System.out.println(array[i]);
	        }
	        
	        System.out.println("\n\nLength of array is:- "+array.length);
	    }catch (SAXException|ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        } 		
    }
}
