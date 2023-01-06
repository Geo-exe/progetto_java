package sourcecode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import utils.AppointmentUtils;





public class Agenda {
	private String name;
	private ArrayList<Appointment> appointments;
	
	public Agenda(String name){
		if(name.equals("")) {
			throw new IllegalArgumentException("Il Nome non può essere vuoto");
		}
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
	
	public void addAppointment(Calendar date_time, String location, String person, int duration) throws ParseException {
		if(AppointmentUtils.checkAvailability(date_time, location, person, duration,appointments)) {
			this.appointments.add(new Appointment(date_time, location, person, duration));
		}
		
	}
	
	public void removeAt(int i) {
		
		appointments.remove(i);
	}
	
	public void removeAll() {
		
		appointments.clear();
	}
	
	public void modifyAppointment(Calendar date_time, String location, String person, String duration) {
		
		
	}
	
	public ArrayList<Appointment> sortAppointmets(OrderMethodEnum selectedMethod) {
		ArrayList<Appointment> result = new ArrayList<Appointment>(appointments);
		selectedMethod.orderByDate(result);
		return result;
	}
	
	
	
}