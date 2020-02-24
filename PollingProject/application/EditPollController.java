package application;

import javafx.fxml.FXML;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
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
	
	@FXML
	private TextField projNumberOfSeats;
	@FXML
	private TextField projPercentageOfVotes;
	@FXML
	private MenuButton pollMenu;
	@FXML
	private MenuButton partyMenu;
	
	public void setPollTrackerApp(PollTrackerApp app) {
		System.out.println("EditPollController constructor ...");
		this.app = app;
		this.polls = app.getPolls();
		this.factory = app.getFactory();
	}
	
	public void refresh() {
		this.polls = app.getPolls();
		System.out.println("In refresh method of AddPollController");
		projNumberOfSeats.setText("");
		projPercentageOfVotes.setText("");
		pollMenu.getItems().clear();
		
		for (int i=0; i<polls.getPolls().length; i++) {
			System.out.println("Updating poll menu items ... " + polls.getPolls()[i].getPollName());
			MenuItem add1 = new MenuItem(polls.getPolls()[i].getPollName());
			pollMenu.getItems().add(add1);
		    add1.setOnAction(new EventHandler<ActionEvent>() {
		        public void handle(ActionEvent t) {
		        	System.out.println("Choosing to edit Poll " + add1.getText());
		        	pollMenu.setText(add1.getText());
		        	originalName = add1.getText();
		        	for (int j=0; j<polls.getPolls().length; j++) {
		        		if (polls.getPolls()[j].getPollName() == originalName) {
		        			Party[] temp_party = polls.getPolls()[j].getPartiesSortedBySeats();
		        			System.out.println(temp_party);
		        		}
		        	}
		        }
		    });
		}
	}
	
    public void handleClearEditAction() {
    	refresh();
   }
    
    public void handleUpdateAction() {
    	System.out.println("In handleUpdateAction");
    	System.out.println(polls.getPolls().length);
		System.out.println(polls);
		polls = app.getPolls();
		System.out.println(polls);
		
        refresh();
    }
    
}
