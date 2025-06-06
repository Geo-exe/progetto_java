package graphic_user_interface;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import sourcecode.Appointment;

/**
 * ActionWindow e' la classe astratta che predispone una finestra per un form,
 * la classe SelectAppointmentToEditWindow estende ActionWindow implementando i
 * componenti e le funzioni necessarie per selezionare un appuntamento da
 * modificare, aprendo poi una finestra di modifica richiamndo l'oggetto
 * EditAppointmentWindow.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class SelectAppointmentToEditWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;
	/**
	 * Lista di appuntamenti da cui selezionare quello da modificare.
	 */
	private JRadioButton[] radioButton;

	/**
	 * Costruttore della classe. Passa al super costruttore title e cambia il
	 * contenuto del tasto confirm.
	 * 
	 * @param title titolo della finestra
	 * @throws Exception un'altra finestra gia' aperta
	 */
	public SelectAppointmentToEditWindow(String title) throws Exception {
		super(title);
		confirm.setText("Modifica");
		setBounds(0, 0, 500, 400);
		setLocationRelativeTo(null);
	}

	/**
	 * Esegue le operazioni necessarie a modificare un appuntamento selezionato,
	 * eseguondo apportuni controlli.
	 */
	public void confirmAction() {
		try {

			int indexSelected = -1;
			if (radioButton != null) {

				for (int j = 0; j < radioButton.length; j++) {
					if (radioButton[j].isSelected()) {
						indexSelected = j;
						break;
					}
				}

				if (indexSelected >= 0) {
					EditAppointmentWindow editWindow = new EditAppointmentWindow(this.getTitle());
					editWindow.passFields(indexSelected);
					setVisible(false);
					dispose();
				} else {
					DialogMessage.error("Selezione Errata", "Nessun appuntamento selezionato!");
				}

			} else {
				setVisible(false);
				dispose();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inizializza i campi del form necessari a selezionare un appuntamento da
	 * modificare.
	 * 
	 * @return JPanel contente la GUI del form
	 */
	protected JPanel loadFields() {
		JPanel temp = new JPanel();
		JPanel tempPanel = new JPanel();
		JScrollPane scrollBar = new JScrollPane(tempPanel);
		scrollBar.setBorder(null);
		scrollBar.getVerticalScrollBar().setUnitIncrement(16);
		ArrayList<Appointment> result;
		GridBagConstraints gbc = new GridBagConstraints();

		result = agendas.get(agendasList.getSelectedIndex()).getAppointments();

		if (!result.isEmpty()) {
			scrollBar.setPreferredSize(new Dimension(400, 200));
			tempPanel.setLayout(new GridBagLayout());
			tempPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
			gbc.gridy = 0;
			temp.add(new JLabel("Seleziona un appuntamento:"), gbc);

			radioButton = new JRadioButton[result.size()];

			for (int i = 0; i < result.size(); i++) {
				radioButton[i] = new JRadioButton();
				// Listener che rende selezionabile un solo elemento alla volta
				radioButton[i].addActionListener(e -> {

					for (int j = 0; j < radioButton.length; j++) {
						if (!radioButton[j].equals(e.getSource())) {
							radioButton[j].setSelected(false);
						}
					}

				});

				gbc.gridy = i + 2;
				tempPanel.add(radioButton[i], gbc);
				tempPanel.add(new AppointmentBox(result.get(i)), gbc);

			}

		} else {
			tempPanel.add(new JLabel("Nessun appuntamento!"));
		}

		temp.add(scrollBar);
		return temp;
	}

}
