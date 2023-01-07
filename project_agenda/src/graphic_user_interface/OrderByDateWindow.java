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
import sourcecode.OrderMethodEnum;


public class OrderByDateWindow extends ActionWindow {

private static final long serialVersionUID = 1L;
	
	private JComboBox<OrderMethodEnum> comboBox;
	
	public OrderByDateWindow(String title) throws Exception {
		super(title);
		confirm.setText("Conferma");
	}

	public void confirmAction() {
		ArrayList<Appointment> result =new ArrayList<Appointment>();
		JPanel temp= new JPanel();
		
		result=agendas.get(agendasList.getSelectedIndex()).sortAppointmets(OrderMethodEnum.valueOf(comboBox.getSelectedItem().toString()));
		
		temp.setLayout(new GridLayout(result.size(),1));
		
		if(!result.isEmpty()) {
			for(Appointment a: result) {
			temp.add(new AppointmentBox(a));
			
			}
		}else {
			temp.add(new JLabel("Nessun appuntamento!"));
		}
		
		
		setVisible(false);
		dispose();
		JOptionPane.showMessageDialog(null, temp);
		
	}

	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(2,2,5,5));
		tempPanel.setBorder(new EmptyBorder(40, 30, 30, 40));
		tempPanel.add(new JLabel("Seleziona ordine da visualizzare:"));

		comboBox= new JComboBox<>();
		for (OrderMethodEnum value : OrderMethodEnum.values()) {
		    comboBox.addItem(value);
		}

		tempPanel.add(comboBox);
		
		return tempPanel;
	}

}
