package sourcecode;

import java.text.ParseException;
import java.util.ArrayList;
/**
 * La classe astratta interface viene usata per implementare la classe
 * enum(FindByInterface).
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public interface FindByInterface {
	/**
	 * Cerca uno o piu' appuntamenti conteneti la stringa passata.
	 * @param searchingParamether stringa da cercare
	 * @param agenda agenda in cui cercare
	 * @return uno o piu' appuntamenti
	 * @throws ParseException errore nella conversione
	 */
	public ArrayList<Appointment> findBy(String searchingParamether,  ArrayList<Appointment> appointments) throws ParseException;
}
