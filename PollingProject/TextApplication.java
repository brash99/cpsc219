//package application;

public class TextApplication {
	
	private PollList polls;
	private final int MAX_NUMBER_OF_STARS = 25;
	
	public TextApplication() {
		System.out.println("Starting program ... max stars = "+MAX_NUMBER_OF_STARS);
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
	
	private int getTotalSeatsInPoll (Party[] partyList) {
		float mySum = 0;
		for (int i=0; i<partyList.length; i++) {
			mySum += partyList[i].getProjectedNumberOfSeats();
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
				
		Poll aggregatePoll = new Poll("aggregate",partyNames.length);
		aggregatePoll = polls.getAggregatePoll(partyNames);
		this.displayPollDataBySeat(aggregatePoll);
	}
	
	public static void main(String[] args) {
		
		// Testing Code
		int numOfSeats = 100;
		PollList polls = new PollList(4, numOfSeats);
		Factory factory = new Factory(numOfSeats);
		String[] partyNames = {"BQ","CPC","Green","Liberal","NDP","PPC","Rhinoceros"};
		factory.setPartyNames(partyNames);
		polls.addPoll(factory.createRandomPoll("Poll1"));
		polls.addPoll(factory.createRandomPoll("Poll2"));
		polls.addPoll(factory.createRandomPoll("Poll3"));
		polls.addPoll(factory.createRandomPoll("Poll4"));
		TextApplication app = new TextApplication(polls);
		app.displayPollsBySeat(factory.getPartyNames());
		// End of Testing Code
		
	}

}
