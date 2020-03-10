package model;

import java.util.Random;

import model.PollList;

 //(For teams of 4 or less this class is optional.)
public class Factory {
	private int numOfSeats;
	private String[] partyNames;
	private PollList localList;
	
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
				System.out.println("Invalid party data exception!!");
				e.printStackTrace();
			} catch (PollFullException f) {
				System.out.println("Poll full exception!!");
				f.printStackTrace();
			}
			
		}
		return poll;
	}
	
	public Poll createEmptyPoll(String name) {
		
		Poll poll = new Poll(name, partyNames.length);
		
		Float[] seats = new Float[partyNames.length];
		Float[] percentOfVotes = new Float[partyNames.length];	
		
		for (int i=0; i<partyNames.length; i++) {
				seats[i] = 0.0f;
				percentOfVotes[i] = 0.0f;
		} 
		
		for (int i=0; i<partyNames.length; i++) {
			try {
				poll.addParty(new Party(partyNames[i], seats[i], percentOfVotes[i]));
			} catch (InvalidPartyDataException e) {
				System.out.println("Invalid party data exception!!");
				e.printStackTrace();
			} catch (PollFullException f) {
				System.out.println("Poll full exception!!");
				f.printStackTrace();
			}
		}
		return poll;
	}

	public PollList createRandomPollList(int numOfPolls) {
		
		localList = new PollList(numOfPolls,numOfSeats);
		for (int counter = 0; counter < numOfPolls; counter++) {
			localList.addPoll(createRandomPoll("Poll" + counter));
		}
		
		return localList;
		
	}
	
	public PollList createEmptyPollList(int numOfPolls) {

		localList = new PollList(numOfPolls,numOfSeats);
		for (int counter = 0; counter < numOfPolls; counter++) {
			localList.addPoll(createEmptyPoll("Poll" + counter));
		}
		
		return localList;
	}
	
	public PollList promptForPollList(int numOfPolls) {
		return createRandomPollList(numOfPolls);
	}
}
