import java.io.*;

public class RecursionChallenge {
	
	public int numOfDigits(int number) {
		if (number > -10 && number < 10) {
			return 1;
		}
		return 1+numOfDigits(number/10);
	}
	
	public int countChar(String sString, char cChar) {
		if (sString.length() == 0) {
			return 0;
		} else if (sString.charAt(0) == cChar) {
			return 1 + countChar(sString.substring(1), cChar);
		} else {
			return countChar(sString.substring(1), cChar);
		}
	}
	
	public int addDigits(int adding) {
		if (adding < 10) {
			return adding;
		} else {
			int toAdd = adding % 10;
			return toAdd + addDigits(adding/10);
		}
	}

}
