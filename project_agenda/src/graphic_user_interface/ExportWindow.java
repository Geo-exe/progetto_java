package graphic_user_interface;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.AgendaUtils;

public class ExportWindow extends ActionWindow {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox;

	public ExportWindow(String title) throws Exception {
		super(title);
		confirm.setText("Esporta");
	}

	@Override
	public void confirmAction() {
		if(FileDialog.FileSaveDialog(agendas.get(comboBox.getSelectedIndex()))) {
			setVisible(false);
			dispose();
		}
	}

	@Override
	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(2, 2, 5, 5));
		tempPanel.setBorder(new EmptyBorder(40, 30, 30, 40));
		tempPanel.add(new JLabel("Seleziona un'agenda da :"));

		comboBox = new JComboBox<String>(AgendaUtils.agendaListToArray(agendas));
		
		tempPanel.add(comboBox);

		return tempPanel;
	}

}
