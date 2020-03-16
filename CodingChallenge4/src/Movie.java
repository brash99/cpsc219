
public class Movie extends StreamingMedia {
	
	private int rating;

	public Movie(String aTitle, int aLength, int aRating) {
		super(aTitle, aLength);
		if (aRating >= 0 && aRating <=10) {
			this.rating = aRating;
		} else {
			this.rating = 0;
		}
	}

	public Movie(StreamingMedia toCopy) {
		super(toCopy);
		this.rating = toCopy.getRating();
	}
	
	public void setRating(int aRating) {
		if (aRating >= 0 && aRating <=10) {
			this.rating = aRating;
		}
	}

	@Override
	public int getRating() {
		return rating;
	}
	
	public String toString() {
		return super.toString() + " Rating: " + this.getRating();	
	}
	
	

}
