package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import model.InvalidSetupDataException;

import model.Factory;
import model.PollList;
import model.PollListFullException;

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
	@FXML
	private Label errorSetupPolls;
	
	public void setupController(PollTrackerApp app) {
		System.out.println("In SetupPollTrackerController setupController ...");
		this.app = app;
		this.createNewPolls();
	}
	
	public void refresh() {
		System.out.println("In refresh method of SetupPollTrackerController");		
		numberOfPollsToTrack.setText(Integer.toString(localNumberOfPolls));
		numberOfSeatsAvailable.setText(Integer.toString(localNumberOfSeats));
		numberOfPartiesRunning.setText(Integer.toString(localNumberOfParties));

	}
	
    public void handleSubmitAction() throws InvalidSetupDataException {
    	
        localNumberOfPolls = Integer.parseInt(numberOfPollsToTrack.getText());
        localNumberOfSeats = Integer.parseInt(numberOfSeatsAvailable.getText());
        localNumberOfParties = Integer.parseInt(numberOfPartiesRunning.getText());
        
        if (localNumberOfPolls > 0 && localNumberOfSeats > 0 && localNumberOfParties > 0) {
        	errorSetupPolls.setText("");
        	this.createNewPolls();
        } else {
    		errorSetupPolls.setText("Error: All entries must be positive numbers!!!");
    	    errorSetupPolls.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
    	    handleClearAction();
    	    throw new InvalidSetupDataException("Invalid data ... all entries must be > 0");
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
		try {
			localPolls = localFactory.createEmptyPollList(localNumberOfPolls);
		} catch (PollListFullException e) {
			e.printStackTrace();
		}

		app.setPolls(localPolls);		
		app.setFactory(localFactory);
	}

}