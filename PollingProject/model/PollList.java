package model;

public class PollList {
	private Poll[] polls;
	private int numOfSeats;
	
	public PollList(int numOfPolls, int numOfSeats) {
		polls = new Poll[numOfPolls];
		this.numOfSeats = numOfSeats;
	}
	
	public int getNumOfSeats() {
		return numOfSeats;
	}

	public Poll[] getPolls() {
		return polls;
	}

	public void addPoll(Poll aPoll) {
		if (aPoll == null) {
			System.out.println("Error:  null poll passed to addPoll");
			return;
		} else {
			System.out.println("Adding poll " + aPoll.getPollName() + "  to PollList ");
		}
		//
		// Step 1: Get the index of the next empty space in the poll list
		int goodIndex = getEmptyPollIndex(polls);
		//System.out.println("goodIndex = " + goodIndex);
		//
		// Step 2: Search through the poll list from the end, to check for a matching poll name
		//			If one is found, replace it with the new poll and then return
		// N.B.  We use polls.length-1 because the first poll is at element zero (so -1 for that)
		//       
		//
		for(int i=0;i<goodIndex;i++){
			if(polls[i].getPollName() == aPoll.getPollName()){
				System.out.println("Replacing poll at index = " + i);
				polls[i]=aPoll;
				//System.out.println("");
				return;
			}
		}
		
		// Step 3: If we get to here, no match was found, so we need to add this poll to the list
		//
		// Check to see if the index is (the last element allowed + 1).  If so, the poll list is already full.
		// Otherwise, add the new poll to the list.
		//
		//System.out.println("Adding NEW poll ...");
		if (goodIndex == (polls.length)) {
			System.out.println("Poll list already full.");
		} else {
			polls[goodIndex] = aPoll;
		}
		
		//System.out.println("");
		return;
	}
	
	public void replacePollAtIndex(Poll aPoll, String originalName) {
		
		if (aPoll == null) {
			System.out.println("Error:  null poll passed to addPoll");
			return;
		} else {
			System.out.println("Adding poll " + aPoll.getPollName() + "  to PollList ");
		}
		
		for(int i=0;i<polls.length;i++){
			if(polls[i].getPollName() == originalName){
				System.out.println("Replacing poll at index = " + i);
				polls[i]=aPoll;
				//System.out.println("");
				return;
			}
		}
		
		return;
	}
	
	private static int getEmptyPollIndex(Poll[] p) {
		//
		// This is a helper method for the addPoll method
		//
		// Start from the END of the PollList
		// Count down to the beginning until we find something that is NOT null
		// In this way, we will not get any errors due to going beyond the end of the array
		//
		for(int i=p.length-1;i>=0;i--){
			//System.out.println(i + " " + p[i]);
			if(p[i]!=null){
				return i+1;
			}
		}
		return 0;
	}
	
	public Poll getAggregatePoll(String[] partyNames) {
		
		// create a test Poll that we will fill with appropriate information
		Poll testPoll = new Poll("aggregate",partyNames.length);
		
		// create the aggregate poll
		testPoll = createAggregatePoll(testPoll,partyNames);
		//System.out.println("Original Aggregate Poll");
		//System.out.println(testPoll);
		
		// Before we return the aggregate poll object, we need to go through
		// and calculate the total of all of the percentages, and seats, and
		// possibly renormalize them, if the percentages add up to more than
		// 100%, or if the number of seats is greater than the number available.	

		testPoll = renormalize(partyNames, testPoll);		
		return testPoll;
	}
	
	
	private Poll createAggregatePoll(Poll testPoll, String[] partyNames) {
		// First define a "testparty" object that we will use to temporarily store
		// information
		Party testparty = new Party("Test");
		//
		// start looping through the array of partyNames that was passed to this method
		//
		for (int i=0; i<partyNames.length; i++) {

			// Loop through all of the polls in the list ... the endpoint is polls.length - 1 because
			// we don't want to search that last space in this list that is reserved for the aggregate poll itself
		
			double seatsum = 0.0;
			double percentsum = 0.0;
			int counter = 0;
		
			for (int j=0; j<polls.length; j++) {
				//System.out.println(polls[j].getParty(partyNames[i]));
				// store the current poll information associated with the current search party in a temporary object 
				if (polls[j] != null) {
					testparty = polls[j].getParty(partyNames[i]);

					// if this party is actually in this poll:
					if (testparty != null) {
						counter++;
						seatsum = seatsum + testparty.getProjectedNumberOfSeats();
						percentsum = percentsum + testparty.getProjectedPercentageOfVotes();
					}
				}
			}
			// average the number of seats and percentages over the polls 
			// that this party was actually in.
			seatsum = seatsum / counter;
			percentsum = percentsum / counter;
		
			// create a party object for this party and add it to the
			// aggregate poll
			try {
				Party testparty2 = new Party(partyNames[i],(float)(seatsum),(float)(percentsum));
				try {
					testPoll.addParty(testparty2);
				} catch (PollFullException f) {
					f.printStackTrace();
				}
			} catch (InvalidPartyDataException e) {
				e.printStackTrace();
			}

			
		}

		return testPoll;
	}
	
