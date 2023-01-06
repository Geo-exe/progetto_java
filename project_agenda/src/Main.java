import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import graphic_user_interface.Dashboard;
import sourcecode.Agenda;

public class Main {
	
	private static ArrayList<Agenda> agendas;
	public static Dashboard dashboard;
	
	public static void main(String[] args) throws ParseException {
		agendas = new ArrayList<Agenda>();
		agendas.add(new Agenda("Ufficio"));
		agendas.add(new Agenda("Casa"));
		
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
		
		
		dashboard = new Dashboard();
		dashboard.initializeDashboard(agendas);
	}
	
	/*private static ListDataListener EventsHandlerAgenda = new ListDataListener() {
	    @Override
	    public void intervalAdded(ListDataEvent e) {
	        // TODO Modificare l'ADDAgenda
	    }

	    @Override
	    public void intervalRemoved(ListDataEvent e) {
	    	System.out.println("CIAONE");
	    	dashboard.setAgendasList(agendas);
	    }

	    @Override
	    public void contentsChanged(ListDataEvent e) {
	        // TODO Implementare il cambia nome agenda?
	    }
	};*/

}