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
	
	public void modifyAppointment(Calendar date_time, String location, String person, String duration) {
		
		
	}
	
	public ArrayList<Appointment> findByDate(Calendar date_time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm"); 
		ArrayList<Appointment> result= new ArrayList<Appointment>();
		Calendar temp;
		
		for(Appointment appointment: appointments) {
			temp=appointment.getDate_time();
			if(sdf.format(temp.getTime())==sdf.format(date_time.getTime())) {
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
		ArrayList<Appointment> temp= new ArrayList<Appointment>();
		Appointment min;
		
		for(Appointment a: appointments) {
			temp.add(a);
		}
		
		for(int i=0;i<appointments.size();i++) {
		
				min=AppointmentUtils.findMin(temp);
				result.add(min);
				temp.remove(min);
		  }
		
		return result;
	}
	
	
	
}