
public class Theatre {
	
	private String title;
	private int rating;
	
	public Theatre(String aTitle, int aRating) {
		this.title = aTitle.toUpperCase();
		if (aRating>=0 && aRating<=10) {
			this.rating = aRating;
		} else {
			this.rating = 0;
		}
	}
	
	public Theatre(Theatre toCopy) {
		this.title = toCopy.title;
		this.rating = toCopy.rating;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getRating() {
		return rating;
	}
	
	public String getCategory() {
		if (rating < 3) {
			return "F";
		} else if (rating < 5) {
			return "D";
		} else if (rating < 7) {
			return "C";
		} else if (rating < 9) {
			return "B";
		} else {
			return "A";
		}
	}
	
	public void setRating(int aRating) {
		if (aRating>=0 && aRating<=10) {
			this.rating = aRating;
		}
	}
	
	public void setTitle(String aTitle) {
		this.title = aTitle.toUpperCase();
	}

}
