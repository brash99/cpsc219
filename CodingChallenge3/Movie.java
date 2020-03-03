import java.util.Date;

public class Movie extends Theatre {
	
	private String director;
	private Date releaseDate;
	
	public Movie(String aTitle, int aRating, String aDirector, Date aReleaseDate) {
		super(aTitle,aRating);
		this.director = aDirector;
		
		Date currentDate = new Date();
		long startTime = currentDate.getTime();
		long endTime = aReleaseDate.getTime();
		long diffTime = endTime - startTime;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
		
		//System.out.println(aReleaseDate);
		//System.out.println(currentDate);
		//System.out.println("diff = " + diffDays);
	
		if (diffDays > 365) {
			this.releaseDate = currentDate;
		} else {
			this.releaseDate = aReleaseDate;
		}
		
	}
	
	public Movie(Movie toCopy) {
		super(toCopy.getTitle(),toCopy.getRating());
		this.director = toCopy.director;
		this.releaseDate = toCopy.releaseDate;
	}
	
	public String getDirector() {
		return director;
	}
	
	public void setDirector(String aDirector) {
		director = aDirector;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	public String getCategory() {
		String category = super.getCategory();
		category = this.releaseDate.toString() + "-" + category;
		return category;
	}
	
	public void setReleaseDate(Date aReleaseDate) {
		
		Date currentDate = new Date();
		long startTime = currentDate.getTime();
		long endTime = aReleaseDate.getTime();
		long diffTime = endTime - startTime;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
	
		//System.out.println(aReleaseDate);
		//System.out.println(currentDate);
		//System.out.println("diff = " + diffDays);
	
		if (diffDays > 365) {
			this.releaseDate = currentDate;
		} else {
			this.releaseDate = aReleaseDate;
		}
		
	}

}
