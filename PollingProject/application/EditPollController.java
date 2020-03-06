package application;

import javafx.fxml.FXML;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import model.PollList;
import model.InvalidPartyDataException;
import model.Party;
import model.PollFullException;

public class EditPollController extends PollTrackerController {
	
	private PollTrackerApp app;
	
	private PollList polls;

	private String tempPollName;
	private Party[] tempPartyList;
	private int tempPollIndex;
	private int tempPartyIndex;
	
	@FXML
	private TextField projNumberOfSeats;
	@FXML
	private TextField projPercentageOfVotes;
	@FXML
	private Label numberOfSeats;
	@FXML
	private Label errorLabel1;
	@FXML
	private Label errorLabel2;
	@FXML
	private MenuButton pollMenu;
	@FXML
	private MenuButton partyMenu;
	
	public void setupController(PollTrackerApp app) {
		System.out.println("In EditPollController setupController ...");
		this.app = app;
		this.polls = app.getPolls();
		refresh();
	}
	
	public void refresh() {

		this.polls = app.getPolls();
		System.out.println("In refresh method of EditPollController");
		projNumberOfSeats.setText("");
		projPercentageOfVotes.setText("");
		
		pollMenu.getItems().clear();
		partyMenu.getItems().clear();
		pollMenuCreate();
		partyMenuCreate(tempPartyList);
		
	}
	
	private void pollMenuCreate() {
		for (int i=0; i<polls.getPolls().length; i++) {
			MenuItem add1 = new MenuItem(polls.getPolls()[i].getPollName());
			pollMenu.getItems().add(add1);
		    add1.setOnAction(new EventHandler<ActionEvent>() {
		        public void handle(ActionEvent t) {
		        	pollMenu.setText(add1.getText());
		        	tempPollName = add1.getText();
		        	for (int j=0; j<polls.getPolls().length; j++) {
		        		if (polls.getPolls()[j].getPollName() == tempPollName) {
		        			tempPartyList = polls.getPolls()[j].getPartiesSortedBySeats();
		        			tempPollIndex = j;
		        		}
		        	}
		    		partyMenu.getItems().clear();
		        	partyMenuCreate(tempPartyList);
		        }
		    });	    
		}
	}
	
	private void partyMenuCreate(Party[] tempPartyList) {
		if (tempPartyList != null) {
			for (int i=0; i<tempPartyList.length; i++) {
				String partyName = tempPartyList[i].getName();
				MenuItem add2 = new MenuItem(partyName);
				partyMenu.getItems().add(add2);
				add2.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						float seatSum = 0.0f;
						partyMenu.setText(add2.getText());
						for (int j=0; j<tempPartyList.length; j++) {
							seatSum = seatSum + tempPartyList[j].getProjectedNumberOfSeats();
							if (tempPartyList[j].getName() == add2.getText()) {
								String s = String.format ("%.2f", tempPartyList[j].getProjectedNumberOfSeats());
								projNumberOfSeats.setText(s);
								String ss = String.format ("%.2f", tempPartyList[j].getProjectedPercentageOfVotes());
								projPercentageOfVotes.setText(ss);
								tempPartyIndex = j;
							}
						}
						String s = "/" + String.format("%.0f", seatSum);
						numberOfSeats.setText(s);
					}
				});	    		
			}
		}
	}
	
    public void handleClearEditAction() {
    	pollMenu.setText("");
    	partyMenu.setText("");
    	refresh();
   }
    
    public void handleUpdateAction() {
    	float seats = Float.parseFloat(projNumberOfSeats.getText());
    	try {
    		tempPartyList[tempPartyIndex].setProjectedNumberOfSeats(seats);
    		errorLabel1.setText("");
    	} catch (InvalidPartyDataException e1) {
    		errorLabel1.setText("Error: seats < 0 !!");
    	    errorLabel1.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
    		e1.printStackTrace();
    	}
    	float votes = Float.parseFloat(projPercentageOfVotes.getText());
    	try {
        	tempPartyList[tempPartyIndex].setProjectedPercentageOfVotes(votes);	
    		errorLabel2.setText("");
    	} catch (InvalidPartyDataException e2) {
    		errorLabel2.setText("Error: percentage out of range!!");
    	    errorLabel2.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
    		e2.printStackTrace();
    	}
    	
		try {
			polls.getPolls()[tempPollIndex].replaceParty(tempPartyList[tempPartyIndex],tempPartyIndex);
		} catch (PollFullException e) {
			e.printStackTrace();
		}
		
        refresh();
    }
    
}
