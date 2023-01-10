package graphic_user_interface;


import java.awt.Dimension;
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
import javax.swing.JScrollPane;
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
		JPanel tempPanel= new JPanel();
		JScrollPane scrollBar = new JScrollPane(temp);
		scrollBar.setBorder(null);
		try {
			FindByEnum selectedMethod = FindByEnum.valueOf(comboBox.getSelectedItem().toString());
			try {
				result = selectedMethod.findBy(textBox.getText(), agendas.get(agendasList.getSelectedIndex()));
				if(!result.isEmpty()) {
					scrollBar.setPreferredSize(new Dimension(350, 250));
					temp.setLayout(new GridLayout(result.size(),1));
					for(Appointment a: result) {
						temp.add(new AppointmentBox(a));
					}

					setVisible(false);
					dispose();
					tempPanel.add(scrollBar);
					DialogMessage.object("Appuntamento", tempPanel);
				
				}else {
					DialogMessage.error("Non trovato", "Nessun appuntamento trovato!");
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
				DialogMessage.error("Errore di inserimento", "Data inserita non valida!");
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			DialogMessage.error("Errore di inserimento", "Errore con il dato selezionato nella ComboBox!");
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
