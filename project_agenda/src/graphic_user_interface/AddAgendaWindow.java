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
import utils.AgendaUtils;

public class AddAgendaWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;
	private JTextField nameBox;

	public AddAgendaWindow(String title) throws Exception {
		super(title);
		confirm.setText("Aggiungi");
	}

	public void confirmAction() {
			DefaultListModel<String> model = (DefaultListModel<String>) agendasList.getModel();
			try {
				if(!AgendaUtils.agendaExist(agendas, nameBox.getText())) {
					agendas.add(new Agenda(nameBox.getText()));
					model.addElement(nameBox.getText());
					setVisible(false);
			        dispose();
			        JOptionPane.showMessageDialog(null, "Agenda aggiunta!");
				} else {
					JOptionPane.showMessageDialog(null, "Esiste già un'agenda con questo nome!", "Impossibile",JOptionPane.ERROR_MESSAGE);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Carattere non valido!", "Eccezione Generata",JOptionPane.ERROR_MESSAGE);
			}
			
		}

	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(1,2,5,5));
		tempPanel.setBorder(new EmptyBorder(50, 20, 50, 20));
		tempPanel.add(new JLabel("Nome Agenda:"));
		nameBox = new JTextField();
		tempPanel.add(nameBox);
		return tempPanel;
	}

}
