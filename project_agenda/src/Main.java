import java.awt.event.WindowAdapter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import graphic_user_interface.Dashboard;
import sourcecode.Agenda;

public class Main implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static ArrayList<Agenda> agendas;
	private static Dashboard dashboard;
	
	public static void main(String[] args) {
		agendas = new ArrayList<Agenda>();
		
		
		
		// Launch GUI
		dashboard = new Dashboard();
		dashboard.addWindowListener(closingEvents());
		dashboard.initializeDashboard(agendas);
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
		
	}
	
	private void saveToFile(String fileName) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(Main.agendas);
        }
    }

}