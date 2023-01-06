package graphic_user_interface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import sourcecode.Agenda;
import sourcecode.Appointment;

public class EditAppointmentWindow extends ActionWindow {
	
	private static final long serialVersionUID = 1L;
	private JRadioButton[] radioButton;
	
	public EditAppointmentWindow(String title, ArrayList<Agenda> agendas, JList<String> agendasList,
			boolean actionWindowIsOpen) throws Exception {
		super(title, agendas, agendasList, actionWindowIsOpen);
		confirm.setText("Modifica");
		setBounds(0, 0, 350, 650);
		setLocationRelativeTo(null);
	}

	@Override
	public void confirmAction() {
		// TODO Auto-generated method stub

	}

	@Override
	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		ArrayList<Appointment> result;
		GridBagConstraints gbc = new GridBagConstraints();
		
		result=agendas.get(agendasList.getSelectedIndex()).getAppointments();
		
		if(!result.isEmpty()) {
		
		tempPanel.setLayout(new GridBagLayout());
		tempPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		gbc.gridy=0;
		tempPanel.add(new JLabel("Seleziona un appuntamento:"),gbc);
		
		
		radioButton = new JRadioButton[result.size()]; 

		for(int i = 0; i < result.size(); i++) {
			radioButton[i] = new JRadioButton();
			
			radioButton[i].addActionListener(e->{
				
				for(int j=0;j<radioButton.length;j++) {
					if(!radioButton[j].equals(e.getSource()) ) {
						radioButton[j].setSelected(false);
					}
				}
				
			});
			
			gbc.gridy = i+2;
			tempPanel.add(radioButton[i],gbc);
			tempPanel.add(new AppointmentBox(result.get(i)),gbc);
			
		}
		
		}else {
			tempPanel.add(new JLabel("Nessun appuntamento!"));
		}
		
		return tempPanel;
	}

}
