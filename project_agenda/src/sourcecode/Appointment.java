package sourcecode;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * La classe Appointment implementa la classe Serializable. E' composta da
 * stringa contente il luogo, una strina con il nome della persona, un intero
 * con la durata in minuti e un Calendar che contiene sia la data che l'ora di
 * inizio. Sono stati sviluppati i vari metodi necessari e il costruttore.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Formattatore da stringa a data.
	 */
	SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * Formattatore da stringa ad ora.
	 */
	SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
	/**
	 * Il nome del luogo.
	 */
	private String location;
	/**
	 * La data e l'ora.
	 */
	private Calendar date_time;
	/**
	 * La urata in minuti.
	 */
	private int duration;
	/**
	 * Il nome della persona.
	 */
	private String person;

	/**
	 * Costruttore della classe.
	 * 
	 * @param date_time data e ora
	 * @param location  luogo
	 * @param person    nome della persona
	 * @param duration  durata in minuti
	 */
	public Appointment(Calendar date_time, String location, String person, int duration) {
		if (duration < 1 || duration > 1440) {
			throw new IllegalArgumentException(
					"La durata dell'appuntamento non può essere minore di 1 minuto o maggiore di 24 ore (1440 minuti).");
		}
		if (location.equals("")) {
			throw new IllegalArgumentException("Il luogo non può essere vuoto");
		}
		if (person.equals("")) {
			throw new IllegalArgumentException("Il campo persona non può essere vuoto");
		}
		this.date_time = date_time;
		this.duration = duration;
		this.location = location;
		this.person = person;

	}

	/**
	 * Restituisce il luogo dell'appuntamento.
	 * @return il luogo.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Restituisce la data e l'ora sottofroma di Calendar
	 * @return la data e l'ora 
	 */
	public Calendar getDateTime() {
		return date_time;
	}

	/**
	 * Restituisce il nome della persona dell'appuntamento sottofroma di stringa.
	 * @return il nome
	 */
	public String getPerson() {
		return person;
	}

	/**
	 * Restituisce la durata dell'appuntamento in minuti sottofroma di int.
	 * @return la durata in minuti.
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Restituisce solo l'ora dell'appuntamento sottofroma di stringa.
	 * @return solo l'ora in stringa
	 */
	public String getTime() {
		return format2.format(date_time.getTime());
	}

	/**
	 * Restituisce la data dell'appuntamento sottofroma di stringa.
	 * @return la data in stringa
	 */
	public String getStrDate() {

		return format1.format(date_time.getTime());
	}

	/**
	 * Restituisce la fine dell'appuntamento sottofroma di Calendar.
	 * 
	 * @return la fine
	 */
	public Calendar getEndDate_time() {
		Calendar result = (Calendar) date_time.clone();
		result.add(Calendar.MINUTE, duration);
		return result;
	}

}