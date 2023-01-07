package sourcecode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import utils.AppointmentUtils;

public class Agenda implements Iterable<Appointment> {
	private String name;
	private ArrayList<Appointment> appointments;

	public Agenda(String name) {
		if (name.equals("")) {
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

	public void addAppointment(Calendar date_time, String location, String person, int duration) throws ParseException, UnavailabilityException {
		if (AppointmentUtils.checkAvailability(date_time, location, person, duration, appointments)) {
			this.appointments.add(new Appointment(date_time, location, person, duration));
		} else {
			throw new UnavailabilityException("Impossibile creare il nuovo appuntamento, è già presente un altro appuntamento nello stesso periodo.");
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

	@Override
	public Iterator<Appointment> iterator() {
		return new Iterator<Appointment>() {
			private Iterator<Appointment> iterator = appointments.iterator();

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public Appointment next() {
				return iterator.next();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Impossibile rimuovere elementi durante l'iterazione");
			}
		};
	}

	public void removeObj(Appointment selectedAppointment) {
		appointments.remove(selectedAppointment);		
	}
	
	public void addObj(Appointment selectedAppointment) {
		appointments.add(selectedAppointment);		
	}

	public int getAppointmentIndex(Appointment selectedAppointment) {
		return appointments.indexOf(selectedAppointment);
	}

	public void addAppointmentAtIndex(Calendar date_time, String location, String person, int duration, int index) throws ParseException , UnavailabilityException {
		if (AppointmentUtils.checkAvailability(date_time, location, person, duration, appointments)) {
			this.appointments.add(index, new Appointment(date_time, location, person, duration));
		}else {
			throw new UnavailabilityException("Impossibile creare il nuovo appuntamento, è già presente un altro appuntamento nello stesso periodo.");
		}
	}

	public Appointment getAppointmentAt(int index) {
		return appointments.get(index);
	}
}