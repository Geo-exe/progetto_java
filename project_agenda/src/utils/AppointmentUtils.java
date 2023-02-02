package utils;

import java.util.ArrayList;
import java.util.Calendar;

import sourcecode.Appointment;

/**
 * La classe AppointmentUtils contiene dei metodi utili a fare controlli nella
 * classe Appointments.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class AppointmentUtils {

	/**
	 * Dato un arraylist di oggetti appuntamento e tutti i dati relativi ad un solo
	 * appuntamento, verfica che quest'ultimo non occupi uno spazio di tempo già
	 * utilizzato.
	 * 
	 * @param date_time calendar della data e dll'ora
	 * @param location  string del luogo
	 * @param person    string del nome
	 * @param duration  int durata in minuti
	 * @param list      ArrayList di oggetti appuntamento
	 * @return true se non ci sono altri appuntamenti nello stesso momento
	 *         altrimenti ritorna false.
	 */
	public static boolean checkAvailability(Calendar date_time, String location, String person, int duration,
			ArrayList<Appointment> list) {
		Calendar end = (Calendar) date_time.clone();
		end.add(Calendar.MINUTE, duration);
		int count = 0;

		for (Appointment a : list) {
			/*
			 * Controlla che l'appuntamento finisca(data inizio + durata) prima o nello
			 * stesso momento di quelli nell'arraylist. Controlla che l'appuntamento inizi
			 * dopo o nello stesso momento della fine(data inizio + durata)di quelli
			 * nell'arraylist.
			 */
			if (end.before(a.getDateTime()) || end.equals(a.getDateTime()) || date_time.after(a.getEndDateTime())
					|| date_time.equals(a.getEndDateTime())) {
				count++;
			}
		}

		if (count == list.size()) {
			return true;
		} else {
			return false;
		}

	}
}
