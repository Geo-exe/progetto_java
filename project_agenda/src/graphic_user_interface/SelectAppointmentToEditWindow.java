package graphic_user_interface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import sourcecode.Agenda;
import sourcecode.Appointment;

public class SelectAppointmentToEditWindow extends ActionWindow {

	private static final long serialVersionUID = 1L;
	private JRadioButton[] radioButton;

	public SelectAppointmentToEditWindow(String title) throws Exception {
		super(title);
		confirm.setText("Modifica");
		setBounds(0, 0, 350, 650);
		setLocationRelativeTo(null);
	}

	public void confirmAction() {
		try {
			
			int indexSelected = -1;
			if(radioButton!=null) {
				
				for (int j = 0; j < radioButton.length; j++) {
				if (radioButton[j].isSelected()) {
					indexSelected = j;
					break;
				}
			}
				
			if(indexSelected >= 0) {
				EditAppointmentWindow editWindow = new EditAppointmentWindow(this.getTitle());
				editWindow.passFields(indexSelected);
				setVisible(false);
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Nessun appuntamento selezionato!");
			}
					
			} else {
				setVisible(false);
				dispose();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected JPanel loadFields() {
		JPanel tempPanel = new JPanel();
		ArrayList<Appointment> result;
		GridBagConstraints gbc = new GridBagConstraints();

		result = agendas.get(agendasList.getSelectedIndex()).getAppointments();

		if (!result.isEmpty()) {

			tempPanel.setLayout(new GridBagLayout());
			tempPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
			gbc.gridy = 0;
			tempPanel.add(new JLabel("Seleziona un appuntamento:"), gbc);

			radioButton = new JRadioButton[result.size()];

			for (int i = 0; i < result.size(); i++) {
				radioButton[i] = new JRadioButton();

				radioButton[i].addActionListener(e -> {

					for (int j = 0; j < radioButton.length; j++) {
						if (!radioButton[j].equals(e.getSource())) {
							radioButton[j].setSelected(false);
						}
					}

				});

				gbc.gridy = i + 2;
				tempPanel.add(radioButton[i], gbc);
				tempPanel.add(new AppointmentBox(result.get(i)), gbc);

			}

		} else {
			tempPanel.add(new JLabel("Nessun appuntamento!"));
		}

		return tempPanel;
	}

}
