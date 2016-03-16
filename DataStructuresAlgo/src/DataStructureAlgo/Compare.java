package DataStructureAlgo;

public class Compare {
	
	public static int comparecount = 0;
	
	public static boolean doCompare(int value1, int value2, int comparecode)
	{
		Compare.comparecount++;
		
		switch(comparecode)
		 {
			case 0:
				return value1 == value2 ? true : false;
			case 1:
				return value1 > value2 ? true : false;
			case 2:
				return value1 >= value2 ? true : false;
			case 3:
				return value1 < value2 ? true : false;
			case 4:
				return value1 <= value2 ? true : false;
			case 5:
				return value1 != value2 ? true : false;
			default:
				return false;
		}
	}
}
