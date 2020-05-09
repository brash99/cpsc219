import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateBinaryDataFiles {
	
	private static void createFile(String filename, String[] nameArray) throws IOException {
		DataOutputStream out = new DataOutputStream(new FileOutputStream(filename));
		
		for (int i=0; i<nameArray.length; i++) {
			//System.out.println(intArray[i]);
			out.writeUTF(nameArray[i]);
		}
		out.close();
	}

	public static void main(String[] args) throws IOException {
		
		int[] intArray = {1,2,3,4,5,6,7,8,9,10,9,8,7,6,5,4,3,2,1};
		String[] nameArray = {"","","","","","","","","","","","","","","","","","",""};
		for (int i=0; i<intArray.length; i++) {
			String name = "Name" + Integer.toString(intArray[i]) + "\n";
			nameArray[i]=name;
		}
		System.out.println("Calling createFile method ...");	
		createFile("testMadison.bin",nameArray);
		System.out.println("Done!");
		
	}

}
