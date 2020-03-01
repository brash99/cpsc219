package model;

/**
 * @author Olivia
 * @version Iteration 1
 * The class PollList represents an array of objects called polls in Poll[] that
 * consist of parties in Party[]. PollList has a set of polls in Poll[] and the
 * number seats. 
 * 
 * PollList contains getters for the number of seats and polls in Poll[]
 * This class has public methods that return the number of polls and the number
 * of seats in the PollList, returns a poll and adds it to the PollList, and
 * returns the party names in the polls and get the aggregate poll.
 * 
 */

public class PollList {
	private Poll[] polls;
	private int numOfSeats;
	
    /**
     * The constructor takes the name of the poll and the maximum number of
     * parties this poll will take as arguments
     * 
     * @param Poll[] polls	 An array of polls that should be in the list
     * @param numOfSeats 	 The total number of seats
     */
	
	public PollList(int numOfPolls, int numOfSeats) {
		polls = new Poll[numOfPolls];
		this.numOfSeats = numOfSeats;
	}
	
	// Get total number of seats
	public int getNumOfSeats() {
		return numOfSeats;
	}

	// Get polls that will be in the poll list
	public Poll[] getPolls() {
		return polls;
	} 	

	/**
	 * Add polls to the poll list, if the argument in null: print error
	 * message, call getEmptyPollIndex, return sent name equals if it
	 * equals the name in polls, add polls to list if it has goodIndex
	 * @param aPoll
	 */
	public void addPoll(Poll aPoll) {
		if (aPoll == null) {
			System.out.println("Error: Argument is null");
			return;
		}
		
		// Step 1: Get the index of the next empty space in the poll list
		int goodIndex = getEmptyPollIndex(polls);
		
		// System.out.println("goodIndex = " + goodIndex);
		
		// Step 2: Search through the poll list from the end, to check for a matching poll name
		//  If one is found, replace it with the new poll and then return
		String sentPollName = aPoll.getPollName();
		for(int i=0;i<goodIndex;i++) {
			if(sentPollName == polls[i].getPollName()) {
				polls[i] = aPoll;
				return;
			}
		}
		
		/** Step 3: If we get to here, no match was found, so we need to add this poll to the list
		 * 
		 * Check to see if the index is (the last element allowed + 1).  If so, the poll list is already full.
		 * Otherwise, add the new poll to the list. */
		
		if (goodIndex != (polls.length)) {
			polls[goodIndex] = aPoll;
		} else {
			System.out.println("Error: Poll list is full!");
		}
		return;
	}
	
	/**
	 * Aggregate returns a poll representing the aggregate polls in the
	 * list, polls with existing names in list are replaced by poll in
	 * argument, poll is added to end of list if party name does not
	 * exist in list yet, prints error message when no more polls can
	 * be added.
	 * @param partyNames		The names of the parties in polls
	 * @return bob				The aggregate poll
	 */
	
