package application;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SetupPartiesController extends PollTrackerController {
	
	private PollTrackerApp app;
	
	private String[] partyNames;
	private boolean reload = true;
	
	@FXML
	private TextField newPartyName;
	@FXML
	private TextField currentPartyName;
	@FXML
	private MenuButton partyMenu;
	
	public void setPollTrackerApp(PollTrackerApp app) {
		System.out.println("In SetupPartiesController constructor ...");
		this.app = app;
		
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
			partyNames = app.getFactory().getPartyNames();
			reload = false;
		}
		partyMenu.getItems().clear();
		for (int i=0; i<partyNames.length; i++) {
			System.out.println("Updating menu items ... " + partyNames[i]);
			MenuItem add1 = new MenuItem(partyNames[i]);
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
    	for (int i=0; i<partyNames.length; i++) {
    		System.out.println(partyNames[i] + " ...*... " + currentPartyName.getText());
    		if (partyNames[i].equals(currentPartyName.getText())) {
    			System.out.println("Match! " + i);
    			partyNames[i] = newPartyName.getText();
    		}
    	}

        refresh();
    }
    
    public void handleSubmitPartyInfoAction() {
    	app.getFactory().setPartyNames(partyNames);
    	refresh();
    }
    
    public void handleMenuChoice() {
    	System.out.println(partyMenu.getId());
    	partyMenu.setText(currentPartyName.getText());
    }
    
}
