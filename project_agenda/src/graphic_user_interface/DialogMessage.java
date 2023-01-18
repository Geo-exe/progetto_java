package graphic_user_interface;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 * La classe DialogMessage fornisce una serie di metodi per la stampa di
 * messaggi tramite finestre.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class DialogMessage {

	/**
	 * Viene visulizzata una pagina di errore con messaggio e titolo modificabili
	 * 
	 * @param title   titolo della finestra
	 * @param message messaggio di errore
	 */
	public static void error(String title, String message) {
		JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, title);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

	/**
	 * Viene visulizzata una pagina con un messaggio e titolo modificabili
	 * 
	 * @param title   titolo della finestra
	 * @param message messaggio da visualizzare
	 */
	public static void information(String title, String message) {
		JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, title);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

	/**
	 * Viene visulizzata una pagina con un JScrollPane e titolo modificabili
	 * 
	 * @param title titolo della finestra
	 * @param temp  componenti da visualizzare
	 */
	public static void object(String title, JScrollPane temp) {
		JOptionPane optionPane = new JOptionPane(temp, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, title);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);

	}

}
