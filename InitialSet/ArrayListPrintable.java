import java.util.ArrayList;

public class ArrayListPrintable {
	
	public static void main(String[] args) {

		ArrayList<Integer> y = new ArrayList<Integer>();
		
		for (int i = 0; i<20; i++) {
			y.add(i);
		}
		
		System.out.println(y);
		
		y.add(0,999);
		y.add(11,567);
		y.set(12,9999);
		y.remove(18);
		
		System.out.println(y);
		
		ArrayList<PrintableObject> myList = new ArrayList<PrintableObject>();
		
		// Create some SimpleObject objects
		PrintableObject olivia = new PrintableObject(25,100000.01,"Olivia Brash");
		PrintableObject dad = new PrintableObject(54,100.01,"Dad");
		PrintableObject sarah = new PrintableObject(25,10000000.00,"Sarah Brash");
		
		System.out.println(olivia);
		System.out.println(dad);
		System.out.println(sarah);
		
		myList.add(olivia);
		myList.add(dad);
		myList.add(sarah);
		
		System.out.println("The ArrayList follows: ");
		
		for (PrintableObject tmp : myList) {
			System.out.println(tmp);
		}
	}
}