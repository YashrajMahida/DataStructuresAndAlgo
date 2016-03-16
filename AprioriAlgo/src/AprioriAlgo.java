import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;  
import java.util.Date;
import java.util.Hashtable;  
import java.util.Scanner;  
import java.util.StringTokenizer;  

public class AprioriAlgo 
{  
	Vector<String> itemsData=null;     
	public static String[] items={"HersheyKissesChocolateCandy","ReesePeanutButterCupsChocolateCandy","MMPeanutChocolateCandy","SnickersChocolateCandy","KitKatChocolateCandy","GhirardelliChocolate","LindtChocolate","SkittlesCandy","TootsiePops","StarburstCandy"};
    public static String splitkey=",";  
    public static String fileName="Database1.txt";  
        
    private static double minSupV=0;  
    private static int itemCounter[];  
    private double minConV=1.0;  
    private static int transactions=0;
    int count;
    private static Scanner sc = new Scanner(System.in);  
    Vector<Hashtable<String,Integer>> supportData = new Vector<Hashtable<String,Integer>>();  
    Hashtable<String,Integer> confidence = new Hashtable<String,Integer>();  
    Vector<Vector<String>> strongRules=null;  
    Date date;
	long start;
    public static void main(String[] args)throws Exception  
    {  
    	AprioriAlgo aa=new AprioriAlgo();  
    	  
    	aa.initialise();  
    	
    	{  
    		aa.processData();  
    	}  
    }  
    public void initialise()  
    {  
    	System.out.println("Enter the Minimum Support Value:");  
    	minSupV=sc.nextDouble();  
    	System.out.println("Enter the Confidence Value:");  
    	minConV=sc.nextDouble();  
    }  
    public void processData()throws Exception  
    {  
    	strongRules = new Vector<Vector<String>>();  
    	System.out.println("Enter the File Name:");  
    	
    	fileName=sc.next();  
    	System.out.println("filename:"+fileName+"\n");  
    	itemsData=new Vector<String>();      
    	count=0;  
    	transactions=0;
   
    	date=new Date();
    	start=date.getTime();
    	do  
    	{  
    		count++;  
    		numberOfItemsProcessed(count);  
    		getFrequentItems(fileName);  
    		calculateFrequentItems();  
    		Vector<String> tempData = new Vector<String>(itemsData);  
    		strongRules.add(tempData);  
    	}while(itemsData.size()>1);  
    	
    	calculateCofidence();	
    }  
    public void calculateCofidence() throws IOException  
    {  
    	String[] spliter=null; 
    	
    	for(int i=0; i<strongRules.size(); i++)  
    	{  
    			itemsData=strongRules.get(i);  
    			System.out.println("Frequent " + (i+1) + "-itemsets");  
    			for(int items=0; items<itemsData.size(); items++)  
    			{  
    				String key = itemsData.get(items);  
    				Hashtable<String,Integer> confidence=supportData.get(i);  
    				int data = confidence.get(key);  
    				String rule = itemsData.get(items);  
    				spliter = rule.split("\\"+" ");
    				double calculateSupport = (itemCounter[i]/(double)transactions);
    				if(spliter.length>1)  
    				{  
    					String topKey = spliter[0];  
    					int topValue = confidence.get(topKey);  
    					double calculateConfidence = ((double)topValue/data);  
    					if(calculateConfidence >= minConV)  
    					{  
   							System.out.println(key +", Minimum Support: "+ calculateSupport+ ", Minimum Cofidence: "+calculateConfidence);
    					}  
    				}  
    				else  
    				{  
    					if(data>=minConV)  
    					{  
    						
    						{
    							System.out.println(key +", Minimum Support: "+ calculateSupport+ ", Minimum Cofidence: "+minConV);
    						}  
    					}  
    				}  
    			}  
    		}
    	
    	date=new Date();
    	long end = date.getTime();
    	System.out.println("Execution Time is: "+(double)((end-start)/1000)+" Seconds");
    }  
  
    public Vector<String> numberOfItemsProcessed(int count)  
    {  
    	Vector<String> tempItemsData = new Vector<String>();  
    	StringTokenizer st1 = null;  
    	StringTokenizer st2 = null;  
    	String a = null;  
    	String b = null;  
    	if(count == 1)  
    	{  
    		for(int i=0; i<items.length; i++)  
    		{  
    			
    			tempItemsData.add(items[i]);  
    		}  
    	}  
    	else if(count == 2)  
    	{  
    			for(int i=0; i<items.length; i++)
    			{
    				for(int j=(i+1); j<items.length; j++)
    				{
    					if(i!=j)
    						tempItemsData.add(items[i]+" "+items[j]);
    				}	
    			}
    	}
    	else  
    	{  
    		tempItemsData = new Vector<String>();  
    		for(int items=0; items<itemsData.size(); items++)  
    		{  
    			for(int temp=items+1; temp<itemsData.size(); temp++)  
    			{              
    				a = "";  
    				b = "";  
    				st1 = new StringTokenizer(itemsData.get(items));  
    				st2 = new StringTokenizer(itemsData.get(temp));  	
    				for(int s=0; s<count-2; s++)  
    				{  
    					a = a + " " + st1.nextToken();  
    					b = b + " " + st2.nextToken();  
    				}  
    				if(b.compareToIgnoreCase(a)==0)  
    					tempItemsData.add((a + " " +st1.nextToken() + " " + st2.nextToken()).trim());  
    			}  
    		}  
    	}  
    	itemsData.clear();  
    	itemsData = new Vector<String>(tempItemsData);  
    	tempItemsData.clear();  
    	return itemsData;  
    }  
    public void getFrequentItems(String fileName)throws Exception  
    {  
    	BufferedReader br = null;  
    	br = new BufferedReader(new FileReader(fileName));  
    	String stline = null;  
    	splitkey = ",";  
    	StringTokenizer fileToken = null;  
    	itemCounter = new int[itemsData.size()];  
    	Hashtable<String, String> hashData = new Hashtable<String, String>();  
    	while((stline=br.readLine())!=null)  
    	{  
    		hashData = new Hashtable<String, String>();  
    		fileToken = new StringTokenizer(stline, splitkey);   
    		for(int j=0; j<items.length; j++)  
    		{  
    			if(fileToken.hasMoreTokens())  
    			{  
    				String token=fileToken.nextToken();  
    				hashData.put(token, token);  
    			}  
    		}  
    		for(int items=0; items<itemsData.size(); items++)  
    		{  
    			boolean match = false;   
    			StringTokenizer st = new StringTokenizer(itemsData.get(items));  
    			int count = 0;  
    			int scount = 0;  
    			while(st.hasMoreTokens())  
    			{  
    				String token = st.nextToken();  
    				if(hashData.get(token)!=null)  
    				{  
    					match = true;  
    				}  
    				else  
    					match = false;               
    				if(!match)   
    					break;  
    			}  
    			if(match)   
    			{               
    				itemCounter[items]++;  
    			}  
    		}  
    		transactions++;  
    	}  
    }  
    public void calculateFrequentItems()  
    {  
    	Vector<String> frequentItems = new Vector<String>();
    	for(int i=0; i<itemsData.size(); i++)  
    	{  
    		if((itemCounter[i]/(double)transactions) >= minSupV)  
    		{  
    			frequentItems.add(itemsData.get(i));  
    			confidence.put(itemsData.get(i), itemCounter[i]);  
    		}  
    	}  
    	supportData.add(confidence);  
    	itemsData.clear();  
    	itemsData = new Vector<String>(frequentItems);  
    	frequentItems.clear();  
    }  
}  

