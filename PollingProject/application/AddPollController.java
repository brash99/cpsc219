package application;

import javafx.fxml.FXML;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import model.PollList;
import model.Poll;

public class AddPollController extends PollTrackerController {
	
	private PollTrackerApp app;
	
	private PollList polls;
	private String originalName;
	
	@FXML
	private TextField newPollName;
	@FXML
	private MenuButton pollMenu;
	
	public void setupController(PollTrackerApp app) {
		System.out.println("In AddPollController setupController ...");
		this.app = app;
		this.polls = app.getPolls();
		System.out.println("Initial party names:");
		for (int i=0; i<app.getFactory().getPartyNames().length; i++) {
			System.out.println(app.getFactory().getPartyNames()[i]);
		}
	}
	
	public void refresh() {
		this.polls = app.getPolls();
		System.out.println("In refresh method of AddPollController");
    	newPollName.setText("");
		System.out.println("Main app party names:");
		for (int i=0; i<app.getFactory().getPartyNames().length;i++) {
			System.out.println(app.getFactory().getPartyNames()[i]);
		}
		
		pollMenu.getItems().clear();
		for (int i=0; i<polls.getPolls().length; i++) {
			
			System.out.println("Updating menu items ... " + polls.getPolls()[i].getPollName());
			MenuItem add1 = new MenuItem(polls.getPolls()[i].getPollName());
			pollMenu.getItems().add(add1);
		    add1.setOnAction(new EventHandler<ActionEvent>() {
		        public void handle(ActionEvent t) {
		        	System.out.println("Choosing to replace " + add1.getText() + " with new random Poll = " + newPollName.getText());
		        	pollMenu.setText(add1.getText());
		        	originalName = add1.getText();
		        }
		    });
		}
	}
	
    public void handlePollClearAction() {
    	refresh();
    	newPollName.setText("");
   }
    
    public void handleAddPollAction() {
    	pollMenu.setText(newPollName.getText());
    	System.out.println("In handlePollAction");
    	System.out.println(polls.getPolls().length);
		Poll aPoll = app.getFactory().createRandomPoll(newPollName.getText());
		System.out.println(polls);
		polls.replacePollAtIndex(aPoll, originalName);
		polls = app.getPolls();
		System.out.println(polls);
		
        refresh();
    }
    
    public void handleMenuChoice() {
    	System.out.println("In handleMenuChoice");
    	return;
    }
    
}
