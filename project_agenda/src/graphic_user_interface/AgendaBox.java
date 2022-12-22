package graphic_user_interface;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AgendaBox extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel name;
	private JLabel lenght;
	
	public AgendaBox(String name, String lenght) {
		super();
		setLayout(new GridLayout(1, 2));
		
		this.name = new JLabel(name);
		this.lenght = new JLabel(lenght);
		
		add(this.name);
		add(this.lenght);
		
		setPreferredSize(new Dimension(300, 150));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void modifyAgenda(String name, String lenght) {
		this.name.setText(name);
		this.lenght.setText(lenght);
	}
	
	
}
