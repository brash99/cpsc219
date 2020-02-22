package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import model.Factory;
import model.PollList;

public class SetupPollTrackerController extends PollTrackerController {
	
	private PollTrackerApp app;
	
	private int numberOfPolls = 0;
	private int numberOfSeats = 0;
	private int numberOfParties = 0;
	private PollList polls;
	private Factory factory;
	
	@FXML
	private TextField numberOfPollsToTrack;
	@FXML
	private TextField numberOfSeatsAvailable;
	@FXML
	private TextField numberOfPartiesRunning;
	
	public void setPollTrackerApp(PollTrackerApp app) {
		System.out.println("In SetupPollTrackerController constructor ...");
		this.app = app;
		this.createNewPolls();
	}
	
	public void refresh() {
		System.out.println("In refresh method of SetupPollTrackerController");
		System.out.println("Number of polls: " + numberOfPolls + " Number of Seats: " + numberOfSeats + " Number of Parties: " + numberOfParties);
		
		numberOfPollsToTrack.setText(Integer.toString(numberOfPolls));
		numberOfSeatsAvailable.setText(Integer.toString(numberOfSeats));
		numberOfPartiesRunning.setText(Integer.toString(numberOfParties));

	}
	
    public void handleSubmitAction() {
        System.out.println("Submitting information ... ");
        numberOfPolls = Integer.parseInt(numberOfPollsToTrack.getText());
        numberOfSeats = Integer.parseInt(numberOfSeatsAvailable.getText());
        numberOfParties = Integer.parseInt(numberOfPartiesRunning.getText());
        
		this.createNewPolls();
        
        refresh();
    }
    
    public void handleClearAction() {
        System.out.println("Clearing information ... ");
        numberOfPolls = 0;
        numberOfSeats = 0;
        numberOfParties = 0;
        refresh();
    }
    
	private void createNewPolls() {
		System.out.println("Creating factory and polls");
		String[] nameList = new String[numberOfParties];
		for (int i=0; i<numberOfParties; i++ ) {
			nameList[i]=Integer.toString(i+1);
		}
		factory = new Factory(numberOfSeats);
		factory.setPartyNames(nameList);
		//PollList polls = factory.createRandomPollList(numberOfPolls);
		polls = new PollList(numberOfPolls, numberOfSeats);

		System.out.println("Setting polls in main app ...");
		app.setPolls(polls);
		System.out.println("Setting factory in main app ...");		
		app.setFactory(factory);
	}

}