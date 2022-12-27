import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import graphic_user_interface.Dashboard;
import sourcecode.Agenda;

public class Main {
	
	private static ArrayList<Agenda> agendas;
	public static Dashboard window;
	
	public static void main(String[] args) throws ParseException {
		agendas = new ArrayList<Agenda>();
		agendas.add(new Agenda("Casa"));
		agendas.add(new Agenda("Ufficio"));
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();

		//i mesi partono da zero
		c1.set(2000, 0, 1, 11, 11);
		c2.set(2022, 8, 12, 12, 9);
		c3.set(2022, 8, 14, 12, 25);
		
		agendas.get(0).addAppointment(c1, "Ufficio", "Capo", 25);
		agendas.get(0).addAppointment(c1, "Ufficio", "Capo", 25);
		agendas.get(1).addAppointment(c2, "Casa", "Capo", 25);
		agendas.get(1).addAppointment(c3, "Casa", "Capo", 25);
		
		window = new Dashboard(agendas);
		window.initializeAgendaList(agendas, selectionHandler());
		window.initializeAppointmentsPanel();
	}
	
//	private void addAgenda(String Nome) {
//		
//	}
	
	private static ListSelectionListener selectionHandler() {
		return (new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					window.setAppointmentsPanel(agendas.get(window.getSelectedItem()));
				}
			}
		});
	}
}
