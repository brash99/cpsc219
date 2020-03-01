package model;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Kylie
 *	The class Party represents a single political party, with its own name, projected number of seats, and projected percentage of votes. 
 * 
 * 	In addition to the getter and setter methods for the three instance variables listed above, methods are included for formatting the output
 *  information, calculating the percentage of seats a party is expected to win, and visualization of the seats and votes for a party.
 */
public class Party {
    private String name;
    private float seats;
    private float percent;

    /**
     * This constructor takes only the name of the party.
     * 
     * @param partyName The name of the party. Can take any string value.
     */
    public Party(String partyName) {
        setName(partyName);
    }

    /**
     * This Constructor takes the name of the party, projected number of seats, and projected percentage of votes.
     * 
     * @param partyName The name of the party. Can take any string value.
     * @param projectedNumberOfSeats The projected number of seats. Can take any non-negative float value.
     * @param projectedPercentageOfVotes The projected percentage of votes. Can take any float value between and including 0 and 1.
     */
    public Party(String partyName, float projectedNumberOfSeats, float projectedPercentageOfVotes) {
        setName(partyName);
        setProjectedNumberOfSeats(projectedNumberOfSeats);
        setProjectedPercentageOfVotes(projectedPercentageOfVotes);
    }

    /**
     * Getter for the name of the party.
     * 
     * @return The name of the party.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the party.
     * 
     * @param partyName The name of the party. Can take any string value.
     */
    public void setName(String partyName) {
        name = partyName;
    }

    /**
     * Getter for the projected number of seats for the party.
     * 
     * @return The projected number of seats.
     */
    public float getProjectedNumberOfSeats() {
        return seats;
    }

    /**
     * Setter for the projected number of seats for the party.
     * 
     * @param projectedNumberOfSeats The projected number of seats. Can take any non-negative float value.
     */
    public void setProjectedNumberOfSeats(float projectedNumberOfSeats) {
        if (projectedNumberOfSeats >= 0) {
            seats = projectedNumberOfSeats;
        } else {
            //Error message if a negative value is entered.
            System.out.println("This is not a valid projected number of seats. It must be a non-negative value.");
        }
    }

    /** 
     * Getter for the projected percentage of votes for the party.
     * 
     * @return The projected percentage of votes.
     */
    public float getProjectedPercentageOfVotes() {
        return percent;
    }

    /**
     *  Setter for the projected percentage of votes for the party.
     *  
     * @param projectedPercentageOfVotes The projected percentage of votes. Can take any float value between and including 0 and 1.
     */
    public void setProjectedPercentageOfVotes(float projectedPercentageOfVotes) {
        if (projectedPercentageOfVotes >= 0 & projectedPercentageOfVotes <= 1) {
            percent = projectedPercentageOfVotes;
        } else {
            // Error message if a value smaller than 0 or larger than 0 is entered.
            System.out.println("This is not a valid projected percentage number of votes. It must be between 0 and 1 inclusive.");
        }

    }

    /**
     * Returns a string containing party information, with the format: '<name>(<projected % of votes>%, <projected seats>)'.
     * 
     * @return formatted party information.
     */
    @Override
    public String toString() {
        return getName() + " (" + Math.round((getProjectedPercentageOfVotes()) * 100) + "%, " + getProjectedNumberOfSeats() + ")";
    }

    /**
     * Calculates the percentage of seats in the parliament a party is expected to win. 
     * 
     * @param totalNumberOfSeats The total number of seats available in parliament.
     * @return percentage of seats in the parliament a party is expected to win.
     */
    public double projectedPercentOfSeats(int totalNumberOfSeats) {
        float projectedNumberOfSeats = getProjectedNumberOfSeats();
        double projectedPercentOfSeats = projectedNumberOfSeats / (float) totalNumberOfSeats;
        return projectedPercentOfSeats;
    }

