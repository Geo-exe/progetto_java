package graphic_user_interface;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import sourcecode.Appointment;
import sourcecode.OrderMethodEnum;

/**
 * ActionWindow è la classe astratta che predispone una finestra per un form. La
 * classe OrderByDateWindow estende ActionWindow implementando i componenti e le
 * funzioni necessarie per visualizzare gli appuntamenti ordinati per data
 * crescente o decrescente.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class OrderByDateWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;

	private JComboBox<OrderMethodEnum> comboBox;

	/**
	 * Costruttore della classe. Passa al super costruttore title e cambia il
	 * contenuto del tasto confirm.
	 * 
	 * @param title
	 * @throws Exception
	 */
	public OrderByDateWindow(String title) throws Exception {
		super(title);
		confirm.setText("Conferma");
	}

	/**
	 * Esegue le operazioni necessarie ad ordinare gli appuntamenti e stamparli a
	 * schermo, eseguondo apportuni controlli.
	 */
	public void confirmAction() {
		ArrayList<Appointment> result = new ArrayList<Appointment>();
		JPanel temp = new JPanel();
		JScrollPane scrollBar = new JScrollPane(temp);
		scrollBar.setBorder(null);
		// incrementa la velocità della scrollbar
		scrollBar.getVerticalScrollBar().setUnitIncrement(16);

		result = agendas.get(agendasList.getSelectedIndex())
				.sortAppointmets(OrderMethodEnum.valueOf(comboBox.getSelectedItem().toString()));

		temp.setLayout(new GridLayout(result.size(), 1));

		if (!result.isEmpty()) {
			scrollBar.setPreferredSize(new Dimension(350, 250));
			for (Appointment a : result) {
				temp.add(new AppointmentBox(a));

			}
		} else {
			temp.add(new JLabel("Nessun appuntamento!"));
		}

		setVisible(false);
		dispose();
		DialogMessage.object("Appuntamenti", scrollBar);

	}

	/**
	 * Inizializza i campi del form necessari a selezionare un ordinamento crescente
	 * o decrescente.
	 * 
	 * @return JPanel contente la GUI del form.
	 */
	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(2, 2, 5, 5));
		tempPanel.setBorder(new EmptyBorder(40, 30, 30, 40));
		tempPanel.add(new JLabel("Seleziona ordine da visualizzare:"));

		comboBox = new JComboBox<>();
		for (OrderMethodEnum value : OrderMethodEnum.values()) {
			comboBox.addItem(value);
		}

		tempPanel.add(comboBox);

		return tempPanel;
	}

}
