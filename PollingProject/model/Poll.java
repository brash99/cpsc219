// Dvij's class

package model;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Dvij
 * @version iteration 1
 * The class Poll allows for the generation a named political poll which can contain 
 * multiple political parties (Party []). Each party contained within the poll 
 * has a unique name, projected number of seats and projected percentage of votes 
 * for that poll.
 * 
 * Poll contains getters for the name of the poll, a specific party in the poll and
 * number of total parties in the poll. The class has sorters which sort the poll by
 * either seats won by each party or percentage of votes. 
 */
public class Poll {
    private String name;
    private Party[] parties;
    private int partiesInPoll;


    /**
     * The constructor generates a empty poll and assigns it the name provided. 
     * The Poll will contain the number of spots specified as long as the number 
     * if greater then or equal to 1. 
     * 
     * @param name				 Name which will be assigned to the poll
     * @param maxNumberOfParties The max number of parties which can be put into the poll 
     */
    public Poll(String name, int maxNumberOfParties) {
        this.name = name;
        /* set size of array to the one provided by maxNumberOfParties if int is >= 1.
         * Otherwise set the size to 10.
         */ 
        if (maxNumberOfParties >= 1) {
            parties = new Party[maxNumberOfParties];
        } else { 
            parties = new Party[10];
        }
    }


    /**
     * Getter for the name of the poll.
     * 
     * @return The name of the poll
     */
    public String getPollName() {
        return name;
    }


    /**
     * Getter for the number of parties in the poll.
     * 
     * @return Number of parties in the poll.
     */
    public int getNumberOfParties() {
        int index = 0;
        /* Return 0 when no party is found in index 0. Otherwise loop through the poll
         * until a null index is in countered, increment partiesInPoll each time.
         * 
         */
        if (parties[index] == null) {
            partiesInPoll = 0;
        } else {
            partiesInPoll = 0;
            while (index < parties.length && parties[index] != null) {
                ++index;
                ++partiesInPoll;
            }
        }
        return partiesInPoll;
    }

    /**
     * Adds a party specified by the provided name to the end of the poll lit. 
     * Prints an error message if the party does not exists or if the poll is full.
     * If a party already present in the poll has the same name as the one provided,
     * the method replaces the old party with the new one.
     *  
     * @param aParty The name of the party being added to the poll
     */
    public void addParty(Party aParty) {
        int index = 0;
        String testName = null;
        // Find if the poll already contains a party with the same name (not case sensitive)
        for (int i = 0; i < partiesInPoll; i++) {
            String partyName = parties[i].getName().toLowerCase();
            if (partyName.equals(aParty.getName().toLowerCase())) {
                testName = partyName;
                index = i;
            }
        }
        if (aParty == null) {
            System.out.println("The party is not defined.");
        }
        //Adds a party to index 0 if no party is present (empty case)
        else if (parties[0] == null) {
            parties[0] = aParty;
            partiesInPoll++;
        } 
        // replaces an existing party with aParty if they have the same name
        else if (testName != null) {
            parties[index] = aParty;
        } else if (partiesInPoll >= parties.length) {
            System.out.println("The poll is full and no further party can be added.");
        } 
        // adds a party to the end of the list and increments party counter by +1
        else {
            parties[partiesInPoll++] = aParty;
        }
    }


    /**
     * Getter for a party within a poll. 
     * 
     * @param name	Name of the party looking to be obtained
     * @return The party specified by the name if it exists, otherwise returns null.
     */
    public Party getParty(String name) {
        Party partyWithName = null;
        for (Party p: parties) {
            if (p.getName().equals(name)) {
                partyWithName = p;
            }
        }
        return partyWithName;
    }


    /**
     * Creates a deep copy of the party array within a poll.
     * 
     * @return A list of parties which is a copy of the array in a poll
     */
    private Party[] createDeepCopy() {
        Party[] aCopy = new Party[partiesInPoll];
        for (int i = 0; i < aCopy.length; i++) {
            String copyName = parties[i].getName();
            float copySeats = parties[i].getProjectedNumberOfSeats();
            float copyVotes = parties[i].getProjectedPercentageOfVotes();
            aCopy[i] = new Party(copyName, copySeats, copyVotes);
        }
        return aCopy;

    }

    /**
     * @author Dvij
     * Comparator class which compares two parties within a poll based on the number
     * of seats won by each.
     */
    static class SeatsComparator implements Comparator < Party > {
        @Override
        public int compare(Party a, Party b) {
            return Float.compare(b.getProjectedNumberOfSeats(), a.getProjectedNumberOfSeats());
        }
    }


    /**
     * Sorts the parties in a poll by the number of seats won by each party from greatest to fewest
     * and returns them as an list of parties (array party[])
     * 
     * @return Array of Party[] which is sorted by number of seats won. 
     */
    public Party[] getPartiesSortedBySeats() {
        //Making a deep copy of the poll (parties array)
        Party[] sortedSeats = createDeepCopy();
        Arrays.sort(sortedSeats, new SeatsComparator());
        return sortedSeats;
    }


    /**
     * @author Dvij
     * Comparator class which compares two parties within a poll based on the number
     * of votes received by each.
     */
    static class VotesComparator implements Comparator < Party > {
        @Override
        public int compare(Party a, Party b) {
            return Float.compare(b.getProjectedPercentageOfVotes(), a.getProjectedPercentageOfVotes());
        }
    }


