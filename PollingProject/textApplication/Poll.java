package textApplication;

import java.util.Arrays;

public class Poll {
	private String name;
	private Party[] parties;
	private int partiesInPoll;

	public Poll(String name, int maxNumberOfParties) {
		this.name = name; 
		parties = new Party[maxNumberOfParties];
	}
	
	public String getPollName() {
		return name;
	}
	
	public int getNumberOfParties() {
		return partiesInPoll;
	}
	public void addParty(Party aParty) {
		parties[partiesInPoll++] = aParty;
	}
	
	public void replaceParty(Party aParty, int index) {
		parties[index] = aParty;
	}

	public Party getParty(String name) {
		for (int i=0; i<parties.length;i++) {
			if (parties[i].getName() == name) {
				return parties[i];
			}
		}
		return null;
	}
	
	public Party[] getPartiesSortedBySeats() {
		
		Arrays.sort(parties, new SeatComparator());
		return parties;
	}
	

	public Party[] getPartiesSortedByVotes() {
		
		Arrays.sort(parties, new VoteComparator());
		return parties;
	}
	
	@Override
	public String toString() {
		System.out.println("In Poll toString() method ...");
		String output = this.name + ":\n";
		Party[] sortedPartyList = this.getPartiesSortedBySeats();
		for (int i=0; i<sortedPartyList.length; i++) {
			output = output + (parties[i].getName() + " " + parties[i].getProjectedNumberOfSeats() + " " + parties[i].getProjectedPercentageOfVotes() + "%\n");
		}

		return output;
	}
}
