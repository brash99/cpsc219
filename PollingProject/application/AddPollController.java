package application;

import javafx.fxml.FXML;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import model.Factory;
import model.PollList;

public class AddPollController extends PollTrackerController {
	
	private PollTrackerApp app;
	
	private PollList polls;
	private Factory factory;
	
	@FXML
	private TextField newPollName;
	@FXML
	private MenuButton pollMenu;
	
	public void setPollTrackerApp(PollTrackerApp app) {
		System.out.println("AddPollController constructor ...");
		this.app = app;
		polls = app.getPolls();
		factory = app.getFactory();
		System.out.println("Initial party names:");
		for (int i=0; i<app.getFactory().getPartyNames().length; i++) {
			System.out.println(app.getFactory().getPartyNames()[i]);
		}
	}
	
	public void refresh() {
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
		        	System.out.println("Choosing to replace Poll = " + newPollName.getText());
		        }
		    });
		}
	}
	
    public void handlePollClearAction() {
    	refresh();
    	newPollName.setText("");
   }
    
    public void handleAddPollAction() {
    	for (int i=0; i<polls.getPolls().length; i++) {
    		if (polls.getPolls()[i].getPollName().equals(newPollName.getText())) {
    			System.out.println("Match! " + i);
    			//partyNames[i] = newPartyName.getText();
    		}
    	}
        refresh();
    }
    
    public void handleMenuChoice() {
    	System.out.println(pollMenu.getId());
    }
    
}