    /**
     * Sorts the parties in a poll by the percentage of votes received by each party from greatest to fewest
     * and returns them as an list of parties (array party[])
     * 
     * @return Array of Party[] which is sorted by percentage of votes. 
     */
    public Party[] getPartiesSortedByVotes() {
        //Making a deep copy of the poll (parties array)
        Party[] sortedVotes = createDeepCopy();
        Arrays.sort(sortedVotes, new VotesComparator());
        return sortedVotes;
    }


    /**
     * Convert the information contained within a poll to a string with 
     * format '<name><newline><string representation of first party><newline>
     * <string representation of second party><newline>..."
     */
    @Override
    public String toString() {
        String pollString = name;
        for (Party p: parties) {
        	if (p != null) {
                String aParty = p.toString();
                pollString = pollString + "\n" + aParty;
            } 
        	// prints null if a party does not exists at that index
        	else {
                pollString = pollString + "\nnull";
            }
        }
        return pollString;
    }


    //Bonus
    /**
     * Creates and returns a part which is named 'Undetermined' and has the seats and
     * percentage of votes not already accounted for by the sum of all the other parties 
     * in a poll
     * 
     * @param totalNumOfSeats	The number of seats which the poll encompasses
     * @return A party with the number of seats and percentage of votes not accounted for in the poll.
     */
    public Party undeterminedVotesAndSeats(int totalNumOfSeats) {
        String undeterminedName = "Undetermined";
        float seatsCountedFor = 0.0f;
        float votesCountedFor = 0.0f;
        //Totaling the number of seats and percentage of votes already accounted for 
        for (Party p: parties) {
            seatsCountedFor = seatsCountedFor + p.getProjectedNumberOfSeats();
            votesCountedFor = votesCountedFor + p.getProjectedPercentageOfVotes();
        }
        /* Calculating the unaccounted for seats and votes, and using them to create 
         * the 'Undetermined' party
         */
        if (votesCountedFor < 1.0) {
            float undeterminedSeats = totalNumOfSeats - seatsCountedFor;
            float undeterminedVotes = 1.0f - votesCountedFor;
            Party undetermined = new Party(undeterminedName, undeterminedSeats, undeterminedVotes);
            return undetermined;
        }
        //Returning 0.0 values with the 'Undetermined' party if all the votes are accounted for 
        else {
            float undeterminedSeats = 0.0f;
            float undeterminedVotes = 0.0f;
            Party undetermined = new Party(undeterminedName, undeterminedSeats, undeterminedVotes);
            return undetermined;
        }
    }


    //For testing purposes
    public static void main(String[] args) {

        //Checking Poll initialization
        Poll testPoll = new Poll("test poll", 5);
        System.out.println(testPoll.getPollName());
        System.out.println(testPoll.getNumberOfParties());
        System.out.println(testPoll.toString());

        //Generating parties
        Party liberal = new Party("liberal", 30.0f, 0.3f);
        Party Conservative = new Party("Conservative", 20.0f, 0.2f);
        Party NDP = new Party("NDP", 20.0f, 0.2f);
        Party BQ = new Party("BQ", 15.0f, 0.15f);
        Party green = new Party("Green", 15.0f, 0.15f);
        Party Liberal = new Party("Liberal", 30.0f, 0.3f);
        Party Extra = new Party("Extra", 100.0f, 1.0f);

        //Populating testPoll
        testPoll.addParty(liberal);
        System.out.println(testPoll.getNumberOfParties());
        testPoll.addParty(Conservative);
        System.out.println(testPoll.getNumberOfParties());
        testPoll.addParty(NDP);
        System.out.println(testPoll.getNumberOfParties());
        testPoll.addParty(BQ);
        System.out.println(testPoll.getNumberOfParties());
        testPoll.addParty(green);
        System.out.println(testPoll.getNumberOfParties());

        //Checking replacement
        testPoll.addParty(Liberal);
        System.out.println(testPoll.getNumberOfParties());

        //Checking party addition over array limit
        testPoll.addParty(Extra);
        System.out.println(testPoll.getNumberOfParties());

        //Checking getParty method
        Party testGetParty = testPoll.getParty("NDP");
        System.out.println(testGetParty);

        //Checking toString method
        System.out.println(testPoll.toString());

        //Checking getPartiesSortedBySeats method
        Party[] testSeats = testPoll.getPartiesSortedBySeats();
        String testPrintSeats = "\n";
        for (Party p: testSeats) {
            String aParty = p.toString();
            testPrintSeats = testPrintSeats + aParty + "\n";
        }
        System.out.println(testPrintSeats);

        //Checking getPartiesSortedByVotes method
        Party[] testVotes = testPoll.getPartiesSortedByVotes();
        String testPrintVotes = "\n";
        for (Party p: testVotes) {
            String aParty = p.toString();
            testPrintVotes = testPrintVotes + aParty + "\n";
        }
        System.out.println(testPrintVotes);

        //Checking undeterminedVotesAndSeats method with a poll where all votes and seats are accounted for
        Party testUndeterminedNotPresent = testPoll.undeterminedVotesAndSeats(100);
        System.out.println(testUndeterminedNotPresent.toString());

        //Checking undeterminedVotesAndSeats method with a poll which has undetermined votes and seats
        Party Green = new Party("Green", 5.0f, 0.05f);
        testPoll.addParty(Green);
        Party testUndeterminedPresent = testPoll.undeterminedVotesAndSeats(100);
        System.out.println(testUndeterminedPresent.toString());

    }
}
