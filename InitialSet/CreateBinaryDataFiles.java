import static org.junit.Assert.fail;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateBinaryDataFiles {
	private static void createFile(String filename, int[] header, int[][] nums) throws IOException {
		DataOutputStream out = new DataOutputStream(new FileOutputStream(filename));
		for (int headerNum : header) {
			out.writeInt(headerNum);
		}
		for (int rowIndex = 0; rowIndex < nums.length; rowIndex++) {
			int[] row = nums[rowIndex];
			for (int colIndex = 0; colIndex < row.length; colIndex++) {
				out.writeInt(row[colIndex]);
			}
		}
		out.close();		
	}

	public static void main(String[] args) throws IOException {
		int[] header = {4,5};
		int[][] content = {	{255,255,255,0,0,0,255,0,0,0,0,256,1,2,3},				  					
					{100,101,102,103,104,105,106,107,108,109,110,111,112,113,114},
					{115,116,117,118,119,120,121,122,123,124,125,126,127,128,129},
					{130,131,132,133,134,135,136,137,138,139,140,141,142,143,144}};
		createFile("testFile1.bin", header, content);

		int[] header2 = {2,3};
		int[][] content2 = { {255,255,255,0,0,0,255,0,0},
					{255,0,0,0,0,255,0,255,0}};
		createFile("testFile2.bin", header2, content2);
	}

}
