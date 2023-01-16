package sourcecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * La classe speciale enum, nella quale si trovano i due metodi per
 * l'ordinamneto. Uno ritorna una lista ordinata in modo crescente mentre
 * l'altro in modo decrescente.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public enum OrderMethodEnum implements OrderMethodInterface {
	/**
	 * Ordina gli appuntamenti in modo crescente.
	 */
	CRESCENTE {
		public void orderByDate(ArrayList<Appointment> appointments) {
			Collections.sort(appointments, new Comparator<Appointment>() {
				@Override
				public int compare(Appointment a1, Appointment a2) {
					return a1.getDateTime().compareTo(a2.getDateTime());
				}
			});
		}
	},
	/**
	 * Ordina gli appuntamenti in modo decrescente.
	 */
	DECRESCENTE {
		public void orderByDate(ArrayList<Appointment> appointments) {
			Collections.sort(appointments, new Comparator<Appointment>() {
				@Override
				public int compare(Appointment a1, Appointment a2) {
					return a2.getDateTime().compareTo(a1.getDateTime());
				}
			});
		}
	};
}