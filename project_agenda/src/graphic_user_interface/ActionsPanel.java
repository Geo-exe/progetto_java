package graphic_user_interface;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import sourcecode.Agenda;

public class ActionsPanel extends JPanel {
	private JButton find;
	private JButton addAgenda;
	private JButton delAgenda;
	private JButton addAppointment;
	private JButton delAppointment;
	private JButton editAppointment;
	private JButton orderByDate;
	private JButton importFile;
	private JButton exportFile;
	private boolean actionWindowIsOpen;
	private JFrame actionWindowOpen;
	private ArrayList<Agenda> agendas;
	private JList<String> agendasList;

	public ActionsPanel(ArrayList<Agenda> agendas, JList<String> agendasList) {
		super();
		setLayout(new GridLayout(1, 9, 2, 2));
		find = new JButton("Trova Appuntamento");
		addAgenda = new JButton("Aggiungi Agenda");
		delAgenda = new JButton("Elimina Agenda");
		addAppointment = new JButton("Aggiungi Appuntamento");
		delAppointment = new JButton("Elimina Appunamento");
		editAppointment = new JButton("Modifica Appuntamento");
		orderByDate = new JButton("Ordina Appunamenti");
		importFile = new JButton("Importa Agenda");
		exportFile = new JButton("Esporta Agenda");
		actionWindowIsOpen = false;
		addAgenda.addActionListener(e -> {
			try {
				actionWindowOpen = new AddAgendaWindow("Aggiungi Agenda", agendas, agendasList, actionWindowIsOpen);
				actionWindowOpen.addWindowListener(closingEvents());
				actionWindowIsOpen = true;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		
		addAppointment.addActionListener(e -> {
			try {
				actionWindowOpen = new AddAppointmentWindow("Aggiungi Appuntamento", agendas, agendasList, actionWindowIsOpen);
				actionWindowOpen.addWindowListener(closingEvents());
				actionWindowIsOpen = true;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		add(addAgenda);
		add(addAppointment);
		setVisible(true);
	}
	
	private WindowAdapter closingEvents() {
		return new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosed(java.awt.event.WindowEvent windowEvent) {
				actionWindowIsOpen = false;
			}

			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				actionWindowIsOpen = false;
			}
		};
	}
}
