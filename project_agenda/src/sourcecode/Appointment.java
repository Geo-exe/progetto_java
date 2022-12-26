package sourcecode;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment {
	private String location;
	private Date date;
	private Time time;
	private int duration;
	private String person;
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
	public Appointment(String date, String time, String location, String person, int duration) throws ParseException {
		
		this.date = dateFormatter.parse(date);
		this.time = new Time(timeFormatter.parse(time).getTime());
		this.duration= duration;
		this.location = location;
		this.person = person;
		
	}

	public String getLocation() {
		return location;
	}

	public Date getDate() {
		return date;
	}
	
	public String getStrDate() {
		return dateFormatter.format(date);
	}

	public Time getTime() {
		return time;
	}

	public String getPerson() {
		return person;
	}

	public int getDuration() {
		return duration;
	}
	
	
}