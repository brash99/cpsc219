import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateBinaryDataFiles {
	
	private static void createFile(String filename, int[] intArray) throws IOException {
		DataOutputStream out = new DataOutputStream(new FileOutputStream(filename));
		
		for (int i=0; i<intArray.length; i++) {
			//System.out.println(intArray[i]);
			out.writeInt(intArray[i]);
		}
		out.close();
	}

	public static void main(String[] args) throws IOException {
		
		int[] intArray = {1,2,3,4,5,6,7,8,9,10,9,8,7,6,5,4,3,2,1};
		System.out.println("Calling createFile method ...");	
		createFile("testMadison.bin",intArray);
		System.out.println("Done!");
		
	}

}
