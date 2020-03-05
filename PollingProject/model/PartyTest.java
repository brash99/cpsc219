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
			Party c = new Party("Liberal");
			assertEquals("Created party with name Liberal", "Liberal", c.getName());
		}
		
		@Test
		public void test_Constructor_2(){
			testClassDefinition();
			float seats = 100.0f;
			float votes = 50.0f;
			Party c = new Party("Liberal",seats,votes);
			assertEquals("Created party with name Liberal", "Liberal", c.getName());
			assertEquals("Created party with 100.0 seats", 100.0, c.getProjectedNumberOfSeats(),0.000001f);
			assertEquals("Created party with 50.0% of vote", 50.0, c.getProjectedPercentageOfVotes(),0.000001f);		
		}
		
		@Test
		public void test_Constructor_bounciness_99_TooLow(){
			testClassDefinition();
			Party c = new Party(.99,0);
			assertEquals("Created bouncer with valid 99% bounciness", .99, c.getBounciness(), 0.000001);
			assertEquals("Created bouncer with invalid 0 height.", 1, c.getHeight());
		}
		
		@Test
		public void test_Constructor_bounciness_45_1(){
			testClassDefinition();
			Party c = new Party(.45,1);
			assertEquals("Created bouncer with valid 45% bounciness", .45, c.getBounciness(), 0.000001);
			assertEquals("Created bouncer with 1 height.", 1, c.getHeight());
		}
		
}
