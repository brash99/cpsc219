import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class IntegerMultiply {
	
	public static int randomIntMultiply(RandomAccessFile raf, int m, int n) throws IOException {

		raf.seek(4*(m-1));
		int value1 = raf.readInt();
		
		//System.out.println(m + " " + n + " " + value1);
		
		raf.seek(4*(n-1));
		int value2 = raf.readInt();
		
		//System.out.println(m + " " + n + " " + value2);
		
		return value1*value2;
		
	}
	
	public static ArrayList<Integer> findCommon(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		
		list1.retainAll(list2);
		
		ArrayList<Integer> newList = new ArrayList<Integer>();
		
		for (int i: list1) {
			if (!newList.contains(i)) {
				newList.add(i);
			}
		}
		
		return newList;
	}
	

	public static void main(String[] args) throws FileNotFoundException,IOException {
		
		RandomAccessFile inFile = new RandomAccessFile("testMadison.bin","r");

		int m = 10;
		int n = 13;
		int product = randomIntMultiply(inFile,m,n);
		System.out.println("Product = " + product);
		
		
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		ArrayList<Integer> common = new ArrayList<Integer>();
		
		Random r = new Random();
		int max = 1000;
		int min = 500;
		
		for (int i = 0; i<100; i++) {
			//int value1 = i;
			int value1 = r.nextInt((max-min)+1)+min;
			list1.add(value1);
			//int value2 = 2*i;
			int value2 = r.nextInt((max-min)+1)+min;
			list2.add(value2);
		}
		
		common = findCommon(list1,list2);
		Collections.sort(common);
		
		System.out.println("Common = ");
		for (int i: common) {
			System.out.println(i);
		}
		
	}
	
}