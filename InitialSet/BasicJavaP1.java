public class BasicJavaP1{
	public static long floor(double num) {
		int a = (int)num;
		return a;
	}
	public static double conversion(double fahr) {
		double c = (fahr-32.0)*5.0/9.0;
		return c;
	}
	public static boolean willRoundUp(double num) {
		int num2 = (int) (num*10.0);
		int num3 = num2%10;
		boolean result = num3 >= 5;
		System.out.println(num + " " + num2 + " " + num3 + " " + result);
		return result;
	}
	public static int sumRange(int start, int end) {
		int sum = 0;
		if (start<end) {
			for (int i=start;i<end;i++) {
				sum = sum + i;
			}
		} else {
			for (int i=start;i>end;i--) {
				sum = sum + i;
			}
		}
		return sum;
	}
	public static int countChar(String str, char c) {
		int len = str.length();
		int count = 0;
		for (int i=0;i<len;i++) {
			if (str.charAt(i) == c) {
				count = count + 1;
			}
		}
		return count;
	}
	public static int addDigits(int sum) {
		int ss = 0;
		while (sum >0) {
			int rem = sum%10;
			ss = ss + rem;
			sum = sum/10;
		}
		return ss;
	}
}
