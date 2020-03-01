// Andrew Li's class

package model;

import java.util.Scanner;

/**
 * @author Andrew
 * @version iteration 1
 * 
 * The class TextApplication utilizes all of the other classes (Party, Poll, PollList, and Factory)
 * in order to form a complete text-based application. 
 * 
 * TextApplication contains a constant (the maximum number of stars) and a PollList.
 */
public class TextApplication {
    public final int MAX_NUMBER_OF_STARS = 25;
    private PollList polls;

    /**
     * The default constructor - takes no arguments and does nothing
     */
    public TextApplication() {}

    /**
     * The constructor that generates a TextApplication and assigns it a PollList
     * 
     * @param aPollList	A PollList that will be assigned to TextApplication
     */
    public TextApplication(PollList aPollList) {
        polls = aPollList;
    }

    /**
     * Visualizes one poll's data
     * Prints the poll's name
     * Prints the stars, name, votes, and seats for each party in the poll
     * Sorted by number of seats
     * 
     * @param aPoll			A Poll that will have its data visualized
     * @param maxNumOfSeats The total number of seats available
     */
    public void displayPollDataBySeat(Poll aPoll, int maxNumOfSeats) {
        System.out.println(aPoll.getPollName());
        for (Party aParty: aPoll.getPartiesSortedBySeats()) {
            System.out.println(aParty.textVisualizationBySeats(MAX_NUMBER_OF_STARS, 13, maxNumOfSeats / MAX_NUMBER_OF_STARS));
        }
        System.out.println("");
    }

    /**
     * Visualizes all Poll objects in the polls instance variable then presents an 
     * aggregate poll that only includes the parties that have been chosen by the user.
     * Sorted by number of seats
     * 
     * @param partyNames		An array of strings containing the names of parties to be included in the aggregate poll
     * @param maxNumOfSeats 	The total number of seats available
     */
    public void displayPollsBySeat(String[] partyNames, int maxNumOfSeats) {
        for (Poll aPoll: polls.getPolls()) {
            displayPollDataBySeat(aPoll, maxNumOfSeats);
        }

        Poll aggregate = polls.getAggregatePoll(partyNames);
        displayPollDataBySeat(aggregate, maxNumOfSeats);
    }

    /**
     * Visualizes one poll's data
     * Prints the poll's name
     * Prints the stars, name, votes, and seats for each party in the poll
     * Sorted by amount of votes
     * 
     * @param aPoll			A Poll that will have its data visualized
     */
    public void displayPollDataByVote(Poll aPoll) {
        System.out.println(aPoll.getPollName());
        for (Party aParty: aPoll.getPartiesSortedByVotes()) {
            System.out.println(aParty.textVisualizationByVotes(MAX_NUMBER_OF_STARS, 13, 100 / MAX_NUMBER_OF_STARS));
        }
        System.out.println("");
    }

    /**
     * Visualizes all Poll objects in the polls instance variable then presents an 
     * aggregate poll that only includes the parties that have been chosen by the user.
     * Sorted by amount of votes
     * 
     * @param partyNames		An array of strings containing the names of parties to be included in the aggregate poll
     * @param maxNumOfSeats 	The total number of seats available
     */
    public void displayPollsByVote(String[] partyNames, int maxNumOfSeats) {
        for (Poll aPoll: polls.getPolls()) {
            displayPollDataByVote(aPoll);
        }

        Poll aggregate = polls.getAggregatePoll(partyNames);
        displayPollDataByVote(aggregate);
    }

    /**
     * This method is used to visualize poll data by seat. 
     * Asks the user to chose a display option and then presents the required information.
     * 
     * @param chosenDisplayOption	The display option entered by the user (all, aggregate, or quit)	
     * @param chosenScanner 		The object used to provide user input
     * @param chosenNumOfSeats 		The total number of seats available
     */

    private void visualizeBySeat(String chosenDisplayOption, Scanner chosenScanner, int chosenNumOfSeats) {

        while (!chosenDisplayOption.equals("quit")) {
            //Runs this block if the display option is aggregate
            if (chosenDisplayOption.equals("aggregate")) {
                System.out.println("Which parties would you like me to include?");
                String aggregatenames = chosenScanner.nextLine();
                String[] aggregateNameList = aggregatenames.split(",");
                System.out.println("");
                displayPollsBySeat(aggregateNameList, chosenNumOfSeats);
            }
            //Runs this block if the display option is all
            else if (chosenDisplayOption.equals("all")) {
                for (Poll aPoll: polls.getPolls()) {
                    displayPollDataBySeat(aPoll, chosenNumOfSeats);
                }
            }
            //Runs this block if neither was entered
            else {
                System.out.println("Please select a valid option");
            }
            chosenDisplayOption = selectDisplayOption(chosenScanner);
        }
        System.out.println("Thank you for using the poll tracker");
        chosenScanner.close();
    }

