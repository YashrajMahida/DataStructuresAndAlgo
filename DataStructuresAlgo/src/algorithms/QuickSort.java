package algorithms;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import DataStructureAlgo.Compare;

public class QuickSort {
	
	private int[] ArraySort;
	
	public static void main(String[] args) {
		
		
		String filePath = "Input-2-5.xml";
	    File xmlFile = new File(filePath);
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder DB;
	    try {
	        DB = dbf.newDocumentBuilder();
	        Document doc = DB.parse(xmlFile);
	        doc.getDocumentElement().normalize();	        
	        NodeList nodeList = doc.getElementsByTagName("Value");
	        
	        int[] InputArray = new int[nodeList.getLength()];
	        for (int i = 0; i < nodeList.getLength(); i++) {
                InputArray[i]= Integer.parseInt(nodeList.item(i).getTextContent());
            }
	    
	        QuickSort qs = new QuickSort();
			
			long StartTimer = System.nanoTime();		
			qs.quickSort(InputArray);	
			long StopTimer = System.nanoTime();		
			double FinalTimer = (StopTimer - StartTimer)/1000000F;			
			
			System.out.println("Sorted Array is: \n");	 
			for(int i = 0; i<InputArray.length; i++){
	            System.out.println(InputArray[i]+" ");            
	        }	
			System.out.println("\nTime taken for execution: " + FinalTimer + " milliseconds");
			System.out.println("key comparison count is: "+Compare.comparecount);
	       
	    }catch (SAXException|ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }		
	}
	
	public void quickSort(int[] ArraySort2)
	{		
		if(ArraySort2.length <= 1 || ArraySort2 == null)		
			return;		
		else
		{
			this.ArraySort = ArraySort2;
			doQuickSort(0, ArraySort2.length - 1);
			
			System.out.println("Sorted Array is: \n");	 
			for(int i:ArraySort){
	            System.out.print(i+" ");            
	        }
		}
	}
	
	public void doQuickSort(int IndexF, int IndexR)
	{
		int i = IndexF;
		int j = IndexR;
		
		if(Compare.doCompare(IndexF, IndexR, 2))	
			return;
		

		Random random = new Random();
		
		int pivot_value = this.ArraySort[IndexF + random.nextInt(IndexR - IndexF + 1)];
	
		while(i<=j) 
		{
			while(Compare.doCompare(this.ArraySort[i], pivot_value, 3))
			{
				i++;
			}
			
			while(Compare.doCompare(this.ArraySort[j], pivot_value, 1)) 
			{
				j--;
			}
			
			if(i <= j) 
			{
				swap(i, j);
				i++;
				j--;
			}
		} 
		
		if(IndexF < j)		
			doQuickSort(IndexF, j);
		if(i<IndexR)	
			doQuickSort(i, IndexR);				
	}
	
	public void swap(int i, int j)
	{
		int temp = this.ArraySort[i];
		this.ArraySort[i] = this.ArraySort[j];
		this.ArraySort[j] = temp;
	}
}
