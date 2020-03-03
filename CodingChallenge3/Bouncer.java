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
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setBounciness (double bounciness) {
		this.bounciness = bounciness;
	}
	
	public double getBounciness() {
		return bounciness;
	}
	
	public void bounce() {
		height = (int)(height*bounciness);
	}
	
	public int numberOfBounces () {
		int n = 0;
		while (true) {
			int initial_height = this.height;
			bounce();
			if (this.height != initial_height) {
				n++;
			} else {
				break;
			}
		}
		
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
