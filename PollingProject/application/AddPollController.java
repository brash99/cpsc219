package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import model.PollList;
import model.Poll;
import model.PollListFullException;

public class AddPollController extends PollTrackerController {
	
	private PollTrackerApp app;
	
	private PollList polls;
	private String originalName;
	
	@FXML
	private TextField newPollName;
	@FXML
	private MenuButton pollMenu;
	@FXML
	private Label addPollError;
	
	public void setupController(PollTrackerApp app) {
		System.out.println("In AddPollController setupController ...");
		this.app = app;
		this.polls = app.getPolls();
	}
	
	public void refresh() {
		this.polls = app.getPolls();
		System.out.println("In refresh method of AddPollController");
    	newPollName.setText("");
		
		pollMenu.getItems().clear();
		for (int i=0; i<polls.getPolls().length; i++) {
			
			MenuItem add1 = new MenuItem(polls.getPolls()[i].getPollName());
			pollMenu.getItems().add(add1);
		    add1.setOnAction(new EventHandler<ActionEvent>() {
		        public void handle(ActionEvent t) {
		        	pollMenu.setText(add1.getText());
		        	originalName = add1.getText();
		        }
		    });
		}
	}
	
    public void handlePollClearAction() {
    	refresh();
    	newPollName.setText("");
    	addPollError.setText("");
   }
    
    public void handleAddPollAction() {
    	try {
    		pollMenu.setText(newPollName.getText());
    		Poll aPoll = app.getFactory().createRandomPoll(newPollName.getText());
    		polls.replacePollAtIndex(aPoll, originalName);
    		polls = app.getPolls();
    	} catch (PollListFullException e) {
    		System.out.println("Poll list full exception!");
    		addPollError.setText("Error:  Poll list is already full!");
    		e.printStackTrace();
    	}

        refresh();
    }
    
    public void handleMenuChoice() {
    	return;
    }
    
}
