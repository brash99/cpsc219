
public class Trampoline extends Bouncer {
	
	private int weightOfJumper;
	private double[] heightAfterBounces = new double[1000];

	public Trampoline(int weightOfJumper,double height) {
		super(height);
		if (weightOfJumper <= 300 && weightOfJumper >=50) {
			this.weightOfJumper = weightOfJumper;
		} else {
			this.weightOfJumper = 140;
		}
		
		int n = 1;
		this.heightAfterBounces[0] = this.getHeight();
		System.out.println("n: " + "0" + " height: " + this.heightAfterBounces[0]);
		
		while (true) {
			double bounciness = (this.weightOfJumper + this.heightAfterBounces[n-1])/(3.5*this.heightAfterBounces[n-1]);
			this.heightAfterBounces[n] = this.heightAfterBounces[n-1]*bounciness/100.0;	
			n++;
			if (this.heightAfterBounces[n-1]<1.0) {
				this.heightAfterBounces[n-1] = 0.0;
				System.out.println("n: " + (n-1) + " height: " + this.heightAfterBounces[n-1]);
				break;
			} else {
				System.out.println("n: " + (n-1) + " height: " + this.heightAfterBounces[n-1]);
			}
		}
		
	}

	public Trampoline(Trampoline toCopy) {
		super(toCopy);
		if (toCopy.getWeightOfJumper() <= 300 && toCopy.getWeightOfJumper() >=50) {
			this.weightOfJumper = toCopy.getWeightOfJumper();
		} else {
			this.weightOfJumper = 140;
		}
		
		int n = 1;
		this.heightAfterBounces[0] = toCopy.heightAfterBounces[0];
		
		while (true) {
			double bounciness = (this.weightOfJumper + this.heightAfterBounces[n-1])/(3.5*this.heightAfterBounces[n-1]);
			this.heightAfterBounces[n] = this.heightAfterBounces[n-1]*bounciness/100.0;
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
	
	public void setWeightOfJumper (int weight) {
		if (weight <= 300 && weight >=50) {
			this.weightOfJumper = weight;
		} else {
			this.weightOfJumper = 140;
		}
	}
	
	public int getWeightOfJumper() {
		return this.weightOfJumper;
	}
	
	public String toString() {
		return "[Trampoline] " + super.toString();
	}

}
