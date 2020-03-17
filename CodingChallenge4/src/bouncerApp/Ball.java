package bouncerApp;

public class Ball extends Bouncer {
	
	private int bounciness;
	private double[] heightAfterBounces = new double[1000];

	public Ball(int bounciness, double height) {
		super(height);
		if (bounciness > 0 && bounciness < 100) {
			this.bounciness = bounciness;
		} else {
			this.bounciness = 50;
		}
		
		int n = 1;
		this.heightAfterBounces[0] = this.getHeight();
		
		while (true) {
			this.heightAfterBounces[n] = this.heightAfterBounces[n-1]*this.bounciness/100.0;
			n++;
			if (this.heightAfterBounces[n-1]<1.0) {
				this.heightAfterBounces[n-1] = 0.0;
				break;
			}
		}
	}

	public Ball(Ball toCopy) {
		super(toCopy);
		if (toCopy.bounciness > 0 && toCopy.bounciness < 100) {
			this.bounciness = toCopy.bounciness;
		} else {
			this.bounciness = 50;
		}
		
		int n = 1;
		this.heightAfterBounces[0] = toCopy.heightAfterBounces[0];
		
		while (true) {
			this.heightAfterBounces[n] = this.heightAfterBounces[n-1]*this.bounciness/100.0;
			n++;
			if (this.heightAfterBounces[n-1]<1.0) {
				this.heightAfterBounces[n-1] = 0.0;
				break;
			}
		}

		
	}

	@Override
	public double heightAfterBounces(int numOfBounces) {
		return heightAfterBounces[numOfBounces];
	}
	
	public void setBounciness(int bounciness) {
		if (bounciness > 0 && bounciness < 100) {
			this.bounciness = bounciness;
		} else {
			this.bounciness = 50;
		}
	}
	
	public int getBounciness() {
		return this.bounciness;
	}
	
	public String toString() {
		return "[Ball] " + super.toString() + " Bounciness: " + this.getBounciness() + "%";
	}

}
