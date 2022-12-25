package graphic_user_interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sourcecode.Agenda;
import sourcecode.Appointment;
import utils.AgendaUtils;

public class WindowAgenda extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel agendasList;
	private JPanel appointmentsPanel;
	private ArrayList<Agenda> agendas;
	private GridLayout layout;
	private JLabel label;
	public WindowAgenda(ArrayList<Agenda> agendas) {
		super("Dashboard");
		setLayout(new GridLayout(1, 1));
		this.agendas = agendas;
		layout = new GridLayout(4, 1, 0, 5);
		label = new JLabel("Selezionare un'Agenda!");
		agendasList = initializeAgendaList();
		appointmentsPanel = initializeAppointmentsPanel();
		add(agendasList);
		add(appointmentsPanel);
		setPreferredSize(new Dimension(800, 600));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private JPanel initializeAgendaList() {
		JPanel tempPanel = new JPanel();
		String[] tempStrList = AgendaUtils.agendaListToArray(this.agendas);
		JList<String> tempList = new JList<>(tempStrList);
		tempPanel.setLayout(new BorderLayout());
		tempPanel.add(tempList);
		tempList.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent e) {
			        if (!e.getValueIsAdjusting()) {
			          setAppointmentsPanel(tempList.getSelectedIndex());
			        }
			      }
			    });
		
		tempPanel.setPreferredSize(new Dimension(150, 600));
		tempPanel.setVisible(true);
		return tempPanel;
	}
	
	private JPanel initializeAppointmentsPanel() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(layout);
		
		tempPanel.add(label, CENTER_ALIGNMENT);
		
		tempPanel.setPreferredSize(new Dimension(650, 600));
		tempPanel.setVisible(true);
		return tempPanel;
	}
	
	private void setAppointmentsPanel(int selectedAgenda) {
		appointmentsPanel.setVisible(false);
		int size = 4;
		if(agendas.get(selectedAgenda).size()>size) size = agendas.get(selectedAgenda).size();
		appointmentsPanel.removeAll();
		layout.setRows(size);
		for(Appointment appointment: agendas.get(selectedAgenda).getAppointments()) {
			appointmentsPanel.add(new AppointmentBox(appointment));
		}
		appointmentsPanel.setVisible(true);
		revalidate();
	}
	
	private JPanel agendaButtons() {
		JPanel tempPanel = new JPanel();
		// Add Button
		JButton addButton = new JButton();
		
		
		
		// Remove Button
		JButton delButton = new JButton();
		
		
		
		
		
		return tempPanel;
	}
	
	private JPanel appointmentsButtons() {
		JPanel tempPanel = new JPanel();
		// Add Button
		JButton addButton = new JButton();
		
		
		// Remove Button
		JButton delButton = new JButton();
		
		
		
		// Edit Button
		JButton editButton = new JButton();
		
		
		
		return tempPanel;
	}
	
}