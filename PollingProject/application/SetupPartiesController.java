package application;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import model.Factory;
import model.PollList;

public class SetupPartiesController extends PollTrackerController {
	
	private PollTrackerApp app;
	
	private Factory factory;
	private String[] partyNames;
	
	@FXML
	private TextField newPartyName;
	@FXML
	private MenuButton m;
	private MenuItem m1;
	
	public void setPollTrackerApp(PollTrackerApp app) {
		System.out.println("In SetupPartiesController constructor ...");
		this.app = app;
		factory = app.getFactory();
		partyNames = factory.getPartyNames();
		System.out.println("Initial party names:");
		for (int i=0; i<partyNames.length; i++) {
			System.out.println(partyNames[i]);
		}
	}
	
	public void refresh() {
		System.out.println("In refresh method of SetupPartiesController");
    	newPartyName.setText("");
		System.out.println("Main app party names:");
		for (int i=0; i<app.getFactory().getPartyNames().length;i++) {
			System.out.println(app.getFactory().getPartyNames()[i]);
		}
		partyNames = app.getFactory().getPartyNames();
	}
	
    public void handlePartyClearAction() {
    	refresh();
    	newPartyName.setText("");
    }
    
    public void handleSetPartyInfoAction() {
    	String temp_party = newPartyName.getText();
    	partyNames[1]=temp_party;
        //refresh();
    }
    
    public void handleSubmitPartyInfoAction() {
    	partyNames[0]="crap";
    	updatePartyInfo();
        //refresh();
    }
    
	private void updatePartyInfo() {
		System.out.println("Updating party Information");
		app.getFactory().setPartyNames(partyNames);
	}
}
