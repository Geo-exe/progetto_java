package graphic_user_interface;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sourcecode.Agenda;
import utils.AgendaUtils;

/**
 * ActionWindow e' la classe astratta che predispone una finestra per un form.
 * La classe AddAgendaWindows estende ActionWindow implementando i componenti e
 * le funzioni necessarie per inserire una nuova agenda.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 * 
 */
public class AddAgendaWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;
	/**
	 * Casella di testo del nome dell'agenda.
	 */
	private JTextField nameBox;

	/**
	 * Costruttore della classe. Passa al super costruttore title e cambia il
	 * contenuto del tasto confirm.
	 * 
	 * @param title titolo della finestra
	 * @throws Exception un'altra finestra gia' aperta
	 */

	public AddAgendaWindow(String title) throws Exception {
		super(title);
		confirm.setText("Aggiungi");
	}

	/**
	 * Esegue le operazioni necessarie ad inserire la nuova agenda, eseguondo
	 * apportuni controlli.
	 */
	public void confirmAction() {
		// viene utilizzato il DefaultListModel per cambiare il contenuto della JList
		DefaultListModel<String> model = (DefaultListModel<String>) agendasList.getModel();
		try {
			if (!AgendaUtils.agendaExist(agendas, nameBox.getText())) {
				agendas.add(new Agenda(nameBox.getText()));
				model.addElement(nameBox.getText());
				setVisible(false);
				dispose();
				DialogMessage.information("Successo", "Agenda aggiunta!");
			} else {
				DialogMessage.error("Impossibile", "Esiste già un'agenda con questo nome!");
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			DialogMessage.error("Eccezione Generata", "Carattere non valido!");
		}

	}

	/**
	 * Inizializza i campi del form necessari ad inserire una nuova agenda.
	 * 
	 * @return JPanel contente la GUI del form
	 */
	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(1, 2, 5, 5));
		tempPanel.setBorder(new EmptyBorder(50, 20, 50, 20));
		tempPanel.add(new JLabel("Nome Agenda:"));
		nameBox = new JTextField();
		tempPanel.add(nameBox);
		return tempPanel;
	}

}
