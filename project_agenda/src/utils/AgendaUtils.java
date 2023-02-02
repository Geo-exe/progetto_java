package utils;

import java.util.ArrayList;

import sourcecode.Agenda;

/**
 * La classe AgendaUtils contiene dei metodi utili a fare controlli nella classe
 * Agenda.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class AgendaUtils {

	/**
	 * Dato un ArrayList di oggetti Agenda in un array di stringhe contenete i nomi
	 * di tutte le agende.
	 * 
	 * @param list lista di agende
	 * @return String[] array di stringhe
	 */
	public static String[] agendaListToArray(ArrayList<Agenda> list) {
		String[] arrayString = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arrayString[i] = list.get(i).getName();
		}
		return arrayString;
	}

	/**
	 * Dato un ArrayList di oggetti Agenda in un ArrayList di stringhe contenete i
	 * nomi di tutte le agende.
	 * 
	 * @param list lista di agende
	 * @return ArrayList di stringhe
	 */
	public static ArrayList<String> getNames(ArrayList<Agenda> list) {
		ArrayList<String> arrayString = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			arrayString.add(list.get(i).getName());
		}
		return arrayString;
	}

	/**
	 * Dato un nome di un'agenda ed un ArrayList di agende controlla se il nome è
	 * gia presente
	 * 
	 * @param list lista di agende
	 * @param name String del nome
	 * @return true se il nome è contenuto altrimenti ritorna false
	 */
	public static boolean agendaExist(ArrayList<Agenda> list, String name) {
		return getNames(list).contains(name);
	}
}