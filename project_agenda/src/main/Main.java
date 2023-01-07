package main;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import graphic_user_interface.Dashboard;
import sourcecode.Agenda;
import sourcecode.FileHandler;

public class Main {
	
	public static ArrayList<Agenda> agendas;
	private static Dashboard dashboard;
	
	public static void main(String[] args) {
		onStartup();
		
		// Launch GUI
		dashboard = new Dashboard();
		dashboard.addWindowListener(closingEvents());
		dashboard.initializeDashboard();
	}
	
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
	
	private static void onExit() {
		FileHandler.exportToFile("dataBackup.agenda", agendas);
	}
	
	@SuppressWarnings("unchecked")
	private static void onStartup() {
		ArrayList<Agenda> temp = (ArrayList<Agenda>) FileHandler.importFromFile("dataBackup.agenda", true);
		if(temp != null) {
			agendas = temp;
		} else {
			agendas = new ArrayList<Agenda>();
		}
	}
}