package model;

public class Party implements Comparable{
	private String name;
	private float seats;
	private float percent;
	
	public Party(String partyName) {
		name = partyName;
	}
	
	public Party(String partyName, float projectedNumberOfSeats, float projectedPercentageOfVotes) {
		name = partyName;
		if (projectedNumberOfSeats >= 0.0) {
			seats = projectedNumberOfSeats;
		} else {
			System.out.println("Number of seats must be >= zero!");
		}
		
		if (projectedPercentageOfVotes >=0.0 && projectedPercentageOfVotes <=100.0) {
			percent = projectedPercentageOfVotes;
		} else {
			System.out.println("Percentage of votes must be between 0 and 100!");
		}
	}
	
	public String getName() {
		return name;
	}
	
	public float getProjectedNumberOfSeats() {
		return seats;
	}
	
	public float getProjectedPercentageOfVotes() {
		return percent;
	}
	
	public void setProjectedNumberOfSeats(float projectedNumberOfSeats) {
		if (projectedNumberOfSeats >= 0) {
			seats = projectedNumberOfSeats;
		} else {
			System.out.println("Number of seats must be >= zero!");
		}
	}
	
	public void setProjectedPercentageOfVotes(float projectedPercentageOfVotes) {
		if (projectedPercentageOfVotes >=0 && projectedPercentageOfVotes <=100.0) {
			percent = projectedPercentageOfVotes;
		} else {
			System.out.println("Percentage of votes must be between 0 and 1!");
		}
	}
	
	public double projectedPercentOfSeats(int totalNumberOfSeats) {
		return (double)(this.getProjectedNumberOfSeats()/totalNumberOfSeats*100.0);
	}
	
	private String createStarString(int starsNeededForMajority,int stars, int maxStars) {
		
		int sectionOneCharacters = 40;
		int remainingCharacters = sectionOneCharacters-maxStars;
		
		String starstring = new String(new char[maxStars+1]).replace('\0', ' ');
		String finalstring = new String(new char[remainingCharacters]).replace('\0', ' ');
		
		
		int end_index;
		if (stars>starsNeededForMajority) {
			end_index = stars+1;
		} else {
			end_index = stars;
		}
		
		char ch = '*';
		for (int i=0; i<end_index;i++) {
			starstring = starstring.substring(0,i)+ch+starstring.substring(i+1);
		}
		
		int index = starsNeededForMajority;
		starstring = starstring.substring(0,index) 
	              + '|' 
	              + starstring.substring(index+1);
		
		return starstring + finalstring + name + " (" + percent + "% " + seats + ")";
	}
	
	public String textVisualizationBySeats(int maxStars, int starsNeededForMajority, double numOfSeatsPerStar) {
		int stars = (int)Math.round(this.getProjectedNumberOfSeats()/numOfSeatsPerStar);

		return createStarString(starsNeededForMajority,stars,maxStars);
		
	}

	public String textVisualizationByVotes(int maxStars, int starsNeededForMajority, double percentOfVotesPerStar) {
		int stars = (int)Math.round(this.getProjectedPercentageOfVotes()/percentOfVotesPerStar);
		
		return createStarString(starsNeededForMajority,stars,maxStars);
	}
	
	@Override
	public String toString() {
		return name + " (" + percent + "% " + seats + ")";
	}
	
	public int compareTo(Object o) {
	       return (this.getProjectedNumberOfSeats() > ((Party) o).getProjectedNumberOfSeats() ? -1 : (this.getProjectedNumberOfSeats() == ((Party) o).getProjectedNumberOfSeats() ? 0 : 1));
	   }
}
