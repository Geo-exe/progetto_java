import java.text.ParseException;
import java.util.ArrayList;

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
		agendas.get(0).addAppointment("12/09/2022", "12:09", "Ufficio", "Capo", 25);
		agendas.get(0).addAppointment("14/09/2022", "12:25", "Ufficio", "Capo", 25);
		
		agendas.get(1).addAppointment("12/09/2022", "12:09", "Casa", "Capo", 25);
		agendas.get(1).addAppointment("14/09/2022", "12:25", "Casa", "Capo", 25);
		
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
