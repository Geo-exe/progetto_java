package graphic_user_interface;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * JPanel è un contenitore per oggetti dichiarato nella libreria javax.swing
 * ActionsPanel a sua volta estende JPanel per implementare alcune proprietà e
 * alcuni metodi. ActionsPanel contiene un numero variabile di JButton
 * cliccabili. I valori assegnati ai JButton sono presi dall'Enum
 * ActionButtonEnum
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 */
public class ActionsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static boolean actionWindowIsOpen;
	private ActionWindow actionWindowOpen;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuAgendas;
	private JMenu menuAppointments;
	private JMenuItem[] menuItems;

	/**
	 * Costruttore della classe.
	 * 
	 * @return Ritorna un'istanza della classe ActionsPanel.
	 */
	public ActionsPanel() {
		super();

		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuAgendas = new JMenu("Agenda");
		menuAppointments = new JMenu("Appuntamento");
		menuBar.add(menuFile);
		menuBar.add(menuAgendas);
		menuBar.add(menuAppointments);
		add(menuBar);

		actionWindowIsOpen = false;
		setLayout(new GridLayout(1, ActionMenuItemsEnum.values().length + 1));
		menuItems = new JMenuItem[ActionMenuItemsEnum.values().length];
		int count = 0;
		for (ActionMenuItemsEnum enButton : ActionMenuItemsEnum.values()) {
			menuItems[count] = new JMenuItem(enButton.getTitle());
			menuItems[count].addActionListener(e -> {
				try {
					actionWindowOpen = (ActionWindow) enButton.getAssociatedClass().getDeclaredConstructor(String.class)
							.newInstance(enButton.getTitle());
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

		int i = 0;
		for (JMenuItem button : menuItems) {
			if (i >= 0 && i <= 1) {
				menuAgendas.add(button);
			} else if (i >= 2 && i <= 6) {
				menuAppointments.add(button);
			} else if (i >= 6) {
				menuFile.add(button);
			}
			i++;
		}
		setVisible(true);
	}

	public void setButtonsStatus(boolean status) {
		String[] enumNames = ActionMenuItemsEnum.getNames();
		for (int i = 0; i < menuItems.length; i++) {
			if (!ActionMenuItemsEnum.valueOf(enumNames[i]).AlwaysEnabled()) {
				menuItems[i].setEnabled(status);
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
