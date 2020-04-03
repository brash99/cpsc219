import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileExercises {
	
	public int parseNonNegativeInt(String testString) throws NumberFormatException,IOException {
		
		try {
			int testInt = Integer.parseInt(testString);
		
			if (testInt<0) {
				throw new IOException("Converted String is not a positive integer!!!");
			} else {
				return testInt;
			}
			
		} catch (NumberFormatException e) {
			throw new IOException("String cannot be converted to an integer!!!");
		}
		
	}
	
	public boolean is3ByteRGB(String fileName) {
		
		boolean result = true;
	
		try {
			
			File file = new File(fileName);
			FileInputStream fileInputStream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fileInputStream);
		
			int rows, columns;
			rows = in.readInt();
			columns = in.readInt();
			//System.out.println(rows + " " + columns);
		
			for (int r=0; r<rows; r++) {
				for (int c=0; c<columns; c++) {
					int redValue = in.readInt();
					if (redValue < 0 || redValue > 255) {
						result = false;
					}
					int greenValue = in.readInt();
					if (greenValue < 0 || greenValue > 255) {
						result = false;
					}
					int blueValue = in.readInt();
					if (blueValue < 0 || blueValue > 255) {
						result = false;
					}
					//System.out.println("row = " + r + "  column = " + c + " " + redValue + "," + greenValue + "," + blueValue);
				}
			}
			
			in.close();
			
		} catch (FileNotFoundException f) {
			System.out.println("File " + fileName + " not found ... check file name!");
			result = false;
		} catch (IOException e) {
			System.out.println("IO Exception reading " + fileName);
			result = false;
		}
		
		return result;
		
	}
	
	public void append(int[] data, String fileName) {
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName, true));
			if (data != null) {
				for (int i: data) {
					System.out.println(i);
					out.writeInt(i);
				}
			}
			
			out.close();
			
		} catch (FileNotFoundException f) {
			System.out.println("File Not Found!!");
		} catch (IOException e) {
			System.out.println("IO Exception!!");
		}
	}
	
	public void encrypt(int shift, String inFileName, String outFileName) throws FileNotFoundException {
		
		try {
			FileReader fr = new FileReader(inFileName);
			FileWriter fw = new FileWriter(outFileName);
		
			int i;
			int j;
			while ((i = fr.read())!=-1) {

				j=i;
				if (i>=65 && i<=90) {
					j = i + shift;
					if (j>90) {
						j = j-26;
					}
				}
				if (i>=97 && i<=122) {
					j = i + shift;
					if (j>122) {
						j = j-26;
					}
				}
				System.out.println((char)i + ": " + i + "     " + (char)j + ": " + j);
			
				fw.write((char)j);
			}
			
			fr.close();
			fw.close();
			
		} catch (FileNotFoundException f) {
			System.out.println("File not found!!");
			throw new FileNotFoundException("File not found!!");
		} catch (IOException e) {
			System.out.println("IO Exception!!");
		}

	}
	
	
	public void printFile(String fileName) throws FileNotFoundException,IOException {
		File file = new File(fileName);
		FileInputStream fileInputStream = new FileInputStream(file);
		DataInputStream in = new DataInputStream(fileInputStream);
	
		int rows, columns;
		rows = in.readInt();
		columns = in.readInt();
		//System.out.println(rows + " " + columns);
	
		for (int r=0; r<rows; r++) {
			for (int c=0; c<columns; c++) {
				int redValue = in.readInt();
				int greenValue = in.readInt();
				int blueValue = in.readInt();
				//System.out.println("row = " + r + "  column = " + c + " " + redValue + "," + greenValue + "," + blueValue);
			}
		}
		
		for (int i=0;i<5;i++) {
			int restOfData;
			restOfData = in.readInt();
			//System.out.println(restOfData);
		}
		
		in.close();
	}
	
}