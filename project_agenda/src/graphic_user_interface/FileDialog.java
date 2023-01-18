package graphic_user_interface;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import sourcecode.FileHandler;

/**
 * La classe Filedialog si occupa di salvare o aprire file di tipo agenda. Viene
 * implementata tramite il JFileChooser.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class FileDialog {

	/**
	 * Apre una finestra di esplora file per sscegliere dove e come salvare il file
	 * di tipo agenda genarato dall'oggetto della classe agenda.
	 * 
	 * @param toSave oggetto da salvare.
	 * @return false se non l'operazione non è andata a buon fine altrimenti ritorna
	 *         true.
	 */
	public static boolean FileSaveDialog(Object toSave) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Salva File");
		fileChooser.setFileFilter(new FileNameExtensionFilter("File Agenda", "agenda"));

		// Apertura della finestra di dialogo
		int result = fileChooser.showSaveDialog(null);

		// Verifica del risultato
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			FileHandler.exportToFile(file.getPath(), toSave);
			return true;
		} else if (result == JFileChooser.CANCEL_OPTION) {
			DialogMessage.information("Operazione", "Operazione annullata");
		}
		return false;
	}

	/**
	 * Apre una finestra di esplora risorse per selezionare il file di tipo agenda
	 * da importare. Ritorna un oggetto generico che contiene l'agenda dal file
	 * importato.
	 * 
	 * @return Object che contiene agenda
	 */
	public static Object FileOpenDialog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Apri File");
		fileChooser.setFileFilter(new FileNameExtensionFilter("File Agenda", "agenda"));
		Object temp = null;

		// Apertura della finestra di dialogo
		int result = fileChooser.showOpenDialog(null);

		// Verifica del risultato
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			temp = FileHandler.importFromFile(file.getPath());
		} else if (result == JFileChooser.CANCEL_OPTION) {
			DialogMessage.information("Operazione", "Operazione annullata");
		}
		return temp;
	}

}
