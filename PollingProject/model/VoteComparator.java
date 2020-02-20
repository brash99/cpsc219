package model;

import java.util.Comparator;
 
public class VoteComparator implements Comparator<Party> {
 
    @Override
    public int compare(Party p1, Party p2) {
	       return (p1.getProjectedPercentageOfVotes() > p2.getProjectedPercentageOfVotes() ? -1 : 
	    	   (p1.getProjectedPercentageOfVotes() == p2.getProjectedPercentageOfVotes() ? 0 : 1));
    }
}