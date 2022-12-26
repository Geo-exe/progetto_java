package graphic_user_interface;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sourcecode.Appointment;

public class AppointmentBox extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel date;
	private JLabel time;
	private JLabel location;
	private JLabel person;
	private JLabel duration;
	public AppointmentBox(Appointment appointment) {
		super();
		setLayout(new GridLayout(4, 2));
		setBorder(BorderFactory.createTitledBorder("Appuntamento"));
		this.date = new JLabel(appointment.getStrDate());
		this.time = new JLabel(appointment.getTime().toString());
		this.location = new JLabel(appointment.getLocation());
		this.person = new JLabel(appointment.getPerson());
		this.duration = new JLabel(appointment.getDuration().toString());
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