import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class RecursionChallengeTestForEclipse {
	private String filename = "src/RecursionChallenge.java";

	private boolean usesLibrary(String libraryName) {
		boolean usesLibrary = false;
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line = in.readLine();
			while (line != null && !usesLibrary) {
				if (line.contains(libraryName)) {
					usesLibrary = true;
				} 
				line = in.readLine();
			}
			in.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return usesLibrary;	
	}

	//Check the submission file for the words "for" and "while"
	public void testCheckWords(){
		Scanner scan = null;
		try {
			scan = new Scanner (new FileInputStream(filename));
		} catch (FileNotFoundException e){
			fail(filename + " not found");
		}
		
		while (scan.hasNext()){
			String line = scan.nextLine();
			
			if (line.contains("for") || line.contains("while")){
				fail("Found word \"for\" or \"while\": " + line);
			}
		}
		
		scan.close();
	}
	
	@Test 
	public void test_numOfDigits_one() {
		testCheckWords();
		RecursionChallenge se = new RecursionChallenge();
		assertEquals("Testing 0", 1, se.numOfDigits(0));
		assertEquals("Testing 9", 1, se.numOfDigits(9));
		assertEquals("Testing 5", 1, se.numOfDigits(5));
	}
	
	@Test 
	public void test_numOfDigits_two() {
		testCheckWords();
		RecursionChallenge se = new RecursionChallenge();
		assertEquals("Testing 10", 2, se.numOfDigits(10));
		assertEquals("Testing 99", 2, se.numOfDigits(99));
		assertEquals("Testing 45", 2, se.numOfDigits(45));
	}
	
	@Test 
	public void test_numOfDigits_many() {
		testCheckWords();
		RecursionChallenge se = new RecursionChallenge();
		assertEquals("Testing 1234", 4, se.numOfDigits(1234));
		assertEquals("Testing 5639576", 7, se.numOfDigits(5639576));
	}
	
	@Test
	public void test_addDigits_12345() {
		testCheckWords();
		assertFalse("Don't use the Math library or the word Math in your code.",usesLibrary("Math"));
		assertFalse("Don't use the Integer library or the word Integer in your code.",usesLibrary("Math"));
		assertFalse("Don't use the StringBuilder library or the word StringBuilder in your code.",usesLibrary("StringBuilder"));

		int expected = 15;
		int actual = (new RecursionChallenge()).addDigits(12345);
		
		assertEquals("testing 12345", expected, actual);
	}
	
	@Test
	public void test_addDigits_0() {
		testCheckWords();
		assertFalse("Don't use the Math library or the word Math in your code.",usesLibrary("Math"));
		assertFalse("Don't use the Integer library or the word Integer in your code.",usesLibrary("Math"));
		assertFalse("Don't use the StringBuilder library or the word StringBuilder in your code.",usesLibrary("StringBuilder"));
		int expected = 0;
		int actual = (new RecursionChallenge()).addDigits(0);
		
		assertEquals("testing 0", expected, actual);
	}

	@Test
	public void test_addDigits_98765() {
		testCheckWords();
		assertFalse("Don't use the Math library or the word Math in your code.",usesLibrary("Math"));
		assertFalse("Don't use the Integer library or the word Integer in your code.",usesLibrary("Math"));
		assertFalse("Don't use the StringBuilder library or the word StringBuilder in your code.",usesLibrary("StringBuilder"));
		int expected = 35;
		int actual = (new RecursionChallenge()).addDigits(98765);
		
		assertEquals("testing 98765", expected, actual);
	}
	
	@Test
	public void test_addDigits_10() {
		testCheckWords();
		assertFalse("Don't use the Math library or the word Math in your code.",usesLibrary("Math"));
		assertFalse("Don't use the Integer library or the word Integer in your code.",usesLibrary("Math"));
		assertFalse("Don't use the StringBuilder library or the word StringBuilder in your code.",usesLibrary("StringBuilder"));
		int expected = 1;
		int actual = (new RecursionChallenge()).addDigits(10);
		
		assertEquals("testing 10", expected, actual);
	}

	@Test
	public void test_addDigits_911111() {
		testCheckWords();
		assertFalse("Don't use the Math library or the word Math in your code.",usesLibrary("Math"));
		assertFalse("Don't use the Integer library or the word Integer in your code.",usesLibrary("Math"));
		assertFalse("Don't use the StringBuilder library or the word StringBuilder in your code.",usesLibrary("StringBuilder"));
		int expected = 14;
		int actual = (new RecursionChallenge()).addDigits(911111);
		
		assertEquals("testing 911111", expected, actual);
	}
	
	@Test
	public void test_countChar_emptyString() {
		testCheckWords();
		int expected = 0;
		int actual = (new RecursionChallenge()).countChar("", 'a');
		
		assertEquals("Testing how many times 'a' appears in empty string", expected, actual);
	}
	
	@Test
	public void test_countChar_StringWithSpaces() {
		testCheckWords();
		int expected = 3;
		int actual = (new RecursionChallenge()).countChar("   ", ' ');
		
		assertEquals("Testing how many times spaces appear in '   '", expected, actual);
	}
	
	@Test
	public void test_countChar_StringWithMultipleChars() {
		testCheckWords();
		int expected = 3;
		int actual = (new RecursionChallenge()).countChar("this is a test", 't');
		
		assertEquals("Testing how many times 't' appear in 'this is a test'", expected, actual);
	}
}
