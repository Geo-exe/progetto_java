import java.text.ParseException;
import java.util.ArrayList;

import graphic_user_interface.WindowAgenda;
import sourcecode.Agenda;

public class Main {
	
	private ArrayList<Agenda> agendas;
	
	public void main() throws ParseException {
		agendas = new ArrayList<Agenda>();
		agendas.add(null);
		new WindowAgenda(agendas);
	  }
	
	private void addAgenda(String Nome) {
		
	}
}
