
public class Trampoline extends Bouncer{
	
	private int weightOfJumper;
	
	public Trampoline(int weightOfJumper, double bounciness, double height) {
		super(bounciness,(int)height);
		if (weightOfJumper <= 300 && weightOfJumper >=50) {
			this.weightOfJumper = weightOfJumper;
		} else {
			this.weightOfJumper = 140;
		}
	}
	
	public Trampoline(Trampoline toCopy) {
		super(toCopy.getBounciness(),toCopy.getHeight());
		this.weightOfJumper = toCopy.weightOfJumper;
	}
	
	public void setWeightOfJumper (int weight) {
		if (weight>0 && weight<=300) {
			this.weightOfJumper = weight;
		} else {
			this.weightOfJumper = 140;
		}
	}
	
	public int getWeightOfJumper() {
		return weightOfJumper;
	}
	
	public double getBounciness() {
		double start_bounciness = super.getBounciness();
		double new_bounciness;
		
		if (weightOfJumper < 100) {
			new_bounciness = 0.75*start_bounciness;
		} else if (weightOfJumper > 200) {
			new_bounciness = 1.20*start_bounciness;
		} else if (weightOfJumper > 150 && weightOfJumper <= 200) {
			new_bounciness = 1.10*start_bounciness;
		} else {
			new_bounciness = start_bounciness;
		}
		
		if (new_bounciness>=1.0) {
			new_bounciness = 0.99;
		}
		
		super.setBounciness(new_bounciness);
		return super.getBounciness();
	}

}
