package main;

import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import graphic_user_interface.Dashboard;
import sourcecode.Agenda;
import sourcecode.FileHandler;

/**
 * Classe Main.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
public class Main {
	/**
	 * ArrayList di oggetti Agenda.
	 */
	public static ArrayList<Agenda> agendas;
	private static Dashboard dashboard;

	/**
	 * Metodo main.
	 * 
	 * @param args argomenti da passare al main
	 */
	public static void main(String[] args) {
		onStartup();

		// Launch GUI
		dashboard = new Dashboard();
		dashboard.addWindowListener(closingEvents());
		dashboard.initializeDashboard();
	}

	/**
	 * Quando il programma viene chiuso richiama onExit().
	 * 
	 * @return WindowAdapter
	 */
	private static WindowAdapter closingEvents() {
		return new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosed(java.awt.event.WindowEvent windowEvent) {
				onExit();
			}

			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				onExit();
			}
		};
	}

	/**
	 * Esporta le agende su file.
	 */
	private static void onExit() {
		FileHandler.exportToFile("dataBackup.agenda", agendas);
	}

	@SuppressWarnings("unchecked")
	/**
	 * Quando il programma viene avviato importa le agende da file.
	 */
	private static void onStartup() {
		try {
			ArrayList<Agenda> temp = (ArrayList<Agenda>) FileHandler.importFromFile("dataBackup.agenda");
			if (temp != null) {
				agendas = temp;
			} else {
				agendas = new ArrayList<Agenda>();
			}
		} catch (ClassCastException e) {
			e.printStackTrace();
			agendas = new ArrayList<Agenda>();
		}

	}
}