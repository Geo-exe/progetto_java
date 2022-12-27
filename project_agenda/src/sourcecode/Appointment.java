package sourcecode;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Appointment {
	SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
	private String location;
	private Calendar date_time;
	private int duration;
	private String person;
	public Appointment(Calendar date_time, String location, String person, int duration) throws ParseException {
		
		this.date_time=date_time;
		this.duration= duration;
		this.location = location;
		this.person = person;
		
	}

	public String getLocation() {
		return location;
	}

	public Calendar getDate_time() {
		return date_time;
	}
	
	public String getTime() {
		return format2.format(date_time.getTime());
	}
	
	public String getStrDate() {
		
		return  format1.format(date_time.getTime());
	}

	public String getPerson() {
		return person;
	}

	public int getDuration() {
		return duration;
	}
	
	public int compare(Appointment a) {
		return date_time.compareTo(a.date_time);
	}
	
	
}