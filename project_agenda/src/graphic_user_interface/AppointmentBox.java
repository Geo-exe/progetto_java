package graphic_user_interface;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sourcecode.Appointment;

/**
 * JPanel e' un contenitore per oggetti dichiarato nella libreria javax.swing
 * AppointmentBox a sua volta estende JPanel per implementare alcune proprieta' e
 * alcuni metodi. AppointemntBox popola un JPanel con tutte le informazioni
 * relative ad un singolo appuntamento.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */

public class AppointmentBox extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * Label contenete la data.
	 */
	private JLabel date;
	/**
	 * Label contenete l'ora.
	 */
	private JLabel time;
	/**
	 * Label contenete il luogo.
	 */
	private JLabel location;
	/**
	 * Label contenete il nome della persona.
	 */
	private JLabel person;
	/**
	 * Label contenete la durata in minuti.
	 */
	private JLabel duration;

	/**
	 * Costruttore della classe. Dato un appuntamento vengono stampate tutte le sue
	 * informazioni in modo formattato all'interno di un JPanel.
	 * 
	 * @param appointment da stampare
	 */
	public AppointmentBox(Appointment appointment) {
		super();
		setLayout(new GridLayout(5, 2));
		setBorder(BorderFactory.createTitledBorder("Appuntamento"));
		this.date = new JLabel(appointment.getStrDate());
		this.time = new JLabel(appointment.getTime());
		this.location = new JLabel(appointment.getLocation());
		this.person = new JLabel(appointment.getPerson());
		this.duration = new JLabel(Integer.toString(appointment.getDuration()));
		add(new JLabel("Data:"));
		add(this.date);
		add(new JLabel("Ora:"));
		add(this.time);
		add(new JLabel("Durata(min.):"));
		add(this.duration);
		add(new JLabel("Luogo:"));
		add(this.location);
		add(new JLabel("Persona:"));
		add(this.person);

		setPreferredSize(new Dimension(300, 150));
		setVisible(true);
	}
}