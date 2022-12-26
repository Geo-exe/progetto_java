import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import graphic_user_interface.Dashboard;
import sourcecode.Agenda;

public class Main {
	
	private ArrayList<Agenda> agendas;
	public Dashboard window;
	
	public void main() throws ParseException {
		agendas = new ArrayList<Agenda>();
		agendas.add(new Agenda("Casa"));
		agendas.add(new Agenda("Ufficio"));
		agendas.get(0).addAppointment("12/09/2022", "12:09", "", "", "");
		this.window = new Dashboard(agendas);
		this.window.initializeAgendaList(agendas, selectionHandler());
		this.window.initializeAppointmentsPanel();
	}
	
	private void addAgenda(String Nome) {
		
	}
	
	private ListSelectionListener selectionHandler() {
		return (new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					window.setAppointmentsPanel(agendas.get(window.getSelectedItem()));
				}
			}
		});
	}
}
