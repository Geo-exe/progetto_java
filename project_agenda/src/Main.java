import java.text.ParseException;
import java.util.ArrayList;

import graphic_user_interface.WindowAgenda;
import sourcecode.Agenda;

public class Main {
	public static void main(String[] args) throws ParseException {
		
		// ancora da implementare. Il codice seguente è solo di test.
		
		ArrayList<Agenda> ags = new ArrayList<Agenda>();
		Agenda ag = new Agenda("Casa");
		Agenda ag2 = new Agenda("Lavoro");
		ag.addAppointment("12/12/2022", "12:30", "Ufficio", "Dario");
		ag.addAppointment("12/12/2022", "12:30", "Ufficio", "Dario");
		ag2.addAppointment("13/12/2022", "14:30", "Casa", "Mamma");
		ag2.addAppointment("13/12/2022", "14:30", "Casa", "Mamma");
		ag2.addAppointment("13/12/2022", "14:30", "Casa", "Mamma");
		ag2.addAppointment("13/12/2022", "14:30", "Casa", "Mamma");
		ag2.addAppointment("13/12/2022", "14:30", "Casa", "Mamma");
		ag2.addAppointment("13/12/2022", "14:30", "Casa", "Mamma");
		ags.add(ag);
		ags.add(ag2);
	    new WindowAgenda(ags);
	  }
}
