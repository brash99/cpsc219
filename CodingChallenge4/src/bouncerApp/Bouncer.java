package bouncerApp;
/**
 * Bouncer class for coding challenge 3 - practice 2
 */

/**
 * @author brash
 *
 */
public abstract class Bouncer {
	
	private double height;
	
	public Bouncer(double height) {
		
		if (height > 0.0) {
			this.height = height;
		} else {
			this.height = 1.0;
		}
	}
	
	public Bouncer(Bouncer toCopy) {
		
		if (toCopy.height > 0.0) {
			this.height = toCopy.height;
		} else {
			this.height = 1.0;
		}
	}
	
	protected void setHeight(double height) {
		if (height > 0.0) {
			this.height = height;
		} else {
			this.height = 1.0;
		}
	}
	
	protected double getHeight() {
		return height;
	}
	
	public void bounce() {
		//System.out.println("In bounce 1 ... " + this.height);
		this.height = heightAfterBounces(1);
		//System.out.println("In bounce 2 ... " + this.height);
	}
	
	public abstract double heightAfterBounces(int numOfBounces);
	
	public int numberOfBounces () {
		int n =1;
		double original_height = this.height;
		
		//System.out.println("Original Height = " + original_height);
		if (original_height == 0.0) {
			return 0;
		}
		
		while (true) { 
			double current_height = heightAfterBounces(n);
			//System.out.println("n = " + n + " current_height = " + current_height);
			if (current_height > 0) {
				n++;
			} else {
				break;
			}
		}
		
		height = original_height;
		return n;
	}
	
	public boolean equals(Bouncer other) {
		if ((this.height == other.height)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return "Height: " + this.height + " Number of bounces: " + this.numberOfBounces();
	}
}
