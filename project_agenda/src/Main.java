import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import graphic_user_interface.Dashboard;
import sourcecode.Agenda;
//import sourcecode.Appointment;

public class Main {
	
	private static ArrayList<Agenda> agendas;
	public static Dashboard dashboard;
	
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
		
		dashboard = new Dashboard(agendas);
		dashboard.initializeDashboard(agendas);
	}
}