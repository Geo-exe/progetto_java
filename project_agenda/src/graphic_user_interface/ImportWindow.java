package graphic_user_interface;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sourcecode.Agenda;
import utils.AgendaUtils;

/**
 * ActionWindow e' la classe astratta che predispone una finestra per un form.
 * La classe ImportWindow estende ActionWindow implementando i componenti e le
 * funzioni necessarie per importare una o più agende.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class ImportWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;
	/**
	 * Lista per selezionare se importare una o piu' agende.
	 */
	private JComboBox<String> comboBox;

	/**
	 * Costruttore della classe. Passa al super costruttore title e cambia il
	 * contenuto del tasto confirm.
	 * 
	 * @param title titolo della finestra
	 * @throws Exception un'altra finestra gia' aperta
	 */
	public ImportWindow(String title) throws Exception {
		super(title);
		confirm.setText("Importa");
	}

	/**
	 * Esegue le operazioni necessarie ad importare da un file una o più agende,
	 * eseguondo apportuni controlli.
	 */
	public void confirmAction() {
		// viene utilizzato il DefaultListModel per cambiare il contenuto della JList
		DefaultListModel<String> newModel = new DefaultListModel<String>();
		String txt = "Agenda Aggiunta!";
		boolean toEdit = true;
		setVisible(false);
		dispose();

		// Viene importata una sola agenda
		if (comboBox.getSelectedItem() == "1") {

			Agenda temp = (Agenda) FileDialog.FileOpenDialog();
			// Controllo se esiste già un'agenda con quel nome
			if (!AgendaUtils.agendaExist(agendas, temp.getName()))
				agendas.add(temp);
			else {
				// Se il nome è già in uso si apre un form per cambiarlo
				String newName = "";
				do {
					newName = JOptionPane
							.showInputDialog("Assegnare un nuovo nome all'agenda <" + temp.getName() + "> :");
				} while (AgendaUtils.agendaExist(agendas, newName) && newName == "");
				if (newName != null) {
					agendas.add(new Agenda(newName, temp.getAppointments()));
				} else
					toEdit = false;

			}
		} else {
			// Vengono imporatate più agende
			@SuppressWarnings("unchecked")
			ArrayList<Agenda> temps = (ArrayList<Agenda>) FileDialog.FileOpenDialog();
			for (Agenda temp : temps) {
				// Controllo se esiste già un'agenda con quel nome
				if (!AgendaUtils.agendaExist(agendas, temp.getName())) {
					agendas.add(temp);
				} else {
					// Se il nome è già in uso si apre un form per cambiarlo
					String newName = "";
					do {
						newName = JOptionPane
								.showInputDialog("Assegnare un nuovo nome all'agenda <" + temp.getName() + "> :");
					} while (AgendaUtils.agendaExist(agendas, newName) && newName == "");
					if (newName != null) {
						agendas.add(new Agenda(newName, temp.getAppointments()));
					} else {
						DialogMessage.error("Skip", "Agenda saltata.");
					}
				}
			}
			txt = "Agende Aggiunte!";
		}
		if (toEdit) {
			for (String name : AgendaUtils.agendaListToArray(agendas)) {
				newModel.addElement(name);
			}

			DialogMessage.information("Successo", txt);

			agendasList.setModel(newModel);
			agendasList.revalidate();
		} else {
			DialogMessage.error("No Agenda", "Nessun Agenda Aggiunta.");
		}

	}

	/**
	 * Inizializza i campi del form necessari a selezionare se si vuole importare
	 * una o più agende.
	 * 
	 * @return JPanel contente la GUI del form
	 */
	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(2, 2, 5, 5));
		tempPanel.setBorder(new EmptyBorder(40, 30, 30, 40));
		tempPanel.add(new JLabel("Seleziona quante agende si vogliono importare: "));

		comboBox = new JComboBox<String>(new String[] { "1", "2+" });

		tempPanel.add(comboBox);
		return tempPanel;
	}

}
