package graphic_user_interface;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import sourcecode.FileHandler;

public class FileDialog {

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

	public static Object FileOpenDialog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Salva File");
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
