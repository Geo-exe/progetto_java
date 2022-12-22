package graphic_user_interface;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;

public class AgendaList extends JPanel{
	private static final long serialVersionUID = 1L;	
	
	public AgendaList() {
		super();
		setLayout(new BorderLayout());
		DefaultListModel<AgendaBox> listModel = new DefaultListModel<>();		
		listModel.addElement(new AgendaBox("Ciaone", "100"));
		JList<AgendaBox> agende = new JList<>(listModel);
		
		add(agende);
		
		setPreferredSize(new Dimension(300, 150));
		setVisible(true);
	}
}