    /**
     * This method is used to visualize poll data by vote. 
     * Asks the user to chose a display option and then presents the required information.
     * 
     * @param chosenDisplayOption	The display option entered by the user (all, aggregate, or quit)	
     * @param chosenScanner 		The object used to provide user input
     * @param chosenNumOfSeats 		The total number of seats available
     */
    private void visualizeByVote(String chosenDisplayOption, Scanner chosenScanner, int chosenNumOfSeats) {
        while (!chosenDisplayOption.equals("quit")) {
            //Runs this block if the display option is aggregate
            if (chosenDisplayOption.equals("aggregate")) {
                System.out.println("Which parties would you like me to include?");
                String aggregatenames = chosenScanner.nextLine();
                String[] aggregateNameList = aggregatenames.split(",");
                System.out.println("");
                displayPollsByVote(aggregateNameList, chosenNumOfSeats);
            }
            //Runs this block if the display option is all
            else if (chosenDisplayOption.equals("all")) {
                for (Poll aPoll: polls.getPolls()) {
                    displayPollDataByVote(aPoll);
                }
            }
            //Runs this block if neither was entered
            else {
                System.out.println("Please select a valid option");
            }
            chosenDisplayOption = selectDisplayOption(chosenScanner);
        }
        System.out.println("Thank you for using the poll tracker");
        chosenScanner.close();
    }

    /**
     * This method is used to accept a visualization option (vote or seat) from the user 
     * 
     * @param chosenScanner 		The object used to provide user input
     */
    private String selectVisualizationOption(Scanner chosenScanner) {
        String visOption = chosenScanner.nextLine();
        visOption = visOption.trim();
        visOption = visOption.toLowerCase();
        return visOption;
    }

    /**
     * This method is used to accept a display option (all, aggregate, or quit) from the user 
     * 
     * @param chosenScanner 		The object used to provide user input
     */
    private String selectDisplayOption(Scanner chosenScanner) {
        System.out.println("Options: all (show result of all polls), aggregate (show aggregate result), quit (end application)\n" +
            "Choose an option:");
        String displayOption = chosenScanner.nextLine();
        displayOption = displayOption.trim();
        displayOption = displayOption.toLowerCase();
        return displayOption;
    }

    /**
     * This method uses the Factory class to generate a PollList that is populated by 
     * a number Polls determined by the user.
     * The polls are filled with Parties chosen by the user, with random data. 
     * 
     * @param chosenNumOfPolls		The number of Polls to be generated, chosen by the user
     * @param chosenNumOfSeats 		The number of seats available, chosen by the user
     * @param chosenNameList 		A list of strings, each the name of a party, chosen by the user
     */
    private PollList generatePollList(int chosenNumOfPolls, int chosenNumOfSeats, String[] chosenNameList) {
        PollList newPollList = new PollList(chosenNumOfPolls, chosenNumOfSeats);
        Factory factory = new Factory(chosenNumOfSeats);
        factory.setPartyNames(chosenNameList);
        for (int i = 1; i <= chosenNumOfPolls; i++) {
            newPollList.addPoll(factory.createRandomPoll("Poll #" + i));
        }
        return newPollList;
    }

    /**
     * Runs the entire application.
     * Prompts the user for information
     * 
     * Uses factory to generate random polls
     * 
     * Creates a TextApplication object then presents the poll data 
     * 
     * @param partyNames		An array of strings containing the names of parties to be included in the aggregate poll
     */
    public void run() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to the poll tracker.");
        
        System.out.println("How many seats are available in the election?");
        int numOfSeats = keyboard.nextInt();
        keyboard.nextLine();
        
        System.out.println("Which parties are in the election (provide names, comma separated)?");
        String names = keyboard.nextLine();
        String[] nameList = names.split(",");
        
        System.out.println("How many polls do you want to track with this application?");
        int numOfPolls = keyboard.nextInt();
        keyboard.nextLine();
        
        System.out.println("Would you like me to create a random set of polls? (Yes/No)");
        String generateRandom = keyboard.nextLine();
        
        System.out.println("How would you like me to visualize the data? (seat/vote)");
        String visualizationOption = selectVisualizationOption(keyboard);
        
        while (!(visualizationOption.equals("seat") || visualizationOption.equals("vote"))) {
            System.out.println("Please enter a valid option? (seat/vote)");
            visualizationOption = selectVisualizationOption(keyboard);
        }
        
        String displayOption = selectDisplayOption(keyboard);
        
        PollList polls = generatePollList(numOfPolls, numOfSeats, nameList);

        TextApplication application = new TextApplication(polls);

