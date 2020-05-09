import java.io.*;

public class Worksheet {
	
	private static int gcdCounter = 0;
	
	private static int numPeople(RandomAccessFile inFile) {
		
		String thisLine;
		int counter = 0;
		
		try {
			
			while ((thisLine = inFile.readLine()) != null) {
				//System.out.println(thisLine);
				counter++;
			}
		
			if (counter>0) {
				return counter;
			} else {
				return -1;
			}
			
		} catch (Exception e) {
			System.out.println("Error Reading File!!!");
			return -1;
		}
		
	}
	
	private static int find(String in, int[][] pos, String search) {
		int xpos,xlength;
		int result = -1;
		
		// pos.length = number of rows
		// pos[0].length = number of columns
		
		for (int i=0; i<pos.length; i++) {
				xpos = pos[i][0];
				xlength = pos[i][1];
				String sub = in.substring(xpos,xpos+xlength);
				if (search.contentEquals(sub)) {
					result = xpos+1;
				}
		}
		
		return result;
	}
	
	private static int calcGCD(int d1, int d2) {
		
		gcdCounter++;
		
		int d3, d4;
		
		// check size of numbers; d3 will be the larger, d4 will be the smaller
		if (d2>d1) {
			d3=d2;
			d4=d1;
		} else {
			d3=d1;
			d4=d2;
		}

		if (d3 % d4 == 0) {
			return d4;
		} else {
			return calcGCD(d3,d3%d4);
		}
			
	}
	
	public static void main(String[] args) {
		
		try {
			RandomAccessFile inFile = new RandomAccessFile("testMadison.bin","r");
			
			System.out.println("Number of people = " + Integer.toString(numPeople(inFile)));
			
			inFile.close();
		} catch (Exception e) {
			System.out.println("File not found!");
		}
		
		String together = "aaboutandbyforwith";
		int[][] positions = {{0,1},{1,5},{6,3},{9,2},{11,3},{14,4}};
		String searchword = "and";
		
		int result = find(together,positions,searchword);
		System.out.println("The result is " + Integer.toString(result));
		
		together = "Hereisanothertestphrase";
		int[][] positions2 = {{0,4},{4,2},{6,7},{13,4},{17,6}};
		searchword = "test";
		
		result = find(together,positions2,searchword);
		System.out.println("The result is " + Integer.toString(result));
		
		gcdCounter = 0;
		System.out.println(calcGCD(9,3) + " " + gcdCounter);
		gcdCounter = 0;
		System.out.println(calcGCD(12,9) + " " + gcdCounter);	
		gcdCounter = 0;
		System.out.println(calcGCD(17,15) + " " + gcdCounter);
		gcdCounter = 0;
		System.out.println(calcGCD(28,40) + " " + gcdCounter);
		
		
	}
	
}

