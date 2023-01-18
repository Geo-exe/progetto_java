package sourcecode;

import java.util.ArrayList;

/**
 * La classe astratta interface viene usata per implementare la classe
 * enum(OrderMethodEnum).
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public interface OrderMethodInterface {
	/**
	 * Ordina gli appuntamenti.
	 * 
	 * @param appointments arraylist di appuntamenti
	 */
	public void orderByDate(ArrayList<Appointment> appointments);
}
