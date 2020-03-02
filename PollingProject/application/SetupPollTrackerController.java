package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import model.Factory;
import model.PollList;

public class SetupPollTrackerController extends PollTrackerController {
	
	private PollTrackerApp app;
	
	private int localNumberOfPolls = 0;
	private int localNumberOfSeats = 0;
	private int localNumberOfParties = 0;
	private PollList localPolls;
	private Factory localFactory;
	
	@FXML
	private TextField numberOfPollsToTrack;
	@FXML
	private TextField numberOfSeatsAvailable;
	@FXML
	private TextField numberOfPartiesRunning;
	
	public void setupController(PollTrackerApp app) {
		System.out.println("In SetupPollTrackerController setupController ...");
		this.app = app;
		this.createNewPolls();
	}
	
	public void refresh() {
		System.out.println("In refresh method of SetupPollTrackerController");
		System.out.println("Number of polls: " + localNumberOfPolls + " Number of Seats: " + localNumberOfSeats + " Number of Parties: " + localNumberOfParties);
		
		numberOfPollsToTrack.setText(Integer.toString(localNumberOfPolls));
		numberOfSeatsAvailable.setText(Integer.toString(localNumberOfSeats));
		numberOfPartiesRunning.setText(Integer.toString(localNumberOfParties));

	}
	
    public void handleSubmitAction() {
        System.out.println("Submitting information ... ");
        localNumberOfPolls = Integer.parseInt(numberOfPollsToTrack.getText());
        localNumberOfSeats = Integer.parseInt(numberOfSeatsAvailable.getText());
        localNumberOfParties = Integer.parseInt(numberOfPartiesRunning.getText());
        
		this.createNewPolls();
        
        refresh();
    }
    
    public void handleClearAction() {
        System.out.println("Clearing information ... ");
        localNumberOfPolls = 0;
        localNumberOfSeats = 0;
        localNumberOfParties = 0;
        refresh();
    }
    
	private void createNewPolls() {
		System.out.println("Creating factory and polls");
		String[] nameList = new String[localNumberOfParties];
		for (int i=0; i<localNumberOfParties; i++ ) {
			nameList[i]=Integer.toString(i+1);
			System.out.println(nameList[i]);
		}
		localFactory = new Factory(localNumberOfSeats);
		localFactory.setPartyNames(nameList);
		localPolls = localFactory.createRandomPollList(localNumberOfPolls);
	    //polls = new PollList(numberOfPolls, numberOfSeats);
	    System.out.println(localPolls);

		System.out.println("Setting polls in main app ...");
		app.setPolls(localPolls);
		System.out.println("Setting factory in main app ...");		
		app.setFactory(localFactory);
	}

}