	private Poll renormalize(String[] partyNames, Poll testPoll) {
		// initialize counters
		double ssum = 0;
		double psum = 0; 
		// go through each entry in the poll, and add up the total number of seats,
		// and the totals of the percentages.
		for (int i=0; i<testPoll.getNumberOfParties(); i++) {
			ssum =  ssum + testPoll.getParty(partyNames[i]).getProjectedNumberOfSeats();
			psum = psum + testPoll.getParty(partyNames[i]).getProjectedPercentageOfVotes();
		}
		
		// if the percentage total is greater than 100, go through each entry in the poll
		// again, and apply normalization factor of 100/psum to each percentage.
		try {
			if (psum>100.0) {
				for (int i=0; i<testPoll.getNumberOfParties(); i++) {
					testPoll.getParty(partyNames[i]).setProjectedPercentageOfVotes(
							(float)(testPoll.getParty(partyNames[i]).
									getProjectedPercentageOfVotes()*100.0f/psum));
				}

			}
		} catch (InvalidPartyDataException e) {
			e.printStackTrace();
		}
		
		// if the seat total is greater than allowed number of seats, go through each entry in the poll
		// again, and apply normalization factor of (numSeats)/ssum to each seat number.
		
		try {
			if (ssum > this.getNumOfSeats()) {
				for (int i=0; i<testPoll.getNumberOfParties(); i++) {
					testPoll.getParty(partyNames[i]).setProjectedNumberOfSeats(
							(float)(testPoll.getParty(partyNames[i]).
									getProjectedNumberOfSeats()*this.getNumOfSeats()/ssum));
				}
			}
		} catch (InvalidPartyDataException e) {
			e.printStackTrace();
		}
		
		return testPoll;
	}
	

	public String toString() {
		//
		// Add a toString method for the purposes of debugging
		// This should loop through all of the polls in the poll list (we don't know how many there are)
		// and for each one it will print that poll - printing that poll means that it will in turn call
		// the toString() method in the Poll class definition.
		//
		String returnString = "";
		for (int i=0; i<polls.length; i++) {
			System.out.println(i);
			if (polls[i] != null) {
				returnString += polls[i].toString();
			}
		}
		
		return returnString;
	}
	
	public static void main(String[] args) {
		
		// We want to first define a variable (for testing purposes) that is the number of polls that
		// we might want to store in the PollList
		int numberOfPolls = 4;
		
		//
		// Next we create a PollList that allows for the number of Polls that we will store
		//
		PollList testList = new PollList(numberOfPolls,278);
		

		// Add first poll
		try {
			Party cpc0 = new Party("CPC",81.92139f,33f);
			Party liberal0 = new Party("Liberal",154.11794f,56f);
			Party ndp0 = new Party("NDP",1.0f,4f);
			Party green0 = new Party("Green",40.0f,5f);
			Party rhino0 = new Party("Rhinoceros",1.0f,0f);
			Poll Poll0 = new Poll("Poll0",5);
			Poll0.addParty(cpc0);
			Poll0.addParty(liberal0);
			Poll0.addParty(ndp0);
			Poll0.addParty(green0);
			Poll0.addParty(rhino0);
			System.out.println(Poll0);

		
			// Add second poll
			Party cpc1 = new Party("CPC",33.0f,3f);
			Party liberal1 = new Party("Liberal",85.86417f,40f);
			Party ndp1 = new Party("NDP",35.877453f,10f);
			Party green1 = new Party("Green",80.175026f,35f);
			Party ppc1 = new Party("PPC",42.0f,9f);

			Poll Poll1 = new Poll("Poll1",5);
			Poll1.addParty(cpc1);
			Poll1.addParty(liberal1);
			Poll1.addParty(ndp1);
			Poll1.addParty(green1);
			Poll1.addParty(ppc1);
				
			System.out.println(Poll1);

			// Add third poll
			Party cpc2 = new Party("CPC",236.83212f,88f);
			Party liberal2 = new Party("Liberal",14.0f,3f);
			Party ndp2 = new Party("NDP",2.0f,0f);
			Party green2 = new Party("Green",25.0f,8f);

			Poll Poll2 = new Poll("Poll2",4);
			Poll2.addParty(cpc2);
			Poll2.addParty(liberal2);
			Poll2.addParty(ndp2);
			Poll2.addParty(green2);
				
			System.out.println(Poll2);


			System.out.println("Adding first poll ... ");
			testList.addPoll(Poll0);
			System.out.println("Adding second poll ... ");
			testList.addPoll(Poll1);
			System.out.println("Adding third poll ... ");
			testList.addPoll(Poll2);
			System.out.println("Done adding polls ");
		
			System.out.println("\n Final Poll List + Aggregate: \n");
		
			System.out.println(testList);
		
			String[] aggPollNames = {"CPC","Liberal","Green","NDP","PPC","Rhinoceros"};
			//String[] aggPollNames = {"CPC","Liberal","Green","NDP"};		
			Poll aggregatePoll = new Poll("aggregate",aggPollNames.length);
			aggregatePoll = testList.getAggregatePoll(aggPollNames);
		
			System.out.println("Final Aggregate Poll:");
			System.out.println(aggregatePoll);
		} catch (InvalidPartyDataException e) {
			e.printStackTrace();
		} catch (PollFullException f) {
			f.printStackTrace();
		}
	}
	
}
