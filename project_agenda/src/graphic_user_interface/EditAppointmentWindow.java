package graphic_user_interface;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import sourcecode.Appointment;
import sourcecode.UnavailabilityException;

/**
 * AddAppointmentwindow e' la classe che predispone una finestra per un form per
 * inserire un nuovo appuntamento. La classe EditAppointmentWindow estende
 * ActionWindow implementando i componenti e le funzioni necessarie per
 * modificare appuntamento, sfruttando i componenti di AddAppointemntWindow.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class EditAppointmentWindow extends AddAppointmentWindow {

	private static final long serialVersionUID = 1L;
	/**
	 * Oggetto appuntamento selezionato.
	 */
	private Appointment selectedAppointment;

	/**
	 * Costruttore della classe. Passa al super costruttore title e cambia il
	 * contenuto del tasto confirm.
	 * 
	 * @param title titolo della finestra
	 * @throws Exception un'altra finestra gia' aperta
	 */
	public EditAppointmentWindow(String title) throws Exception {
		super(title + " ");
		confirm.setText("Modifica");
	}

	/**
	 * Esegue le operazioni necessarie a modificare appuntamento, eseguondo
	 * apportuni controlli.
	 * @param index indice dell'agenda
	 */
	public void passFields(int index) {
		Appointment appointment = agendas.get(agendasList.getSelectedIndex()).getAppointmentAt(index);
		this.dateBox.setText(appointment.getStrDate());
		this.timeBox.setText(appointment.getTime());
		this.locationBox.setText(appointment.getLocation());
		this.personBox.setText(appointment.getPerson());
		this.durationBox.setText(Integer.toString(appointment.getDuration()));
		this.selectedAppointment = appointment;
	}

	/**
	 * Inizializza i campi del form necessari a modificare un appuntamento.
	 * 
	 */
	public void confirmAction() {
		int index = agendas.get(agendasList.getSelectedIndex()).getAppointmentIndex(selectedAppointment);
		agendas.get(agendasList.getSelectedIndex()).removeObj(selectedAppointment);
		Calendar c1 = Calendar.getInstance();
		DateFormat format3 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		try {
			c1.setTime(format3.parse(this.dateBox.getText() + ' ' + this.timeBox.getText()));
			try {
				agendas.get(agendasList.getSelectedIndex()).addAppointmentAtIndex(c1, this.locationBox.getText(),
						this.personBox.getText(), Integer.parseInt(this.durationBox.getText()), index);

				DialogMessage.information("Successo", "Appuntamento Modificato!");
			} catch (NumberFormatException | ParseException e) {
				e.printStackTrace();
			} catch (UnavailabilityException e) {
				DialogMessage.error("Impossibile", "Già impegnato! Impossibile modificare l'appuntamento.");
				agendas.get(agendasList.getSelectedIndex()).addObj(selectedAppointment);
				e.printStackTrace();
			}

			int select = agendasList.getSelectedIndex();
			agendasList.clearSelection();
			agendasList.setSelectedIndex(select);

			setVisible(false);
			dispose();

		} catch (IllegalArgumentException | ParseException e) {
			DialogMessage.error("Errore di inserimento", "Non è possibile lasciare un valore vuoto");
			e.printStackTrace();
		}
	}

}
