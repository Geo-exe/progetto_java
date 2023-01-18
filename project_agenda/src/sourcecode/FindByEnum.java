package sourcecode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * La classe speciale enum, nella quale si trovano i due metodi per la ricerca.
 * Entrambi ritornano un arraylist di appuntamenti. Il primo filtra per data
 * mentre il secondo seleziona solo gli appuntamenti con il nome corrispondente.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public enum FindByEnum implements FindByInterface {
	
	/**
	 * Ricerca di uno o piu' appuntamenti per data
	 */
	DATA {
		public ArrayList<Appointment> findBy(String searchingParameter, ArrayList<Appointment> appointments) throws ParseException {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
			ArrayList<Appointment> result = new ArrayList<Appointment>();
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar input = Calendar.getInstance();
			input.setTime(format.parse(searchingParameter));
			Calendar temp;
			for (Appointment appointment : appointments) {
				temp = appointment.getDateTime();
				if (sdf.format(temp.getTime()).equals(sdf.format(input.getTime()))) {
					result.add(appointment);
				}
			}

			return result;
		}
	},
	/**
	 * Ricerca di uno o piu' appuntamenti per nome
	 */
	NOME {
		public ArrayList<Appointment> findBy(String searchingParamether,  ArrayList<Appointment> appointments) {
			ArrayList<Appointment> result = new ArrayList<Appointment>();

			for (Appointment appointment : appointments) {
				if (appointment.getPerson().equals(searchingParamether)) {
					result.add(appointment);
				}
			}

			return result;
		}
	};
}