        if (visualizationOption.equals("seat")) {
            application.visualizeBySeat(displayOption, keyboard, numOfSeats);
        }
        else if (visualizationOption.equals("vote")) {
            application.visualizeByVote(displayOption, keyboard, numOfSeats);
        }
    }

    /**
     * The main() method simply creates a TextApplication object and calls the run() method
     */
    public static void main(String[] args) {
        TextApplication app = new TextApplication();
        app.run();

        /**
         * @author Nathaly Verwaal
         * Testing code initially provided by the skeleton
         */
        //		int numOfSeats = 100;
        //		PollList polls = new PollList(4, numOfSeats);
        //		Factory factory = new Factory(numOfSeats);	
        //		String[] partyNames = {"BQ","CPC","Green","Liberal","NDP","PPC","Rhinoceros"};
        //		factory.setPartyNames(partyNames);
        //		polls.addPoll(factory.createRandomPoll("Poll1"));
        //		polls.addPoll(factory.createRandomPoll("Poll2"));
        //		polls.addPoll(factory.createRandomPoll("Poll3"));
        //		polls.addPoll(factory.createRandomPoll("Poll4"));
        //		TextApplication testApp = new TextApplication(polls);
        //		for (Poll aPoll: polls.getPolls()){
        //			testApp.displayPollDataBySeat(aPoll, numOfSeats);
        //			}
        //      String[] testAggregateNameList = {"BQ","Green","NDP","PPC","Rhinoceros"};
        //		testApp.displayPollsBySeat(testAggregateNameList, numOfSeats);

        /**
         *
         *@author Andrew Li
         *Testing Code
         *
         *First creates a number of parties, polls, and a poll list. 
         */

        //Setting the number of seats and creating some parties
        //		int numOfSeats = 100;
        //		Party conservative = new Party("Conservative",96,0.6f);
        //		Party liberal = new Party("Liberal",30,0.3f);
        //		Party ndp = new Party("NDP",95,0.4f);
        //		Party conservative2 = new Party("Conservative",69,0.69f);
        //		Party green = new Party("Green", 20, 0.35f);
        //		
        //		//Setting the list of names to be included in an aggregate poll
        //		String[] testNameList = {"Conservative","Liberal","NDP"};
        //
        //	
        //		//Creating some polls and adding parties to them
        //		Poll testPoll1 = new Poll("Test Poll 1",3);
        //		Poll testPoll2 = new Poll("Test Poll 2",2);
        //		Poll testPoll3 = new Poll("Test Poll 3",4);
        //		testPoll1.addParty(conservative);
        //		testPoll1.addParty(liberal);
        //		testPoll1.addParty(ndp);
        //		testPoll2.addParty(conservative);
        //		testPoll2.addParty(liberal);
        //		testPoll3.addParty(liberal);
        //		testPoll3.addParty(ndp);
        //		testPoll3.addParty(conservative2);
        //		testPoll3.addParty(green);
        //
        //		//Creating a PollList and adding the Polls to it
        //		PollList testList = new PollList(3,225);
        //		testList.addPoll(testPoll1);
        //		testList.addPoll(testPoll2);
        //		testList.addPoll(testPoll3);
        //		
        //		//Creating a TextApplication object using the test PollList
        //		TextApplication testApp = new TextApplication(testList);
        //		
        //		//Checking displayPollDataBySeat functionality for a single poll
        //		System.out.println("Testing display displayPollDataBySeat for a single poll");
        //		testApp.displayPollDataBySeat(testPoll1, numOfSeats);
        //		
        //		//Checking displayPollDataBySeat functionality for a list of polls
        //		System.out.println("Testing display displayPollDataBySeat for a pollList");
        //		for (Poll aPoll: testList.getPolls()){
        //			testApp.displayPollDataBySeat(aPoll, numOfSeats);
        //		}
        //
        //		//Checking displayPollsBySeat functionality for a list of polls
        //		System.out.println("Testing display displayPollsBySeat for a pollList");
        //		testApp.displayPollsBySeat(testNameList, numOfSeats);
        //		
        //		//Checking displayPollDataByVote functionality for a single poll
        //		System.out.println("Testing display displayPollDataByVote for a single poll");
        //		testApp.displayPollDataByVote(testPoll1);
        //		
        //		//Checking displayPollDataByVote functionality for a list of polls
        //		System.out.println("Testing display displayPollDataByVote for a pollList");
        //		for (Poll aPoll: testList.getPolls()){
        //			testApp.displayPollDataByVote(aPoll);
        //		}
        //
        //		//Checking displayPollsByVote functionality for a list of polls
        //		System.out.println("Testing display displayPollsByVote for a pollList");
        //		testApp.displayPollsByVote(testNameList, numOfSeats);
    }
}