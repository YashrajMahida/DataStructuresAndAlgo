package algorithms;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import DataStructureAlgo.Compare;

public class MergeSort {
	
	private int[] ArraySort;
	private int[] ArrayT;	
	
	public static void main(String args[])
	{
		
		String filePath = "Input-2-5.xml";
	    File xmlFile = new File(filePath);
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder DB;
	    try {
	        DB = dbf.newDocumentBuilder();
	        Document doc = DB.parse(xmlFile);
	        doc.getDocumentElement().normalize();	        
	        NodeList nodeList = doc.getElementsByTagName("Value");
	        
	        int[] ArrayInput = new int[nodeList.getLength()];
	        for (int i = 0; i < nodeList.getLength(); i++) {
                ArrayInput[i]= Integer.parseInt(nodeList.item(i).getTextContent());
            }
	    
	        
	        MergeSort MS = new MergeSort();
			
			
			long StartTimer = System.nanoTime();		
			MS.mergeSort(ArrayInput);
			long StopTimer = System.nanoTime();		
			double FinalTimer = (StopTimer - StartTimer)/1000000F;			
			
			System.out.println("Sorted Array is: \n");	 
			for(int i = 0; i<ArrayInput.length; i++){
	            System.out.print(ArrayInput[i]+" ");            
	        }	
			System.out.println("\nTime taken for execution: " + FinalTimer + " milliseconds");
			System.out.println("key comparison count is: "+Compare.comparecount);
			
	    }catch (SAXException|ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
	}
	
	public void mergeSort(int[] ArraySort2)
	{	
		if(ArraySort2.length <= 1 || ArraySort2 == null)
			return;
		else
		{
			this.ArraySort = ArraySort2;
			this.ArrayT = new int[ArraySort2.length];
			doMergeSort(0, ArraySort2.length - 1);				
		}
	}
	
	public void doMergeSort(int indexl, int indexr)
	{		
		if(Compare.doCompare(indexl, indexr, 3)) 
		{						
			int midpoint = indexl + (indexr - indexl)/2;					
			
			
			doMergeSort(indexl, midpoint);
			
			
			doMergeSort(midpoint+1, indexr);
			
			
			mergeSubArrays(indexl, midpoint, indexr);							
		}		
	}
	
	public void mergeSubArrays(int indexl, int midpoint, int indexr)
	{		
		for (int i = indexl; i <= indexr; i++) 
		{
            ArrayT[i] = ArraySort[i];
        }
		
		int i = indexl; 
		int j = midpoint + 1; 
		int k = indexl; 
		
		while(i <= midpoint && j <= indexr) 
		{
			if(Compare.doCompare(ArrayT[i], ArrayT[j], 4))  
			{				
				ArraySort[k] = ArrayT[i];
				i++;
			}
			else
			{				
				ArraySort[k] = ArrayT[j];
				j++;
			}
			k++;
		}				
		
		while(i<=midpoint)
		{
			ArraySort[k] = ArrayT[i];
			k++;
			i++;
		}
	}
	
	
}