package sourcecode;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import utils.AppointmentUtils;

/**
 * La classe Agenda implementa le classi Iterable e Serializable. E' composta da
 * una stringa che corrisponde al nome dell'agenda e da un arraylist di oggetti
 * Appointment. Sono stati sviluppati i costruttori e vari metodi necessari.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class Agenda implements Iterable<Appointment>, Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Nome dell'agenda.
	 */
	private String name;
	/**
	 * Lista degli oggetti appuntamenti.
	 */
	private ArrayList<Appointment> appointments;

	/**
	 * Costruttore della classe che richiede una stringa.
	 * 
	 * @param name dell'agenda
	 */
	public Agenda(String name) {
		if (name.equals("")) {
			throw new IllegalArgumentException("Il Nome non può essere vuoto");
		}
		this.name = name;
		this.appointments = new ArrayList<Appointment>();
	}

	/**
	 * Costruttore della classe che richiede una stringa e un arraylist di
	 * Appointment.
	 * 
	 * @param name         dell'agenda
	 * @param appointments arraylist
	 */
	public Agenda(String name, ArrayList<Appointment> appointments) {
		if (name.equals("")) {
			throw new IllegalArgumentException("Il Nome non può essere vuoto");
		}
		this.name = name;
		this.appointments = appointments;
	}

	/**
	 * Restituisce il nome di un'agenda.
	 * 
	 * @return Il nome dell'agenda
	 */
	public String getName() {
		return name;
	}

	/**
	 * Restituisce la lista degli appuntamenti.
	 * 
	 * @return un arraylist di appuntamenti
	 */
	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	/**
	 * Restituisce il numero totale di appuntamenti.
	 * 
	 * @return il numero di appuntamenti
	 */
	public int size() {
		return appointments.size();
	}

	/**
	 * Aggiunge un appuntamento all'agenda.
	 * 
	 * @param date_time data e ora
	 * @param location  luogo
	 * @param person    nome della persona
	 * @param duration  durata in minuti
	 * @throws UnavailabilityException conflitto temporale con un altro appuntamento
	 */
	public void addAppointment(Calendar date_time, String location, String person, int duration)
			throws UnavailabilityException {
		if (AppointmentUtils.checkAvailability(date_time, location, person, duration, appointments)) {
			this.appointments.add(new Appointment(date_time, location, person, duration));
		} else {
			throw new UnavailabilityException(
					"Impossibile creare il nuovo appuntamento, è già presente un altro appuntamento nello stesso periodo.");
		}

	}

	/**
	 * Rimuove un appuntamento all'indice passato.
	 * 
	 * @param i indice
	 */
	public void removeAt(int i) {
		appointments.remove(i);
	}

	/**
	 * Rimuove tutte gli appuntamenti presenti nell'agenda.
	 */
	public void removeAll() {
		appointments.clear();
	}

	/**
	 * Modifica un appuntamento all' indice specificato.
	 * 
	 * @param date_time data e ora
	 * @param location  luogo
	 * @param person    nome della persona
	 * @param duration  durata in minuti
	 * @param index     indice
	 * @throws UnavailabilityException conflitto temporale con un altro appuntamento
	 */
	public void modifyAppointment(Calendar date_time, String location, String person, int duration, int index)
			throws UnavailabilityException {

		if (AppointmentUtils.checkAvailability(date_time, location, person, duration, appointments)) {
			appointments.remove(index);
			this.appointments.add(index, new Appointment(date_time, location, person, duration));
		} else {
			throw new UnavailabilityException(
					"Impossibile creare il nuovo appuntamento, è già presente un altro appuntamento nello stesso periodo.");
		}
	}

	/**
	 * Ordina gli appuntamenti in modo crescente o decrescente.
	 * 
	 * @param selectedMethod enum selezionato
	 * @return arraylist di appuntamenti
	 */
	public ArrayList<Appointment> sortAppointments(OrderMethodEnum selectedMethod) {
		ArrayList<Appointment> result = new ArrayList<Appointment>(appointments);
		selectedMethod.orderByDate(result);
		return result;
	}

	/**
	 * Cerca gli appuntamenti corrispondenti alla striga, che puo'essere ua data o
	 * un nome di persona.
	 * 
	 * @param selectedMethod enum selezionato
	 * @param search         stringa da cercare
	 * @return arraylist di appuntamenti
	 */
	public ArrayList<Appointment> findAppointments(FindByEnum selectedMethod, String search) {
		ArrayList<Appointment> result = null;
		try {
			result = selectedMethod.findBy(search, new ArrayList<Appointment>(appointments));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Dichiaro l'iteratore.
	 */
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

	/**
	 * Rimuove un appuntamento.
	 * 
	 * @param selectedAppointment appuntamento selezionato
	 */
	public void removeObj(Appointment selectedAppointment) {
		appointments.remove(selectedAppointment);
	}

	/**
	 * Aggiunge un appuntamento.
	 * 
	 * @param selectedAppointment appuntamento selezionato
	 * @throws UnavailabilityException conflitto temporale con un altro appuntamento
	 */
	public void addObj(Appointment selectedAppointment) throws UnavailabilityException {
		if (AppointmentUtils.checkAvailability(selectedAppointment.getDateTime(), selectedAppointment.getLocation(),
				selectedAppointment.getPerson(), selectedAppointment.getDuration(), appointments)) {
			appointments.add(selectedAppointment);
		} else {
			throw new UnavailabilityException(
					"Impossibile creare il nuovo appuntamento, è già presente un altro appuntamento nello stesso periodo.");
		}
	}

	/**
	 * Restituisce l'indice di un appuntamento.
	 * 
	 * @param selectedAppointment appuntamento selezionato
	 * @return l'indice dell'appuntamento
	 */
	public int getAppointmentIndex(Appointment selectedAppointment) {
		return appointments.indexOf(selectedAppointment);
	}

	/**
	 * Aggiunge un appuntamento alla arraylist di appuntamenti.
	 * 
	 * @param date_time data e ora
	 * @param location  luogo
	 * @param person    nome della persona
	 * @param duration  durata in minuti
	 * @param index     indice
	 * @throws ParseException          errore nella conversione della data
	 * @throws UnavailabilityException conflitto temporale con un altro appuntamento
	 */
	public void addAppointmentAtIndex(Calendar date_time, String location, String person, int duration, int index)
			throws ParseException, UnavailabilityException {
		if (AppointmentUtils.checkAvailability(date_time, location, person, duration, appointments)) {
			this.appointments.add(index, new Appointment(date_time, location, person, duration));
		} else {
			throw new UnavailabilityException(
					"Impossibile creare il nuovo appuntamento, è già presente un altro appuntamento nello stesso periodo.");
		}
	}

	/**
	 * Restituisce un appuntaemnto dato l'indice.
	 * 
	 * @param index indice
	 * @return ritorna un appuntamento all'indice specificato
	 */
	public Appointment getAppointmentAt(int index) {
		return appointments.get(index);
	}
}