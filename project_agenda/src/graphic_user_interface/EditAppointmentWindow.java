package graphic_user_interface;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JList;
import javax.swing.JOptionPane;

import sourcecode.Agenda;
import sourcecode.Appointment;

public class EditAppointmentWindow extends AddAppointmentWindow {

	private static final long serialVersionUID = 1L;

	private Appointment selectedAppointment;
	
	public EditAppointmentWindow(String title) throws Exception {
		super(title);
		confirm.setText("Modifica");
	}
	
	public void passFields(int index) {
		Appointment appointment = agendas.get(agendasList.getSelectedIndex()).getAppointmentAt(index);
		this.dateBox.setText(appointment.getStrDate());
		this.timeBox.setText(appointment.getTime());
		this.locationBox.setText(appointment.getLocation());
		this.personBox.setText(appointment.getPerson());
		this.durationBox.setText(Integer.toString(appointment.getDuration()));
		this.selectedAppointment = appointment;
	}
	
	public void confirmAction() {
		int index = agendas.get(agendasList.getSelectedIndex()).getAppointmentIndex(selectedAppointment);
		agendas.get(agendasList.getSelectedIndex()).removeObj(selectedAppointment);
		Calendar c1 = Calendar.getInstance();
		DateFormat format3 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		try {
				c1.setTime(format3.parse(this.dateBox.getText() + ' ' + this.timeBox.getText()));
				try {
					agendas.get(agendasList.getSelectedIndex()).addAppointmentAtIndex(c1, this.locationBox.getText(), this.personBox.getText(), Integer.parseInt(this.durationBox.getText()), index);
				} catch (NumberFormatException | ParseException e) {
					e.printStackTrace();
				}
				
				int select = agendasList.getSelectedIndex();
				int a = agendasList.getLastVisibleIndex();
				int b = agendasList.getFirstVisibleIndex();

				agendasList.setSelectedIndex(a);
				agendasList.setSelectedIndex(b);
				agendasList.setSelectedIndex(select);
				setVisible(false);
				dispose();
				JOptionPane.showMessageDialog(null, "Appuntamento Modificato!");
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Non è possibile lasciare un valore vuoto");
			e.printStackTrace();
		}  catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Data e ore inserite non sono valide");
			e.printStackTrace();
		}
	}
	
}
