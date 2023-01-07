package graphic_user_interface;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sourcecode.Agenda;
import sourcecode.Appointment;
import sourcecode.FindByEnum;

public class FindAppointmentWindow extends ActionWindow {
	
	private static final long serialVersionUID = 1L;
	private JTextField textBox;
	private JLabel label;
	private JComboBox<FindByEnum> comboBox;

	
	public FindAppointmentWindow(String title) throws Exception {
		super(title);
		confirm.setText("Trova");
	}

	public void confirmAction() {
		ArrayList<Appointment> result=new ArrayList<Appointment>();
		JPanel temp= new JPanel();
		try {
			FindByEnum selectedMethod = FindByEnum.valueOf(comboBox.getSelectedItem().toString());
			try {
				result = selectedMethod.findBy(textBox.getText(), agendas.get(agendasList.getSelectedIndex()));
				if(!result.isEmpty()) {
					
					temp.setLayout(new GridLayout(result.size(),1));
					for(Appointment a: result) {
						temp.add(new AppointmentBox(a));
					}

					setVisible(false);
					dispose();
					JOptionPane.showMessageDialog(null, temp);
				
				}else {
					JOptionPane.showMessageDialog(null, "Nessun appuntamento trovato!");
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Data inserita non valida!", "Errore di inserimento", JOptionPane.ERROR_MESSAGE);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Errore con il dato selezionato nella ComboBox!", "Errore di inserimento", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(2,2,5,5));
		tempPanel.setBorder(new EmptyBorder(35, 25, 25, 35));
		tempPanel.add(new JLabel("Trova per:"));
		
		comboBox= new JComboBox<>();
		for (FindByEnum value : FindByEnum.values()) {
		    comboBox.addItem(value);
		}
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
