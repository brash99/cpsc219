
import java.util.Date;

public class Play extends Theatre {
	private String writer;
	private int yearWritten;

	
	public Play(String aTitle, int aRating, String aWriter, int yearWritten) {
		super(aTitle, aRating);
		if (yearWritten <= 2017) {
			this.yearWritten = yearWritten;
		} else {
			this.yearWritten = 2018;
		}
		this.writer = aWriter;
	}
	
	public Play(Play toCopy) {
		super(toCopy.getTitle(),toCopy.getRating());
		this.yearWritten = toCopy.yearWritten;
		this.writer = toCopy.writer;
	}
	
	public int getYearWritten() {
		return yearWritten;
	}
	
	public void setYearWritten(int year) {
		if (year <= 2017) {
			this.yearWritten = year;
		} else {
			this.yearWritten = 2018;
		}
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String aWriter) {
		this.writer = aWriter;
	}
	
	public String getCategory() {
		Date currentDate = new Date();
		long currentYear = currentDate.getYear();
		long diffYears = currentYear-yearWritten;
		
		if (diffYears > 200) {
			return "Classic";
		} else if (diffYears <=200 && diffYears > 50) {
			return "Contemporary";
		} else {
			return "Modern";
		}

	}

 }
