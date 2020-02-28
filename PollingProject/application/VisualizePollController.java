package application;

import javafx.fxml.FXML;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.chart.PieChart;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import model.PollList;
import model.Party;

public class VisualizePollController extends PollTrackerController {
	
	private PollTrackerApp app;
	
	private PollList polls;

	private String tempPollName;
	private Party[] tempPartyList;
	
	@FXML
	private MenuButton displayPollChoice;
	@FXML
	private PieChart leftChart;
	@FXML
	private PieChart rightChart;
	
	public void setupController(PollTrackerApp app) {
		System.out.println("In VisualizePollController setupController ...");
		this.app = app;
		this.polls = app.getPolls();
		refresh();
	}
	
	public void refresh() {
		this.polls = app.getPolls();
		System.out.println("In refresh method of AddPollController");
		
		displayPollChoice.getItems().clear();
		displayPollChoiceCreate();
	}
	
	private void displayPollChoiceCreate() {
		for (int i=0; i<polls.getPolls().length; i++) {
			System.out.println("Adding poll menu item ... " + polls.getPolls()[i].getPollName());
			MenuItem add1 = new MenuItem(polls.getPolls()[i].getPollName());
			displayPollChoice.getItems().add(add1);
		    add1.setOnAction(new EventHandler<ActionEvent>() {
		        public void handle(ActionEvent t) {
					System.out.println("Choosing to display Poll " + add1.getText());
		        	displayPollChoice.setText(add1.getText());
		        	tempPollName = add1.getText();
		        	for (int j=0; j<polls.getPolls().length; j++) {
		        		if (polls.getPolls()[j].getPollName() == tempPollName) {
		        			tempPartyList = polls.getPolls()[j].getPartiesSortedBySeats();
		        		}
		        	}
		        	displayPieCharts(tempPartyList);
		        }
		    });	    
		}
		
		System.out.println("Adding aggregate poll menu item ... ");
		MenuItem add2 = new MenuItem("aggregate");
		displayPollChoice.getItems().add(add2);
	    add2.setOnAction(new EventHandler<ActionEvent>() {
	        public void handle(ActionEvent t) {
				System.out.println("Choosing to display aggregate Poll " + add2.getText());
	        	displayPollChoice.setText(add2.getText());
	        	tempPollName = add2.getText();
	        	String[] sss = app.getFactory().getPartyNames();
	        	tempPartyList = polls.getAggregatePoll(sss).getPartiesSortedBySeats();
	        	displayPieCharts(tempPartyList);
	        }
	    });	 
	}
	
	private void displayPieCharts(Party[] tempPartyList) {
	    if (tempPartyList != null) {
	    	ObservableList<PieChart.Data> leftChartObservableList =
	    			FXCollections.observableArrayList();
	    	for (int i=0;i<tempPartyList.length;i++) {
	    		leftChartObservableList.add(i,new PieChart.Data(tempPartyList[i].getName(),tempPartyList[i].getProjectedNumberOfSeats()));
	    	}
	    
	    	ObservableList<PieChart.Data> rightChartObservableList =
	    			FXCollections.observableArrayList();
	    	for (int i=0;i<tempPartyList.length;i++) {
	    		rightChartObservableList.add(i,new PieChart.Data(tempPartyList[i].getName(),tempPartyList[i].getProjectedPercentageOfVotes()));
	    	}
	    
	    	leftChart.setData(leftChartObservableList);
	    	rightChart.setData(rightChartObservableList);
	    }
	}
	
	
    
}
