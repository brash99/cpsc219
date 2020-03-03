/**
 * Bouncer class for coding challenge 3 - practice 2
 */

/**
 * @author brash
 *
 */
public class Bouncer {
	
	private int height;
	private double bounciness;
	
	public Bouncer(double bounciness, int height) {
		if (bounciness > 0 && bounciness < 1) {
			this.bounciness = bounciness;
		} else {
			this.bounciness = 0.50;
		}
		if (height > 0) {
			this.height = height;
		} else {
			this.height = 1;
		}
	}
	
	public Bouncer(Bouncer toCopy) {
		if (toCopy.bounciness > 0 && toCopy.bounciness < 1) {
			this.bounciness = toCopy.bounciness;
		} else {
			this.bounciness = 0.50;
		}
		if (toCopy.height > 0) {
			this.height = toCopy.height;
		} else {
			this.height = 1;
		}
	}
	
	public void setHeight(int height) {
		if (height > 0) {
			this.height = height;
		} else {
			this.height = 1;
		}
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setBounciness (double bounciness) {
		if (bounciness > 0 && bounciness < 1) {
			this.bounciness = bounciness;
		} else {
			this.bounciness = 0.50;
		}
	}
	
	public double getBounciness() {
		return bounciness;
	}
	
	public void bounce() {
		height = (int)(height*this.getBounciness());
	}
	
	public int numberOfBounces () {
		int n = 0;
		int original_height = this.height;
		
		while (true) {
			int initial_height = this.height;
			bounce();
			//System.out.println("Bounce(): n = " + n + "   initial_height = " + initial_height + " new height = " + this.height);
			if (this.height != initial_height) {
				n++;
			} else {
				break;
			}
		}
		
		height = original_height;
		return n;
	}
	
	public boolean equals(Bouncer other) {
		if ((this.height == other.height) && (this.bounciness == other.bounciness)) {
			return true;
		} else {
			return false;
		}
	}
}
