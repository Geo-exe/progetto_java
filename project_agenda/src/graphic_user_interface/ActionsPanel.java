package graphic_user_interface;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * JPanel e' un contenitore per oggetti dichiarato nella libreria javax.swing
 * ActionsPanel a sua volta estende JPanel per implementare alcune proprieta' e
 * alcuni metodi. ActionsPanel contiene un numero variabile di JMenuItem
 * cliccabili. I valori assegnati ai JMenuItem sono presi dall'Enum
 * ActionMenuItemsEnum.
 * 
 * @author Griffa Francesco
 * @author Peracini Fabio
 */
public class ActionsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * Inidica lo stato della finestra, false nessuna finestra aperta mentre true
	 * finestra aperta.
	 */
	public static boolean actionWindowIsOpen;
	/**
	 * Finestra che si apre dopo aver cliccato su una voce del menu'.
	 */
	private ActionWindow actionWindowOpen;
	/**
	 * Barra contenete i vari menu'.
	 */
	private JMenuBar menuBar;
	/**
	 * Menu' delle azioni eseguibili sui file.
	 */
	private JMenu menuFile;
	/**
	 * Menu' delle azioni eseguibili sulle agende.
	 */
	private JMenu menuAgendas;
	/**
	 * Menu' delle azioni eseguibili sugli appuntamenti.
	 */
	private JMenu menuAppointments;
	/**
	 * Tutte le voci dei vari menu'.
	 */
	private JMenuItem[] menuItems;

	/**
	 * Costruttore della classe.
	 * 
	 */
	public ActionsPanel() {
		super();

		// creo il menu delle action
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuAgendas = new JMenu("Agenda");
		menuAppointments = new JMenu("Appuntamento");
		menuBar.add(menuFile);
		menuBar.add(menuAgendas);
		menuBar.add(menuAppointments);
		add(menuBar);

		// assegno ad ogni item del menu un listener, il titolo e la classe
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

		// posiziono i vari item in modo ordinato

		for (JMenuItem item : menuItems) {
			if (item.getText().contains("File")) {
				menuFile.add(item);
			} else if (item.getText().contains("Agenda")) {
				menuAgendas.add(item);
			} else if (item.getText().contains("Appuntamento")) {
				menuAppointments.add(item);
			}

		}
		setVisible(true);
	}

	/**
	 * Viene impostato lo stato di ogni item. Se lo stato e' attivo sarà cliccabile,
	 * altrimenti verrà visualizzato sbiadito e non sarà cliccabile.
	 * 
	 * @param status del tasto
	 */
	public void setButtonsStatus(boolean status) {
		String[] enumNames = ActionMenuItemsEnum.getNames();
		for (int i = 0; i < menuItems.length; i++) {
			if (!ActionMenuItemsEnum.valueOf(enumNames[i]).AlwaysEnabled()) {
				menuItems[i].setEnabled(status);
			}
		}
	}

	/**
	 * Tiene traccia della chiusare delle finestra che si apre a seguito della
	 * selezione di item del menu'.
	 * 
	 * @return WindowAdapter
	 */
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
