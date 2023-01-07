package sourcecode;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
	private String location;
	private Calendar date_time;
	private int duration;
	private String person;
	public Appointment(Calendar date_time, String location, String person, int duration) {
		if(duration < 1 || duration > 1440) {
			throw new IllegalArgumentException("La durata dell'appuntamento non può essere minore di 1 minuto o maggiore di 24 ore (1440 minuti).");
		}
		if(location.equals("")) {
			throw new IllegalArgumentException("Il luogo non può essere vuoto");
		}
		if(person.equals("")) {
			throw new IllegalArgumentException("Il campo persona non può essere vuoto");
		}
		this.date_time=date_time;
		this.duration= duration;
		this.location = location;
		this.person = person;
		
	}

	public String getLocation() {
		return location;
	}

	public Calendar getDateTime() {
		return date_time;
	}
	
	public String getPerson() {
		return person;
	}

	public int getDuration() {
		return duration;
	}
	
	public String getTime() {
		return format2.format(date_time.getTime());
	}
	
	public String getStrDate() {
		
		return  format1.format(date_time.getTime());
	}

	public Calendar getEndDate_time() {
		Calendar result=(Calendar) date_time.clone();
		result.add(Calendar.MINUTE, duration);
		return result;
	}
	
}