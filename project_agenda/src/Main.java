import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import graphic_user_interface.Dashboard;
import sourcecode.Agenda;
//import sourcecode.Appointment;

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
		Calendar c4 = Calendar.getInstance();
		Calendar c5 = Calendar.getInstance();
		
		//i mesi partono da zero
		c1.set(2000, 0, 1, 11, 11);
		c2.set(2022, 8, 12, 12, 9);
		c3.set(2022, 8, 14, 12, 25);
		c4.set(2022, 8, 10, 12, 00);
		c5.set(2022, 8, 10, 14, 00);
		
		agendas.get(0).addAppointment(c1, "Ufficio", "Capo", 25);
		agendas.get(0).addAppointment(c1, "Ufficio", "Capo", 25);
		agendas.get(1).addAppointment(c2, "Casa", "Capo", 25);
		agendas.get(1).addAppointment(c3, "Casa", "Capo", 25);
		agendas.get(1).addAppointment(c4, "Casa", "Capo", 120);
		agendas.get(1).addAppointment(c5, "Casa", "Capo", 25);
		
		window = new Dashboard(agendas);
		window.initializeDashboard(agendas, selectionHandler());
	}
	
//	private void addAgenda(String Nome) {
//		
//	}
	
	private static ListSelectionListener selectionHandler() {
		return (new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					window.setAppointmentsPanel(agendas.get(window.getSelectedAgenda()));
				}
			}
		});
	}

	// Evento Bottone add Agenda
	private static ActionListener addAgendaActionHandler() {
		return (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Codice da eseguire quando il pulsante viene premuto

			}
		});
	}

	// Evento Bottone delete agenda
	private static ActionListener deleteAgendaActionHandler() {
		return (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Codice da eseguire quando il pulsante viene premuto

			}
		});
	}

	// Evento Bottone add appointment
	private static ActionListener addAppointmentActionHandler() {
		return (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Codice da eseguire quando il pulsante viene premuto

			}
		});
	}
	
	// Evento Bottone delete appointment
		private static ActionListener deleteAppointmentActionHandler() {
			return (new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Codice da eseguire quando il pulsante viene premuto

				}
			});
		}
//Evento Bottone edit appointment
		private static ActionListener editAppointmentActionHandler() {
			return (new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Codice da eseguire quando il pulsante viene premuto

				}
			});
		}
	}
