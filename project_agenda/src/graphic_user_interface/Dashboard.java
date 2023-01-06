package graphic_user_interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sourcecode.Agenda;
import sourcecode.Appointment;
import utils.AgendaUtils;

public class Dashboard extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JPanel agendasListPanel;
	private JPanel appointmentsPanel;
	private GridLayout appointmentsLayout;
	private GridBagConstraints gbc;
	private GridBagLayout windowLayout;
	private JLabel startLabel;
	private JList<String> agendasList;
	private ActionsPanel actionsPanel;
	
	public Dashboard(ArrayList<Agenda> agendas) {
		// richiamo il costruttore del JFrame passando il titolo della finestra
		super("Dashboard");
		windowLayout = new GridBagLayout();
		// setto il layout su griglia pesata
		setLayout(windowLayout);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH; 
		// inizializzo il layout
		appointmentsLayout = new GridLayout(4, 1, 0, 5);
		actionsPanel = new ActionsPanel(agendas, agendasList);
		// inizializzo la label
		startLabel = new JLabel("Selezionare un'Agenda!");
		
		// inizializzo i pannelli
		agendasListPanel = new JPanel();
		appointmentsPanel = new JPanel();	
		
		// aggiungere componenti alla finestra
		// lista agenda
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 1.0;
		gbc.weightx = 0.01;
		add(agendasListPanel, gbc);
		
		// lista appuntamenti
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		add(appointmentsPanel, gbc);		
		
		
		setPreferredSize(new Dimension(900, 650));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void initializeDashboard(ArrayList<Agenda> agendas) {	
		
        agendasListPanel.setLayout(new BorderLayout());
        DefaultListModel<String> model = new DefaultListModel<String>();
        for(String name: AgendaUtils.agendaListToArray(agendas)) {
        	model.addElement(name);
        }
		agendasList = new JList<>(model);
		agendasList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					setAppointmentsPanel(agendas.get(getSelectedAgenda()));
					if(!agendasList.isSelectionEmpty()) {
						actionsPanel.setButtonsStatus(true);
					} else actionsPanel.setButtonsStatus(false);
				}
			}
		});
		agendasListPanel.add(agendasList);
		agendasListPanel.setPreferredSize(new Dimension(150, 600));
		agendasListPanel.setVisible(true);
		// Pannello appuntamenti
		appointmentsPanel.setLayout(appointmentsLayout);
		appointmentsPanel.add(startLabel, CENTER_ALIGNMENT);
		appointmentsPanel.setPreferredSize(new Dimension(650, 600));
		appointmentsPanel.setVisible(true);
		
		
		//Aggiungo il pannello delle azioni
				gbc.gridwidth = 2;
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.weighty = 0.5;
				gbc.weightx = 1.0;
				add(actionsPanel, gbc);
		
	}
	
	private void setAppointmentsPanel(Agenda agenda) {
		appointmentsPanel.setVisible(false);
		int size = 5;
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
	
	public int getSelectedAgenda() {
		return agendasList.getSelectedIndex();
	}	
}