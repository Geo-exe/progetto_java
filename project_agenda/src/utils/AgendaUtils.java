package utils;

import java.util.ArrayList;

import sourcecode.Agenda;

public class AgendaUtils {
	public static String[] agendaListToArray(ArrayList<Agenda> list) {
		String[] arrayString = new String[list.size()];
		for(int i = 0; i<list.size();i++) {
			arrayString[i] = list.get(i).getName();
		}
		return arrayString;
	}
	
	public static boolean checkAvailability(String date, String time, String location, String person, String duration) {
		
		//controllo disponibilità per inserire appuntamento
		return false;
	}
}