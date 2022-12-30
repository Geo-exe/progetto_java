package graphic_user_interface;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sourcecode.Agenda;

public class AddAgendaWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;
	private JTextField nameBox;

	public AddAgendaWindow(String title, ArrayList<Agenda> agendas, JList<String> agendasList, boolean actionWindowIsOpen) throws Exception {
		super(title, agendas, agendasList, actionWindowIsOpen);
	}

	@Override
	public void confirmAction() {
		
			DefaultListModel<String> model = (DefaultListModel<String>) agendasList.getModel();
			try {
				agendas.add(new Agenda(nameBox.getText()));
				model.addElement(nameBox.getText());
				setVisible(false);
		        dispose();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Carattere non valido!");
			}
			
		}


	@Override
	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(1,2,5,5));
		tempPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		tempPanel.add(new JLabel("Nome Agenda:"));
		nameBox = new JTextField();
		tempPanel.add(nameBox);
		return tempPanel;
	}

}
