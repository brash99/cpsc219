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
		
		System.out.println("Initial party names:");
		for (int i=0; i<partyNames.length; i++) {
			System.out.println(partyNames[i]);
		}
	}
	
	public void refresh() {
		System.out.println("In refresh method of SetupPartiesController");
    	newPartyName.setText("");
    	currentPartyName.setText("");
		System.out.println("Main app party names:");
		for (int i=0; i<app.getFactory().getPartyNames().length;i++) {
			System.out.println(app.getFactory().getPartyNames()[i]);
		}
		if (reload) {
			System.out.println("Reload ...");
			localPolls = app.getPolls();
			localFactory = app.getFactory();
			partyNames = app.getFactory().getPartyNames();
			newPartyNames = new String[partyNames.length];
			for (int i=0; i<newPartyNames.length; i++) {
				newPartyNames[i] = Integer.toString(i+1);
				System.out.println("new names = " + newPartyNames[i]);
			}
			
			reload = false;
		}
		
		partyMenu.getItems().clear();
		
		for (int i=0; i<partyNames.length; i++) {
			System.out.println("Updating menu items ... " + newPartyNames[i]);
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
    		System.out.println(partyNames[i] + " ...*... " + currentPartyName.getText());
    		if (partyNames[i].equals(currentPartyName.getText())) {
    			System.out.println("Match! " + i);
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
    		System.out.println("Checking party = " + tempPartyName + " for replacement with " + replacementPartyName);
			int pollCounter = 0;
    		for (Poll tempPoll:  localPolls.getPolls()) {
    			System.out.println("Poll = " + tempPoll);
				int partyCounter = 0;
    			for (Party tempParty: tempPoll.getPartiesSortedBySeats()) {
    				System.out.println("Party = " + tempParty);
    				if(tempParty.getName() == tempPartyName) {
    					System.out.println("Match!!! ...  partyCounter = " + partyCounter);
    					try {
    						Party replacementParty = new model.Party(replacementPartyName,tempParty.getProjectedNumberOfSeats(),tempParty.getProjectedPercentageOfVotes());
    						localPolls.getPolls()[pollCounter].replaceParty(replacementParty,partyCounter);
    					} catch (InvalidPartyDataException e) {
    						e.printStackTrace();
    					}
    				}
    				partyCounter++;
    			}
    			System.out.println("Updated local Poll = " + localPolls.getPolls()[pollCounter]);
    			System.out.println("Updated main Poll = " + app.getPolls().getPolls()[pollCounter]);
    			pollCounter++;
    		}
    	}
    	
    	System.out.println(localPolls);
    	localFactory.setPartyNames(newPartyNames);
    	app.setPolls(localPolls);
    	app.setFactory(localFactory);
    	System.out.println(app.getPolls());

    	refresh();
    }
    
    public void handleMenuChoice() {
    	System.out.println(partyMenu.getId());
    	partyMenu.setText(currentPartyName.getText());
    }
    
}