	public Poll getAggregatePoll(String[] partyNames) {
		
		// Create the aggregate poll
		Poll bob = new Poll("Aggregate",partyNames.length);
		for(int k=0;k<partyNames.length;k++) {
			
			// Loop through all of the polls in the list ... the endpoint is polls.length - 1 because
			// we don't want to search that last space in this list that is reserved for the aggregate poll itself
			int counter = 0;
			float averageSeats = 0.0f;
			float averagePercent = 0.0f;
			
			for(int i=0;i<polls.length;i++) {
				
				// Store the current poll information associated with the current search party in a temporary object
				if (this.polls[i].getParty(partyNames[k]) != null) {
					if(this.polls[i].getParty(partyNames[k]).getName().contentEquals(partyNames[k])) {
						counter++;
						averageSeats = averageSeats + this.polls[i].getParty(partyNames[k]).getProjectedNumberOfSeats();
						averagePercent = averagePercent + this.polls[i].getParty(partyNames[k]).getProjectedPercentageOfVotes();
					}
				}
			}
			
			/** If this party is actually in this poll:
			 * 
			 * Average the number of seats and percentages over the polls 
			 * that this party was actually in.
			 * 
			 * Create a party object for this party and add it to the
			   aggregate poll */
			
			if (counter != 0) {
				float realSeats = (float) (averageSeats/counter);
				float realPercent = (float) (averagePercent/counter);
				
				Party dummyParty = new Party(partyNames[k],realSeats,realPercent);
				bob.addParty(dummyParty);
			} else {
				float realSeats = (float) averageSeats;
				float realPercent = (float) averagePercent;
				
				Party dummyParty = new Party(partyNames[k],realSeats,realPercent);
				bob.addParty(dummyParty);
			}
		}
		// Check totals
		float seatSum = 0.0f;
		float percentSum = 0.0f;
		for(int i=0;i<bob.getNumberOfParties();i++) {
			// Go through each entry in the poll, and add up the total number of seats,
			// and the totals of the percentages.
			if (bob.getParty(partyNames[i]) != null) {
				seatSum = seatSum + bob.getParty(partyNames[i]).getProjectedNumberOfSeats();
				percentSum = percentSum + bob.getParty(partyNames[i]).getProjectedPercentageOfVotes();
			}
		}
		
		// If the seat total is greater than allowed number of seats, go through each entry in the poll
		// again, and apply normalization factor to each seat number.
		
		if (seatSum > numOfSeats) {
			if (seatSum != 0) {
				float factor = numOfSeats/seatSum;
				for(int j=0;j<bob.getNumberOfParties();j++) {
					bob.getParty(partyNames[j]).setProjectedNumberOfSeats(bob.getParty(partyNames[j]).getProjectedNumberOfSeats()*factor);
				}
			}
		}
		
		// If the percentage total is greater than 100, go through each entry in the poll
		// again, and apply normalization factor to each percentage.
		
		if (percentSum > 100.0f) {
			if (percentSum != 0) {
				float factor = 100.0f/percentSum;
				for(int k=0;k<bob.getNumberOfParties();k++) {
					bob.getParty(partyNames[k]).setProjectedPercentageOfVotes(bob.getParty(partyNames[k]).getProjectedPercentageOfVotes()*factor);
				}
			}
		} return bob;
	}
	
	private static int getEmptyPollIndex(Poll[] polls) {
		
		/** This is a helper method for the addPoll method
		 * 
		 * Start from the END of the PollList
		 * Count down to the beginning until we find something that is NOT null
		 * In this way, we will not get any errors due to going beyond the end of the array
		 * @author oliviabrash
		 * @param polls */
		
		for(int i=polls.length-1;i>=0;i--) {
			if (polls[i]!=null) {
				return i+1;
			}
		}
		return 0;
	}
	
	/** Add a toString method for the purposes of debugging
	 * This should loop through all of the polls in the poll list (we don't know how many there are)
	 * and for each one it will print that poll - printing that poll means that it will in turn call
	 * the toString() method in the Poll class definition.
	 * @author oliviabrash */
	
	public String toString() {
		int index=0;
		while (true) {
			try {
				if (polls[index]==null) {
					return "";
				} else {
					System.out.println(polls[index]);
					index++;
				}
			} catch (Exception e) {
				return "";
			}
		}
	}
	
	public static void main(String[] args) {
		// For testing purposes
		
		// Create parties for testing
		Party conservative = new Party("Conservative",200,0.600f);
		Party liberal = new Party("Liberal",100,0.300f);
		Party ndp = new Party("NDP",150,0.400f);
		
		// Create polls for testing
		Poll testPoll1 = new Poll("Test Poll 1",3);
		Poll testPoll2 = new Poll("Test Poll 2",2);
		Poll testPoll3 = new Poll("Test Poll 3",2);
		
		// Add parties
		testPoll1.addParty(conservative);
		testPoll1.addParty(liberal);
		testPoll1.addParty(ndp);
		
		testPoll2.addParty(conservative);
		testPoll2.addParty(liberal);
		
		testPoll3.addParty(liberal);
		testPoll3.addParty(ndp);
		
		// Creates a PollList that allows for the number of Polls that we will store
		PollList testList = new PollList(3,100);
		
		// Add polls
		testList.addPoll(testPoll1);
		testList.addPoll(testPoll2);
		testList.addPoll(testPoll3);
		
		// For printing testList:
		//System.out.println("testList 4: " + testList + "\n");
		
		// Create aggregate poll
		String[] myString = {"Conservative","Liberal","NDP"};
		Poll newAggregate = new Poll("Aggregate",myString.length);
		newAggregate = testList.getAggregatePoll(myString);
		//System.out.println(newAggregate);
	}
	
}
