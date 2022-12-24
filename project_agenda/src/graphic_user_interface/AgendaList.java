package graphic_user_interface;

import sourcecode.Agenda;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;

public class AgendaList extends JPanel{
	private static final long serialVersionUID = 1L;
	private JList<String> guiList;
	public AgendaList(ArrayList<Agenda> agendaList) {
		super();
		setLayout(new BorderLayout());
		guiList = new JList<>();
		add(guiList);
		modifyList(agendaList);
		setPreferredSize(new Dimension(300, 150));
		setVisible(true);
	}
	
	public void modifyList(ArrayList<Agenda> newList) {
		remove(guiList);
		String[] list = agendaListToArray(newList);
		guiList = new JList<>(list);
		add(guiList);
	}
	
	private String[] agendaListToArray(ArrayList<Agenda> list) {
		String[] arrayString = new String[list.size()];
		for(int i = 0; i<list.size();i++) {
			arrayString[i] = list.get(i).getName();
		}
		return arrayString;
	}
	
}
