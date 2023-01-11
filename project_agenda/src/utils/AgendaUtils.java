package utils;

import java.util.ArrayList;

import sourcecode.Agenda;

public class AgendaUtils {
	public static String[] agendaListToArray(ArrayList<Agenda> list) {
		String[] arrayString = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arrayString[i] = list.get(i).getName();
		}
		return arrayString;
	}

	public static ArrayList<String> getNames(ArrayList<Agenda> list) {
		ArrayList<String> arrayString = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			arrayString.add(list.get(i).getName());
		}
		return arrayString;
	}

	public static boolean agendaExist(ArrayList<Agenda> list, String name) {
		return getNames(list).contains(name);
	}
}