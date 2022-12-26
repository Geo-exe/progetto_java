package sourcecode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import utils.AgendaUtils;

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
	public void addAppointment(String date, String time, String location, String person, String duration) throws ParseException {
		if(AgendaUtils.checkAvailability(date, time, location, person, duration)) {
			this.appointments.add(new Appointment(date, time, location, person, duration));
		}
	}
	public void modifyAppointment(String date, String time, String location, String person, String duration) {
		
		
	}
	
	public ArrayList<Appointment> findByDate(String date) throws ParseException {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		ArrayList<Appointment> result= new ArrayList<Appointment>();
		
		for(Appointment appointment: appointments) {
			if(appointment.getDate()==dateFormatter.parse(date)) {
				result.add(appointment);
			}
		}
		
		return result;
	}
	
	public ArrayList<Appointment> findByName(String name) {
		ArrayList<Appointment> result= new ArrayList<Appointment>();
		
		for(Appointment appointment: appointments) {
			if(appointment.getPerson()==name) {
				result.add(appointment);
			}
		}
		
		return result;
	}
	
	public ArrayList<Appointment> getAllAppointments() {
		ArrayList<Appointment> result= new ArrayList<Appointment>();
		//ciao
		
		return result;
	}
	
}
