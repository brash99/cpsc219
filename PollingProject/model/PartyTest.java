package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PartyTest extends FormatTester{
	
	public static final String CLASSNAME = "Party";
	
	public PartyTest() {
		super(CLASSNAME, true);
	}
	
	private void testClassDefinition(){
		String[] instanceVars = {"String name", "float seats", "float percent"};
		assertTrue("Instance variables should be private with correct name and type.", instanceVariablesArePrivate(instanceVars));

		assertTrue("Class should not have the default constructor.", noDefaultConstructor());
	}
	
	
	// Testing constructors
	
		@Test
		public void test_Constructor_1(){
			testClassDefinition();
			String name = "Liberal";
			Party c = new Party(name);
			assertEquals("Created party with name Liberal", name, c.getName());
		}
		
		@Test
		public void test_Constructor_2(){
			testClassDefinition();
			String name = "Liberal";
			float seats = 100.0f;
			float percent = 50.0f;
			try {
				Party c = new Party("Liberal",seats,percent);
				assertEquals("Created party with name Liberal", name, c.getName());
				assertEquals("Created party with 100.0 seats", seats, c.getProjectedNumberOfSeats(),0.000001f);
				assertEquals("Created party with 50% of vote", percent, c.getProjectedPercentageOfVotes(),0.000001f);
			} catch (InvalidPartyDataException e) {
				e.printStackTrace();
				assertEquals("Execption Thrown - invalid Party data!!",0.0,0.0,.0000001);				
			}
		}
			
		@Test
		public void test_Constructor_3(){
			testClassDefinition();
			String name = "Liberal";
			float seats = -100.0f;
			float percent = 50.0f;
			try {
				Party c = new Party("Liberal",seats,percent);
			} catch (InvalidPartyDataException e) {
				e.printStackTrace();
				assertEquals("Execption Thrown - invalid Party data!!",0.0,0.0,.000001);
			}
		}
}
