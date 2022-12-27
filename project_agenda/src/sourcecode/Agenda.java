package sourcecode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;




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
	public void addAppointment(Calendar date_time, String location, String person, int duration) throws ParseException {
//		if(AgendaUtils.checkAvailability(date, time, location, person, duration)) {
//			this.appointments.add(new Appointment(date, time, location, person, duration));
//		}
		this.appointments.add(new Appointment(date_time, location, person, duration));
	}
	public void modifyAppointment(String date, String time, String location, String person, String duration) {
		
		
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
		
		
		return result;
	}
	
}