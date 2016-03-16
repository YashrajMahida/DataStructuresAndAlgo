package algorithms;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public final class RandomNumberGenerator {
	
	public static void main(String args[])
	{
		System.out.println("Please enter the value of base...");
		String input_baseval = new Scanner(System.in).nextLine();
		System.out.println("Please enter the value of exponent...");
		String input_expval = new Scanner(System.in).nextLine();
		if(tryParseInt(input_baseval) && tryParseInt(input_expval))
		{
			int basevalue = Integer.parseInt(input_baseval);
			int expvalue = Integer.parseInt(input_expval);			
			try {
				RandomNumberGenerator.generateNumbers(basevalue, expvalue);
				System.out.println("XML file containing the randomized array has been generated and has been saved in 'InputFiles' Directory.");
			} catch (IOException e) {				
				e.printStackTrace();
			}			
		}
		else
		{
			System.out.println("Error in parsing the values. Kindly enter integer values!");
		}
		
	}

	public static void generateNumbers(int base, int exp) throws IOException
	{
		try {
			Random randomGenerator = new Random();
			int endval = (int) Math.pow((double)base, (double)exp);
			
			//build the XML file which will store our randomly generated values
			DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docbuilder = docfactory.newDocumentBuilder();
			Document doc = docbuilder.newDocument();
			
			//element - Values
			Element element_allvalues = doc.createElement("File");
			doc.appendChild(element_allvalues);
			
			for (int i = 1; i <= endval; i++)
			{
			     int randomInt = randomGenerator.nextInt(endval);	
			     
			     Element element_val = doc.createElement("Value");
			     element_allvalues.appendChild(element_val);
			     Attr attr_val = doc.createAttribute("index");
			     attr_val.setValue(String.valueOf(i-1));
			     element_val.setAttributeNode(attr_val);
			     element_val.appendChild(doc.createTextNode(String.valueOf(randomInt)));			     
			}
			
			//write these values into the XML file
			TransformerFactory transfactory = TransformerFactory.newInstance();
			Transformer transformer = transfactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Input-"+base+"-"+exp+".xml"));
			transformer.transform(source, result);			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {		
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}		
	}
	
	public static boolean tryParseInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		} 
	}
}
