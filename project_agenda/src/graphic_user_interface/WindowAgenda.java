package graphic_user_interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import sourcecode.Agenda;

public class WindowAgenda extends JFrame {
	private static final long serialVersionUID = 1L;

	public WindowAgenda() {
		super("Dashboard");
		setLayout(new GridLayout(2, 2));
		ArrayList<Agenda> bho = new ArrayList<Agenda>();
		bho.add(new Agenda("Ciaone"));
		bho.add(new Agenda("Lavoro"));
		bho.add(new Agenda("Casa"));
		AgendaList ciaone = new AgendaList(bho);
		
		add(ciaone, BorderLayout.CENTER);

		setPreferredSize(new Dimension(400, 300));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}