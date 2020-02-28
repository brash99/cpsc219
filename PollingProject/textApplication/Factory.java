package textApplication;

import java.util.Random;

 //(For teams of 4 or less this class is optional.)
public class Factory {
	private int numOfSeats;
	private String[] partyNames;
	
	public Factory(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}
	
	public int getNumOfSeats() {
		return numOfSeats;
	}
	
	public void setPartyNames(String[] names) {
		partyNames = names;
	}
	
	public String[] getPartyNames() {
		return partyNames;
	}
	
	public Poll createRandomPoll(String name) {
		
		System.out.println("Random poll: "+name);
		//System.out.println("partyNames: "+partyNames.length);		
		Poll poll = new Poll(name, partyNames.length);
		
		Random rand = new Random();
		
		Integer[] seats = new Integer[partyNames.length];
		
		float seatsum = 0.0f;
		int seatsAvailable = numOfSeats;
		for (int i=0; i<partyNames.length; i++) {
			if (i == partyNames.length-1) {
				seats[i] = seatsAvailable;
			} else {
				seats[i] = rand.nextInt(seatsAvailable);
				seatsAvailable -= seats[i];
			}
			seatsum = seatsum + seats[i];
			//System.out.println(i+" "+partyNames[i]+" "+seats[i]+" "+seatsum);
		}
		for (int i=0; i<partyNames.length; i++) {
			seats[i] = (int)(float)(seats[i]/seatsum*numOfSeats);
		}
		
		for (int i=0; i<partyNames.length; i++) {
			float percentOfVotes = (float)((float)seats[i]/numOfSeats*100.0);
			//System.out.println(i+" "+partyNames[i]+" "+seats[i]+" "+percentOfVotes);
			poll.addParty(new Party(partyNames[i], (float)(seats[i]), percentOfVotes));
		}
		return poll;
	}

	public PollList createRandomPollList(int numOfPolls) {
		PollList list = new PollList(numOfPolls,numOfSeats);
		for (int counter = 0; counter < numOfPolls; counter++) {
			list.addPoll(createRandomPoll("Poll" + counter));
		}
		return list;
	}
	
	public PollList promptForPollList(int numOfPolls) {
		return createRandomPollList(numOfPolls);
	}
}
