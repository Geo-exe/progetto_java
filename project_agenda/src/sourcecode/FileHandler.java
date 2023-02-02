package sourcecode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe Serializable viene estesa implementando due metodi. Uno per la
 * scrittura di oggetti su file ed un altro per la lettura di uno o più oggetti
 * da un file.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class FileHandler implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Crea un file in cui scrive l'oggetto Agenda.
	 * 
	 * @param fileName nome del file
	 * @param toExport oggetto da esportare
	 */
	public static void exportToFile(String fileName, Object toExport) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(toExport);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Legge da un file dato il nome l'oggetto Agenda al suo interno, se ci sono più
	 * oggetti Agenda ritorna un arraylist di Agenda
	 * 
	 * @param fileName nome del file
	 * @return Object oggetto da importare
	 */
	public static Object importFromFile(String fileName) {
		Object rawImported = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			rawImported = in.readObject();
			in.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (rawImported instanceof Agenda) {
			return (Agenda) rawImported;
		} else if (rawImported instanceof ArrayList<?>) {
			ArrayList<?> temps = (ArrayList<?>) rawImported;
			boolean isInstance = true;
			int count = 0;
			for (@SuppressWarnings("unused")
			Object temp : temps) {
				if (!(temps.get(count) instanceof Agenda)) {
					isInstance = false;
					break;
				}
			}
			if (isInstance) {
				@SuppressWarnings("unchecked")
				ArrayList<Agenda> tempAgendas = (ArrayList<Agenda>) rawImported;
				return tempAgendas;
			}
		}

		return null;
	}

}
