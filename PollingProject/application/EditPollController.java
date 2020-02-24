package application;

import javafx.fxml.FXML;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import model.PollList;
import model.Poll;
import model.Factory;
import model.Party;

public class EditPollController extends PollTrackerController {
	
	private PollTrackerApp app;
	
	private PollList polls;
	private Factory factory;
	private String originalName;
	private String originalParty;
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
	private MenuButton pollMenu;
	@FXML
	private MenuButton partyMenu;
	
	public void setPollTrackerApp(PollTrackerApp app) {
		System.out.println("EditPollController constructor ...");
		this.app = app;
		this.polls = app.getPolls();
		this.factory = app.getFactory();
		refresh();
	}
	
	public void refresh() {
		this.polls = app.getPolls();
		System.out.println("In refresh method of AddPollController");
		projNumberOfSeats.setText("");
		projPercentageOfVotes.setText("");
		
		pollMenu.getItems().clear();
		partyMenu.getItems().clear();
		pollMenuCreate();
		partyMenuCreate(tempPartyList);
		
	}
	
	private void pollMenuCreate() {
		for (int i=0; i<polls.getPolls().length; i++) {
			System.out.println("Adding poll menu item ... " + polls.getPolls()[i].getPollName());
			MenuItem add1 = new MenuItem(polls.getPolls()[i].getPollName());
			pollMenu.getItems().add(add1);
		    add1.setOnAction(new EventHandler<ActionEvent>() {
		        public void handle(ActionEvent t) {
					System.out.println("Choosing to edit Poll " + add1.getText());
		        	pollMenu.setText(add1.getText());
		        	originalName = add1.getText();
		        	for (int j=0; j<polls.getPolls().length; j++) {
		        		if (polls.getPolls()[j].getPollName() == originalName) {
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
				System.out.println(tempPartyList[i]);
				MenuItem add2 = new MenuItem(partyName);
				partyMenu.getItems().add(add2);
				add2.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						float seatSum = 0.0f;
						System.out.println("Choosing to edit Party " + add2.getText());
						partyMenu.setText(add2.getText());
						originalParty = add2.getText();
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
    	System.out.println("In handleUpdateAction");
    	float seats = Float.parseFloat(projNumberOfSeats.getText());
    	tempPartyList[tempPartyIndex].setProjectedNumberOfSeats(seats);
    	float votes = Float.parseFloat(projPercentageOfVotes.getText());
    	tempPartyList[tempPartyIndex].setProjectedPercentageOfVotes(votes);
    	
		System.out.println(polls.getPolls()[tempPollIndex].getParty(tempPartyList[tempPartyIndex].getName()));
		polls.getPolls()[tempPollIndex].replaceParty(tempPartyList[tempPartyIndex],tempPartyIndex);
		System.out.println(polls.getPolls()[tempPollIndex].getParty(tempPartyList[tempPartyIndex].getName()));
		
        refresh();
    }
    
}
