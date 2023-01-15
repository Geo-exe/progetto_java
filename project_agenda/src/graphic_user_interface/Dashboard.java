package graphic_user_interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.Main;
import sourcecode.Agenda;
import sourcecode.Appointment;
import utils.AgendaUtils;

/**
 * JFrame è una finestra di dialogo dichiarata nella libreria javax.swing. Essa
 * contiene al suo interno i vari componenti dell'interfaccia grafica. Dashboard
 * estende JFrame implementando una classe che crea una finestra in cui si trova
 * in alto una JMenuBar, a sinistra un elenco delle agende presenti nel
 * programma e a destra l'elenco degli appuntamenti dell'agenda selezionata.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 *
 */
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

	/**
	 * Costruttore della classe. Viene creato il JFrame e posizionati i vari
	 * componenti grafici.
	 */
	public Dashboard() {
		// richiamo il costruttore del JFrame passando il titolo della finestra
		super("Dashboard");

		// imposto un tema della gui, prendendo quello sistema
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		JScrollPane scrollBar = new JScrollPane(appointmentsPanel);
		scrollBar.setBorder(null);
		scrollBar.getVerticalScrollBar().setUnitIncrement(16);
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
		add(scrollBar, gbc);

		// action Panel
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.03;
		gbc.weightx = 1.0;
		actionsPanel = new ActionsPanel();
		add(actionsPanel, gbc);

		setPreferredSize(new Dimension(450, 650));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Vengono inizializzati i due pannelli contenenti le agende e gli appuntamenti.
	 */
	public void initializeDashboard() {
		// Pannello Agende
		agendasListPanel.setLayout(new BorderLayout());
		agendasListPanel.setPreferredSize(new Dimension(150, 600));
		setAgendasList();

		// Pannello appuntamenti
		appointmentsPanel.setLayout(appointmentsLayout);
		appointmentsPanel.add(startLabel, CENTER_ALIGNMENT);
		appointmentsPanel.setVisible(true);

		setAppointmentsPanel(null);
	}

	/**
	 * Vengono stampati i vari appuntamenti dell'agenda selezionata, tramite la
	 * classe AppointmentBox.
	 * 
	 * @param agenda selezionata
	 */
	private void setAppointmentsPanel(Agenda agenda) {
		appointmentsPanel.setVisible(false);
		appointmentsPanel.removeAll();
		if (agenda != null) {
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

	/**
	 * Ritorna l'indice dell'agenda in JList selezionata.
	 * 
	 * @return int index
	 */
	public int getSelectedAgenda() {
		return agendasList.getSelectedIndex();
	}

	/**
	 * Viengono stampate le varie agende nella JList.
	 */
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