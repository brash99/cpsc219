package application;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Factory;
import model.InvalidPartyDataException;
import model.Party;
import model.Poll;
import model.PollFullException;
import model.PollList;

public class SetupPartiesController extends PollTrackerController {
	
	private PollTrackerApp app;
	private PollList localPolls;
	private Factory localFactory;
	
	private String[] partyNames;
	private String[] newPartyNames;
	private boolean reload = true;
	
	@FXML
	private TextField newPartyName;
	@FXML
	private TextField currentPartyName;
	@FXML
	private MenuButton partyMenu;
	
	public void setupController(PollTrackerApp app) {
		System.out.println("In SetupPartiesController setupController ...");
		this.app = app;
		localPolls = app.getPolls();
		localFactory = app.getFactory();
		partyNames = app.getFactory().getPartyNames();
		
	}
	
	public void refresh() {
		System.out.println("In refresh method of SetupPartiesController");
    	newPartyName.setText("");
    	currentPartyName.setText("");

		if (reload) {
			localPolls = app.getPolls();
			localFactory = app.getFactory();
			partyNames = app.getFactory().getPartyNames();
			newPartyNames = new String[partyNames.length];
			for (int i=0; i<newPartyNames.length; i++) {
				newPartyNames[i] = Integer.toString(i+1);
			}
			reload = false;
		}
		
		partyMenu.getItems().clear();
		
		for (int i=0; i<partyNames.length; i++) {
			MenuItem add1 = new MenuItem(newPartyNames[i]);
			partyMenu.getItems().add(add1);
		    add1.setOnAction(new EventHandler<ActionEvent>() {
		        public void handle(ActionEvent t) {
		        	currentPartyName.setText(add1.getText());
		        	partyMenu.setText(currentPartyName.getText());
		        	newPartyName.setText(add1.getText());
		        }
		    });
		}
	}
	
    public void handlePartyClearAction() {
    	reload = true;
    	refresh();
    	newPartyName.setText("");
    	currentPartyName.setText("");
    }
    
    public void handleSetPartyInfoAction() {
    	
    	partyMenu.setText(newPartyName.getText());
    	for (int i=0; i<newPartyNames.length; i++) {
    		if (partyNames[i].equals(currentPartyName.getText())) {
    			newPartyNames[i] = newPartyName.getText();
    		}
    	}

        refresh();
    }
    
    public void handleSubmitPartyInfoAction() {
    	

    	
    	// go through each party name
    	// go through each poll in the poll list
    	// replace the entire party entry in each poll with the new party name, using the replaceParty method of the poll class
    	
    	for (int i=0; i<newPartyNames.length; i++) {
    		String tempPartyName = partyNames[i];
    		String replacementPartyName = newPartyNames[i];
			int pollCounter = 0;
    		for (Poll tempPoll:  localPolls.getPolls()) {
				int partyCounter = 0;
    			for (Party tempParty: tempPoll.getPartiesSortedBySeats()) {
    				if(tempParty.getName() == tempPartyName) {
    					try {
    						Party replacementParty = new model.Party(replacementPartyName,tempParty.getProjectedNumberOfSeats(),tempParty.getProjectedPercentageOfVotes());
    						try {
    							localPolls.getPolls()[pollCounter].replaceParty(replacementParty,partyCounter);
    						} catch (PollFullException e) {
    							e.printStackTrace();
    						}
    					} catch (InvalidPartyDataException e) {
    						e.printStackTrace();
    					}
    				}
    				partyCounter++;
    			}
    			pollCounter++;
    		}
    	}
    	
    	localFactory.setPartyNames(newPartyNames);
    	app.setPolls(localPolls);
    	app.setFactory(localFactory);

    	refresh();
    }
    
    public void handleMenuChoice() {
    	partyMenu.setText(currentPartyName.getText());
    }
    
}
