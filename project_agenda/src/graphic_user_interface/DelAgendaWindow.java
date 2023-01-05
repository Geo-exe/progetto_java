package graphic_user_interface;

import java.awt.GridLayout;
import java.util.ArrayList;


import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sourcecode.Agenda;


public class DelAgendaWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> comboBox;

	public DelAgendaWindow(String title, ArrayList<Agenda> agendas, JList<String> agendasList,
			boolean actionWindowIsOpen) throws Exception {
		super(title, agendas, agendasList, actionWindowIsOpen);
		
	}

	
	@Override
	public void confirmAction() {

		DefaultListModel<String> model = (DefaultListModel<String>) agendasList.getModel();

		model.removeElement(comboBox.getSelectedItem());
		
		
		for(int i=0;i<agendas.size();i++) {
			if(agendas.get(i).getName().equals(comboBox.getSelectedItem())) {
				agendas.remove(i);
			}
		}
		
		setVisible(false);
		dispose();
		JOptionPane.showMessageDialog(null, "Agenda "+ comboBox.getSelectedItem() +" eliminata!");
		
		
		

	}

	@Override
	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(2,2,5,5));
		tempPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		tempPanel.add(new JLabel("Seleziona un' agenda da :"));
		
		comboBox= new JComboBox<String>();
		
		for(int i=0;i<agendas.size();i++) {
			comboBox.addItem(agendas.get(i).getName());

		}

		tempPanel.add(comboBox);
		
		return tempPanel;
	}

}
