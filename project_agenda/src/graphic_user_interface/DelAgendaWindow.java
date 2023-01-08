package graphic_user_interface;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sourcecode.Agenda;
import utils.AgendaUtils;

public class DelAgendaWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;

	private JComboBox<String> comboBox;

	public DelAgendaWindow(String title) throws Exception {
		super(title);
		confirm.setText("Elimina");
	}

	public void confirmAction() {
	
		for(Iterator<Agenda> iterator = agendas.iterator(); iterator.hasNext();) {
			Agenda agenda = iterator.next();
			if(agenda.getName().equals(comboBox.getSelectedItem())) {
				iterator.remove();
			}
		}
		
		DefaultListModel<String> model = (DefaultListModel<String>) agendasList.getModel();
		model.removeElement(comboBox.getSelectedItem());

		setVisible(false);
		dispose();
		DialogMessage.information("Successo", "Agenda " + comboBox.getSelectedItem() + " eliminata!");

	}

	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(2, 2, 5, 5));
		tempPanel.setBorder(new EmptyBorder(40, 30, 30, 40));
		tempPanel.add(new JLabel("Seleziona un' agenda da :"));

		comboBox = new JComboBox<String>(AgendaUtils.agendaListToArray(agendas));
		
		tempPanel.add(comboBox);

		return tempPanel;
	}

}
