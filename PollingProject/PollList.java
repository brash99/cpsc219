// package application;

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
		//
		// Step 1: Get the index of the next empty space in the poll list
		int goodIndex = getEmptyPollIndex(polls);
		System.out.println("goodIndex = " + goodIndex);
		//
		// Step 2: Search through the poll list from the end, to check for a matching poll name
		//			If one is found, replace it with the new poll and then return
		for(int i=0;i<Math.min(goodIndex,polls.length-1);i++){
			System.out.println(polls[i].getPollName() + " " + aPoll.getPollName());
			if(polls[i].getPollName() == aPoll.getPollName()){
				System.out.println("Found a match at i = " + i);
				polls[i]=aPoll;
				System.out.println("");
				return;
			}
		}
		
		// Step 3: If we get to here, no match was found, so we need to add this poll to the list
		//
		// Check to see if the index is (the last element allowed + 1).  If so, the poll list is already full.
		// Otherwise, add the new poll to the list.
		//
		System.out.println("Adding NEW poll ...");
		if (goodIndex == (polls.length)) {
			System.out.println("Poll list already full.");
		} else {
			polls[goodIndex] = aPoll;
		}
		
		System.out.println("");
		return;
	}
	
	public void getAggregatePoll(String[] partyNames) {
		
		for (int i=0; i<partyNames.length; i++) {
			System.out.println("Search for " + partyNames[i]);
			for (int j=0; j<polls.length; j++) {
				System.out.println(polls[j].getParty(partyNames[i]));
			}
			
		}
		
		return;
	}
	

	private static int getEmptyPollIndex(Poll[] p) {
		//
		// Start from the END of the PollList
		// Count down to the beginning until we find something that is NOT null
		// In this way, we will not get any errors due to going beyond the end of the array
		//
		for(int i=p.length-1;i>=0;i--){
			System.out.println(i + " " + p[i]);
			if(p[i]!=null){
				return i+1;
			}
		}
		return 0;
	}
	
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
		Party conservative1 = new Party("Conservative",100,50f);
		Party liberal1 = new Party("Liberal",50,25f);
		Party ndp1 = new Party("NDP",50,25f);
		Poll testPoll1 = new Poll("Test Poll 1",3);
		testPoll1.addParty(conservative1);
		testPoll1.addParty(liberal1);
		testPoll1.addParty(ndp1);
		System.out.println(testPoll1);
		System.out.println(conservative1);
		System.out.println(liberal1);
		System.out.println(ndp1);
		
		
		Party conservative2 = new Party("Conservative",80,40f);
		Party liberal2 = new Party("Liberal",60,30f);
		Party ndp2 = new Party("NDP",60,30f);
		Poll testPoll2 = new Poll("Test Poll 2",3);
		testPoll2.addParty(conservative2);
		testPoll2.addParty(liberal2);
		testPoll2.addParty(ndp2);
		System.out.println(testPoll2);
		System.out.println(conservative2);
		System.out.println(liberal2);
		System.out.println(ndp2);
		
		Party conservative3 = new Party("Conservative",60,30f);
		Party liberal3 = new Party("Liberal",100,50f);
		Party ndp3 = new Party("NDP",40,20f);
		Poll testPoll3 = new Poll("Test Poll 3",3);
		testPoll3.addParty(conservative3);
		testPoll3.addParty(liberal3);
		testPoll3.addParty(ndp3);
		System.out.println(testPoll3);
		System.out.println(conservative3);
		System.out.println(liberal3);
		System.out.println(ndp3);
		
		Party conservative4 = new Party("Conservative",200,100.0f);
		Party liberal4 = new Party("Liberal",0,0f);
		Party ndp4 = new Party("NDP",0,0f);
		Poll testPoll4 = new Poll("Test Poll 4",3);
		testPoll4.addParty(conservative4);
		testPoll4.addParty(liberal4);
		testPoll4.addParty(ndp4);
		System.out.println(testPoll4);
		System.out.println(conservative4);
		System.out.println(liberal4);
		System.out.println(ndp4);
			
		Party conservative5 = new Party("Conservative",2,1f);
		Party liberal5 = new Party("Liberal",4,2f);
		Party ndp5 = new Party("NDP",194,97f);
		Poll testPoll5 = new Poll("Test Poll 2",3);
		testPoll5.addParty(conservative5);
		testPoll5.addParty(liberal5);
		testPoll5.addParty(ndp5);
		System.out.println(testPoll5);
		System.out.println(conservative5);
		System.out.println(liberal5);
		System.out.println(ndp5);

		PollList testList = new PollList(3,225);
		
		System.out.println("Adding first poll ... ");
		testList.addPoll(testPoll1);
		System.out.println("Adding second poll ... ");
		testList.addPoll(testPoll2);
		System.out.println("Adding third poll ... ");
		testList.addPoll(testPoll3);
		System.out.println("Adding fourth poll ... should say poll list is full");
		testList.addPoll(testPoll4);
		System.out.println("Adding fifth poll ... should replace second poll");
		testList.addPoll(testPoll5);
		System.out.println("Done adding polls ");
		
		System.out.println(testList);
		
		String[] aggPollNames = {"Conservative","Liberal","NDP"};
		
		
		testList.getAggregatePoll(aggPollNames);
	}
	
}
