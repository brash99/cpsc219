package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

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
		if (localNumberOfPolls >= 0 && localNumberOfSeats >= 0 && localNumberOfParties >= 0) {
        	this.createNewPolls();
        } else {
    	    handleClearAction();
        }
	}
	
	public void refresh() {
		System.out.println("In refresh method of SetupPollTrackerController");		
		numberOfPollsToTrack.setText(Integer.toString(localNumberOfPolls));
		numberOfSeatsAvailable.setText(Integer.toString(localNumberOfSeats));
		numberOfPartiesRunning.setText(Integer.toString(localNumberOfParties));
	}
	
    public void handleSubmitAction() {
    	
        localNumberOfPolls = Integer.parseInt(numberOfPollsToTrack.getText());
        localNumberOfSeats = Integer.parseInt(numberOfSeatsAvailable.getText());
        localNumberOfParties = Integer.parseInt(numberOfPartiesRunning.getText());
        
        if (localNumberOfPolls > 0 && localNumberOfSeats > 0 && localNumberOfParties > 0) {
        	this.createNewPolls();
        } else {
    	    handleClearAction();
        }
        
    }
    
    public void handleClearAction() {
        localNumberOfPolls = 0;
        localNumberOfSeats = 0;
        localNumberOfParties = 0;
        refresh();
    }
    
	private void createNewPolls() {
		String[] nameList = new String[localNumberOfParties];
		for (int i=0; i<localNumberOfParties; i++ ) {
			nameList[i]=Integer.toString(i+1);
		}
		localFactory = new Factory(localNumberOfSeats);
		localFactory.setPartyNames(nameList);
		localPolls = localFactory.createEmptyPollList(localNumberOfPolls);

		app.setPolls(localPolls);		
		app.setFactory(localFactory);
	}

}