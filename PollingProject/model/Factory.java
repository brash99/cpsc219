package model;

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
	
	private static int[] RandomizeArray(int[] array){
		Random rgen = new Random();  // Random number generator			
 
		for (int i=0; i<array.length; i++) {
		    int randomPosition = rgen.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
 
		return array;
	}
	
	public Poll createRandomPoll(String name) {
		
		Poll poll = new Poll(name, partyNames.length);
		
		Random rand = new Random();
		
		Integer[] seats = new Integer[partyNames.length];
		
		float seatsum = 0.0f;
		int seatsAvailable = numOfSeats;
		
		int[] indexlist = new int[partyNames.length];
		for (int i=0; i<partyNames.length; i++) {
			indexlist[i]=i;
		}
		indexlist = RandomizeArray(indexlist);
		
		
		for (int i=0; i<partyNames.length; i++) {
			if (i == partyNames.length-1) {
				seats[indexlist[i]] = seatsAvailable;
			} else {
				seats[indexlist[i]] = rand.nextInt(seatsAvailable);
				seatsAvailable -= seats[indexlist[i]];
			}
			seatsum = seatsum + seats[indexlist[i]];

		}
		for (int i=0; i<partyNames.length; i++) {
			seats[i] = (int)(float)(seats[i]/seatsum*numOfSeats);
		}
		
		for (int i=0; i<partyNames.length; i++) {
			float percentOfVotes = (float)((float)seats[i]/numOfSeats*100.0);

			try {
				poll.addParty(new Party(partyNames[i], (float)(seats[i]), percentOfVotes));
			} catch (InvalidPartyDataException e) {
				e.printStackTrace();
			} catch (PollFullException f) {
				f.printStackTrace();
			}
		}
		return poll;
	}

	public PollList createRandomPollList(int numOfPolls) throws PollListFullException {
		PollList list = new PollList(numOfPolls,numOfSeats);
		for (int counter = 0; counter < numOfPolls; counter++) {
			try {
				list.addPoll(createRandomPoll("Poll" + counter));
			} catch (PollListFullException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public PollList promptForPollList(int numOfPolls) throws PollListFullException {
		try {
			return createRandomPollList(numOfPolls);
		} catch (PollListFullException e) {
			e.printStackTrace();
		}
		return null;
	}
}
