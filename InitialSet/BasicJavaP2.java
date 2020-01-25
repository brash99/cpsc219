public class BasicJavaP2{
	public static boolean isUpper(char testchar) {
		return (testchar >= 'A' && testchar <= 'Z');
	}
	
	public static double computePolynomial(double x) {
		return 28.0-2.0*x+x*x;
	}
	
	public static int floorAfterMult(int i,double x) {
		double y = i*x;
		return (int)y;
	}
	
	public static boolean containsAllChars(String stra, String strb) {
		boolean result = true;
		int lena = stra.length();
		int lenb = strb.length();
		if (lenb == 0) {
			return result;
		}
		for (int i=0; i<lenb; i++) {
			int match = 0;
			for (int j=0; j<lena; j++) {
				if (stra.charAt(j) == strb.charAt(i)) {
					match = 1;
				}
			}
			if (match == 0) {
				result = false;
			}
		}
		return result;
	}
}
