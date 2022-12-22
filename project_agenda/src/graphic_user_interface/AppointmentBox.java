package graphic_user_interface;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AppointmentBox extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel date;
	private JLabel time;
	private JLabel location;
	private JLabel person;
	public AppointmentBox(String date, String time, String location, String person) {
		super();
		setLayout(new GridLayout(4, 2));
		
		this.date = new JLabel(date);
		this.time = new JLabel(time);
		this.location = new JLabel(location);
		this.person = new JLabel(person);
		
		add(new JLabel("Data:"));
		add(this.date);
		add(new JLabel("Ora:"));
		add(this.time);
		add(new JLabel("Luogo:"));
		add(this.location);
		add(new JLabel("Persona:"));
		add(this.person);

		setPreferredSize(new Dimension(300, 150));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void modifyAppointment(String date, String time, String location, String person) {
		this.date.setText(date);
		this.time.setText(time);
		this.location.setText(location);
		this.person.setText(person);
	}
}