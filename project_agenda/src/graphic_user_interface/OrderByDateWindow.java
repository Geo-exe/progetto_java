package graphic_user_interface;


import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sourcecode.Agenda;
import sourcecode.Appointment;


public class OrderByDateWindow extends ActionWindow {

private static final long serialVersionUID = 1L;
	
	private JComboBox<String> comboBox;
	
	public OrderByDateWindow(String title, ArrayList<Agenda> agendas, JList<String> agendasList,
			boolean actionWindowIsOpen) throws Exception {
		super(title, agendas, agendasList, actionWindowIsOpen);
		confirm.setText("Conferma");
	}

	@Override
	public void confirmAction() {
		ArrayList<Appointment> result =new ArrayList<Appointment>();
		JPanel temp= new JPanel();
		
		result=agendas.get(agendasList.getSelectedIndex()).getAllAppointments(comboBox.getSelectedItem().toString());
		
		temp.setLayout(new GridLayout(result.size(),1));
		
		for(Appointment a: result) {
			temp.add(new AppointmentBox(a));
			
		}
		
		setVisible(false);
		dispose();
		JOptionPane.showMessageDialog(null, temp);
		
	}

	@Override
	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(2,2,5,5));
		tempPanel.setBorder(new EmptyBorder(40, 30, 30, 40));
		tempPanel.add(new JLabel("Seleziona ordine da visualizzare:"));
		
		String [] options= {"Ascendente","Discendente"};
		comboBox= new JComboBox<String>(options);

		tempPanel.add(comboBox);
		
		return tempPanel;
	}

}
