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

public class HeapSort {

	private int[] ArraySort;
	private static int h;
    private static int left_sorted;
    private static int right_sorted;
    private static int TopValue;
	
	public static void main(String[] args) {
		
		String filePath = "Input-2-20.xml";
	    File xmlFile = new File(filePath);
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder DB;
	    try {
	        DB = dbf.newDocumentBuilder();
	        Document doc = DB.parse(xmlFile);
	        doc.getDocumentElement().normalize();	        
	        NodeList nl = doc.getElementsByTagName("Value");
	        
	        int[] InputArray= {0, 1, 2, 2, 4, 4, 5, 6, 7, 9, 9, 10, 10, 11, 12, 13, 14, 15, 17, 20, 20, 21, 22, 23, 24, 25, 26, 26, 28, 28, 29,30};//new int[nl.getLength()];
	        for (int i = 0; i < nl.getLength(); i++) {
                //InputArray[i]= Integer.parseInt(nl.item(i).getTextContent());
            }
	    
	        
	        HeapSort hp=new HeapSort();        
	        		
			long StartTime = System.nanoTime();		
			hp.heapsort(InputArray);
			long StopTimer = System.nanoTime();		
			double FInalTimer = (StopTimer - StartTime)/1000000F;			
			
			System.out.println("Sorted Array : \n");	 
			for(int i = 0; i<InputArray.length; i++){
	            System.out.print(InputArray[i]+" ");            
	        }	
			System.out.println("\nExecution Time: " + FInalTimer + " milliseconds");
			System.out.println("Number of key Comparisions are: "+Compare.comparecount);
	       
	    }catch (SAXException|ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
	}
	
	public void heapsort(int[] ArraySort2)
	{
		this.ArraySort = ArraySort2;
		buildheap(ArraySort);
		
		for(int i=h ; i>0 ; i--)
        {
            swap(0, i);
            h--;
            maxheap(ArraySort, 0);
        }
	}
	
	public void buildheap(int[] ArraySort)
    {
		
		
        h=ArraySort.length-1;
        for(int i=h/2;i>=0;i--)
        {
        	Compare.comparecount++;
            maxheap(ArraySort,i);
        }
    }
	
	public void maxheap(int[] ArraySort, int i)
    { 
		Compare.comparecount++;
		
        left_sorted=2*i;
        right_sorted=2*i+1;
        if(left_sorted <= h && ArraySort[left_sorted] > ArraySort[i])
        {
            TopValue=left_sorted;
        }
        else
        {
            TopValue=i;
        }
        
        if(right_sorted <= h && ArraySort[right_sorted] > ArraySort[TopValue])
        {
            TopValue=right_sorted;
        }
        if(TopValue!=i)
        {
            swap(i,TopValue);
            maxheap(ArraySort, TopValue);
        }
    }
	
	public void swap(int i, int j)
	{
		int temp = this.ArraySort[i];
		this.ArraySort[i] = this.ArraySort[j];
		this.ArraySort[j] = temp;
	}
	

}
