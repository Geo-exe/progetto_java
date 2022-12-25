package sourcecode;

import java.text.ParseException;
import java.util.ArrayList;

public class Agenda {
	private String name;
	private ArrayList<Appointment> appointments;
	public Agenda(String name) {
		this.name = name;
		this.appointments = new ArrayList<Appointment>();
	}
	public String getName() {
		return name;
	}
	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}
	public int size() {
		return appointments.size();
	}
	public void addAppointment(String date, String time, String location, String person) throws ParseException {
		this.appointments.add(new Appointment(date, time, location, person));
	}
	
}
