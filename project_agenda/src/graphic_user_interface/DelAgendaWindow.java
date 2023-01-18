package graphic_user_interface;

import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sourcecode.Agenda;
import utils.AgendaUtils;

/**
 * ActionWindow e' la classe astratta che predispone una finestra per un form.
 * La classe DelAgendaWindow estende ActionWindow implementando i componenti e
 * le funzioni necessarie per eliminare un'agenda.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class DelAgendaWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;
	/**
	 * Lista di agenda da cui selezionare quella da eliminare.
	 */
	private JComboBox<String> comboBox;

	/**
	 * Costruttore della classe. Passa al super costruttore title e cambia il
	 * contenuto del tasto confirm.
	 * 
	 * @param title della finestra
	 * @throws Exception un'altra finestra gia' aperta
	 */
	public DelAgendaWindow(String title) throws Exception {
		super(title);
		confirm.setText("Elimina");
	}

	/**
	 * Esegue le operazioni necessarie ad elimare un'agenda, eseguondo apportuni
	 * controlli.
	 */
	public void confirmAction() {
		if (comboBox != null) {
			for (Iterator<Agenda> iterator = agendas.iterator(); iterator.hasNext();) {
				Agenda agenda = iterator.next();
				if (agenda.getName().equals(comboBox.getSelectedItem())) {
					iterator.remove();
				}
			}
			// viene utilizzato il DefaultListModel per cambiare il contenuto della JList
			DefaultListModel<String> model = (DefaultListModel<String>) agendasList.getModel();
			model.removeElement(comboBox.getSelectedItem());

			setVisible(false);
			dispose();
			DialogMessage.information("Successo", "Agenda " + comboBox.getSelectedItem() + " eliminata!");
		} else {
			setVisible(false);
			dispose();
		}

	}

	/**
	 * Inizializza i campi del form necessari ad elimnare un'agenda.
	 * 
	 * @return JPanel contente la GUI del form
	 */
	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();

		if (agendas.size() != 0) {
			tempPanel.setLayout(new GridLayout(2, 2, 5, 5));
			tempPanel.setBorder(new EmptyBorder(40, 30, 30, 40));
			tempPanel.add(new JLabel("Seleziona un' agenda da :"));

			comboBox = new JComboBox<String>(AgendaUtils.agendaListToArray(agendas));

			tempPanel.add(comboBox);
		} else {
			tempPanel.add(new JLabel("Nessuna Agenda!"));
		}

		return tempPanel;
	}

}
