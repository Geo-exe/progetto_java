package graphic_user_interface;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sourcecode.Appointment;
import sourcecode.FindByEnum;

/**
 * ActionWindow e' la classe astratta che predispone una finestra per un form. La
 * classe FindAppointmentWindow estende ActionWindow implementando i componenti
 * e le funzioni necessarie per trovare uno o più appuntamenti.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class FindAppointmentWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;
	/**
	 * Casella di testo che contiene la stringa da cercare.
	 */
	private JTextField textBox;
	/**
	 * Label che specifica che insirire una data o un nome.
	 */
	private JLabel label;
	/**
	 * Lista per selezionare se insirire una data o un nome.
	 */
	private JComboBox<FindByEnum> comboBox;

	/**
	 * Costruttore della classe. Passa al super costruttore title e cambia il
	 * contenuto del tasto confirm.
	 * 
	 * @param title titolo della finestra
	 * @throws Exception un'altra finestra gia' aperta
	 */
	public FindAppointmentWindow(String title) throws Exception {
		super(title);
		confirm.setText("Trova");
	}

	/**
	 * Esegue le operazioni necessarie per trovare uno o più appuntamenti
	 * corrispondenti alle informazioni fornite e stamparli a schermo, eseguondo
	 * apportuni controlli.
	 */
	public void confirmAction() {
		ArrayList<Appointment> result = new ArrayList<Appointment>();
		JPanel temp = new JPanel();
		JScrollPane scrollBar = new JScrollPane(temp);
		scrollBar.setBorder(null);
		// incrementa la velocità della scrollbar
		scrollBar.getVerticalScrollBar().setUnitIncrement(16);
		try {
			FindByEnum selectedMethod = FindByEnum.valueOf(comboBox.getSelectedItem().toString());
			try {
				result = selectedMethod.findBy(textBox.getText(), agendas.get(agendasList.getSelectedIndex()));
				if (!result.isEmpty()) {
					scrollBar.setPreferredSize(new Dimension(350, 250));
					temp.setLayout(new GridLayout(result.size(), 1));
					for (Appointment a : result) {
						temp.add(new AppointmentBox(a));
					}

					setVisible(false);
					dispose();
					DialogMessage.object("Appuntamento", scrollBar);

				} else {
					DialogMessage.error("Non trovato", "Nessun appuntamento trovato!");
				}

			} catch (ParseException e) {
				e.printStackTrace();
				DialogMessage.error("Errore di inserimento", "Data inserita non valida!");
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			DialogMessage.error("Errore di inserimento", "Errore con il dato selezionato nella ComboBox!");
		}
	}

	/**
	 * Inizializza i campi del form necessari ad inserire le informazioni con cui
	 * cercare uno o più appuntamenti.
	 * 
	 * @return JPanel contente la GUI del form.
	 */
	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(2, 2, 5, 5));
		tempPanel.setBorder(new EmptyBorder(35, 25, 25, 35));
		tempPanel.add(new JLabel("Trova per:"));

		comboBox = new JComboBox<>();
		for (FindByEnum value : FindByEnum.values()) {
			comboBox.addItem(value);
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				label.setText(comboBox.getItemAt(comboBox.getSelectedIndex()).toString() + " :");
			}
		});

		tempPanel.add(comboBox);

		label = new JLabel(comboBox.getItemAt(comboBox.getSelectedIndex()).toString() + " :");
		tempPanel.add(label);

		textBox = new JTextField();
		tempPanel.add(textBox);
		return tempPanel;
	}

}
