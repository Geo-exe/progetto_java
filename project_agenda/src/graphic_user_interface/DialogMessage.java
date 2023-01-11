package graphic_user_interface;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class DialogMessage {

	public static void error(String title, String message) {
		JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, title);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

	public static void information(String title, String message) {
		JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, title);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

	public static void object(String title, JScrollPane temp) {
		JOptionPane optionPane = new JOptionPane(temp, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane.createDialog(null, title);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);

	}

}
