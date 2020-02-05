public class BasicJavaP2Dad{
	public static boolean isUpper(char testchar) {
		// algorithm is simple:  See if the test character is in the upper case range
		return (testchar >= 'A' && testchar <= 'Z');
	}
	
	public static double computePolynomial(double x) {
		// calculate and return the value of the polynomial
		return 28.0-2.0*x+x*x;
	}
	
	public static int floorAfterMult(int i,double x) {
		// do the multiplication first, and only then cast the result into an integer		
		double y = i*x;
		return (int)y;
	}
	
	public static boolean containsAllChars(String stra, String strb) {
		// create a boolean result variable.  We will assume that it is TRUE,
		// and then look for a contradiction (no match of a character in string b).
		boolean result = true;
		
		// We need the length of each string
		int lena = stra.length();
		int lenb = strb.length();

		// if the length of string b is zero, the following nested loop will not get executed,
		// and the function will return TRUE, as it should!
		//
		// Otherwise, we choose each character in string b, one at a time,
		// and then loop through each character of string a to see if there
		// is a match.  If there is, great.  If not (match = 0), then we set
		// the return value to false.
		//
		for (int i=0; i<lenb; i++) {
			int match = 0;
			
			for (int j=0; j<lena; j++) {
				if (stra.charAt(j) == strb.charAt(i)) {match = 1;}
			}		
			
			if (match == 0) {result = false;}
		}
		
		return result;
	}
}
