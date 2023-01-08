package graphic_user_interface;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sourcecode.Agenda;
import utils.AgendaUtils;

public class ImportWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox;

	public ImportWindow(String title) throws Exception {
		super(title);
		confirm.setText("Importa");
	}

	public void confirmAction() {
		DefaultListModel<String> newModel = new DefaultListModel<String>();
		String txt = "Agenda Aggiunta!";
		boolean toEdit = true;
		if (comboBox.getSelectedItem() == "1") {
			Agenda temp = (Agenda) FileDialog.FileOpenDialog();
			if (!AgendaUtils.agendaExist(agendas, temp.getName()))
				agendas.add(temp);
			else {
				String newName = "";
				do {
					newName = JOptionPane
							.showInputDialog("Assegnare un nuovo nome all'agenda <" + temp.getName() + "> :");
				} while (AgendaUtils.agendaExist(agendas, newName) && newName == "");
				if (newName != null) {
					agendas.add(new Agenda(newName, temp.getAppointments()));
				} else
					toEdit = false;

			}
		} else {
			@SuppressWarnings("unchecked")
			ArrayList<Agenda> temps = (ArrayList<Agenda>) FileDialog.FileOpenDialog();
			for (Agenda temp : temps) {
				if (!AgendaUtils.agendaExist(agendas, temp.getName())) {
					agendas.add(temp);
				} else {
					String newName = "";
					do {
						newName = JOptionPane
								.showInputDialog("Assegnare un nuovo nome all'agenda <" + temp.getName() + "> :");
					} while (AgendaUtils.agendaExist(agendas, newName) && newName == "");
					if (newName != null) {
						agendas.add(new Agenda(newName, temp.getAppointments()));
					} else {
						DialogMessage.error("Skip", "Agenda saltata.");
					}
				}
			}
			txt = "Agende Aggiunte!";
		}
		if (toEdit) {
			for (String name : AgendaUtils.agendaListToArray(agendas)) {
				newModel.addElement(name);
			}

			DialogMessage.information("Successo", txt);

			agendasList.setModel(newModel);
			agendasList.revalidate();
		} else {
			DialogMessage.error("No Agenda", "Nessun Agenda Aggiunta.");
		}
		setVisible(false);
		dispose();
	}

	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(2, 2, 5, 5));
		tempPanel.setBorder(new EmptyBorder(40, 30, 30, 40));
		tempPanel.add(new JLabel("Seleziona quante agende si vogliono importare: "));

		comboBox = new JComboBox<String>(new String[] { "1", "2+" });

		tempPanel.add(comboBox);
		return tempPanel;
	}

}
