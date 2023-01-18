package graphic_user_interface;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.AgendaUtils;

/**
 * ActionWindow e' la classe astratta che predispone una finestra per un form.
 * La classe ExportWindow estende ActionWindow implementando i componenti e le
 * funzioni necessarie per esportare un'agenda.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class ExportWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;
	/**
	 * Lista di agende da cui selezionare quella da esportare.
	 */
	private JComboBox<String> comboBox;

	/**
	 * Costruttore della classe. Passa al super costruttore title e cambia il
	 * contenuto del tasto confirm.
	 * 
	 * @param title titolo della finestra
	 * @throws Exception un'altra finestra gia' aperta
	 */
	public ExportWindow(String title) throws Exception {
		super(title);
		confirm.setText("Esporta");
	}

	/**
	 * Esegue le operazioni necessarie ad esportare l'agenda selezionata, eseguondo
	 * apportuni controlli.
	 */
	@Override
	public void confirmAction() {
		setVisible(false);
		dispose();

		if (comboBox != null) {
			if (FileDialog.FileSaveDialog(agendas.get(comboBox.getSelectedIndex()))) {

			}
		}

	}

	/**
	 * Inizializza i campi del form necessari per selezionare un'agenda da
	 * esportare.
	 * 
	 * @return JPanel contente la GUI del form.
	 */
	@Override
	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		if (agendas.size() != 0) {
			tempPanel.setLayout(new GridLayout(2, 2, 5, 5));
			tempPanel.setBorder(new EmptyBorder(40, 30, 30, 40));
			tempPanel.add(new JLabel("Seleziona un'agenda da :"));

			comboBox = new JComboBox<String>(AgendaUtils.agendaListToArray(agendas));

			tempPanel.add(comboBox);
		} else {
			tempPanel.add(new JLabel("Nessuna Agenda!"));
		}

		return tempPanel;
	}

}
