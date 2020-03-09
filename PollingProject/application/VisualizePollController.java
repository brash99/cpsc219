package application;

import javafx.fxml.FXML;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;

import javafx.scene.chart.PieChart;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

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
	@FXML
	private Label leftCaption;
	@FXML
	private Label rightCaption;
	
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
		leftCaption.setText(null);
		rightCaption.setText(null);
		if (polls.getPolls() != null) {
			displayPollChoiceCreate();
		}
	}
	
	private void displayPollChoiceCreate() {
		for (int i=0; i<polls.getPolls().length; i++) {
			MenuItem add1 = new MenuItem(polls.getPolls()[i].getPollName());
			displayPollChoice.getItems().add(add1);
		    add1.setOnAction(new EventHandler<ActionEvent>() {
		        public void handle(ActionEvent t) {
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

		MenuItem add2 = new MenuItem("aggregate");
		displayPollChoice.getItems().add(add2);
	    add2.setOnAction(new EventHandler<ActionEvent>() {
	        public void handle(ActionEvent t) {
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
	    	
	        leftCaption.setTextFill(Color.BLACK);
	        leftCaption.setStyle("-fx-font: 12 arial;");

	    	leftChart.setData(leftChartObservableList);
	    	rightChart.setData(rightChartObservableList);

            
	        for (final PieChart.Data data : leftChart.getData()) {
	            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
	                @Override
	                public void handle(MouseEvent e) {
	                    leftCaption.setTranslateX(e.getSceneX());
	                    leftCaption.setTranslateY(e.getSceneY()-35.0); 
	                    leftCaption.setText(String.format("%.2f",data.getPieValue()  ));
	                }
	            });
	        }
	        
	        for (final PieChart.Data data : rightChart.getData()) {
	            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
	                @Override
	                public void handle(MouseEvent e) {
	                    leftCaption.setTranslateX(e.getSceneX());
	                    leftCaption.setTranslateY(e.getSceneY()-35.0);
	 
	                    leftCaption.setText(String.format("%.2f",data.getPieValue()) + "%");
	                }
	            });
	        }
	        
	    	leftChart.setData(leftChartObservableList);
	    	rightChart.setData(rightChartObservableList);

	    }
	}
    
}
