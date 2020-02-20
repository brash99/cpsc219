package application;

import java.util.Comparator;
 
public class SeatComparator implements Comparator<Party> {
 
    @Override
    public int compare(Party p1, Party p2) {
	       return (p1.getProjectedNumberOfSeats() > p2.getProjectedNumberOfSeats() ? -1 : 
	    	   (p1.getProjectedNumberOfSeats() == p2.getProjectedNumberOfSeats() ? 0 : 1));
    }
}