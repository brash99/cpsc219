package streamingMediaApp;

public class Song extends StreamingMedia {

	private int numOfLikes;
	
	public Song(String aTitle, int aLength) {
		super(aTitle, aLength);
		this.numOfLikes = 0;
	}

	public Song(Song toCopy) {
		super(toCopy);
		this.numOfLikes = toCopy.getNumOfLikes();
	}

	@Override
	public int getRating() {
		int localRating;
		if (numOfLikes >= 5000) {
			localRating = 9;
		} else {
			if (numOfLikes >= 500) {
				localRating = 7;
			} else {
				if (numOfLikes >= 50) {
					localRating = 5;
				} else {
					if (numOfLikes >= 10) {
						localRating = 3;
					} else {
						localRating = 1;
					}
				}
			}
		}
		
		return localRating;
	}
	
	public int getNumOfLikes() {
		return numOfLikes;
	}
	
	public void addLikes(int amount) {
		if (amount >= 0) {
			this.numOfLikes += amount;
		}
	}
	
	public String toString() {
		return super.toString() + " Likes: " + this.getNumOfLikes();
	}

}
