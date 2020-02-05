import java.util.ArrayList;

public class ArrayListExample {
	
	public static void main(String[] args) {

		// Create an ArrayList of type Integer
		// Note that we do not have to specify the size of the list!!  Just like Python!!
		ArrayList<Integer> y = new ArrayList<Integer>();
		
		// Add some values to the ArrayList (this is like using y.append(i) in Python)
		for (int i = 0; i<20; i++) {
			y.add(i);
		}
		
		// Print out the ArrayList ... notice that it looks EXACTLY like a Python list!!!
		System.out.println(y);
		
		// Insert a value at the start of the ArrayList (i.e. at index 0)
		y.add(0,999);
		// Insert a value somewhere in the middle of the ArrayList
		y.add(11,567);
		// Replace one of the values
		y.set(12,9999);
		// Remove one of the values
		y.remove(18);
		
		System.out.println(y);
		
		// Create an ArrayList of type SimpleObject (defined in the file SimpleObject.java)
		ArrayList<SimpleObject> myList = new ArrayList<SimpleObject>();
		
		// Create some SimpleObject objects
		SimpleObject olivia = new SimpleObject(25,100000.01,"Olivia Brash");
		SimpleObject dad = new SimpleObject(54,100.01,"Dad");
		SimpleObject sarah = new SimpleObject(25,10000000.00,"Sarah Brash");
		
		// Print out the SimpleObject objects just created ...
		// Notice that they are not actually printed out ... it just tells us that it is a SimpleObject@######
		// where ###### is the address in memory where this object lives!!!
		//
		// So, the name of the object created (i.e. olivia, dad, sarah) is just a POINTER to the address in memory
		// where the object resides.
		//
		System.out.println(olivia);
		System.out.println(dad);
		System.out.println(sarah);
		
		// Add these objects to the ArrayList created above.
		myList.add(olivia);
		myList.add(dad);
		myList.add(sarah);
		
		// Print out the ArrayList ... again, notice that all we get is that it is a list of
		// objects of type SimpleObject, and the address where these objects live ... notice
		// that the addresses are as above. 
		// So, each element in this array list is actually a pointer to an address where the 
		// actual object lives.
		// In other words, ArrayLists of objects are actually ArrayLists of POINTERS to objects.
		System.out.println(myList);
		
		// Note:  If we want "println" to actually print out the contents of the object in some
		// meaningful way, we would have to add a toString() method to the SimpleObject Class
		// See ArrayListPrintable for an example of how to do this!!!
		
	}
}