package graphic_user_interface;


import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sourcecode.Agenda;
import sourcecode.Appointment;

public class FindAppointmentWindow extends ActionWindow {
	
	private static final long serialVersionUID = 1L;
	private JTextField textBox;
	private JLabel label;
	private JComboBox<Object> comboBox;
	private JPanel temp;

	
	public FindAppointmentWindow(String title, ArrayList<Agenda> agendas, JList<String> agendasList, boolean actionWindowIsOpen) throws Exception {
		super(title, agendas, agendasList, actionWindowIsOpen);
		temp = new JPanel();
		add(temp);
	}

	@Override
	public void confirmAction() {
		ArrayList<Appointment> result;
		result =new ArrayList<Appointment>();
		Calendar date = Calendar.getInstance();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		temp.removeAll();
		temp.setLayout(new GridBagLayout());
		
		try {
			
			if(comboBox.getItemAt(comboBox.getSelectedIndex()).toString()=="Data") {
				
				
					date.setTime(format.parse(this.textBox.getText()));
					result=agendas.get(agendasList.getSelectedIndex()).findByDate(date);
				
				
				
				
			}else if(comboBox.getItemAt(comboBox.getSelectedIndex()).toString()=="Nome") {
				
				result=agendas.get(agendasList.getSelectedIndex()).findByName(textBox.getText());
						
				
			}
			
			if(!result.isEmpty()) {
				
				for(Appointment a: result) {
					temp.add(new AppointmentBox(a));
				}

				setBounds(0, 0, 800, 801);
			setBounds(0, 0, 800, 800);
			
			}else {
				JOptionPane.showMessageDialog(null, "Nessun appuntamento trovato!");
			}
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Carattere non valido!");
		}catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Data inserita non sono valida");
			e.printStackTrace();
		}
		
		
	}

	@Override
	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(2,2,5,5));
		tempPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		tempPanel.add(new JLabel("Trova per:"));
		
		String [] options= {"Data","Nome"};
		comboBox= new JComboBox<Object>(options);
		comboBox.addActionListener(
			new ActionListener () {
			    public void actionPerformed(ActionEvent e1) {
			        label.setText(comboBox.getItemAt(comboBox.getSelectedIndex()).toString()+" :");
			    }}
		);
		
		tempPanel.add(comboBox);
		
		label=new JLabel(comboBox.getItemAt(comboBox.getSelectedIndex()).toString()+" :") ;
		tempPanel.add(label);
		
		textBox = new JTextField();
		tempPanel.add(textBox);
		return tempPanel;
	}

}
