package graphic_user_interface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import sourcecode.Agenda;

public class ActionsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private boolean actionWindowIsOpen;
	private ActionWindow actionWindowOpen;
	private ArrayList<Agenda> agendas;
	private JList<String> agendasList;
	JButton[] buttons;
	public ActionsPanel(ArrayList<Agenda> agendas, JList<String> agendasList) {
		super();
		this.agendas = agendas;
		this.agendasList = agendasList;		
		actionWindowIsOpen = false;
		setLayout(new GridLayout(1, 9, 2, 2));
		
		Map<String, Class<?>> titlesClasses = new HashMap<String, Class<?>>();
		titlesClasses.put("Aggiungi Agenda", AddAgendaWindow.class);
		titlesClasses.put("Elimina Agenda", DelAgendaWindow.class);
		titlesClasses.put("Aggiungi Appuntamento", AddAppointmentWindow.class);
		titlesClasses.put("Modifica Appuntamento", EditAppointmentWindow.class);
		titlesClasses.put("Elimina Appuntamento", DelAppointmentWindow.class);
		titlesClasses.put("Trova Appuntamento", FindAppointmentWindow.class);
		titlesClasses.put("Ordina Appuntamenti", OrderByDateWindow.class);
		String[] titles = titlesClasses.keySet().toArray(new String[0]);
		buttons = new JButton[titlesClasses.size()];
		//titlesClasses.put("Importa Agenda", ImportAgendaWindow.class);
		//titlesClasses.put("Esporta Agenda", ExportAgendaWindow.class);
		
		for(int counter = 0; counter < buttons.length; counter++) {
			buttons[counter] = new JButton(titles[counter]);
			buttons[counter].addActionListener(e -> {
                	try {
                	actionWindowOpen = (ActionWindow)titlesClasses.get(e.getActionCommand()).getDeclaredConstructor(String.class, ArrayList.class, JList.class, boolean.class).newInstance(e.getActionCommand(), this.agendas, this.agendasList, actionWindowIsOpen);
                	actionWindowOpen.addWindowListener(closingEvents());
                	actionWindowIsOpen = true;
                	
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}
                });
		}
		
		for(JButton button: buttons) {
			add(button);
		}
		setVisible(true);
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
