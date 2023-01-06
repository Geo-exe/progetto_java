package graphic_user_interface;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import sourcecode.Agenda;
import sourcecode.OrderMethodEnum;

public class ActionsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private boolean actionWindowIsOpen;
	private ActionWindow actionWindowOpen;
	private ArrayList<Agenda> agendas;
	private JList<String> agendasList;
	private JButton[] buttons;
	public ActionsPanel(ArrayList<Agenda> agendas, JList<String> agendasList) {
		super();
		this.agendas = agendas;
		this.agendasList = agendasList;		
		actionWindowIsOpen = false;
		setLayout(new GridLayout(1, 9));
		
		buttons = new JButton[ActionButtonEnum.values().length];
		int count = 0;
		for(ActionButtonEnum enButton: ActionButtonEnum.values()) {
			buttons[count] = new JButton(enButton.getTitle());
			buttons[count].addActionListener(e -> {
				try {
					actionWindowOpen = (ActionWindow) enButton.getAssociatedClass().getDeclaredConstructor(String.class, ArrayList.class, JList.class, boolean.class).newInstance(enButton.getTitle(), this.agendas, this.agendasList, actionWindowIsOpen);
					actionWindowOpen.addWindowListener(closingEvents());
                	actionWindowIsOpen = true;
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
			}

			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				actionWindowIsOpen = false;
			}
		};
	}
}