    /**
     * Provides a visual representation of the seats for the party using a "*" to represent a specified number of seats, with a "|" marker 
     *  at the majority in the parliament. 
     * 
     * @param maxStars The maximum number of stars that should be displayed on one line.
     * @param starsNeededForMajority The minimum number of stars that would represent a majority of seats.
     * @param numOfSeatsPerStar The number of seats that are represented by one star.
     * @return a string with a visual representation of the seats and formatted party information.
     */
    public String textVisualizationBySeats(int maxStars, int starsNeededForMajority, double numOfSeatsPerStar) {
        float projectedNumberOfSeats = getProjectedNumberOfSeats();
        int expectedNumberOfSeats = (int) Math.round(projectedNumberOfSeats / numOfSeatsPerStar);

        // Convert visual representation string array from textVisualization method to string.
        String[] textVisualizationBySeats = textVisualization(maxStars, expectedNumberOfSeats, starsNeededForMajority);
        String textVisualizationBySeatsString = "";
        textVisualizationBySeatsString = Stream.of(textVisualizationBySeats).collect(Collectors.joining());
        return textVisualizationBySeatsString;
    }

    /**
     * Provides a visual representation of the votes for the party using a "*" to represent a specified number of votes, with a "|" marker
     * at the majority of the votes.
     * 
     * @param maxStars The maximum number of stars that should be displayed on one line.
     * @param starsNeededForMajority The minimum number of stars that would represent a majority of votes.
     * @param percentOfVotesPerStar The number of votes that are represented by one star.
     * @return a string with a visual representation of the votes and formatted party information.
     */
    public String textVisualizationByVotes(int maxStars, int starsNeededForMajority, double percentOfVotesPerStar) {
        float projectedPercentageOfVotes = getProjectedPercentageOfVotes();
        int expectedPercentageOfVotes = (int) Math.round(projectedPercentageOfVotes * 100 / percentOfVotesPerStar);

        // Convert visual representation string array from textVisualization method to string.
        String[] textVisualizationByVotes = textVisualization(maxStars, expectedPercentageOfVotes, starsNeededForMajority);
        String textVisualizationByVotesString = "";
        textVisualizationByVotesString = Stream.of(textVisualizationByVotes).collect(Collectors.joining());
        return textVisualizationByVotesString;
    }

    /** 
     * Creates the visual representation used in both the textVisualizationBySeats and textVisualizationByVotes methods.
     * 
     * @param maxStars The maximum number of stars that should be displayed on one line.
     * @param partyStars The total number of stars a party has, based on their seats or votes.
     * @param starsNeededForMajority The minimum number of stars that would represent a majority of seats or votes.
     * @return a string array used to create the visual representation. 
     */
    private String[] textVisualization(int maxStars, int partyStars, int starsNeededForMajority) {
        int visualizationIndex = maxStars + 2; // Party information will be the last element in the array and displayed at index maxStars + 2. 
        String[] textVisualization = new String[visualizationIndex + 1];

        // If the party has equal to or more than the majority:
        if (partyStars >= starsNeededForMajority) {
            // Create stars for the total number of stars a party has.
            for (int i = 0; i <= partyStars; i++) { // "<=" is used as an extra star is created that will be replaced by the majority marker.
                textVisualization[i] = "*";
            }

            // Create blank space where a star, majority marker, or party information will not be displayed.
            for (int j = visualizationIndex - 1; j > partyStars; j--) {
                textVisualization[j] = " ";
            }
        }

        // If the party has less than the majority:
        if (partyStars < starsNeededForMajority) {
            // Create stars for the total number of stars a party has.
            for (int i = 0; i < partyStars; i++) {
                textVisualization[i] = "*";
            }

            // Create blank space where a star, majority marker, or party information will not be displayed.
            for (int j = visualizationIndex - 1; j >= partyStars; j--) {
                textVisualization[j] = " ";
            }
        }

        // Create a majority marker at the specified location.
        textVisualization[starsNeededForMajority] = "|";
        // Add party information at index maxStars + 2.
        textVisualization[visualizationIndex] = toString();
        return textVisualization;
    }
}