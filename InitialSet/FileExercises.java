import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileExercises {
	
	public static boolean is3ByteRGB(String fileName) throws FileNotFoundException, IOException {
		
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

	public static void main(String[] args) throws FileNotFoundException,IOException {
		
		String fileName1 = "testFile1.bin";
		System.out.println("File 1 result: " + is3ByteRGB(fileName1));
		
		String fileName2 = "testFile2.bin";
		System.out.println("File 2 result: " + is3ByteRGB(fileName2));
		
		String fileName3 = "testFile3.bin";
		System.out.println("File 3 result: " + is3ByteRGB(fileName3));
	}
}