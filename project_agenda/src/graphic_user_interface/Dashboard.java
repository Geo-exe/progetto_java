package graphic_user_interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

import sourcecode.Agenda;
import sourcecode.Appointment;
import utils.AgendaUtils;

public class Dashboard extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel agendasListPanel;
	private JPanel appointmentsPanel;
	private JPanel buttonsPanel;
	private JButton buttonAdd;
	private JButton buttonOrder;
	private JButton buttonFindName;
	private JButton buttonFindDate;
	private JButton buttonDelete;
	private JButton buttonModify;
	
	private GridBagConstraints gbc= new GridBagConstraints();
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
		buttonsPanel = new JPanel();
		buttonAdd= new JButton("Add");
		buttonDelete= new JButton("Delete");
		buttonFindName= new JButton("Find By Name");
		buttonFindDate= new JButton("Find By Date");
		buttonOrder= new JButton("Order By");
		buttonModify= new JButton("Modify");
		
		
		
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
        agendasListPanel.setLayout(new GridBagLayout());
        agendasListPanel.setBorder(BorderFactory.createTitledBorder("Agende"));
		agendasList = new JList<>(AgendaUtils.agendaListToArray(agendas));
		agendasList.addListSelectionListener(selectionHandler);
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.anchor=GridBagConstraints.FIRST_LINE_START;
		gbc.weighty=0.01;
		agendasListPanel.add(agendasList,gbc);
		agendasListPanel.setVisible(true);
		
		initializeButtonsPanel();
		 
		
	}
	
	public void initializeButtonsPanel() {
		
		gbc.anchor=GridBagConstraints.PAGE_END;
		gbc.weighty=1;
		agendasListPanel.add(buttonsPanel,gbc);
		buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonsPanel.setBorder(BorderFactory.createTitledBorder("Funzioni"));
		buttonsPanel.setLayout(new BorderLayout());
		buttonsPanel.setLayout(new GridLayout(2,3,10,10));
		buttonsPanel.add(buttonAdd);
		buttonsPanel.add(buttonDelete);
		buttonsPanel.add(buttonModify);
		buttonsPanel.add(buttonFindDate);
		buttonsPanel.add(buttonFindName);
		buttonsPanel.add(buttonOrder);
		
        
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