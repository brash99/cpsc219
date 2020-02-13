import java.util.Arrays;

//package application;

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
		String output = this.name + ":\n";
		Party[] sortedPartyList = this.getPartiesSortedBySeats();
		for (int i=0; i<sortedPartyList.length; i++) {
			//output = output + sortedPartyList[i].textVisualizationBySeats(26, 13, 11.0) + "\n";
			output = output + (parties[i].getName() + " " + parties[i].getProjectedNumberOfSeats() + " " + parties[i].getProjectedPercentageOfVotes() + "%\n");
		}
		//sortedPartyList = this.getPartiesSortedByVotes();
		//for (int i=0; i<sortedPartyList.length; i++) {
		//	output = output + sortedPartyList[i].textVisualizationByVotes(25, 12, 4.0) + "\n";
		//	//output = output + (parties[i].getName() + " " + parties[i].getProjectedNumberOfSeats() + " " + parties[i].getProjectedPercentageOfVotes() + "%\n");
		//}
		
		return output;
	}
}
