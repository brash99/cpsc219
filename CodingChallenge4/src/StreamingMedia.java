
public abstract class StreamingMedia {
	
	private String title;
	private int length;
	
	public StreamingMedia(String aTitle, int aLength) {
		this.title = aTitle.toUpperCase();
		if (aLength>0) {
			this.length = aLength;
		} else {
			this.length = 10;
		}
	}
	
	public StreamingMedia(StreamingMedia toCopy) {
		this.title = toCopy.title;
		this.length = toCopy.length;
	}
	
	public String getTitle() {
		return title;
	}
	
	protected void setTitle(String aTitle) {
		this.title = aTitle.toUpperCase();
	}
	
	public int getLength() {
		return length;
	}
	
	protected void setLength(int aLength) {
		if (aLength>0) {
			this.length = aLength;
		}
	}
	
	public char getCategory() {
		if (this.getRating() < 3) {
			return 'F';
		} else if (this.getRating() < 5) {
			return 'D';
		} else if (this.getRating() < 7) {
			return 'C';
		} else if (this.getRating() < 9) {
			return 'B';
		} else {
			return 'A';
		}
	}
	
	public abstract int getRating();
	
	public String toString() {
		return "Title: " + this.getTitle() + " Length: " + this.length;
	}
	


}
