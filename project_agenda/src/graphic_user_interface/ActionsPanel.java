package graphic_user_interface;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import sourcecode.Agenda;

public class ActionsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static boolean actionWindowIsOpen;
	private ActionWindow actionWindowOpen;
	private JButton[] buttons;
	public ActionsPanel() {
		super();	
		actionWindowIsOpen = false;
		setLayout(new GridLayout(1, 9));
		buttons = new JButton[ActionButtonEnum.values().length];
		int count = 0;
		for(ActionButtonEnum enButton: ActionButtonEnum.values()) {
			buttons[count] = new JButton(enButton.getTitle());
			buttons[count].addActionListener(e -> {
				try {
					actionWindowOpen = (ActionWindow) enButton.getAssociatedClass().getDeclaredConstructor(String.class).newInstance(enButton.getTitle());
					actionWindowOpen.addWindowListener(closingEvents());
                	actionWindowIsOpen = true;
                	Dashboard.agendasList.setEnabled(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			count++;
		}
		
		setButtonsStatus(false);
		
		for(JButton button: buttons) {
			add(button);
		}
		setVisible(true);
	}
	
	public void setButtonsStatus(boolean status) {
		String[] enumNames = ActionButtonEnum.getNames();
		for(int i = 0; i<buttons.length; i++) {
			if(!ActionButtonEnum.valueOf(enumNames[i]).AlwaysEnabled()) {
				buttons[i].setEnabled(status);
			}
		}
	}
	
	private WindowAdapter closingEvents() {
		return new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosed(java.awt.event.WindowEvent windowEvent) {
				actionWindowIsOpen = false;
				Dashboard.agendasList.setEnabled(true);
				
			}

			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				actionWindowIsOpen = false;
				Dashboard.agendasList.setEnabled(true);
			}
		};
	}
}
