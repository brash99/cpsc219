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
		return parties;
	}

	public Party[] getPartiesSortedByVotes() {
		return parties;
	}
	
	@Override
	public String toString() {
		String output = this.name + ":\n";
		for (int i=0; i<parties.length; i++) {
			output = output + (parties[i].getName() + " " + parties[i].getProjectedNumberOfSeats() + " " + parties[i].getProjectedPercentageOfVotes() + "%\n");
		}
		
		return output;
	}
}
