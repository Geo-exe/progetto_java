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

import main.Main;
import sourcecode.Agenda;
import sourcecode.Appointment;
import utils.AgendaUtils;

public class Dashboard extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel agendasListPanel;
	private JPanel appointmentsPanel;
	private GridLayout appointmentsLayout;
	private GridBagConstraints gbc;
	private GridBagLayout windowLayout;
	private JLabel startLabel;
	public static JList<String> agendasList;
	private DefaultListModel<String> agendasListModel;

	private ActionsPanel actionsPanel;

	public Dashboard() {
		// richiamo il costruttore del JFrame passando il titolo della finestra
		super("Dashboard");
		windowLayout = new GridBagLayout();
		// setto il layout su griglia pesata
		setLayout(windowLayout);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		// inizializzo il layout
		appointmentsLayout = new GridLayout(4, 1, 0, 5);

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

		// action Panel
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.5;
		gbc.weightx = 1.0;
		actionsPanel = new ActionsPanel();
		add(actionsPanel, gbc);
		
		setPreferredSize(new Dimension(900, 650));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void initializeDashboard() {
		// Pannello Agende
		agendasListPanel.setLayout(new BorderLayout());
		agendasListPanel.setPreferredSize(new Dimension(150, 600));
		setAgendasList();
		
		// Pannello appuntamenti
		appointmentsPanel.setLayout(appointmentsLayout);
		appointmentsPanel.add(startLabel, CENTER_ALIGNMENT);
		appointmentsPanel.setPreferredSize(new Dimension(650, 600));
		appointmentsPanel.setVisible(true);
		
		setAppointmentsPanel(null);
	}

	private void setAppointmentsPanel(Agenda agenda) {
		appointmentsPanel.setVisible(false);
		appointmentsPanel.removeAll();
		if(agenda != null) {
			int size = 5;
			if (agenda.size() > size)
				size = agenda.size();
			appointmentsLayout.setRows(size);
			appointmentsPanel.setLayout(appointmentsLayout);
			for (Appointment appointment : agenda.getAppointments()) {
				appointmentsPanel.add(new AppointmentBox(appointment));
			}
		} else {
			appointmentsPanel.setLayout(new GridLayout(4, 1, 0, 5));
			appointmentsPanel.add(startLabel, CENTER_ALIGNMENT);
		}
		appointmentsPanel.setVisible(true);
		revalidate();
	}

	public int getSelectedAgenda() {
		return agendasList.getSelectedIndex();
	}
	
	private void setAgendasList() {
		agendasListPanel.setVisible(false);
		agendasListPanel.removeAll();
		agendasListModel = new DefaultListModel<String>();
		for (String name : AgendaUtils.agendaListToArray(Main.agendas)) {
			agendasListModel.addElement(name);
		}
		agendasList = new JList<>(agendasListModel);
		agendasList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					if (!agendasList.isSelectionEmpty()) {
						setAppointmentsPanel(Main.agendas.get(getSelectedAgenda()));
						actionsPanel.setButtonsStatus(true);
					} else {
						actionsPanel.setButtonsStatus(false);
						setAppointmentsPanel(null);
					}
				}
			}
		});
		agendasList.clearSelection();
		agendasListPanel.add(agendasList);
		agendasListPanel.setVisible(true);
	}
}