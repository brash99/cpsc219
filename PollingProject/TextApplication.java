//package application;

import java.util.Scanner;
import java.util.Arrays;

public class TextApplication {
	
	private PollList polls;
	private final int MAX_NUMBER_OF_STARS = 25;
	
	public TextApplication() {
		//System.out.println("Starting program ... max stars = "+MAX_NUMBER_OF_STARS);
	}
	
	public TextApplication(PollList newpolls) {
		this();
		polls = newpolls;
	}
	
	public int getMaxNumberOfStars() {
		return MAX_NUMBER_OF_STARS;
	}
	
	public void displayPollDataBySeat(Poll aPoll) {
		 
		int majorityStars = (int)(Math.round((double)((double)(MAX_NUMBER_OF_STARS)/2.0)));
		String output = aPoll.getPollName() + ":\n";
		Party[] sortedPartyList = aPoll.getPartiesSortedBySeats();
		double starPercent = (double)(getTotalSeatsInPoll(sortedPartyList))/MAX_NUMBER_OF_STARS;
		for (int i=0; i<sortedPartyList.length; i++) {
			output = output + sortedPartyList[i].textVisualizationBySeats(MAX_NUMBER_OF_STARS, majorityStars, starPercent) + "\n";
		}
		System.out.println(output);
	}
	
	public void displayPollDataByVotes(Poll aPoll) {
		 
		int majorityStars = (int)(Math.round((double)((double)(MAX_NUMBER_OF_STARS)/2.0)));
		String output = aPoll.getPollName() + ":\n";
		Party[] sortedPartyList = aPoll.getPartiesSortedBySeats();
		double starPercent = (double)(getTotalPercentInPoll(sortedPartyList))/MAX_NUMBER_OF_STARS;
		for (int i=0; i<sortedPartyList.length; i++) {
			output = output + sortedPartyList[i].textVisualizationByVotes(MAX_NUMBER_OF_STARS, majorityStars, starPercent) + "\n";
		}
		System.out.println(output);
	}
	
	private int getTotalSeatsInPoll (Party[] partyList) {
		float mySum = 0;
		for (int i=0; i<partyList.length; i++) {
			mySum += partyList[i].getProjectedNumberOfSeats();
		}
		return (int)mySum;
	}
	
	private int getTotalPercentInPoll (Party[] partyList) {
		float mySum = 0;
		for (int i=0; i<partyList.length; i++) {
			mySum += partyList[i].getProjectedPercentageOfVotes();
		}
		return (int)mySum;
	}
	
	public void displayPollsBySeat(String[] partyNames) {
		
		int index=0;
		Poll[] myPolls = polls.getPolls();
		while (true) {
			try {
				if (myPolls[index]==null) {
					break;
				} else {
					this.displayPollDataBySeat(myPolls[index]);;
					index++;
				}
			} catch (Exception e) {
				break;
			}
		}
		
		this.displayAggregatePollbySeat(partyNames);

	}
	
	private void displayAggregatePollbySeat(String[] partyNames) {
		Poll aggregatePoll = new Poll("aggregate",partyNames.length);
		aggregatePoll = polls.getAggregatePoll(partyNames);
		this.displayPollDataBySeat(aggregatePoll);
	}
	
	public void displayPollsByVotes(String[] partyNames) {
		
		int index=0;
		Poll[] myPolls = polls.getPolls();
		while (true) {
			try {
				if (myPolls[index]==null) {
					break;
				} else {
					this.displayPollDataByVotes(myPolls[index]);;
					index++;
				}
			} catch (Exception e) {
				break;
			}
		}
				
		this.displayAggregatePollbyVotes(partyNames);
		
	}
	
	private void displayAggregatePollbyVotes(String[] partyNames) {
		Poll aggregatePoll = new Poll("aggregate",partyNames.length);
		aggregatePoll = polls.getAggregatePoll(partyNames);
		this.displayPollDataByVotes(aggregatePoll);
	}
	
	public static void run() {
		
		//// Testing code
		//int numOfSeats = 100;
		//PollList polls = new PollList(4, numOfSeats);
		//Factory factory = new Factory(numOfSeats);
		//String[] partyNames = {"BQ","CPC","Green","Liberal","NDP","PPC","Rhinoceros"};
		//factory.setPartyNames(partyNames);
		//polls.addPoll(factory.createRandomPoll("Poll1"));
		//polls.addPoll(factory.createRandomPoll("Poll2"));
		//polls.addPoll(factory.createRandomPoll("Poll3"));
		//polls.addPoll(factory.createRandomPoll("Poll4"));
		//TextApplication app = new TextApplication(polls);
		//app.displayPollsBySeat(factory.getPartyNames());
		
		System.out.println("Welcome to the poll tracker");
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		
	    System.out.println("How many seats are available in the election?");
	    String mySeats = myObj.nextLine();  // Read user input
	    int numOfSeats = Integer.parseInt(mySeats);
	    Factory factory = new Factory(numOfSeats);
	    
	    System.out.println("Which parties are in the election (provide names, comma separated):");
	    String myParties = myObj.nextLine();  // Read user input
	    String[] partyNames = myParties.split(",");
	    //System.out.println(Arrays.toString(partyNames));
	    factory.setPartyNames(partyNames);
	    
	    System.out.println("How many polls do you want to track with this application?");
	    String myNumber = myObj.nextLine();
	    int numberOfPolls = Integer.parseInt(myNumber);
	    PollList polls = new PollList(numberOfPolls, numOfSeats);
	    
	    System.out.println("Would you like me to create a random set of polls?");
	    String myChoice = myObj.nextLine();
	    System.out.println(myChoice);
	    if (myChoice.contentEquals("yes")) {
	    	for (int i=0; i<numberOfPolls;i++) {
	    		//System.out.println("creating random set of polls ... ");
	    		String myPollName = "Poll"+i;
	    		polls.addPoll(factory.createRandomPoll(myPollName));
	    	}
	    }
	    TextApplication app = new TextApplication(polls);
	    		
	    while (true) {
	    	System.out.println("Options: all (show result of all polls), aggregate (show aggregate result), quit (end application)");
	    	System.out.println("Choose an option:");
	    	String myChoice2 = myObj.nextLine();
	    	if (myChoice2.contentEquals("all")) {
	    		System.out.println("Printing polls ...");
	    		app.displayPollsBySeat(factory.getPartyNames());
	    		System.out.println("Back from printing ... ");
	    	} else if (myChoice2.contentEquals("aggregate")) {
	    		app.displayAggregatePollbySeat(partyNames);
	    	} else if (myChoice2.contentEquals("quit")) {
	    		break;
	    	}
	    }
	    
	    System.out.println("Goodbye ... :)");
	    
		return;
	}
	
	public static void main(String[] args) {
		
		run();
		
	}

}
