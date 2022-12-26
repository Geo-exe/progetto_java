package graphic_user_interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;

import sourcecode.Agenda;
import sourcecode.Appointment;
import utils.AgendaUtils;

public class Dashboard extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel agendasListPanel;
	private JPanel appointmentsPanel;
	
	private GridLayout appointmentsLayout;
	private JLabel startLabel;
	private JList<String> agendasList;
	public Dashboard(ArrayList<Agenda> agendas) {
		// richiamo il costruttore del JFrame passando il titolo della finestra
		super("Dashboard");
		
		// setto il layout su Layout a griglia con 1 riga e 2 colonne
		setLayout(new GridLayout(1, 2));
		
		// inizializzo il layout
		appointmentsLayout = new GridLayout(4, 1, 0, 5);
		
		// inizializzo la label
		startLabel = new JLabel("Selezionare un'Agenda!");
		
		// inizializzo i pannelli
		agendasListPanel = new JPanel();
		appointmentsPanel = new JPanel();
		
		// aggiungere componenti alla finestra
		add(agendasListPanel);
		add(appointmentsPanel);
		
		
		setPreferredSize(new Dimension(800, 600));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void initializeAgendaList(ArrayList<Agenda> agendas, ListSelectionListener selectionHandler) {	
		agendasListPanel.setLayout(new BorderLayout());
		agendasList = new JList<>(AgendaUtils.agendaListToArray(agendas));
		agendasList.addListSelectionListener(selectionHandler);
		agendasListPanel.add(agendasList);
		agendasListPanel.setPreferredSize(new Dimension(150, 600));
		agendasListPanel.setVisible(true);
	}

	public void initializeAppointmentsPanel() {
		appointmentsPanel.setLayout(appointmentsLayout);

		appointmentsPanel.add(startLabel, CENTER_ALIGNMENT);

		appointmentsPanel.setPreferredSize(new Dimension(650, 600));
		appointmentsPanel.setVisible(true);
	}

	public void setAppointmentsPanel(Agenda agenda) {
		appointmentsPanel.setVisible(false);
		int size = 4;
		if (agenda.size() > size)
			size = agenda.size();
		appointmentsPanel.removeAll();
		appointmentsLayout.setRows(size);
		for (Appointment appointment : agenda.getAppointments()) {
			appointmentsPanel.add(new AppointmentBox(appointment));
		}
		appointmentsPanel.setVisible(true);
		revalidate();
	}
	
	public int getSelectedItem() {
		return agendasList.getSelectedIndex();
	}
	
}