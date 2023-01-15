package graphic_user_interface;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import sourcecode.Appointment;

/**
 * ActionWindow è la classe astratta che predispone una finestra per un form. La
 * classe DelAppointmentWindow estende ActionWindow implementando i componenti e
 * le funzioni necessarie per eliminare appuntamento.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class DelAppointmentWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;
	private JCheckBox[] checkBox;
	private JCheckBox all;

	/**
	 * Costruttore della classe. Passa al super costruttore title e cambia il
	 * contenuto del tasto confirm.
	 * 
	 * @param title
	 * @throws Exception
	 */
	public DelAppointmentWindow(String title) throws Exception {
		super(title);
		confirm.setText("Elimina");
		setBounds(0, 0, 500, 400);
		setLocationRelativeTo(null);
	}

	/**
	 * Esegue le operazioni necessarie ad eliminare un appuntamento, eseguondo
	 * apportuni controlli.
	 */
	public void confirmAction() {
		if (checkBox != null) {
			if (all.isSelected()) {

				agendas.get(agendasList.getSelectedIndex()).removeAll();
			} else {
				int del = 0;
				for (int i = 0; i < checkBox.length; i++) {
					if (checkBox[i].isSelected()) {
						agendas.get(agendasList.getSelectedIndex()).removeAt(i - del);
						del++;
					}
				}
			}

			int select = agendasList.getSelectedIndex();
			agendasList.clearSelection();
			agendasList.setSelectedIndex(select);

			setVisible(false);
			dispose();
			DialogMessage.information("Successo", "Cancellazione effettuata!");
		} else {
			setVisible(false);
			dispose();
		}

	}

	/**
	 * Inizializza i campi del form necessari ad eliminare appuntamento.
	 * 
	 * @return JPanel contente la GUI del form.
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
			temp.add(new JLabel("Seleziona gli appuntamenti:"), gbc);

			all = new JCheckBox("Seleziona tutti");
			gbc.gridy = 1;
			temp.add(all, gbc);
			// Listener che seleziona/deseleziona tutti gli elementi
			all.addActionListener(e -> {

				if (all.isSelected()) {
					for (int i = 0; i < checkBox.length; i++) {
						checkBox[i].setSelected(true);

					}
				} else if (!all.isSelected()) {
					for (int i = 0; i < checkBox.length; i++) {
						checkBox[i].setSelected(false);

					}
				}
			});

			checkBox = new JCheckBox[result.size()];

			for (int i = 0; i < result.size(); i++) {
				checkBox[i] = new JCheckBox();

				gbc.gridy = i + 2;

				tempPanel.add(checkBox[i], gbc);
				tempPanel.add(new AppointmentBox(result.get(i)), gbc);

			}

		} else {
			tempPanel.add(new JLabel("Nessun appuntamento!"));
		}

		temp.add(scrollBar);
		return temp;
	}

}
