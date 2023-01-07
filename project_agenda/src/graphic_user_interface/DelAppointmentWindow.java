package graphic_user_interface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;


import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sourcecode.Agenda;
import sourcecode.Appointment;

public class DelAppointmentWindow extends ActionWindow {
	
	private static final long serialVersionUID = 1L;
	private JCheckBox[] checkBox;
	private JCheckBox all;
	
	public DelAppointmentWindow(String title) throws Exception {
		super(title);
		confirm.setText("Elimina");
		setBounds(0, 0, 350, 650);
		setLocationRelativeTo(null);
	}

	public void confirmAction() {
		if(checkBox!=null) {
		if(all.isSelected()) {
			
			agendas.get(agendasList.getSelectedIndex()).removeAll();
		}else {
			int del=0;
			for(int i = 0; i < checkBox.length; i++) {
			if(checkBox[i].isSelected()) {
				agendas.get(agendasList.getSelectedIndex()).removeAt(i-del);
				del++;
				}
			}
		}
		
		int select = agendasList.getSelectedIndex();
		int a = agendasList.getLastVisibleIndex();
		int b = agendasList.getFirstVisibleIndex();

		agendasList.setSelectedIndex(a);
		agendasList.setSelectedIndex(b);
		agendasList.setSelectedIndex(select);
		setVisible(false);
		dispose();
		JOptionPane.showMessageDialog(null,"Cancellazione effettuata!");
		}else {
			setVisible(false);
			dispose();
		}
		
	}

	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		ArrayList<Appointment> result;
		GridBagConstraints gbc = new GridBagConstraints();
		
		result=agendas.get(agendasList.getSelectedIndex()).getAppointments();
		
		if(!result.isEmpty()) {
		
		tempPanel.setLayout(new GridBagLayout());
		tempPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		gbc.gridy=0;
		tempPanel.add(new JLabel("Seleziona gli appuntamenti:"),gbc);
		
		all=new JCheckBox("Seleziona tutti");
		gbc.gridy=1;
		tempPanel.add(all,gbc);
		all.addActionListener(e->{
			
			if(all.isSelected()) {
				for(int i = 0; i < checkBox.length; i++) {
					checkBox[i].setSelected(true);
					
				}
			}else if(!all.isSelected()){
				for(int i = 0; i < checkBox.length; i++) {
					checkBox[i].setSelected(false);
					
				}
			}
		});
		
		
		/*result=agendas.get(agendasList.getSelectedIndex()).getAppointments();
		
		checkBox = new JCheckBox[result.size()]; 

		for(int i = 0; i < result.size(); i++) {
			checkBox[i] = new JCheckBox("Data: "+result.get(i).getStrDate()+"  Ora: "+result.get(i).getTime()+"  Durata: "+result.get(i).getDuration()+"  Luogo: "+result.get(i).getLocation()+"  Persona: "+result.get(i).getPerson());
			tempPanel.add(checkBox[i]);
		}*/
		
		
		
		
		checkBox = new JCheckBox[result.size()]; 

		for(int i = 0; i < result.size(); i++) {
			checkBox[i] = new JCheckBox();
			
			gbc.gridy = i+2;
			
			tempPanel.add(checkBox[i],gbc);
			tempPanel.add(new AppointmentBox(result.get(i)),gbc);
			
		}
		
		}else {
			tempPanel.add(new JLabel("Nessun appuntamento!"));
		}
		
		return tempPanel;
	}

}
